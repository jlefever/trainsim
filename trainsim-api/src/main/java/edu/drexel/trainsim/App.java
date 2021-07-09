package edu.drexel.trainsim;

import org.sql2o.Sql2o;

import edu.drexel.trainsim.queries.GetAllStops;
import io.javalin.Javalin;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Sql2o sql2o = new Sql2o("jdbc:postgresql://trainsim-db:5432/postgres", "postgres", "mysecretpassword");
        var query = new GetAllStops(sql2o);
        var app = Javalin.create().start(80);
        app.get("/api/stops", ctx -> ctx.json(query.execute()));
    }
}
