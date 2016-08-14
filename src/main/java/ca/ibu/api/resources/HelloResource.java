/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api.resources
 * HelloResource.java
 * Aug 13, 2016
 * 
 */
package ca.ibu.api.resources;

// java

// javax
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author jk
 *
 */
@Path("/hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "Hello world!";
    }
}