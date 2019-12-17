package com.example.gituserrepolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is a data model class which defines all the data members of
 * a repo object including getters and setters
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SimpleRepo {

    // data members
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("html_url")
    private String html_url;

    //getters
    @JsonProperty("name")
    public String getName(){
        return name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("html_url")
    public String getHtml_url() {
        return html_url;
    }

    //setters
    @JsonProperty("name")
    public void setName(@Nullable @NonNull String name){
        this.name = name;
    }

    @JsonProperty("description")
    public void setDescription(@Nullable @NonNull String description) {
        this.description = description;
    }

    @JsonProperty("html_url")
    public void setHtml_url(@Nullable @NonNull String html_url) {
        this.html_url = html_url;
    }

}
