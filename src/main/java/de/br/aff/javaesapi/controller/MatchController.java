package de.br.aff.javaesapi.controller;

import de.br.aff.javaesapi.config.IndexConfiguration;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequiredArgsConstructor
public class MatchController {

    private final Client client;
    private final IndexConfiguration index;

    @GetMapping(value = "/match")
    public Map<String, Object> getProfileUsingRequestParameter(@RequestParam String id) {

        GetRequestBuilder requestBuilder = client
                .prepareGet()
                .setIndex(index.getName()) //elasticsearch index
                .setType(index.getType()) // elasticsearch index type
                .setId(id);

        GetResponse response = requestBuilder.get();

        //previous two lines could be substituted with //GetResponse response = client.prepareGet(index.getName(), index.getType(), id).get();

        Map<String, Object> fieldValues = response.getSource();

        return fieldValues;
    }

}
