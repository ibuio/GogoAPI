/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api
 * TwilioFactory.java
 * Aug 14, 2016
 * 
 */
package ca.ibu.api;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author jk
 *
 */
public class TwilioFactory {

    //@NotNull
    //@NotEmpty
    private String  accountSid;
    
    //@NotNull
    //@NotEmpty
    private String authToken;
    
    //@NotNull
    private String lNumbers;

    @JsonProperty
    public String getAccountSid() {
        return accountSid;
    }

    @JsonProperty
    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    @JsonProperty
    public String getAuthToken() {
        return authToken;
    }

    @JsonProperty
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @JsonProperty
    public String getlNumbers() {
        return lNumbers;
    }

    @JsonProperty
    public void setlNumbers(String lNumbers) {
        this.lNumbers = lNumbers;
    }

//    public TwilioClient build(Environment environment) {
//        TwilioClient client = new TwilioClient(getHost(), getPort());
//        environment.lifecycle().manage(new Managed() {
//            @Override
//            public void start() {
//            }
//
//            @Override
//            public void stop() {
//                client.close();
//            }
//        });
//        return client;
//    }
    
}
