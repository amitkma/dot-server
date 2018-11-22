package com.dot.backend.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invites")
public class PreInvite {
    public String email;
    public String name;
    public String ip;
}
