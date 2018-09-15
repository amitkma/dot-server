package com.dot.backend.data;

import com.dot.backend.domain.SendOtp;
import com.dot.backend.domain.VerifyOtp;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Remote {

    @GET("https://2factor.in/API/V1/{api_key}/SMS/{phone_number}/AUTOGEN")
    public Call<SendOtp> sendOtp(@Path("api_key") String apiKey, @Path("phone_number") String phoneNumber);

    @GET("https://2factor.in/API/V1/{api_key}/SMS/VERIFY/{session_id}/{otp_input}")
    public Call<VerifyOtp> verifyOtp(@Path("api_key") String apiKey, @Path("session_id") String sessionId, @Path("otp_input") String otpInput);

    class Creator {
        private static volatile Retrofit retrofit;

        public static Retrofit getRetrofit() {
            if (retrofit == null) {
                synchronized (Creator.class) {
                    if (retrofit == null) retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl("https://2factor.in/API/")
                            .build();
                }
            }
            return retrofit;
        }

        private Creator() {

        }
    }
}
