package com.studentapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BestBuyProductsDetails {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("shipping")
    private Integer shipping;
    @JsonProperty("upc")
    private String upc;
    @JsonProperty("description")
    private String description;
    @JsonProperty("manufacturer")
    private String manufacturer;
    @JsonProperty("model")
    private String model;
    @JsonProperty("url")
    private String url;
    @JsonProperty("image")
    private String image;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("createdAt")
    private String createdAt;
}