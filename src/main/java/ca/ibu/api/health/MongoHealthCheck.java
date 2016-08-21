/**
 * @author chr0n0metriq: jk
 * 
 *         ca.ibu.api.health
 *         MongoHealthCheck.java
 *         Aug 20, 2016
 * 
 */
package ca.ibu.api.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * @author jk
 *
 */
public class MongoHealthCheck extends HealthCheck {

    private MongoClient mongo;

    public MongoHealthCheck(MongoClient mongo) {
        //super();
        this.mongo = mongo;
    }

    @Override
    protected Result check() throws Exception {
        try {
            mongo.getDatabaseNames();
            return Result.healthy();
        }
        catch (MongoException e) {
            return Result.unhealthy("Cannot connect to " + mongo.getAddress());
        }
    }

}
