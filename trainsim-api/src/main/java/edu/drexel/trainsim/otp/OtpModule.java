package edu.drexel.trainsim.otp;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class OtpModule extends AbstractModule {
    private final String baseUrl;

    public OtpModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    public OtpClient getOtpClient() throws Exception {
        return new OtpClient(this.baseUrl);
    }
}
