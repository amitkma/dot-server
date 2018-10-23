package com.dot.backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "promotions")
public class Promotion {
    @Id
    private String id;
    private String promotion_image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromotion_image() {
        return promotion_image;
    }

    public void setPromotion_image(String promotion_image) {
        this.promotion_image = promotion_image;
    }
}
