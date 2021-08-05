package edu.drexel.trainsim.itinerary;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import edu.drexel.trainsim.itinerary.otp.ItinerarySearchEngineImpl;
import edu.drexel.trainsim.itinerary.search.ItinerarySearchEngine;

public class ItineraryModule extends AbstractModule {
    private final String baseUrl;

    public ItineraryModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    public ItinerarySearchEngine getItinerarySearchEngine() throws Exception {
        return new ItinerarySearchEngineImpl(this.baseUrl);
    }
}
