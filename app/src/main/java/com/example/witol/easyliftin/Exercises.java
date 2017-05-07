package com.example.witol.easyliftin;

/**
 * Created by Witek on 07.05.2017.
 */

public class Exercises {

    private int _id;
    private String _exercisename;
    private String _sets;
    private String _weight;

    public Exercises(int _id, String _exercisename, String _sets, String _weight) {
        this._id = _id;
        this._exercisename = _exercisename;
        this._sets = _sets;
        this._weight = _weight;
    }

    public int get_id() {
        return _id;
    }

    public String get_exercisename() {
        return _exercisename;
    }

    public String get_sets() {
        return _sets;
    }

    public String get_weight() {
        return _weight;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_exercisename(String _exercisename) {
        this._exercisename = _exercisename;
    }

    public void set_sets(String _sets) {
        this._sets = _sets;
    }

    public void set_weight(String _weight) {
        this._weight = _weight;
    }
}
