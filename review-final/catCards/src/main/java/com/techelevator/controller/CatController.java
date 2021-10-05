package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.models.CatCard;
import com.techelevator.models.CatFact;
import com.techelevator.models.CatPicture;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPictureService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")  // every mapping in here will start off with this path
public class CatController {

    private CatCardDao dao;
    private CatPictureService catPicture;
    private CatFactService catFact;

    public CatController(CatCardDao dao, CatPictureService catPicture, CatFactService catFact){
        this.dao = dao;
        this.catPicture = catPicture;
        this.catFact = catFact;
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public List<CatCard> getAllCards(){
        return dao.getAllCards();
    }

    @RequestMapping(path="/random", method = RequestMethod.GET)
    public CatCard getRandomCatCard(){
        CatFact fact = catFact.getFact();  // call the service and generate a random fact
        CatPicture picture = catPicture.getCatPicture(); // get a random picture
        CatCard card = new CatCard();
        card.setImgUrl(picture.getFile());  // load picture in catcard object
        card.setCatFact(fact.getText());   // load fact in catcard object
        return card;   // return
    }

    @RequestMapping(path="", method=RequestMethod.POST)
    public CatCard saveCatCard(@RequestBody CatCard catCard){
        return dao.create(catCard);
    }

    // **********************  NEW CONTENT ***********************

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CatCard getIndividualCard(@PathVariable int id) {

        return dao.getCardById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateExistingCard(@RequestBody CatCard changedCard, @PathVariable int id) {
        dao.update(changedCard, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteExistingCard(@PathVariable int id) {
        if (dao.getCardById(id) != null) {
            dao.delete(id);
        }
    }
}
