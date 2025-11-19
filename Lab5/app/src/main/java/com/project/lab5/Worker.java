package com.project.lab5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workers")
public class Worker {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "lastname")
    private String lastName;
    @ColumnInfo(name = "paycheck")
    private double paycheck;

    public Worker(String name, String lastName, double paycheck) {
        this.name = name;
        this.lastName = lastName;
        this.paycheck = paycheck;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getPaycheck() {
        return paycheck;
    }

    public void setPaycheck(double paycheck) {
        this.paycheck = paycheck;
    }

}
