package com.example.crous.demo;

import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItemService {

    private final String apiUrl;
    private final RestTemplate restTemplate;

    public ItemService(@Value("${api.url}") String apiUrl) {
        this.apiUrl = apiUrl;
        this.restTemplate = new RestTemplate();
    }

    public List<Item> fetchItems() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", "SimpleSAMLSessionID=1c21fc3e1ea3baf6f9439a75deb211e6; qpid=ckkggbrfm5tsvkpg70lg");

        // Construisez la requête JSON
        String json = "{\n" +
                "    \"idTool\": 32,\n" +
                "    \"need_aggregation\": true,\n" +
                "    \"page\": 1,\n" +
                "    \"pageSize\": 24,\n" +
                "    \"sector\": null,\n" +
                "    \"occupationModes\": [],\n" +
                "    \"location\": [\n" +
                "        {\n" +
                "            \"lon\": 5.2286902,\n" +
                "            \"lat\": 43.3910329\n" +
                "        },\n" +
                "        {\n" +
                "            \"lon\": 5.5324758,\n" +
                "            \"lat\": 43.1696205\n" +
                "        }\n" +
                "    ],\n" +
                "    \"residence\": null,\n" +
                "    \"precision\": 5,\n" +
                "    \"equipment\": [],\n" +
                "    \"price\": {\n" +
                "        \"min\": 0,\n" +
                "        \"max\": 10000000\n" +
                "    }\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<Response> response = restTemplate.postForEntity(apiUrl, entity, Response.class);

        return response.getBody().getResults().getItems();
    }
}
