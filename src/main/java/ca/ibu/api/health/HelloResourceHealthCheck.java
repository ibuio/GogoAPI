/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api.health
 * HelloResourceHealthCheck.java
 * Aug 14, 2016
 * 
 */
package ca.ibu.api.health;

import com.codahale.metrics.health.HealthCheck;


/**
 * @author jk
 *
 */
public class HelloResourceHealthCheck extends HealthCheck {

    /* (non-Javadoc)
     * @see com.codahale.metrics.health.HealthCheck#check()
     */
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

}
