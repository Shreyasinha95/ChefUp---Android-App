package com.shreyathegreat.newshuffle;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class SearchResults extends ActionBarActivity {
    ArrayList<FoodResult> food_result = new ArrayList<FoodResult>();
    String query;
    ArrayList<FoodResult> food_result_display = new ArrayList<FoodResult>();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Results");
        //ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent out = getIntent();
        if(out.ACTION_SEARCH.equals(out.getAction())){
            query = out.getStringExtra(SearchManager.QUERY);
            //Toast.makeText(this, "" + query, Toast.LENGTH_LONG).show();
        }
        FoodInfo foods = new FoodInfo(this);
        foods.open();
        food_result = foods.searchfood();
        foods.close();
        ArrayList<String> names =new ArrayList<String>();
        int j = 0;
        for(int i = 0;i<food_result.size();i++) {
            String name =  food_result.get(i).food_name;
            if(name.toLowerCase().contains(query.toLowerCase())){
                //food_result_display.add(++j,food_result.get(i));
            names.add(food_result.get(i).food_name);
                food_result_display.add(j++,food_result.get(i));
            }
        }


        ListView listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter listViewAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SearchResults.this, DisplayRecipeActivity.class);
                intent.putExtra("ID", food_result_display.get(position).id);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;}
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
