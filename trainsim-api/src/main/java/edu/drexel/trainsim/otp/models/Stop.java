package edu.drexel.trainsim.otp.models;

public class Stop {
    private String id;
    private String name;

    public Stop(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
