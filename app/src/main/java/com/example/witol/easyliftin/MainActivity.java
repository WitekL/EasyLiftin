package com.example.witol.easyliftin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] menu = {"New exercise", "New training"};
    private ListView drawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO create menu in the appbar and move the add buttons there
        //TODO populate the drawer with trainings
        //TODO All 3 tables created. Populate the junction table



        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, menu));

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = null;
                switch(i) {
                    case 0:
                        intent = new Intent(getApplicationContext(), NewExercise.class);
                        break;

                    case 1:
                        intent = new Intent(getApplicationContext(), NewTraining.class);
                        break;
                }
                startActivity(intent);


            }
        });
    }



}