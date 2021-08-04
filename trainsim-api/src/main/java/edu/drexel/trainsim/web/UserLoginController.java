package edu.drexel.trainsim.web;

import com.google.inject.Inject;
import edu.drexel.trainsim.db.commands.GetOrCreateGoogleUser;
import edu.drexel.trainsim.db.models.User;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserLoginController implements Controller {
    private final GetOrCreateGoogleUser getOrCreateGoogleUser;

    @Inject
    public UserLoginController(GetOrCreateGoogleUser cmd) {
        this.getOrCreateGoogleUser = cmd;
    }

    public void bindRoutes(Javalin app) {
        app.post("/api/users", ctx -> this.testUser(ctx));
    }

    private void testUser(Context ctx) {
        var user = ctx.bodyAsClass(User.class);
        System.out.println(user.getEmail());
        var key = getOrCreateGoogleUser.call(user);
        user.setId(key);
        System.out.println(user.getId());
        ctx.json(user);
    }
}