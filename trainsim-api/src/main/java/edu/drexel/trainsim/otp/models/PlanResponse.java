package edu.drexel.trainsim.otp.models;

public class PlanResponse {
    private final Plan plan;

    public PlanResponse(Plan plan) {
        this.plan = plan;
    }

    public Plan getPlan() {
        return plan;
    }
}
