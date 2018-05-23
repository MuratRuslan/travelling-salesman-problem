package kg.ksucta.ig15.salesman.algorithms;

import kg.ksucta.ig15.salesman.model.Punkt;
import kg.ksucta.ig15.salesman.model.Weg;

import java.util.*;

public class AlgorithmNearestNeighbour implements AlgorithmShortestPath{

    private List<Punkt> punkts;
    public static int Y_MIN;
    public static int Y_MAX;



    public AlgorithmNearestNeighbour(List<Punkt> punkts) {
        this.punkts = new ArrayList<>(punkts);
        Y_MIN = punkts.get(0).getY();
        Y_MAX = punkts.get(0).getY();
        initAdjacencyMatrix();
    }

    void initAdjacencyMatrix() {
        for(Punkt current: punkts) {
            for(Punkt other: punkts) {
                if(current != other) {
                    double distance = Weg.distanceAandB(current, other);
                    current.getEdges().put(other, distance);
                }
            }
        }
    }



    @Override
    public Weg getShortestPath() {
        Collections.sort(punkts);

        List<Punkt> down = new ArrayList<>();
        Stack<Punkt> stack = new Stack<>();
        down.add(punkts.get(0));
        remove(punkts.get(0));
        try{
            Punkt punkt = punkts.get(0);
            while (!down.get(0).getEdges().isEmpty()) {
                Punkt minEdge = punkt.findMinEdge();
                down.add(minEdge);
                remove(minEdge);
                punkt = minEdge;
            }
        } catch (NoSuchElementException e) {
            return new Weg(down);
        }


        while (!stack.empty()) {
            down.add(stack.pop());
        }

        return new Weg(down);
    }


    private void remove(Punkt a) {
        for(Punkt punkt : punkts) {
            punkt.getEdges().remove(a);
        }
    }
}
