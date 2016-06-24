package com.shreyathegreat.newshuffle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class DisplayRecipeActivity extends ActionBarActivity {
    RecyclerView recyclerView;
    RecipeDetails recipeDetails;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parallax_activity);
        Intent intent = getIntent();
        int food_id = intent.getIntExtra("ID", 0);
        FoodInfo foodInfo = new FoodInfo(this);
        foodInfo.open();
        recipeDetails = foodInfo.getRecipeForFoodItem(food_id);
        foodInfo.close();

/* -------------------------------------------
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle(recipeDetails.foodItem.name+" ");
        setSupportActionBar(toolbar);


        ImageView im = (ImageView)findViewById(R.id.im);
        im.setImageResource(recipeDetails.foodItem.image);
        TextView ptime_entry = (TextView)findViewById(R.id.ptime_entry);
        TextView ctime_entry = (TextView)findViewById(R.id.ctime_entry);
        TextView ingr_entry = (TextView)findViewById(R.id.ingr_entry);
        TextView steps_entry = (TextView)findViewById(R.id.steps_entry);

        ptime_entry.setText(recipeDetails.prep_time+" ");
        ctime_entry.setText(recipeDetails.cook_time+" ");
        ingr_entry.setText(recipeDetails.ingredients+" ");
        steps_entry.setText(recipeDetails.steps+" ");

-------------------------------------------------*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       ImageView header = (ImageView)findViewById(R.id.header);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(recipeDetails.foodItem.name);
        //collapsingToolbar.setContentScrimResource(recipeDetails.foodItem.image);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
               recipeDetails.foodItem.image);
      header.setImageBitmap(bitmap);
      //  header.setImageResource();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyParallaxAdapter myParallaxAdapter = new MyParallaxAdapter(recipeDetails);

        recyclerView.setAdapter(myParallaxAdapter);

        final FloatingActionButton tip_fab = (FloatingActionButton)findViewById(R.id.tip_fab);
        tip_fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Snackbar.make(tip_fab,recipeDetails.tip, Snackbar.LENGTH_INDEFINITE).setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
