package com.shreyathegreat.newshuffle;

import android.support.v7.app.ActionBarActivity;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class RVshow extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    RecyclerView gridView;
    Context context;
    MyAdapter myAdapter;
    Intent out;
    ArrayList<Fruits> list = new ArrayList();
    ArrayList<FoodItem> foodListRegion = new ArrayList<FoodItem>();
    Context c;
    String rName;
    String cat_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvshow);

        out = getIntent();



        rName = out.getStringExtra("Region_NAME");


            cat_val = rName;
            toolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(rName);
            //ActionBar actionBar = getActionBar();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            FoodInfo food5 = new FoodInfo(this);
            food5.open();

            foodListRegion = food5.fetchRegionFood(rName);

            food5.close();


        gridView = (RecyclerView) findViewById(R.id.grid_view);
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gridView.setLayoutManager(staggeredGridLayoutManager);
        myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);
        gridView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        context = this;
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

            case  R.id.veg:
                FoodInfo foodv = new FoodInfo(this);
                foodv.open();
                foodListRegion = foodv.fetchCategoryFood(cat_val, "Veg");
                foodv.close();
                break;
            case  R.id.nveg:
                FoodInfo foodnv = new FoodInfo(this);
                foodnv.open();
                foodListRegion = foodnv.fetchCategoryFood(cat_val, "Non-Veg");
                foodnv.close();
                break;
        }
        myAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

        @Override
        public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = getLayoutInflater().inflate(R.layout.element_grid, viewGroup, false);
            Holder holder = new Holder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder mHolder, final int position) {
            mHolder.cv.setPreventCornerOverlap(false);
            mHolder.name.setText(foodListRegion.get(position).name);
            mHolder.img_food.setImageResource(foodListRegion.get(position).image);
            Log.d("goo position", position + "");
            int p = position;
            mHolder.img_food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String url = (String) view.getTag();
                    /*CarDetails.launch(MainActivity.this, v.findViewById(R.id.imageView)
                            , cars.get(position).image, cars.get(position).year,
                            cars.get(position).make, cars.get(position).names);*/
                    //Toast.makeText(getApplicationContext(), "Mai Cheez badi hu mast mast!", Toast.LENGTH_LONG).show();
                    int d = position;
                    Log.d("goo position d", d + "");
                    Intent intent = new Intent(RVshow.this, DisplayRecipeActivity.class);
                    intent.putExtra("ID", foodListRegion.get(d).id);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return foodListRegion.size();
        }

        class Holder extends RecyclerView.ViewHolder {


            TextView name;
            CardView cv;
            CircleImageView img_food;

            public Holder(View v) {
                super(v);

                name = (TextView) v.findViewById(R.id.tv_name);
                img_food = (CircleImageView) v.findViewById(R.id.img_food);
                cv = (CardView) v.findViewById(R.id.card_view);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rvshow, menu);
        return true;
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        // User pressed the search button
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }


    }


