package com.example.apidemo.gateway;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestWSAdapter extends AbstractWSAdapter {

    public RestWSAdapter() {
    }

    public <T> ResponseEntity<T> postRestApi(String url, HttpEntity httpEntity, Class<T> responseType) {
        return exchange(HttpMethod.POST, url, httpEntity, responseType, null);
    }

    public <T> ResponseEntity<T> exchange(HttpMethod method, String url, HttpEntity httpEntity, Class<T> responseType, String service) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(url, method, httpEntity, responseType);
        } catch (RestClientException e) {
            throw new RestClientException("client failed, " + e);
        }
    }
}
