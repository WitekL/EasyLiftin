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
    final Spinner spinner = (Spinner) findViewById(R.id.chooseTraining);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DatabaseHelper(this, null, null, 1);
        final EditText exerName = (EditText) findViewById(R.id.exName);
        final EditText numSeries = (EditText) findViewById(R.id.numSeries);
        final EditText iniWeight = (EditText) findViewById(R.id.iniWeight);


        //TODO handle the spinner (populate it with trainings to bind the exercises to trainings)


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exerciseName = exerName.getText().toString();
                int numberOfSeries = Integer.parseInt(numSeries.getText().toString());
                float initialWeight = Integer.parseInt(iniWeight.getText().toString());

                loadSpinnerData();

                Exercises exercise = new Exercises(exerciseName, numberOfSeries, initialWeight);
                dbHelper.addExercise(exercise);



                Snackbar.make(view, "Exercise added!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    private void loadSpinnerData() {
        //TODO create a method to load data from db to the spinner
        List<String> trainings = dbHelper.getTrainings();

        ArrayAdapter<String> trainingsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, trainings);
        trainingsAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(trainingsAdapter);
    }

}
