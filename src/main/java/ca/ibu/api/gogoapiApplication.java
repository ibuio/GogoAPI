package ca.ibu.api;

import io.dropwizard.Application;
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
        // TODO: application initialization
    }

    @Override
    public void run(final gogoapiConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
