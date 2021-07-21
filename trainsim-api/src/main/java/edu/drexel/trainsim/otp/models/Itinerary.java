package edu.drexel.trainsim.otp.models;

public class Itinerary {
    private final Leg[] legs;

    public Itinerary(Leg[] legs) {
        this.legs = legs;
    }

    public Leg[] getLegs() {
        return legs;
    }
}
