package kg.ksucta.ig15.salesman.algorithms;

import kg.ksucta.ig15.salesman.model.Punkt;
import kg.ksucta.ig15.salesman.model.Weg;

import java.util.*;

public class AlgorithmCardiogram implements AlgorithmShortestPath {
    private List<Punkt> punkts;
    public static int Y_MIN;
    public static int Y_MAX;



    public AlgorithmCardiogram(List<Punkt> punkts) {
        this.punkts = new ArrayList<>(punkts);
        init();
    }

    private void init() {
        Y_MIN = punkts.get(0).getY();
        Y_MAX = punkts.get(0).getY();

        for(Punkt punkt : punkts) {
            if(punkt.getY() < Y_MIN) {
                Y_MIN = punkt.getY();
            } else if(punkt.getY() > Y_MAX) {
                Y_MAX = punkt.getY();
            }
        }
    }


    @Override
    public Weg getShortestPath() {
        Collections.sort(punkts);

        List<Punkt> result = new ArrayList<>();
        Stack<Punkt> stack = new Stack<>();

        int middle = (int)Math.ceil((Y_MAX - Y_MIN) / 2.0f);

        for(Punkt punkt : punkts) {
                if(punkt.getY() <= middle) {
                    result.add(punkt);
                } else {
                    stack.push(punkt);
                }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return new Weg(result);
    }


}
