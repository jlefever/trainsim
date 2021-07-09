package edu.drexel.trainsim.models;

import java.util.Date;

public class Query {
    private String source;
    private String target;
    private Date departDate;
    private Date returnDate;
    private int travelers;

    public Query() {}

    public Query(String source, String target, Date departDate, Date returnDate, int travelers) {
        this.source = source;
        this.target = target;
        this.departDate = departDate;
        this.returnDate = returnDate;
        this.travelers = travelers;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public int getTravelers() {
        return travelers;
    }
}
