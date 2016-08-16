package ca.ibu.api;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class gogoapiConfiguration extends Configuration {
    @Valid
    @NotNull
    private TwilioFactory twilio = new TwilioFactory();

    @JsonProperty("twilio")
    public TwilioFactory getTwilioFactory() {
        return twilio;
    }

    @JsonProperty("messageQueue")
    public void setTwilioFactory(TwilioFactory factory) {
        this.twilio = factory;
    }
}
