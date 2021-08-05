package edu.drexel.trainsim.itinerary.otp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;

import edu.drexel.trainsim.itinerary.models.Itinerary;
import edu.drexel.trainsim.itinerary.models.Leg;
import edu.drexel.trainsim.itinerary.models.Place;
import edu.drexel.trainsim.itinerary.otp.dtos.ItineraryDto;
import edu.drexel.trainsim.itinerary.otp.dtos.LegDto;
import edu.drexel.trainsim.itinerary.otp.dtos.PlanResponseDto;
import edu.drexel.trainsim.itinerary.search.ItinerarySearch;
import edu.drexel.trainsim.itinerary.search.ItinerarySearchEngine;

public class ItinerarySearchEngineImpl implements ItinerarySearchEngine {
    private String baseUrl;
    private HttpClient http;
    private Gson mapper;

    public ItinerarySearchEngineImpl(String baseUrl) throws Exception {
        this.baseUrl = baseUrl;
        this.mapper = new Gson();
        this.http = new HttpClient();
        this.http.start();
    }

    @Override
    public List<Itinerary> search(ItinerarySearch search) {
        // 1. Make the request to OTP
        var formatter = new SimpleDateFormat("yyyy-MM-dd");
        var date = formatter.format(search.getDepartDate());
        var url = "plan?&mode=TRANSIT&time=12:00AM&searchWindow=86400" + "&fromPlace=" + search.getSource()
                + "&toPlace=" + search.getTarget() + "&date=" + date;

        ContentResponse res;

        try {
            res = this.http.GET(urlFor(url));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        // 2. Map the response JSON to our DTOs.
        var content = res.getContentAsString();
        var dto = this.mapper.fromJson(content, PlanResponseDto.class);

        // 3. Map the DTOs to our model objects.
        var itineraries = new ArrayList<Itinerary>();

        for (var itineraryDto : dto.getPlan().getItineraries()) {
            itineraries.add(new Itinerary(UUID.randomUUID(), createLegs(itineraryDto)));
        }

        return itineraries;
    }

    private String urlFor(String path) {
        return this.baseUrl + "/otp/routers/default/" + path;
    }

    private static List<Leg> createLegs(ItineraryDto dto) {
        var legs = new ArrayList<Leg>();
        
        for(var legDto : dto.getLegs()) {
            legs.add(new Leg(UUID.randomUUID(), legDto.getRouteId(), createPlaces(legDto), legDto.getDistance()));
        }

        return legs;
    }

    private static List<Place> createPlaces(LegDto legDto) {
        var places = new ArrayList<Place>();
        var from = legDto.getFrom();
        places.add(new Place(UUID.randomUUID(), from.getStopId(), from.getArriveAt(), from.getDepartAt()));
        
        // for(var placeDto : legDto.getIntermediateStops()) {
        //     places.add(new PlaceImpl(placeDto));
        // }

        var to = legDto.getTo();
        places.add(new Place(UUID.randomUUID(), to.getStopId(), to.getArriveAt(), to.getDepartAt()));
        return places;
    }
}
