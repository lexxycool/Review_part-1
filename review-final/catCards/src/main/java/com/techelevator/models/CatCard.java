package com.techelevator.models;

public class CatCard {
    // attributes
    private int catCardId;
    private String imgUrl;
    private String catFact;
    private String caption;

    public int getCatCardId() {
        return catCardId;
    }

    public void setCatCardId(int catCardId) {
        this.catCardId = catCardId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCatFact() {
        return catFact;
    }

    public void setCatFact(String catFact) {
        this.catFact = catFact;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
