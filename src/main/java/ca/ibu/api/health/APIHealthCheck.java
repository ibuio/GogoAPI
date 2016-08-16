/**
 * @author chr0n0metriq: jk
 * 
 * ca.ibu.api.resources
 * APIHealthCheck.java
 * Aug 14, 2016
 * 
 */
package ca.ibu.api.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * @author jk
 *
 */
public class APIHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
