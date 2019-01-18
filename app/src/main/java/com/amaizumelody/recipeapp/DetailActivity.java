package com.amaizumelody.recipeapp;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaizumelody.recipeapp.model.Recipe;
import com.bumptech.glide.Glide;

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
