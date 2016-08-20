package ca.ibu.api;

// javax
import javax.validation.Valid;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotEmpty;

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
    
    /*
     * Jersey client
     */
    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
    }

    /*
     * MONGO DB MongoLab
     */
    @JsonProperty
    @NotEmpty
    public String mongohost = System.getenv("MONGO_HOST");
 
    @JsonProperty
    @Min(1)
    @Max(65535)
    public int mongoport = Integer.parseInt(System.getenv("MONGO_PORT"));
 
    @JsonProperty
    @NotEmpty
    public String mongodb = System.getenv("MONGO_DB");
    
    
}
