package com.example.apidemo.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class PostGateway {

    private final RestTemplate restTemplate;
    private final String postApiUrl;

    @Value("${post.api.url.endpoint1}")
    private String postApiUrl1;

    @Value("${post.api.url.endpoint2}")
    private String postApiUrl2;

    @Autowired
    public PostGateway(final RestTemplate restTemplate,
                       @Value("${post.api.url}") final String postApiUrl) {
        this.restTemplate = restTemplate;
        this.postApiUrl = postApiUrl;
    }

    @Autowired
    private RestWSAdapter restWSAdapter;

    public Optional<PostResponse> getPostById(int id) {
        String url = String.format("%s/posts/%d", postApiUrl1, id);

        try {
            return Optional.ofNullable(
                    restTemplate.getForObject(url, PostResponse.class));
        } catch (RestClientException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public PostResponse sendMessageToEndPoint1(int id, String title, String message){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        PostRequest request = new PostRequest();
        request.setUserId(id);
        request.setTitle(title);
        request.setBody(message);
        HttpEntity<PostRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        PostResponse response = restWSAdapter.postRestApi(postApiUrl1, httpEntity, PostResponse.class).getBody();
        return response;
    }

    public PostResponse sendMessageToEndPoint2(int id, String title, String message){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        PostRequest request = new PostRequest();
        request.setUserId(id);
        request.setTitle(title);
        request.setBody(message);
        HttpEntity<PostRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        PostResponse response = restWSAdapter.postRestApi(postApiUrl2, httpEntity, PostResponse.class).getBody();
        return response;
    }

}
