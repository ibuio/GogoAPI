package ca.ibu.api;

// javax
import javax.ws.rs.client.Client;

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
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), 
                        new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(final gogoapiConfiguration configuration, final Environment environment) {

        // Auth Filters
        environment.jersey().register(AuthenticationFilter.class);
        environment.jersey().register(AuthorizationFilter.class);
        
        final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration())
                .build(getName());
        environment.jersey().register(new HelloResource());
        environment.jersey().register(new Auth0JerseyClient(client));
        
    }

}
