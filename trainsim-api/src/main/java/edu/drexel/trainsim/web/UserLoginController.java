package edu.drexel.trainsim.web;

import com.google.inject.Inject;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserLoginController implements Controller {

    @Inject
    public UserLoginController() {
        System.out.println("123");
    }

    public void bindRoutes(Javalin app) {
        app.post("/api/users", ctx -> this.testUser(ctx));
    }

    private void testUser(Context ctx) {
        System.out.println(ctx.body());
        ctx.status(200);
    }
}