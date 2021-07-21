package edu.drexel.trainsim.db.commands;

import edu.drexel.trainsim.db.models.Stop;

@FunctionalInterface
public interface StoreAllStops {
    void call(Stop[] stops);
}
