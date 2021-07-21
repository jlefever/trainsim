package edu.drexel.trainsim;

import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.zaxxer.hikari.HikariConfig;

import edu.drexel.trainsim.db.DatabaseModule;
import edu.drexel.trainsim.otp.OtpModule;
import edu.drexel.trainsim.web.PlanController;
import edu.drexel.trainsim.web.StopController;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJson;

public class App {
    public static void main(String[] args) throws Exception {
        Thread.sleep(2000);

        // Database
        var hikari = new HikariConfig();
        hikari.setJdbcUrl(getEnv("DB_URL"));
        hikari.setUsername(getEnv("DB_USER"));
        hikari.setPassword(getEnv("DB_PASSWORD"));

        // Dependency injection
        var injector = Guice.createInjector(
            new DatabaseModule(hikari),
            new OtpModule(getEnv("OTP_URL"))
        );

        // Web server
        var gson = new GsonBuilder().create();
        JavalinJson.setFromJsonMapper(gson::fromJson);
        JavalinJson.setToJsonMapper(gson::toJson);
        var app = Javalin.create();

        // Setup controllers
        injector.getInstance(PlanController.class).bindRoutes(app);
        injector.getInstance(StopController.class).bindRoutes(app);

        // Start the web server
        app.start(80);
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
