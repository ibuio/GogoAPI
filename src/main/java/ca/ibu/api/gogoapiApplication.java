package ca.ibu.api;

import java.net.UnknownHostException;

// javax
import javax.ws.rs.client.Client;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

// dw
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.client.JerseyClientBuilder;
import ca.ibu.api.api.filter.AuthenticationFilter;
import ca.ibu.api.api.filter.AuthorizationFilter;

// ibu
import ca.ibu.api.client.Auth0JerseyClient;
import ca.ibu.api.db.MongoManaged;
import ca.ibu.api.health.MongoHealthCheck;
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
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.lifecycle().manage(mongoManaged);
        environment.healthChecks().register("mongodb", new MongoHealthCheck(mongo));

        /*
         * jersey client
         */
        final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());

        /*
         * Resources (REST Services)
         */
        environment.jersey().register(new HelloResource());
        environment.jersey().register(new Auth0JerseyClient(client));

    }

}
