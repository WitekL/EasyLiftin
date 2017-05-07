package com.example.witol.easyliftin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Witek on 07.05.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "liftin.db";
    public static final String TABLE_EXERCISES = "Exercises";
    public static final String TABLE_TRAININGS = "Trainings";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EXERCISENAME = "Exercise name";
    public static final String COLUMN_SETS = "No. of sets";
    public static final String COLUMN_WEIGHT = "Weight";
    public static final String COLUMN_TRAININGNAME = "Training name";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTrainings = "CREATE TABLE " + TABLE_TRAININGS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_TRAININGNAME + " TEXT " + ");";

        String createExercises = "CREATE TABLE " + TABLE_EXERCISES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_EXERCISENAME + " TEXT " +
                COLUMN_SETS + " INTEGER " +
                COLUMN_WEIGHT + " REAL " + ");";

        sqLiteDatabase.execSQL(createTrainings);
        sqLiteDatabase.execSQL(createExercises);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addTraining(Trainings training){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRAININGNAME, training.get_trainingname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TRAININGS, null, values);
        db.close();
    }

    public void addExercise(Exercises exercise){
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXERCISENAME, exercise.get_exercisename());
        values.put(COLUMN_SETS, exercise.get_sets());
        values.put(COLUMN_WEIGHT, exercise.get_weight());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EXERCISES, null, values);
        db.close();
    }
}
