package ca.ibu.api;

// javax
import javax.validation.Valid;
import javax.validation.constraints.*;

// dw
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

// jackson
import com.fasterxml.jackson.annotation.JsonProperty;

public class gogoapiConfiguration extends Configuration {
    @Valid
    @NotNull
    private TwilioFactory twilio = new TwilioFactory();

    @JsonProperty("twilio")
    public TwilioFactory getTwilioFactory() {
        return twilio;
    }
    
    @Valid
    @NotNull
    private Auth0Factory auth0 = new Auth0Factory();
    
    @JsonProperty("auth0")
    public Auth0Factory getAuth0Factory() {
        return auth0;
    }

    @JsonProperty("messageQueue")
    public void setTwilioFactory(TwilioFactory factory) {
        this.twilio = factory;
    }
    
    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
    }
    
    
}
