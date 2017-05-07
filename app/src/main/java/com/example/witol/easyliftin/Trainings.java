package com.example.witol.easyliftin;

/**
 * Created by Witek on 08.05.2017.
 */

public class Trainings {
    private int _id;
    private String _trainingname;


    public Trainings(int _id, String _trainingname) {
        this._id = _id;
        this._trainingname = _trainingname;
    }

    public int get_id() {
        return _id;
    }

    public String get_trainingname() {
        return _trainingname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_trainingname(String _trainingname) {
        this._trainingname = _trainingname;
    }
}
