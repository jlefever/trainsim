package edu.drexel.trainsim.db.queries;

import java.util.List;

import edu.drexel.trainsim.db.models.Stop;

@FunctionalInterface
public interface GetAllStops {
    List<Stop> call();
}
