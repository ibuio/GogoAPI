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

    private MongoClient mongoClient;

    public MongoHealthCheck(MongoClient _mongoClient) {
        this.mongoClient = _mongoClient;
    }

    @Override
    protected Result check() throws Exception {
        try {
            mongoClient.getDatabase(System.getenv("MONGO_DB"));
            return Result.healthy();
        }
        catch (MongoException e) {
            return Result.unhealthy("Cannot connect to " + mongoClient.getAddress());
        }
    }

}
