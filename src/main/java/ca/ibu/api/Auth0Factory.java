/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api
 * Auth0Factory.java
 * Aug 19, 2016
 * 
 */
package ca.ibu.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jk
 *
 */
public class Auth0Factory {

    //@NotNull
    public String rooturl;
    
    //@NotNull
    public String userinfosuburl;


    @JsonProperty
    public String getRooturl() {
        return rooturl;
    }

    @JsonProperty
    public void setRooturl(String rooturl) {
        this.rooturl = rooturl;
    }


    @JsonProperty
    public String getUserinfosuburl() {
        return userinfosuburl;
    }


    @JsonProperty
    public void setUserinfosuburl(String userinfosuburl) {
        this.userinfosuburl = userinfosuburl;
    }
    
    
}
