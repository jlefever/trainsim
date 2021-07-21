package edu.drexel.trainsim.otp.models;

public class Route {
    private String id;
    private String shortName;
    private String longName;
    private String mode;

    public Route(String id, String shortName, String longName, String mode) {
        this.id = id;
        this.shortName = shortName;
        this.longName = longName;
        this.mode = mode;
    }

    public String getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getMode() {
        return mode;
    }
}
