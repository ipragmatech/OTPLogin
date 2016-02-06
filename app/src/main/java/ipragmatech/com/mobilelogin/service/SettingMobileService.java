package ipragmatech.com.mobilelogin.service;

import com.joanzapata.android.asyncservice.api.annotation.AsyncService;
import com.joanzapata.android.asyncservice.api.annotation.CacheThenCall;
import com.joanzapata.android.asyncservice.api.annotation.ErrorManagement;
import com.joanzapata.android.asyncservice.api.annotation.Init;
import com.joanzapata.android.asyncservice.api.annotation.Mapping;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Map;

import ipragmatech.com.mobilelogin.request.BaseRequestObject;
import ipragmatech.com.mobilelogin.request.HeaderHttpRequestInterceptor;
import ipragmatech.com.mobilelogin.request.MobileSignupRequestObject;
import ipragmatech.com.mobilelogin.response.MobileVerificationResponse;
import ipragmatech.com.mobilelogin.response.PostDataResponse;
import ipragmatech.com.mobilelogin.utils.Constants;
import ipragmatech.com.mobilelogin.utils.StatusCodeMapper;

/**
 * Created by narender on 30/1/16.
 */

@AsyncService(errorMapper = StatusCodeMapper.class)
public class SettingMobileService extends BaseService {


    @Init
    static void initStatic() {
        // Executed once for all services
    }

    @Init
    static void init() {
        // Executed once for this service
    }

    /*********************     Mobile Change Verification          ************************************/

    public MobileVerificationResponse getSettingMobileChangeResponse(long id,BaseRequestObject requestObject) {

        String url = Constants.BASE_URL + Constants.SET_MOBILE_VERIFICATION_URL;

        RestTemplate restTemplate = createRestTemplate();
        ClientHttpRequestInterceptor interceptor = new HeaderHttpRequestInterceptor(getCookies());
        restTemplate.setInterceptors(Collections.singletonList(interceptor));

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.setAll(requestObject.initializeParams());
        queryParams.add("mobileno", String.valueOf(requestObject.getMobileNo()));

        //Log.d(BaseActivity.TAG, "url :" + url);
        //Log.d(BaseActivity.TAG, "params :" + queryParams.toString());

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

        return (MobileVerificationResponse) postForObject(url, restTemplate, queryParams, httpHeaders, MobileVerificationResponse.class);

    }

    /*********************     Otp accept  Service      ************************************/


    public PostDataResponse getMobileChangeOtpResponse(long id,MobileSignupRequestObject requestObject) {

        String url = requestObject.getServerUrl() + Constants.SET_MOBILE_UPDATE_URL;

        RestTemplate restTemplate = createRestTemplate();
        ClientHttpRequestInterceptor interceptor = new HeaderHttpRequestInterceptor(getCookies());
        restTemplate.setInterceptors(Collections.singletonList(interceptor));

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        queryParams.setAll(requestObject.initializeParams());
        queryParams.add("mobileno", String.valueOf(requestObject.getMobileNo()));
        queryParams.add("otp", requestObject.getOtp());

        //Log.d(BaseActivity.TAG, "url :" + url);
        //Log.d(BaseActivity.TAG, "params :" + queryParams.toString());

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

        return (PostDataResponse) postForObject(url, restTemplate, queryParams, httpHeaders, PostDataResponse.class);

    }
}
