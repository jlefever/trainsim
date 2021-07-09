package edu.drexel.trainsim;

import org.sql2o.Sql2o;

import edu.drexel.trainsim.models.Query;
import edu.drexel.trainsim.queries.GetAllStops;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Sql2o sql2o = new Sql2o(getEnv("DB_URL"), getEnv("DB_USER"), getEnv("DB_PASSWORD"));
        var query = new GetAllStops(sql2o);
        var app = Javalin.create().start(80);
        app.get("/api/stops", ctx -> ctx.json(query.execute()));
        app.post("/api/query", ctx -> {
            var body = ctx.bodyAsClass(Query.class);
            ctx.status(200);
        });
    }

    private static String getEnv(String name) {
        var value = System.getenv(name);

        if (value == null) {
            final String message = "Environment variable `%s` is required.";
            throw new RuntimeException(String.format(message, name));
        }

        return value;
    }
}
