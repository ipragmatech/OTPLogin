package ipragmatech.com.mobilelogin.service;

import com.joanzapata.android.asyncservice.api.annotation.AsyncService;
import com.joanzapata.android.asyncservice.api.annotation.Init;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

import ipragmatech.com.mobilelogin.request.BaseRequestObject;
import ipragmatech.com.mobilelogin.request.MobileSignupRequestObject;
import ipragmatech.com.mobilelogin.response.MobileVerificationResponse;
import ipragmatech.com.mobilelogin.utils.Constants;
import ipragmatech.com.mobilelogin.utils.StatusCodeMapper;

/**
 * Created by narender on 30/1/16.
 */
@AsyncService(errorMapper = StatusCodeMapper.class)
public class MobileService extends BaseService {

    @Init
    static void initStatic() {
        // Executed once for all services
    }

    @Init
    static void init() {
        // Executed once for this service
    }


    /*********************                Mobile Verification Service            ********************************/



    public MobileVerificationResponse getVerificationResponse(long id,BaseRequestObject requestObject) {
        String url = requestObject.getServerUrl() + Constants.MOBILE_VERIFICATION_URL;
        RestTemplate restTemplate = createRestTemplate();
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.setAll(requestObject.initializeParams());
        queryParams.add("mobileno", requestObject.getMobileNo());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity<Map>(queryParams, httpHeaders);

        HttpEntity<?> response = getExchange(url, restTemplate, request, MobileVerificationResponse.class);

        if (response != null) {
            MobileVerificationResponse mobileVerificationResponse = (MobileVerificationResponse) response.getBody();
            return mobileVerificationResponse;
        } else {
            return null;
        }

    }


    //********************   Mobile Signup Service            *******************************



    public MobileVerificationResponse getMobileSignupResponse(long id,MobileSignupRequestObject requestObject) {

        String url = requestObject.getServerUrl() + Constants.MOBILE_SIGNUP_URL;

        RestTemplate restTemplate = createRestTemplate();
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.setAll(requestObject.initializeParams());

        queryParams.add("deviceId",requestObject.getDeviceId());
        queryParams.add("mobileno", requestObject.getMobileNo());
        queryParams.add("otp", requestObject.getOtp());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity<Map>(queryParams, httpHeaders);

        HttpEntity<?> response = getExchange(url, restTemplate, request, MobileVerificationResponse.class);
        if (response != null) {

            MobileVerificationResponse mobileVerificationResponse = (MobileVerificationResponse) response.getBody();
            return mobileVerificationResponse;
        } else {
            return null;
        }
    }

}
