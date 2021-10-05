package com.techelevator.dao;

import com.techelevator.models.CatCard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
//@Component allows spring to inject this into our controller
public class JdbcCatCardDao implements CatCardDao{

    // Attribute
    private JdbcTemplate jdbcTemplate;

    public JdbcCatCardDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CatCard> getAllCards() {
        List<CatCard> catCards = new ArrayList<>();
        String sql = "SELECT id, img_url, fact, caption FROM catcards";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            catCards.add(mapRowToCatCard(results));
        }
        return catCards;
    }

    @Override
    public CatCard getCardById(int id) {
        CatCard catCard = new CatCard();
        String sql = "SELECT id, img_url, fact, caption FROM catcards WHERE id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()){
            catCard = mapRowToCatCard(results);
        }
        return catCard;
    }
    @Override
    public CatCard create(CatCard catCard) {
        String sql = "INSERT INTO catcards (img_url, fact, caption) " +
                "VALUES (?, ?, ?) RETURNING id";
        int id = jdbcTemplate.queryForObject(sql, Integer.class,
                catCard.getImgUrl(), catCard.getCatFact(), catCard.getCaption());
        catCard = getCardById(id);
        return catCard;
    }

// ****************  Finished out the update and delete methods *************************
    @Override
    public CatCard update(CatCard catCard, int id) {

        String sql = "UPDATE catcards SET img_url = ?, fact = ?, caption = ? WHERE id = ? ";
        jdbcTemplate.update(sql, catCard.getImgUrl(), catCard.getCatFact(), catCard.getCaption(), id);
        catCard = getCardById(id);
        return catCard;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM catcards WHERE id = ? ";
        return jdbcTemplate.update(sql, id) == 1;
    }

    private CatCard mapRowToCatCard(SqlRowSet results){
        CatCard catCard = new CatCard();
        catCard.setCatCardId(results.getInt("id"));
        catCard.setImgUrl(results.getString("img_url"));
        catCard.setCatFact(results.getString("fact"));
        catCard.setCaption(results.getString("caption"));

        return catCard;
    }
}
