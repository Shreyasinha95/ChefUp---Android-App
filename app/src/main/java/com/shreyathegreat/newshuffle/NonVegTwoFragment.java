package com.shreyathegreat.newshuffle;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.hardware.SensorEventListener;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NonVegTwoFragment extends Fragment implements SensorEventListener, View.OnClickListener {
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 700;
    public int pos = 0;

    FloatingActionButton fab;
    Spinner spinner2;
    int randm_fruit;
    Random r = new Random();
    TextView tv_fruit;
    CircleImageView img_food;

    ArrayList<FoodItem> foodItemContainer = new ArrayList<FoodItem>();

    public NonVegTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View Input2FragmentView = inflater.inflate(R.layout.fragment_non_veg, container, false);
        fab = (FloatingActionButton) Input2FragmentView.findViewById(R.id.fab_nveg);
        fab.setOnClickListener(this);
        img_food = (CircleImageView) Input2FragmentView.findViewById(R.id.img_food);
        senSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);


        //dropdown
        tv_fruit = (TextView) Input2FragmentView.findViewById(R.id.tv_fruit);
        spinner2 = (Spinner) Input2FragmentView.findViewById(R.id.spinner2);
        String[] items = new String[]{"Main-course", "Desserts", "Breads"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner2.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        pos = position;
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );
        return Input2FragmentView;
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

        // if != -1
        // if p = 0 1 2

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
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_nveg) {
            display();
        }
    }

    public void display() {
        FoodInfo foods1 = new FoodInfo(getActivity());
        foods1.open();
        foodItemContainer = foods1.fetchFood(pos, "Non-Veg");
        int size = foodItemContainer.size();
        foods1.close();
        int prev_randm_fruit = -1;
        randm_fruit = r.nextInt(size);

        while (prev_randm_fruit == randm_fruit) {
            randm_fruit = r.nextInt(size);
            prev_randm_fruit = randm_fruit;
        }
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
