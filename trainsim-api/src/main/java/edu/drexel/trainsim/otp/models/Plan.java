package edu.drexel.trainsim.otp.models;

public class Plan {
    private final Itinerary[] itineraries;

    public Plan(Itinerary[] itineraries) {
        this.itineraries = itineraries;
    }

    public Itinerary[] getItineraries() {
        return itineraries;
    }
}
