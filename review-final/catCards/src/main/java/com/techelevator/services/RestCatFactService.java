package com.techelevator.services;

import com.techelevator.models.CatFact;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
//@Component allows spring to inject this into our controller
public class RestCatFactService implements CatFactService{

    private static final String API_URL = "https://cat-data.netlify.app/api/facts/random";
    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public CatFact getFact() {
        // calling restTemplate allows us to access an external API
        CatFact catFact = restTemplate.getForObject(API_URL, CatFact.class);
        return catFact;
    }
}
