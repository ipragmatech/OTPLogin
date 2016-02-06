package ipragmatech.com.mobilelogin.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import ipragmatech.com.mobilelogin.response.Cookie;

/**
 * Created by narender on 30/1/16.
 */
public class HeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final Cookie cookieObject;

    public HeaderHttpRequestInterceptor(Cookie sessionId) {
        this.cookieObject = sessionId;
    }



    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add("Cookie", cookieObject.getPhpSessionId() + ";" + cookieObject.getLang() + ";" + cookieObject.getLocale());
        return execution.execute(request, body);
    }
}