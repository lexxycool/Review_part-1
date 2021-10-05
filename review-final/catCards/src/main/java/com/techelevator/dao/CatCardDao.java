package com.techelevator.dao;

import com.techelevator.models.CatCard;

import java.util.List;

public interface CatCardDao {

    //  CRUD!!!

    // read or Get all catCard records from the database
    List<CatCard> getAllCards();

    // get one card from database
    CatCard getCardById(int id);

    CatCard create(CatCard catCard);

    CatCard update(CatCard catCard, int id);

    boolean delete(int id);

}
