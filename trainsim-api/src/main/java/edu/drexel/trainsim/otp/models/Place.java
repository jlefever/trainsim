package edu.drexel.trainsim.otp.models;

public class Place {
    private final String stopId;
    private final long arrival;
    private final long departure;

    public Place(String stopId, long arriveAt, long departAt) {
        this.stopId = stopId;
        this.arrival = arriveAt;
        this.departure = departAt;
    }

    public String getStopId() {
        return this.stopId;
    }


    public long getArriveAt() {
        return this.arrival;
    }


    public long getDepartAt() {
        return this.departure;
    }
}
