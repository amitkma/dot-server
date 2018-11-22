package com.dot.backend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    public String id;

    public String name;

    public String image;

    public String description;

    @DBRef
    public Category category;
}
