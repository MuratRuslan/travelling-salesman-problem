package kg.ksucta.ig15.salesman.model;

import kg.ksucta.ig15.salesman.algorithms.AlgorithmNearestNeighbour;

import java.util.*;

public class Punkt implements Comparable<Punkt> {

    private int x;
    private int y;

    private Map<Punkt, Double> edges = new HashMap<>();

    public Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Map<Punkt, Double> getEdges() {
        return edges;
    }

    public int[] toArray() {
        return new int[] {x , y};
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }

    @Override
    public int compareTo(Punkt other) {
        if(y < AlgorithmNearestNeighbour.Y_MIN) {
            AlgorithmNearestNeighbour.Y_MIN = y;
        } else if (y > AlgorithmNearestNeighbour.Y_MAX){
            AlgorithmNearestNeighbour.Y_MAX = y;
        }
        if((x == other.getX() && y > other.y) || x > other.x) {
            return 1;
        }
        if(x == other.getX() && y == other.y){
            return 0;
        }
        return -1;
    }

    public Punkt findMinEdge() throws NoSuchElementException {
        Punkt min = edges.keySet().iterator().next();
        for(Punkt punkt : edges.keySet()) {
            if(edges.get(punkt) < edges.get(min)) {
                min = punkt;
            }
        }
        return min;
    }
}
