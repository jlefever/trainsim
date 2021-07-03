package edu.drexel.trainsim;

import io.javalin.Javalin;

/**
 * Hello world!
 */
public class App
{
    public static void main(String[] args)
    {
        var app = Javalin.create(config -> {
            config.addSinglePageRoot("/", "/frontend/index.html");
        }).start(7000);
        app.get("/hello", ctx -> ctx.result("Hello, World!"));
    }
}
