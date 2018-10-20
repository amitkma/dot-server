package com.dot.backend.controller;

import com.dot.backend.data.Remote;
import com.dot.backend.domain.RequestVerifyOtp;
import com.dot.backend.domain.SendOtp;
import com.dot.backend.domain.User;
import com.dot.backend.domain.VerifyOtp;
import com.dot.backend.exceptions.OtpException;
import com.dot.backend.exceptions.ServerException;
import com.dot.backend.repository.UserRepository;
import com.dot.backend.security.TokenService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("otp")
public class OtpController {

    @Autowired
    private Environment environment;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{phone}")
    public SendOtp sendOtp(@PathVariable String phone, HttpServletResponse httpServletResponse) {
        try {
            Response<SendOtp> response = Remote.Creator.getRetrofit().create(Remote.class)
                    .sendOtp(environment.getProperty("SMS_API_KEY"), phone).execute();
            SendOtp sendOtp = response.isSuccessful() ? response.body()
                    : new Gson().fromJson(response.errorBody().string(), SendOtp.class);
            if (sendOtp.Status.equalsIgnoreCase("Error") && response.code() == 200) {
                httpServletResponse.setStatus(400);
            } else {
                httpServletResponse.setStatus(response.code());
            }
            return sendOtp;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerException();
        }
    }

    @PostMapping(value = "/verify", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void verifyOtp(@RequestBody RequestVerifyOtp requestVerifyOtp, HttpServletResponse httpServletResponse) {
        try {
            Response<VerifyOtp> response = Remote.Creator.getRetrofit().create(Remote.class)
                    .verifyOtp(environment.getProperty("SMS_API_KEY"), requestVerifyOtp.session_id, requestVerifyOtp.otp_input).execute();

            VerifyOtp verifyOtp = response.isSuccessful() ? response.body()
                    : new Gson().fromJson(response.errorBody().string(), VerifyOtp.class);
            if (verifyOtp.Status.equalsIgnoreCase("Success") && verifyOtp.Details.equalsIgnoreCase("OTP Matched")) {
                User user = new User(requestVerifyOtp.phone, requestVerifyOtp.device_name);
                user.setId(TokenService.generateUniqueId(requestVerifyOtp.phone, environment.getProperty("SALT")));
                TokenService.addAuthToken(httpServletResponse, requestVerifyOtp.phone,
                        environment.getProperty("SECRET_KEY"), environment.getProperty("SALT"));
                userRepository.save(user);
            } else {
                throw new OtpException(verifyOtp.Details);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerException();
        }
    }
}
