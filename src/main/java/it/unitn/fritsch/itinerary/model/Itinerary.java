package it.unitn.fritsch.itinerary.model;

import java.util.ArrayList;

public class Itinerary {
    int dist;
    int time;
    ArrayList<Position> position;

    public Itinerary(){
        dist = 0;
        time = 0;
        position = new ArrayList<>();
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<Position> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Position> position) {
        this.position = position;
    }


}

