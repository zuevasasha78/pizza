package ru.pizza.tests;

import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import ru.pizza.helpers.jsonScheme.PizzaScheme;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.pizza.helpers.NetworkManager.responsePost;

@Feature("Приготовление пицы")
public class MakePizzaTest extends TestBase {
    private PizzaScheme pizzaScheme = new PizzaScheme();

    @Description("Приготовить пиццу. Пица должна быть приготовлена")
    @Test()
    public void makePizza() {
        String ingredient = "А,В,Г,Д,Е,Ж,З,И,К,Л";
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(ingredient.split(",")));

        PizzaScheme bodyPayload = pizzaScheme.setIngredients(ingredients);
        ResponseBodyExtractionOptions response = responsePost(bodyPayload);

        assertThat(response.jsonPath().getBoolean("result")).isEqualTo(true);
        assertThat(response.jsonPath().getString("message")).isEqualTo("Pizza is done!");
    }

    @Description("Приготовить пиццу с несоединимыми ингредиентами Б, В")
    @Test()
    public void makePizzaIncompatibleB() {
        String ingredient = "А,Б,В,Д,Е,Ж,З,И,К,Л";
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(ingredient.split(",")));

        PizzaScheme bodyPayload = pizzaScheme.setIngredients(ingredients);
        ResponseBodyExtractionOptions response = responsePost(bodyPayload);

        assertThat(response.jsonPath().getBoolean("result")).isEqualTo(false);
        assertThat(response.jsonPath().getString("message")).isEqualTo("Ingredients are incompatible, choose other ingredients!");
    }

    @Description("Приготовить пиццу с несоединимыми ингредиентами Б, Г")
    @Test()
    public void makePizzaIncompatible() {
        String ingredient = "А,Б,Г,Д,Е,Ж,З,И,К,Л";
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(ingredient.split(",")));

        PizzaScheme bodyPayload = pizzaScheme.setIngredients(ingredients);
        ResponseBodyExtractionOptions response = responsePost(bodyPayload);

        assertThat(response.jsonPath().getBoolean("result")).isEqualTo(false);
        assertThat(response.jsonPath().getString("message")).isEqualTo("Ingredients are incompatible, choose other ingredients!");
    }
}

