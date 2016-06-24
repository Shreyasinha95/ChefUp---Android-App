package com.shreyathegreat.newshuffle;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.widget.Toast;
import java.util.ArrayList;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.shreyathegreat.newshuffle.R;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tv_fruit;
    int randm_fruit;
    Spinner spinner;
    Button b_shuffle;
    Random r = new Random();
    DrawerLayout drawer_Layout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ArrayList<Fruits> list = new ArrayList();
    String ingr, recipe,tip;

    ArrayList<FoodItem> foodList = new ArrayList();



    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_main);
        SharedPreferences prefs = getSharedPreferences("MyPref",0);
        int a=6+9;

        FoodInfo foods = new FoodInfo(this);
        foods.open();
        if(prefs.getBoolean("noData",true)) {
            foods.createFoodItem(0,"Lachha Paratha", "Non-Veg", "North", "Breakfast,Bread", R.drawable.lachha_paratha_);
            ingr = getResources().getString(R.string.LachhaParathaIngr);
            recipe = getResources().getString(R.string.LachhaParathaRecipe);
            tip = getResources().getString(R.string.LachhaParathaTip);
            foods.createRecipe(0, "60 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(1,"Masala Paratha", "Veg", "North", "Breakfast,Bread", R.drawable.masala_paratha_);
            ingr = getResources().getString(R.string.MasalaParathaIngr);
            recipe = getResources().getString(R.string.MasalaParathaRecipe);
            tip = getResources().getString(R.string.MasalaParathaTip);
            foods.createRecipe(1, "30 min", "15 min", ingr, recipe, tip);

            foods.createFoodItem(2,"Bhatura", "Veg", "North", "Breakfast,Bread", R.drawable.masala_paratha_);
            ingr = getResources().getString(R.string.BhaturaIngr);
            recipe = getResources().getString(R.string.BhaturaRecipe);
            tip = getResources().getString(R.string.BhaturaTip);
            foods.createRecipe(2, "20 min", "20 min", ingr, recipe, tip);

            foods.createFoodItem(3,"Red Poha Upma", "Veg", "North", "Breakfast", R.drawable.masala_paratha_);
            ingr = getResources().getString(R.string.RedPohaUpmaIngr);
            recipe = getResources().getString(R.string.RedPohaUpmaRecipe);
            tip = getResources().getString(R.string.RedPohaUpmaTip);
            foods.createRecipe(3, "15 min", "15 min", ingr, recipe, tip);

            foods.createFoodItem(4,"Chura Matar", "Veg", "North", "Breakfast", R.drawable.masala_paratha_);
            ingr = getResources().getString(R.string.ChuraMatarIngr);
            recipe = getResources().getString(R.string.ChuraMatarRecipe);
            tip = getResources().getString(R.string.ChuraMatarTip);
            foods.createRecipe(4, "10 min", "20 min", ingr, recipe, tip);

            foods.createFoodItem(5,"Bedmi Puri", "Veg", "North", "Breakfast,Bread", R.drawable.masala_paratha_);
            ingr = getResources().getString(R.string.BedmiPuriIngr);
            recipe = getResources().getString(R.string.BedmiPuriRecipe);
            tip = getResources().getString(R.string.BedmiPuriTip);
            foods.createRecipe(5, "60 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(6,"Dal Pakwan", "Veg", "North", "Breakfast,Bread", R.drawable.masala_paratha_);
            ingr = getResources().getString(R.string.DalPakwanIngr);
            recipe = getResources().getString(R.string.DalPakwanRecipe);
            tip = getResources().getString(R.string.DalPakwanTip);
            foods.createRecipe(6, "45 min", "45 min", ingr, recipe, tip);

            foods.createFoodItem(7,"Paneer Sandwich", "Veg", "North", "Breakfast,Bread", R.drawable.masala_paratha_);
            ingr = getResources().getString(R.string.PaneerSandwichIngr);
            recipe = getResources().getString(R.string.PaneerSandwichRecipe);
            tip = getResources().getString(R.string.PaneerSandwichTip);
            foods.createRecipe(7, "10 min", "5 min", ingr, recipe, tip);

            foods.createFoodItem(8,"Chilli Cheese Sandwich", "Veg", "North", "Breakfast,Bread", R.drawable.masala_paratha_);
            ingr = getResources().getString(R.string.ChilliCheeseSandwichIngr);
            recipe = getResources().getString(R.string.ChilliCheeseSandwichRecipe);
            tip = getResources().getString(R.string.ChilliCheeseSandwichTip);
            foods.createRecipe(8, "4 min", "6 min", ingr, recipe, tip);

            foods.createFoodItem(9,"Vegetable Masala Toast", "Veg", "North", "Breakfast,Bread", R.drawable.masala_paratha_);
            ingr = getResources().getString(R.string.VegetableMasalaToastIngr);
            recipe = getResources().getString(R.string.VegetableMasalaToastRecipe);
            tip = getResources().getString(R.string.VegetableMasalaToastTip);
            foods.createRecipe(9, "5 min", "12 min", ingr, recipe, tip);

            foods.createFoodItem(10,"Aaloo Fry", "Veg", "North", "Main Course", R.drawable.aaloo_fry_);
            ingr = getResources().getString(R.string.AalooFryIngr);
            recipe = getResources().getString(R.string.AalooFryRecipe);
            tip = getResources().getString(R.string.AalooFryTip);
            foods.createRecipe(10, "30 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(11,"Matar mushroom", "Veg", "North", "Main Course", R.drawable.matar_mushroom_);
            ingr = getResources().getString(R.string.MushroomMatarIngr);
            recipe = getResources().getString(R.string.MushroomMatarRecipe);
            tip = getResources().getString(R.string.MushroomMatarTip);
            foods.createRecipe(11, "15 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(12,"Mushroom Masala", "Veg", "North", "Main Course", R.drawable.matar_mushroom_);
            ingr = getResources().getString(R.string.MushroomMasalaIngr);
            recipe = getResources().getString(R.string.MushroomMasalaRecipe);
            tip = getResources().getString(R.string.MushroomMasalaTip);
            foods.createRecipe(12, "10 min", "40 min", ingr, recipe, tip);

            foods.createFoodItem(13,"Pahari Aloo", "Veg", "North", "Main Course", R.drawable.pahari_aloo_recipe_);
            ingr = getResources().getString(R.string.PahariAlooIngr);
            recipe = getResources().getString(R.string.PahariAlooRecipe);
            tip = getResources().getString(R.string.PahariAlooTip);
            foods.createRecipe(13, "5 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(13,"Bharwa Bhindi", "Veg", "North", "Main Course", R.drawable.pahari_aloo_recipe_);
            ingr = getResources().getString(R.string.BharwaBhindiIngr);
            recipe = getResources().getString(R.string.BharwaBhindiRecipe);
            tip = getResources().getString(R.string.BharwaBhindiTip);
            foods.createRecipe(13, "15 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(14,"Khoya Matar Paneer", "Veg", "North", "Main Course", R.drawable.pahari_aloo_recipe_);
            ingr = getResources().getString(R.string.KhoyaMatarPaneerIngr);
            recipe = getResources().getString(R.string.KhoyaMatarPaneerRecipe);
            tip = getResources().getString(R.string.KhoyaMatarPaneerTip);
            foods.createRecipe(14, "20 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(15,"Punjabi Chole", "Veg", "North", "Main Course", R.drawable.pahari_aloo_recipe_);
            ingr = getResources().getString(R.string.PunjabiCholeIngr);
            recipe = getResources().getString(R.string.PunjabiCholeRecipe);
            tip = getResources().getString(R.string.PunjabiCholeTip);
            foods.createRecipe(15, "15 min", "45 min", ingr, recipe, tip);

            foods.createFoodItem(16,"Kaju Paneer", "Veg", "North", "Main Course", R.drawable.pahari_aloo_recipe_);
            ingr = getResources().getString(R.string.KajuPaneerIngr);
            recipe = getResources().getString(R.string.KajuPaneerRecipe);
            tip = getResources().getString(R.string.KajuPaneerTip);
            foods.createRecipe(16, "10 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(17,"Butter Paneer Masala", "Veg", "North", "Main Course", R.drawable.pahari_aloo_recipe_);
            ingr = getResources().getString(R.string.ButterPaneerMasalaIngr);
            recipe = getResources().getString(R.string.ButterPaneerMasalaRecipe);
            tip = getResources().getString(R.string.ButterPaneerMasalaTip);
            foods.createRecipe(17, "20 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(18,"Aloo Tamatar Subzi", "Veg", "North", "Main Course", R.drawable.pahari_aloo_recipe_);
            ingr = getResources().getString(R.string.AlooTamatarSubziIngr);
            recipe = getResources().getString(R.string.AlooTamatarSubziRecipe);
            tip = getResources().getString(R.string.AlooTamatarSubziTip);
            foods.createRecipe(18, "20 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(19,"Khoya Matar Paneer", "Veg", "North", "Main Course", R.drawable.pahari_aloo_recipe_);
            ingr = getResources().getString(R.string.KhoyaMatarPaneerIngr);
            recipe = getResources().getString(R.string.KhoyaMatarPaneerRecipe);
            tip = getResources().getString(R.string.KhoyaMatarPaneerTip);
            foods.createRecipe(19, "20 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(20,"Mushroom pulao", "Veg", "North", "Main Course (Rice)", R.drawable.mushroom_pulao_);
            ingr = getResources().getString(R.string.MushroomPulaoIngr);
            recipe = getResources().getString(R.string.MushroomPulaoRecipe);
            tip = getResources().getString(R.string.MushroomPulaoTip);
            foods.createRecipe(20, "10 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(30,"Urad Dal", "Veg", "North", "Main Course (Dal)", R.drawable.urad_dal_recipe_);
            ingr = getResources().getString(R.string.UradDalIngr);
            recipe = getResources().getString(R.string.UradDalRecipe);
            tip = getResources().getString(R.string.UradDalTip);
            foods.createRecipe(30, "10 min", "45 min", ingr, recipe, tip);

            foods.createFoodItem(40,"Strawberry Phirni", "Veg", "North", "Deserts", R.drawable.strawberry_phirni_);
            ingr = getResources().getString(R.string.StrawberryPhirniIngr);
            recipe = getResources().getString(R.string.StrawberryPhirniRecipe);
            tip = getResources().getString(R.string.StrawberryPhirniTip);
            foods.createRecipe(40, "15 min", "40 min", ingr, recipe, tip);

            foods.createFoodItem(41,"Besan Ladoo", "Veg", "North", "Deserts", R.drawable.besan_ladoo_);
            ingr = getResources().getString(R.string.BesanLadooIngr);
            recipe = getResources().getString(R.string.BesanLadooRecipe);
            tip = getResources().getString(R.string.BesanLadooTip);
            foods.createRecipe(41, "20 min", "30 min", ingr, recipe, tip);

            foods.createFoodItem(42,"Panjiri", "Veg", "North", "Deserts", R.drawable.panjiri_);
            ingr = getResources().getString(R.string.PanjiriIngr);
            recipe = getResources().getString(R.string.PanjiriRecipe);
            tip = getResources().getString(R.string.PanjiriTip);
            foods.createRecipe(42, "10 min", "50 min", ingr, recipe, tip);

            foods.createFoodItem(43,"Carrot Kheer", "Veg", "North", "Deserts", R.drawable.carrot_kheer_recipe_);
            ingr = getResources().getString(R.string.CarrotKheerIngr);
            recipe = getResources().getString(R.string.CarrotKheerRecipe);
            tip = getResources().getString(R.string.CarrotKheerTip);
            foods.createRecipe(43, "15 min", "30 min", ingr, recipe, tip);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("noData",false);
            editor.commit();
        }
        foodList = foods.getFoodItems();
//        Log.d("Sherya layed: ", foodList.size() + " Eggs");
       // Toast.makeText(this, "Shreya layed: " + foodList.size() + " eggs", Toast.LENGTH_LONG).show();
        Log.d("fooditems",foodList+"");
        foods.close();

        //setting up toolbar

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        try {

            setSupportActionBar(toolbar);
            setTitle("Chef Up!");


        } catch (Throwable t) {

            // WTF
        }

        //viewpager
      final TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Vegetarian"));
        tabLayout.addTab(tabLayout.newTab().setText("Non-Vegetarian"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PagerAdapter padapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(padapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //Toast.makeText(MainActivity.this, "Shreya layed: " + tab + " eggs", Toast.LENGTH_LONG).show();
                //Toast.makeText(MainActivity.this, "I am : " + viewPager.getCurrentItem() + "st Tab", Toast.LENGTH_LONG).show();

                if((viewPager.getCurrentItem())==0)
                tabLayout.setTabTextColors(getResources().getColorStateList(R.color.selector));
                if((viewPager.getCurrentItem())==1)
                    tabLayout.setTabTextColors(getResources().getColorStateList(R.color.selector_r));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });







        //navigation drawer
        drawer_Layout = (DrawerLayout) findViewById(R.id.drawer_layout);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer_Layout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawer_Layout.setDrawerListener(actionBarDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }




    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_rate) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //long num;
                switch (menuItem.getItemId()) {
                    case R.id.north_id: {
                        Intent in = new Intent(MainActivity.this,RVshow.class);
                        //in.putParcelableArrayListExtra("list",list);
                        //num = 1;
                        //in.putExtra("North",num);
                        in.putExtra("Region_NAME", "North");
                        startActivity(in);
                        drawer_Layout.closeDrawers();
                        break;
                    }
                    case R.id.east_id: {
                        //Toast.makeText(getApplicationContext(), "I am East", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(MainActivity.this,RVshow.class);
                        //in.putParcelableArrayListExtra("list",list);
                        //num = 2;
                        //in.putExtra("East",num);
                        in.putExtra("Region_NAME", "East");
                        startActivity(in);
                        drawer_Layout.closeDrawers();
                        break;
                    }
                    case R.id.west_id: {
                        //Toast.makeText(getApplicationContext(), "I am West", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(MainActivity.this,RVshow.class);
                        //in.putParcelableArrayListExtra("list",list);
                        //num = 3;
                        //in.putExtra("West",num);
                        in.putExtra("Region_NAME", "West");
                        startActivity(in);
                        drawer_Layout.closeDrawers();
                        break;
                    }
                    case R.id.south_id: {
                        //Toast.makeText(getApplicationContext(), "I am South", Toast.LENGTH_LONG).show();
                        Intent in = new Intent(MainActivity.this,RVshow.class);
                        //in.putParcelableArrayListExtra("list",list);
                       // num = 4;
                        //in.putExtra("South",num);
                        in.putExtra("Region_NAME", "South");
                        startActivity(in);
                        drawer_Layout.closeDrawers();
                        break;
                    }
                    case R.id.fb: {
                       // Toast.makeText(getApplicationContext(), "I am Facebook", Toast.LENGTH_LONG).show();
                        String message = "Hey!I am using this awesome app and I thought you would like it. Download link : wwww.koochipoochi.com";
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, message);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sharing is caring");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));

                        drawer_Layout.closeDrawers();
                        break;
                    }
                    case R.id.feedback: {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "shreya.sinha95@gmail.com", null));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "FeedBack");
                        intent.putExtra(Intent.EXTRA_TEXT, "");
                        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                        drawer_Layout.closeDrawers();
                        break;
                    }
                    default:
                        break;
                }
                return true;

            }

        });
    }

}