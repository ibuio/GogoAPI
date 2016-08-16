package ca.ibu.api;

import ca.ibu.api.resources.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
//        environment.jersey().register(new AuthDynamicFeature(
//                new OAuthCredentialAuthFilter.Builder<User>()
//                    .setAuthenticator(new FilterOAuthAuthenticator())
//                    .setAuthorizer(new FilterAuthorizer())
//                    .setPrefix("Bearer")
//                    .buildAuthFilter()));
        environment.jersey().register(new HelloResource());
        //TwilioClient twilioC = configuration.getTwilioFactory().build(environment);
    }

}
