package edu.drexel.trainsim;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zaxxer.hikari.HikariConfig;

import org.sql2o.Sql2o;

import edu.drexel.trainsim.db.DatabaseModule;
import edu.drexel.trainsim.itinerary.ItineraryModule;
import edu.drexel.trainsim.itinerary.otp.OtpClient;
import edu.drexel.trainsim.itinerary.otp.Prepopulater;
import edu.drexel.trainsim.web.ItineraryController;
import edu.drexel.trainsim.web.StopController;
import edu.drexel.trainsim.web.UserLoginController;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJson;

public class App {
    public static void main(String[] args) throws Exception {
        // Wait until OTP is fully initilized
        // TODO: There are certainly better ways to do this.
        Thread.sleep(2000);

        // Database
        HikariConfig hikari = new HikariConfig();
        hikari.setJdbcUrl(getEnv("DB_URL"));
        hikari.setUsername(getEnv("DB_USER"));
        hikari.setPassword(getEnv("DB_PASSWORD"));

        // Dependency injection
        Injector injector = Guice.createInjector(
            new DatabaseModule(hikari),
            new ItineraryModule(getEnv("OTP_URL"))
        );

        // Prepopulate routes and stops
        Sql2o db = injector.getInstance(Sql2o.class);
        OtpClient otpClient = injector.getInstance(OtpClient.class);
        new Prepopulater(db, otpClient).prepopulate();

        // Web server
        Gson gson = new GsonBuilder().create();
        JavalinJson.setFromJsonMapper(gson::fromJson);
        JavalinJson.setToJsonMapper(gson::toJson);
        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        // Setup controllers
        injector.getInstance(ItineraryController.class).bindRoutes(app);
        injector.getInstance(StopController.class).bindRoutes(app);
        injector.getInstance(UserLoginController.class).bindRoutes(app);

        // Start the web server
        app.start(80);
    }

    private static String getEnv(String name) {
        String value = System.getenv(name);

        if (value == null) {
            final String message = "Environment variable `%s` is required.";
            throw new RuntimeException(String.format(message, name));
        }

        return value;
    }
}
