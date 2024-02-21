package ru.pivovarov.FoodRecipeSearch.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Ingredient {
    private Long id;
    private Recipe recipe;
    private String text;
    private int quantity;
    private String measure;
    @JsonProperty("food")
    private String name;
    @JsonProperty("foodCategory")
    private String category;
}
