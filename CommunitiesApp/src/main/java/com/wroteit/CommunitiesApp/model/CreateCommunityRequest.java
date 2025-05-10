package com.wroteit.CommunitiesApp.model;
// src/main/java/com/wroteit/CommunitiesApp/model/CreateCommunityRequest.java

import java.util.List;

public class CreateCommunityRequest {
    private String name;
    private String description;
    private List<String> tags;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}
