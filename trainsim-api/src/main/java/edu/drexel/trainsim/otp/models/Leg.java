package edu.drexel.trainsim.otp.models;

public class Leg {
    private final String routeId;
    private final Place from;
    private final Place to;
    private final Place[] intermediateStops;
    private final double distance;

    public Leg(String routeId, Place from, Place to, Place[] intermediateStops, double distance) {
        this.routeId = routeId;
        this.from = from;
        this.to = to;
        this.intermediateStops = intermediateStops;
        this.distance = distance;
    }


    public String getRouteId() {
        return this.routeId;
    }


    public Place getFrom() {
        return this.from;
    }


    public Place getTo() {
        return this.to;
    }


    public Place[] getIntermediateStops() {
        return this.intermediateStops;
    }


    public double getDistance() {
        return this.distance;
    }


}
