package ru.pivovarov.FoodRecipeSearch.client;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.pivovarov.FoodRecipeSearch.client.dto.Recipe;
import ru.pivovarov.FoodRecipeSearch.command.button.CocktailParamsState;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Component
public class RecipeClientImpl implements RecipeClient {

    private final RestTemplate restTemplate;

    private final String API_REQUEST = "/api";
    @Value("${cocktail-service.url}")
    private String cocktailServiceUrl;

    @Autowired
    public RecipeClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<Recipe[]> getRecipes(CocktailParamsState cocktailParamsState) {
        try {
            URIBuilder uriBuilder = new URIBuilder(cocktailServiceUrl.concat(API_REQUEST).concat("/recipes"));
            if (cocktailParamsState.getHealth() != null) {
                uriBuilder.addParameter("health", cocktailParamsState.getHealth());
            }
            if (!cocktailParamsState.getChosenCategory().isEmpty()) {
                for (String category : cocktailParamsState.getChosenCategory()) {
                    uriBuilder.addParameter("category", category);
                }
            }
            String url = uriBuilder.build().toURL().toString();
            System.out.println("requestURL: " + url);
            return restTemplate.getForEntity(url, Recipe[].class);
        } catch (URISyntaxException | MalformedURLException | ResourceAccessException e) {
            //TODO handle exceptions
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
