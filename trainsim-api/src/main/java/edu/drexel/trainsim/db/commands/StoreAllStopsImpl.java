package edu.drexel.trainsim.db.commands;

import com.google.gson.Gson;
import com.google.inject.Inject;

import org.sql2o.Sql2o;

import edu.drexel.trainsim.db.models.Stop;

public class StoreAllStopsImpl implements StoreAllStops {
    private final Sql2o db;
    private final Gson gson;

    @Inject
    public StoreAllStopsImpl(Sql2o db, Gson gson) {
        this.db = db;
        this.gson = gson;
    }

    public void call(Stop[] stops) {
        try (var con = this.db.open()) {
            con.createQuery("CALL otp.load_stops(CAST(:stops AS JSON))")
                .addParameter("stops", this.gson.toJson(stops))
                .executeUpdate();
        }
    }
}
