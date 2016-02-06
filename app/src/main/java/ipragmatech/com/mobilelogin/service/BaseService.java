package ipragmatech.com.mobilelogin.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.joanzapata.android.asyncservice.api.annotation.ApplicationContext;
import com.joanzapata.android.asyncservice.api.annotation.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ipragmatech.com.mobilelogin.activity.MobileLoginApplication;
import ipragmatech.com.mobilelogin.response.Cookie;
import ipragmatech.com.mobilelogin.utils.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.MultiValueMap;

/**
 * Created by narender on 30/1/16.
 */
public abstract class BaseService {

    protected Cookie getCookies() {
        SharedPreferences prefs = MobileLoginApplication.getStaticContext().getSharedPreferences(Constants.SET_COOKIE, Context.MODE_PRIVATE);
        Cookie cookieObject = new Cookie(prefs.getString(Constants.PHPSESSID, ""), prefs.getString(Constants.EN_4_LANGUAGE, ""), prefs.getString(Constants.EN_4_LOCALE, ""));
        return cookieObject;

    }

    protected RestTemplate createRestTemplate() {

        MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        List listHttpMessageConverters = new ArrayList<HttpMessageConverter>();
        listHttpMessageConverters.add(jsonConverter);
        listHttpMessageConverters.add(formHttpMessageConverter);
        listHttpMessageConverters.add(stringHttpMessageConverter);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setMessageConverters(listHttpMessageConverters);

        return restTemplate;

    }

    protected Object postForObject(String url, RestTemplate restTemplate, MultiValueMap<String, String> queryParams, HttpHeaders httpHeaders, Class baseClass) {
        try {
            return restTemplate.postForObject(url,
                    new HttpEntity<Map>(queryParams, httpHeaders), baseClass);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    protected HttpEntity<?> getExchange(String url, RestTemplate restTemplate, HttpEntity request, Class baseClass) {
        try {
            return restTemplate.exchange(url, HttpMethod.POST, request, baseClass);
        } catch (RuntimeException e) {
            throw e;
        }
    }

}