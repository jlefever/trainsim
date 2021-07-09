package edu.drexel.trainsim.queries;

import java.util.List;

import org.sql2o.Sql2o;

import edu.drexel.trainsim.models.Stop;

public class GetAllStops {
    private final Sql2o db;

    public GetAllStops(Sql2o db) {
        this.db = db;
    }

    public List<Stop> execute() {
        final String sql = "SELECT id, otp_id AS otpId, name FROM otp.stops;";

        try (var con = this.db.open()) {
            return con.createQuery(sql).executeAndFetch(Stop.class);
        }
    }
}
