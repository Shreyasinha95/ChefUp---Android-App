package com.shreyathegreat.newshuffle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import com.shreyathegreat.newshuffle.FoodItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shreya on 15/2/16.
 */
public class FoodInfo {

    public static final String DATABASE_NAME = "Food.db";
    public static final int DATABASE_VERSION = 10;

    public static final String INFO_TABLE_NAME = "food_info";
    public static final String RECIPE_TABLE_NAME = "food_recipe";
    public static final String INFO_COLUMN_ID = "fid";

    public static final String INFO_COLUMN_NAME = "fname";
    public static final String INFO_COLUMN_CATEGORY = "category";
    public static final String INFO_COLUMN_REGION = "region";
    public static final String INFO_COLUMN_STATE = "state";
    public static final String INFO_COLUMN_TYPE = "type";
    public static final String INFO_COLUMN_IMAGE = "image";

    public static final String RECIPE_COLUMN_ID = "rid";
    public static final String RECIPE_COLUMN_PREP = "prep_time";
    public static final String RECIPE_COLUMN_COOK = "cooking_time";
    public static final String RECIPE_COLUMN_INGREDIENTS = "ingredients";
    public static final String RECIPE_COLUMN_STEPS = "steps";
    public static final String RECIPE_COLUMN_TIP = "tip";


    private DBHelper ourHelper;
    private final Context ourContext;
    public SQLiteDatabase db;





    public void clearData() {
        db.execSQL("delete from event_details");
    }

    private static class DBHelper extends SQLiteOpenHelper {

        private HashMap hp;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_INFO_TABLE = "CREATE TABLE " + INFO_TABLE_NAME + "("
                    + INFO_COLUMN_ID + " INTEGER PRIMARY KEY, " + INFO_COLUMN_NAME + " TEXT, "
                    + INFO_COLUMN_CATEGORY + " TEXT, " + INFO_COLUMN_REGION + " TEXT, "
                    + INFO_COLUMN_TYPE + " TEXT, "
                    + INFO_COLUMN_IMAGE + " INTEGER" + ");";
            db.execSQL(CREATE_INFO_TABLE);

            String CREATE_RECIPE_TABLE = "CREATE TABLE " + RECIPE_TABLE_NAME + "("
                    + RECIPE_COLUMN_ID + " INTEGER," + RECIPE_COLUMN_PREP + " TEXT, "
                    + RECIPE_COLUMN_COOK + " TEXT, " + RECIPE_COLUMN_INGREDIENTS + " TEXT, "
                    + RECIPE_COLUMN_STEPS + " TEXT, " + RECIPE_COLUMN_TIP + " TEXT, "
                    + "FOREIGN KEY(" + RECIPE_COLUMN_ID + ") REFERENCES "
                    + INFO_TABLE_NAME + "(" + INFO_COLUMN_ID + ")" + ");";
            db.execSQL(CREATE_RECIPE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS food_info");
            db.execSQL("DROP TABLE IF EXISTS food_recipe");
            onCreate(db);
        }
    }

    public FoodInfo(Context c) {
        ourContext = c;

    }

