package ca.ibu.api;


// java
import java.util.Arrays;

// javax
import javax.ws.rs.client.Client;

import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

// mongodb
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// dw
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.client.JerseyClientBuilder;

// mongob
//import net.vz.mongodb.jackson.JacksonDBCollection;

// ibu
//import ca.ibu.api.api.pojo.DancerAgency;
import ca.ibu.api.api.filter.AuthenticationFilter;
import ca.ibu.api.api.filter.AuthorizationFilter;
import ca.ibu.api.client.Auth0JerseyClient;
import ca.ibu.api.db.MongoManaged;
import ca.ibu.api.health.MongoHealthCheck;
import ca.ibu.api.resources.DancerAgencyResource;
import ca.ibu.api.resources.HelloResource;

public class gogoapiApplication extends Application<gogoapiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new gogoapiApplication().run(args);
    }

    @Override
    public String getName() {
        return "gogoapi";
    }

    @Override
    public void initialize(final Bootstrap<gogoapiConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(final gogoapiConfiguration configuration, final Environment environment) {

        /*
         * Auth Filters
         */
        environment.jersey().register(AuthenticationFilter.class);
        environment.jersey().register(AuthorizationFilter.class);

        /*
         * Health Checks
         */
        // Mongo DB
        // http://mongodb.github.io/mongo-java-driver/3.3/driver/reference/connecting/
        MongoCredential credential = MongoCredential.createCredential(configuration.mongouser, configuration.mongodb, configuration.mongopassword.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(configuration.mongohost, configuration.mongoport), Arrays.asList(credential));
        //   mongodb://<dbuser>:<dbpassword>@connectionurl:port/dbname
        //MongoClientURI uri  = new MongoClientURI("mongodb://" + configuration.mongouser + ":" + configuration.mongopassword + "@" + configuration.mongohost + ":" + configuration.mongoport + "/" + configuration.mongodb); 
        //MongoClient mongoClient = new MongoClient(uri);
        
        MongoManaged mongoManaged = new MongoManaged(mongoClient);
        environment.lifecycle().manage(mongoManaged);
        environment.healthChecks().register("mongodb", new MongoHealthCheck(mongoClient));

        Morphia morphia = new Morphia();
        morphia.mapPackage("ca.ibu.api.api.pojo");
        Datastore datastore = morphia.createDatastore(mongoClient, configuration.mongodb);
        datastore.ensureIndexes();
        
        /*
         * jersey client
         */
        final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
        //JacksonDBCollection<DancerAgency, String> agencies = JacksonDBCollection.wrap(db.getCollection("agencies"), DancerAgency.class, String.class);

        //MongoDatabase database = mongoClient.getDatabase(configuration.mongodb);
        //MongoCollection<Document> collAgencies = database.getCollection("agencies");


        /*
         * Resources (REST Services)
         */
        environment.jersey().register(new HelloResource());
        //environment.jersey().register(new DancerAgencyResource(collAgencies));
        environment.jersey().register(new DancerAgencyResource(datastore));
        environment.jersey().register(new Auth0JerseyClient(client));

    }

}
