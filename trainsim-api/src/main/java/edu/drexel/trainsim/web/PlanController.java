package edu.drexel.trainsim.web;

import com.google.inject.Inject;

import edu.drexel.trainsim.otp.OtpClient;
import edu.drexel.trainsim.otp.models.PlanRequest;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class PlanController {
    private final OtpClient otp;

    @Inject
    public PlanController(OtpClient otp) {
        this.otp = otp;
    }

    public void bindRoutes(Javalin app) {
        app.post("/api/query", ctx -> this.plan(ctx));
    }

    private void plan(Context ctx) throws Exception {
        var search = ctx.bodyAsClass(PlanRequest.class);
        ctx.json(otp.getPlan(search).getPlan().getItineraries());
    }
}