    public FoodInfo open() throws SQLException {
        ourHelper = new DBHelper(ourContext);
        db = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public ArrayList<FoodItem> getFoodItems() {

        ArrayList<FoodItem> result = new ArrayList();
        result.clear();
        int id, image;
        String name, category, region, type;
        Cursor mCursor = db.rawQuery("SELECT *  FROM "
                + INFO_TABLE_NAME , null);

        if (mCursor.moveToFirst()) {

            do {
                id = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_ID));
                name = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_NAME));
                category = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_CATEGORY));
                region = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_REGION));
                type = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_TYPE));
                image = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_IMAGE));
                result.add(new FoodItem(id,name,category,region,type,image));
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        return result;
    }
    public RecipeDetails getRecipeForFoodItem(int foodID) {
        RecipeDetails recipeDetails = null;
        int id, image;
        String name, category, region, type,prep_time,cook_time,ingredients,steps,tip;
        Cursor mCursor = db.rawQuery("SELECT *  FROM "
                + INFO_TABLE_NAME + ", "+RECIPE_TABLE_NAME+ " where " + INFO_COLUMN_ID + " = "
                + RECIPE_COLUMN_ID + " AND " + RECIPE_COLUMN_ID + " = " + foodID , null);
        if(mCursor.moveToFirst()) {
            id = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_ID));
            name = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_NAME));
            category = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_CATEGORY));
            region = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_REGION));
            type = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_TYPE));
            image = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_IMAGE));
            prep_time = mCursor.getString(mCursor.getColumnIndex(RECIPE_COLUMN_PREP));
            cook_time = mCursor.getString(mCursor.getColumnIndex(RECIPE_COLUMN_COOK));
            ingredients = mCursor.getString(mCursor.getColumnIndex(RECIPE_COLUMN_INGREDIENTS));
            steps = mCursor.getString(mCursor.getColumnIndex(RECIPE_COLUMN_STEPS));
            tip = mCursor.getString(mCursor.getColumnIndex(RECIPE_COLUMN_TIP));

            FoodItem temp = new FoodItem(id, name, category, region, type, image);
            recipeDetails = new RecipeDetails(id,prep_time,cook_time,ingredients,steps,tip,temp);
        }
        return recipeDetails;

    }



    public void createFoodItem(int id,String name, String category, String region, String type, int image){
        ContentValues a_info = new ContentValues();
        a_info.put(INFO_COLUMN_ID, id);
        a_info.put(INFO_COLUMN_NAME, name);
        a_info.put(INFO_COLUMN_CATEGORY, category);
        a_info.put(INFO_COLUMN_REGION, region);
        a_info.put(INFO_COLUMN_TYPE, type);
        a_info.put(INFO_COLUMN_IMAGE, image);
        db.insert(INFO_TABLE_NAME, null, a_info);
        //Toast.makeText(ourContext, a_info.get("fname") + "",Toast.LENGTH_SHORT).show();
    }

    public void createRecipe(int foodID, String prepTime, String cookTime, String ingredients, String steps, String tip){
        ContentValues a_recipe = new ContentValues();
        a_recipe.put(RECIPE_COLUMN_ID, foodID);
        a_recipe.put(RECIPE_COLUMN_PREP, prepTime);
        a_recipe.put(RECIPE_COLUMN_COOK, cookTime);
        a_recipe.put(RECIPE_COLUMN_INGREDIENTS, ingredients);
        a_recipe.put(RECIPE_COLUMN_STEPS, steps);
        a_recipe.put(RECIPE_COLUMN_TIP, tip);
        db.insert(RECIPE_TABLE_NAME, null, a_recipe);
    }

    public ArrayList<FoodItem> fetchFood(int pos, String catg) {
        ArrayList<FoodItem> food = new ArrayList<>();
        int id, image;
        String name, category, region, type;
        Cursor mCursor;
        if (pos == 0) {
            mCursor = db.rawQuery("SELECT *  FROM "
                    + INFO_TABLE_NAME + " where " + INFO_COLUMN_TYPE + " = 'Main Course' AND " + INFO_COLUMN_CATEGORY + " = '" + catg + "'", null);
            if (mCursor.moveToFirst()) {

                do {
                    id = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_ID));
                    name = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_NAME));
                    category = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_CATEGORY));
                    region = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_REGION));
                    type = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_TYPE));
                    image = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_IMAGE));
                    food.add(new FoodItem(id,name,category,region,type,image));
                } while (mCursor.moveToNext());
            }
            mCursor.close();
        }
        else if (pos == 1) {
            mCursor = db.rawQuery("SELECT *  FROM "
                    + INFO_TABLE_NAME + " where " + INFO_COLUMN_TYPE + " = 'Deserts' AND " + INFO_COLUMN_CATEGORY + " = '" + catg + "'", null);if (mCursor.moveToFirst()) {

                do {
                    id = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_ID));
                    name = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_NAME));
                    category = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_CATEGORY));
                    region = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_REGION));
                    type = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_TYPE));
                    image = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_IMAGE));
                    food.add(new FoodItem(id,name,category,region,type,image));
                } while (mCursor.moveToNext());
            }
            mCursor.close();
        }
        else if (pos == 2) {
            String bread = "Select * from food_info where type LIKE " + "'%Bread%' AND " + INFO_COLUMN_CATEGORY + " = '" + catg + "'";

            mCursor = db.rawQuery(bread, null);
            if (mCursor.moveToFirst()) {

                do {
                    id = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_ID));
                    name = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_NAME));
                    category = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_CATEGORY));
                    region = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_REGION));
                    type = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_TYPE));
                    image = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_IMAGE));
                    food.add(new FoodItem(id,name,category,region,type,image));
                } while (mCursor.moveToNext());
            }
            mCursor.close();
        }
        else
            return food;

            return food;

    }
    public ArrayList<FoodItem> fetchRegionFood(String reg) {
        ArrayList<FoodItem> food = new ArrayList();
        int id, image;
        String name, category, region, type;
        String select;
        select = "Select * from food_info where region LIKE " + "'" + reg +"'";

        Cursor mCursor = db.rawQuery(select, null);
            if (mCursor.moveToFirst()) {

                do {
                    id = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_ID));
                    name = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_NAME));
                    category = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_CATEGORY));
                    region = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_REGION));
                    type = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_TYPE));
                    image = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_IMAGE));
                    food.add(new FoodItem(id,name,category,region,type,image));
                } while (mCursor.moveToNext());
            }
            mCursor.close();
            return food;

    }
    public ArrayList<FoodItem> fetchCategoryFood(String reg,String catg) {
        ArrayList<FoodItem> food = new ArrayList();
        int id, image;
        String name, category, region, type;
        String select;

        select = "Select * from food_info where region = " + "'"+reg+"' AND " + " category = " + "'"+ catg +"'";
        Cursor mCursor = db.rawQuery(select, null);
        if (mCursor.moveToFirst()) {

            do {
                id = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_ID));
                name = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_NAME));
                category = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_CATEGORY));
                region = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_REGION));
                type = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_TYPE));
                image = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_IMAGE));
                food.add(new FoodItem(id,name,category,region,type,image));
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        return food;

    }

public ArrayList<FoodResult> searchfood() {
    ArrayList<FoodResult> food = new ArrayList();
    int id;
    String name;
    String select;

    select = "Select fid,fname from food_info";
    Cursor mCursor = db.rawQuery(select, null);
    if (mCursor.moveToFirst()) {

        do {
            id = mCursor.getInt(mCursor.getColumnIndex(INFO_COLUMN_ID));
            name = mCursor.getString(mCursor.getColumnIndex(INFO_COLUMN_NAME));
            food.add(new FoodResult(name,id));
        } while (mCursor.moveToNext());
    }
    mCursor.close();
    return food;

}
}