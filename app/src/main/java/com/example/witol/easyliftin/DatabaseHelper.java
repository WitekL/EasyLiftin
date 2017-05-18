package com.example.witol.easyliftin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Witek on 07.05.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "liftin.db";
    public static final String TABLE_EXERCISES = "Exercises";
    public static final String TABLE_TRAININGS = "Trainings";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EXERCISENAME = "Exercise_name";
    public static final String COLUMN_SETS = "No_of_sets";
    public static final String COLUMN_WEIGHT = "Weight";
    public static final String COLUMN_TRAININGNAME = "Training_name";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTrainings = "CREATE TABLE " + TABLE_TRAININGS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_TRAININGNAME + " TEXT " + ");";

        String createExercises = "CREATE TABLE " + TABLE_EXERCISES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_EXERCISENAME + " TEXT ," +
                COLUMN_SETS + " INTEGER ," +
                COLUMN_WEIGHT + " REAL " + ");";

        String createJunctionTable = "CREATE TABLE junction" +
                " (training_id INTEGER," +
                " exercise_id INTEGER," +
                " PRIMARY KEY (training_id, exercise_id)," +
                " FOREIGN KEY (training_id) REFERENCES Trainings(_id)," +
                " FOREIGN KEY (exercise_id) REFERENCES Exercises(_id));";

        sqLiteDatabase.execSQL(createTrainings);
        sqLiteDatabase.execSQL(createExercises);
        sqLiteDatabase.execSQL(createJunctionTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public void addTraining(Trainings training) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRAININGNAME, training.get_trainingname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TRAININGS, null, values);
        db.close();
    }

    public void addExercise(Exercises exercise) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXERCISENAME, exercise.get_exercisename());
        values.put(COLUMN_SETS, exercise.get_sets());
        values.put(COLUMN_WEIGHT, exercise.get_weight());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EXERCISES, null, values);
        db.close();
    }

    public void junction(String trainingName, String exerciseName){
        int trainingId = 0;
        int exerciseId = 0;

        String trainingQuery = "SELECT _id FROM Trainings WHERE Training_name = '" + trainingName +"';";
        String exerciseQuery = "SELECT _id FROM Exercises WHERE Exercise_name= '" + exerciseName +"';";

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursorTraining = db.rawQuery(trainingQuery, null);
        Cursor cursorExercise = db.rawQuery(exerciseQuery, null);
        if(cursorTraining.moveToFirst()) {
            trainingId = cursorTraining.getInt(0);
        }

        if(cursorExercise.moveToFirst()){
            exerciseId = cursorExercise.getInt(0);
        }

        //String junctQuery = "INSERT INTO junction (training_id, exercise_id) VALUES (" + trainingId + ", " + exerciseId + ");";
        ContentValues values = new ContentValues();
        values.put("training_id", trainingId);
        values.put("exercise_id", exerciseId);
        db.insert("junction", null, values);


    }

    public List<String> getTrainings() {
        List<String> trainings = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_TRAININGNAME + " FROM " + TABLE_TRAININGS;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                trainings.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return trainings;
    }



}
// TODO deleting from the db


