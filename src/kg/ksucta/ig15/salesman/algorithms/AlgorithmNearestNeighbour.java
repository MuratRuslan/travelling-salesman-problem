package kg.ksucta.ig15.salesman.algorithms;

import kg.ksucta.ig15.salesman.model.Punkt;
import kg.ksucta.ig15.salesman.model.Weg;

import java.util.*;

public class AlgorithmNearestNeighbour implements AlgorithmShortestPath{

    private List<Punkt> punkts;



    public AlgorithmNearestNeighbour(List<Punkt> punkts) {
        this.punkts = new ArrayList<>(punkts);
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
        List<Punkt> result = new ArrayList<>();
        result.add(punkts.get(0));
        remove(punkts.get(0));
        try{
            Punkt punkt = punkts.get(0);
            while (!result.get(0).getEdges().isEmpty()) {
                Punkt minEdge = punkt.findMinEdge();
                result.add(minEdge);
                remove(minEdge);
                punkt = minEdge;
            }
        } catch (NoSuchElementException e) {
            return new Weg(result);
        }


        return new Weg(result);
    }


    private void remove(Punkt a) {
        for(Punkt punkt : punkts) {
            punkt.getEdges().remove(a);
        }
    }
}
