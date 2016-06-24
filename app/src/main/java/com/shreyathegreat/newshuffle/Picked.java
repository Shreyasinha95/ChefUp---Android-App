package com.shreyathegreat.newshuffle;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class Picked extends ActionBarActivity {
    Toolbar t_toolbar;
    Random r = new Random();
    int size;
    int randm_fruit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked);
        t_toolbar  = (Toolbar)findViewById(R.id.t_toolbar);
        setSupportActionBar(t_toolbar);
        setTitle("Breakfast");
        if (t_toolbar != null) {
            ActionBar actionBar = getSupportActionBar();

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);


        }
        TextView tv_picked = (TextView)findViewById(R.id.tv_picked);
        CircleImageView img_food = (CircleImageView)findViewById(R.id.img_food);
        final ArrayList<Fruits> list = new ArrayList<Fruits>();
        {
            list.add(new Fruits("Raagi Idli", R.drawable.raagi));
            list.add(new Fruits("Peas Paratha", R.drawable.peas_paratha));
            list.add(new Fruits("Idli",  R.drawable.idli));
            list.add(new Fruits("Dosa",  R.drawable.dosa));
            list.add(new Fruits("Poori-Sabzi",  R.drawable.poori_sabzi));
            list.add(new Fruits("Chutney Sandwich", R.drawable.chutney_sandwich));
            list.add(new Fruits("Besan Toast",  R.drawable.besan_toast));
            list.add(new Fruits("Cheese Toast",  R.drawable.veg_cheese_toast));
            list.add(new Fruits("Onion Rava Dosa",  R.drawable.onion_rava_dosa));
            list.add(new Fruits("Cheese Garlic Toast",  R.drawable.cheese_garlic_toast));
            list.add(new Fruits("Appam",  R.drawable.appam));
            list.add(new Fruits("Kanda Batata Poha", R.drawable.potato_onion_poha));
            list.add(new Fruits("Sesame Veg Toast ", R.drawable.sesame_veg_toast));
            list.add(new Fruits("Chana Masala Sandwich", R.drawable.chana_masala_toast_sandwich));
            list.add(new Fruits("Aloo Palak Paratha", R.drawable.aloo_palak_parathajpg));
            list.add(new Fruits("Sindhi Pakwan", R.drawable.pakwan));
            list.add(new Fruits("Sugar Paratha", R.drawable.sugar_paratha));
            list.add(new Fruits("Rava Toast", R.drawable.rava_toast_recipe));
            list.add(new Fruits("Sweet Paniyaram", R.drawable.sweet_paniyaram_recipe));
            list.add(new Fruits("Vermicelli Upma", R.drawable.upma_seviyan_recipe));
            list.add(new Fruits("Vegetable Dalia", R.drawable.veg_dalia));
            list.add(new Fruits("Raagi Idli", R.drawable.raagi));
            list.add(new Fruits("Raagi Idli", R.drawable.raagi));
            list.add(new Fruits("Raagi Idli", R.drawable.raagi));
            list.add(new Fruits("Raagi Idli", R.drawable.raagi));
            list.add(new Fruits("Raagi Idli", R.drawable.raagi));
            list.add(new Fruits("Raagi Idli", R.drawable.raagi));
            list.add(new Fruits("Raagi Idli", R.drawable.raagi));


        }
        size = list.size();
        int prev_randm_fruit = -1;
        randm_fruit = r.nextInt(size);
        while (prev_randm_fruit == randm_fruit) {
            randm_fruit = r.nextInt(size);
            prev_randm_fruit = randm_fruit;
        }
        tv_picked.setText(list.get(randm_fruit).name);
        img_food.setImageResource(list.get(randm_fruit).image);


    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picked, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
