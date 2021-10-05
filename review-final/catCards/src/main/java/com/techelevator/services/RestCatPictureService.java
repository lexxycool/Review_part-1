package com.techelevator.services;

import com.techelevator.models.CatPicture;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
// @Component allows spring to inject this for us in the controller
public class RestCatPictureService implements CatPictureService{

    private final String API_URL = "https://cat-data.netlify.app/api/pictures/random";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public CatPicture getCatPicture() {
        // calling restTemplate allows us to access an external API
        return restTemplate.getForObject(API_URL, CatPicture.class);
    }
}
