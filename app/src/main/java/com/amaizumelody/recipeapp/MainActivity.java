package com.amaizumelody.recipeapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.amaizumelody.recipeapp.adapter.RecipeAdapter;
import com.amaizumelody.recipeapp.api.Client;
import com.amaizumelody.recipeapp.api.Service;
import com.amaizumelody.recipeapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    static List<Recipe> recipesList;
    ProgressDialog progressDialog;

    public static final String LOG_TAG = RecipeAdapter.class.getName();

    public Activity getActivity() {
        Context context = this;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }

        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
        progressDialog.show();
        loadJson();


    }

    private void initViews() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching...");
        progressDialog.setCancelable(false);



        recyclerView =  findViewById(R.id.recycler_view);
        recipesList = new ArrayList<>();
        adapter = new RecipeAdapter(this, recipesList);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
 //           recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private void loadJson() {

          Service service = Client.getClient().create(Service.class);
       // Service service = Client.getClient().create(Service.class);
        //Service Service = Client.getClient().create(Service.class);
        Call<List<Recipe>> call = service.readJson();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipesList = response.body();
                adapter.setRecipes(recipesList);
                progressDialog.dismiss();


            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e("failure", "failure");

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();
        if(menuItemId ==R.id.refresh){
            initViews();
            progressDialog.show();
            loadJson();
        }
        return super.onOptionsItemSelected(item);
    }

    /*public  static Recipe getRecipe(int position){
        return recipesList.get(position);
    }*/
    public  static Recipe getRecipe(int position){
        return recipesList.get(position);
    }

}

