package com.amaizumelody.recipeapp.api;

import com.amaizumelody.recipeapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/Nsikaktopdown/Recipe/master/Recipe.json")
    Call<List<Recipe>> readJson();
}
