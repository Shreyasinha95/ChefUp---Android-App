package com.shreyathegreat.newshuffle;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class VegFragment extends Fragment implements SensorEventListener,View.OnClickListener {

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 700;
    public int pos = 0;
    FloatingActionButton fab;
    CircleImageView img_food;
    Spinner spinner1;
    Integer randm_fruit = -1;
    Random r = new Random();
    TextView tv_fruit;
    ArrayList<FoodItem> foodItemContainer = new ArrayList<FoodItem>();
    ShowcaseView.Builder showShowcaseView;
    final int SHOWCASEVIEW_ID = 28;
    boolean done = false;
    public VegFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View InputFragmentView = inflater.inflate(R.layout.fragment_veg2, container, false);


        // Inflate the layout for this fragment

        fab = (FloatingActionButton) InputFragmentView.findViewById(R.id.fab_veg);
       img_food = (CircleImageView)InputFragmentView.findViewById(R.id.img_food);
        senSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        fab.setOnClickListener(this);

        //dropdown
        tv_fruit = (TextView) InputFragmentView.findViewById(R.id.tv_fruit);
        spinner1 = (Spinner) InputFragmentView.findViewById(R.id.spinner1);
        String[] items = new String[]{"Main-course", "Deserts", "Bread"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner1.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        pos = position;
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }


                }
        );


        return InputFragmentView;
    }

    public void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    public void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;


        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    display();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }
        @Override
        public void onAccuracyChanged (Sensor sensor,int accuracy){

        }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


            ViewTarget target=new ViewTarget(R.id.img_food,getActivity());
            // ViewTarget target1=new ViewTarget(R.id.img_food,getActivity());
            RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
            lps.setMargins(margin, margin, margin, margin);
            showShowcaseView = new ShowcaseView.Builder(getActivity())
                    .withMaterialShowcase()
                    .setTarget(target)
                    .setContentTitle("Get Recipe")
                    .setContentText("Click on the image to get the recipe!")
                    .setStyle(R.style.ShowcaseViewStyleTwo)
                    .singleShot(SHOWCASEVIEW_ID)
                    .setShowcaseEventListener(new OnShowcaseEventListener() {
                        @Override
                        public void onShowcaseViewHide(ShowcaseView showcaseView) {

                        }

                        @Override
                        public void onShowcaseViewDidHide(ShowcaseView showcaseView) {

                        }

                        @Override
                        public void onShowcaseViewShow(ShowcaseView showcaseView) {
                            ViewTarget target=new ViewTarget(R.id.fab_veg,getActivity());
                            showShowcaseView = new ShowcaseView.Builder(getActivity())
                                    .withMaterialShowcase()
                                    .setTarget(target)
                                    .setContentTitle("Get a New Dish everytime!")
                                    .setContentText("Shake your phone to get a new dish\n\t\t\t\tOR\nDo it the old way!")
                                    .setStyle(R.style.ShowcaseViewStyleOne)
                                    .singleShot(SHOWCASEVIEW_ID)
                                    .setShowcaseEventListener(new OnShowcaseEventListener() {
                                        @Override
                                        public void onShowcaseViewHide(ShowcaseView showcaseView) {

                                        }

                                        @Override
                                        public void onShowcaseViewDidHide(ShowcaseView showcaseView) {

                                        }

                                        @Override
                                        public void onShowcaseViewShow(ShowcaseView showcaseView) {
                                            done = true;
                                        }
                                    });

                            showShowcaseView.build();
                        }
                    });

            showShowcaseView.build();
        }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab_veg)
            display();
    }
    public void display(){
        FoodInfo foodsn3 = new FoodInfo(getActivity());
        foodsn3.open();
        foodItemContainer = foodsn3.fetchFood(pos,"Veg");
        foodsn3.close();

        int prev_randm_fruit = randm_fruit;
        randm_fruit = r.nextInt(foodItemContainer.size());

        while (prev_randm_fruit == randm_fruit) {
            randm_fruit = r.nextInt(foodItemContainer.size());

            Log.d("dont display mee!!!", randm_fruit + "");
        }
        //prev_randm_fruit = randm_fruit;
        Log.d("chirkut size 2", randm_fruit + "");

        tv_fruit.setText(" " + foodItemContainer.get(randm_fruit).name);
        img_food.setImageResource(foodItemContainer.get(randm_fruit).image);
        img_food.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            Intent intent = new Intent(getActivity(), DisplayRecipeActivity.class);
                                            intent.putExtra("ID", foodItemContainer.get(randm_fruit).id);
                                            startActivity(intent);
                                        }
                                    }
        );
    }
}
