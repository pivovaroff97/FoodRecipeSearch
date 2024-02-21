package ru.pivovarov.FoodRecipeSearch.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class Recipe {
    private Long id;
    private String label;
    private double calories;
    private List<Ingredient> ingredients;
}
