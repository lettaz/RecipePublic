package com.amaizumelody.recipeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaizumelody.recipeapp.model.Recipe;
import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    public static final String RECIPE_POSITION = "recipe_position";

    TextView nameOfRecipe;
    TextView favoriteName;
    TextView Ingredient;
    TextView headline;
    TextView nameOfFiber;
    ImageView imageview;
    TextView description;
    TextView carbos;
    TextView Protein;
    TextView String;
    TextView fats;
    TextView difficulty;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageview = findViewById(R.id.thumb_header);
        Intent intent = getIntent();
        nameOfRecipe = findViewById(R.id.recipe_name);
        Ingredient =  findViewById(R.id.recipe_ingredient);
        description = findViewById(R.id.recipe_description);
        headline = findViewById(R.id.recipe_extension);
        difficulty = findViewById(R.id.difficulty);
        if (intent != null) {
            int position = intent.getIntExtra(RECIPE_POSITION, 0);
            Recipe recipe = MainActivity.getRecipe(position);
            Glide.with(this).load(recipe.getImage()).into(imageview);


            nameOfRecipe.setText(recipe.getName());
            Ingredient.setText(recipe.getIngredients().toString());
            description.setText(recipe.getDescription());
            headline.setText(recipe.getHeadline());
            difficulty.setText(recipe.getDifficulty().toString());
            MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.favourite);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite){
                                SharedPreferences.Editor editor = getSharedPreferences("com.amaizumelody.recipeapp.DetailActivity",MODE_PRIVATE).edit();
                                editor.putBoolean("Favourite Added", true);
                                editor.commit();
                                /*saveFavourite();*/
                                Snackbar.make(buttonView, "Added to Favourite",Snackbar.LENGTH_SHORT).show();
                            }else{
                                SharedPreferences.Editor editor = getSharedPreferences("com.amaizumelody.recipeapp.DetailActivity",MODE_PRIVATE).edit();
                                editor.putBoolean("Favourite Removed", true);
                                editor.commit();
                                Snackbar.make(buttonView, "Removed to Favourite",Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

        }

        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initCollapsingToolbar();

    }
    private void initCollapsingToolbar() {
        /*final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(getString(R.string.Details));
                    isShow = true;
                }else if(isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }

            }
        });*/
    }
}
