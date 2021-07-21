package edu.drexel.trainsim.otp;

import java.text.SimpleDateFormat;

import com.google.gson.Gson;

import org.eclipse.jetty.client.HttpClient;

import edu.drexel.trainsim.otp.models.PlanRequest;
import edu.drexel.trainsim.otp.models.PlanResponse;
import edu.drexel.trainsim.otp.models.Route;
import edu.drexel.trainsim.otp.models.Stop;

public class OtpClient {
    private String baseUrl;
    private HttpClient http;
    private Gson mapper;

    public OtpClient(String baseUrl) throws Exception {
        this.baseUrl = baseUrl;
        this.mapper = new Gson();
        this.http = new HttpClient();
        this.http.start();
    }

    public Stop[] getAllStops() throws Exception {
        var res = this.http.GET(urlFor("index/stops"));
        var content = res.getContentAsString();
        return this.mapper.fromJson(content, Stop[].class);
    }

    public Route[] getAllRoutes() throws Exception {
        var res = this.http.GET(urlFor("index/routes"));
        var content = res.getContentAsString();
        return this.mapper.fromJson(content, Route[].class);
    }

    public PlanResponse getPlan(PlanRequest req) throws Exception {
        var formatter = new SimpleDateFormat("yyyy-MM-dd");
        var date = formatter.format(req.getDepartDate());
        var url = "plan?&mode=TRANSIT&time=12:00AM&searchWindow=86400"
                + "&fromPlace="+ req.getSource()
                + "&toPlace=" + req.getTarget()
                + "&date=" + date;
        var res = this.http.GET(urlFor(url));
        var content = res.getContentAsString();
        return this.mapper.fromJson(content, PlanResponse.class);
    }

    private String urlFor(String path) {
        return this.baseUrl + "/otp/routers/default/" + path;
    }
}
