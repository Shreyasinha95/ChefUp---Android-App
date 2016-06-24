package com.shreyathegreat.newshuffle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shreya on 16/3/16.
 */
public class MyParallaxAdapter extends RecyclerView.Adapter<MyParallaxAdapter.ViewHolder> {
    private RecipeDetails recipeDetails;

    public MyParallaxAdapter(RecipeDetails recipeDetails){
        this.recipeDetails = recipeDetails;
    }
    @Override
    public MyParallaxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parallax_card, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.ingr_entry.setText(recipeDetails.ingredients);
        viewHolder.ptime_entry.setText(recipeDetails.prep_time);
        viewHolder.ctime_entry.setText(recipeDetails.cook_time);
        viewHolder.steps_entry.setText(recipeDetails.steps + "\n\n\n");
        //viewHolder.im.setImageResource(recipeDetails.foodItem.image);


    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ptime_entry;
        public TextView ctime_entry;
        public TextView ingr_entry;
        public TextView steps_entry;
        public ImageView im;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            //im = (ImageView) itemLayoutView.findViewById(R.id.im);
            ptime_entry = (TextView)itemLayoutView.findViewById(R.id.ptime_entry);
            ctime_entry = (TextView)itemLayoutView.findViewById(R.id.ctime_entry);
            ingr_entry = (TextView)itemLayoutView.findViewById(R.id.ingr_entry);
            steps_entry = (TextView)itemLayoutView.findViewById(R.id.steps_entry);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 1;
    }
}
