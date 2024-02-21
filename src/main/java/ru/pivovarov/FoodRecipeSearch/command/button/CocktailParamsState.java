package ru.pivovarov.FoodRecipeSearch.command.button;

import lombok.Data;

import java.util.*;

@Data
public class CocktailParamsState {
    private String stepName;
    private Set<String> chosenCategory = new HashSet<>();
    private String health;

    public void clearParams() {
        stepName = NameHelper.ALCOHOL_OR_NOT_STEP;
        chosenCategory.clear();
        health = null;
    }
}
