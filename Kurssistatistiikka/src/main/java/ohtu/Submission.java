package ohtu;

import java.util.ArrayList;

public class Submission {
    private int week;
    private int hours;
    private ArrayList<Integer> exercises;
    private String course;
    
    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }
    
    public void setExcercises(ArrayList exercises) {
        this.exercises = exercises;
    }

    public ArrayList getExercises() {
        return exercises;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return course + ", viikko " + week + " tehtyjä tehtäviä yhteensä " + exercises.size() + " aikaa kului " + hours +
                " tehdyt tehtävät: " + exercises.toString();
    }
    
}