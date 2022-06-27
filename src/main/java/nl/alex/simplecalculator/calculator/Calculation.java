package nl.alex.simplecalculator.calculator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int _id;
    private double value1;
    private String divider;
    private double value2;
    private double result;

    public Calculation() {

    }

    public Calculation(int _id, double value1, String divider, double value2, double result) {
        this._id = _id;
        this.value1 = value1;
        this.divider = divider;
        this.value2 = value2;
        this.result = result;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public String getDivider() {
        return divider;
    }

    public void setDivider(String divider) {
        this.divider = divider;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}

