package com.example.witol.easyliftin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NewExercise extends AppCompatActivity {

    DatabaseHelper dbHelper;
    Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = (Spinner) findViewById(R.id.chooseTraining);
        dbHelper = new DatabaseHelper(this, null, null, 1);
        final EditText exerName = (EditText) findViewById(R.id.exName);
        final EditText numSeries = (EditText) findViewById(R.id.numSeries);
        final EditText iniWeight = (EditText) findViewById(R.id.iniWeight);
        loadSpinnerData();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exerciseName = exerName.getText().toString();
                int numberOfSeries = Integer.parseInt(numSeries.getText().toString());
                float initialWeight = Integer.parseInt(iniWeight.getText().toString());
                String chosenTraining = spinner.getSelectedItem().toString();


                Exercises exercise = new Exercises(exerciseName, numberOfSeries, initialWeight);
                dbHelper.addExercise(exercise);
                dbHelper.junction(chosenTraining, exerciseName);


                Snackbar.make(view, "Exercise added!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    private void loadSpinnerData() {


        List<String> trainings = dbHelper.getTrainings();

        ArrayAdapter<String> trainingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, trainings);
        trainingsAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(trainingsAdapter);


    }

}
