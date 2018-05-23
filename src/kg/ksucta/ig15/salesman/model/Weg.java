package kg.ksucta.ig15.salesman.model;

import java.util.List;

public class Weg {
    private List<Punkt> weg;

    public Weg(List<Punkt> weg) {
        this.weg = weg;
    }

    public List<Punkt> getWeg() {
        return weg;
    }

    public synchronized double distance() {
        return distance(weg);
    }


    public double distanceFirstAndLast() {
        return distanceFirstAndLast(weg);
    }

    public static double distance(List<Punkt> punkts) {
        double distance = 0.0f;

        for (int i = 1; i < punkts.size(); i++) {

            int a = punkts.get(i).getX() - punkts.get(i - 1).getX();
            int b = punkts.get(i).getY() - punkts.get(i - 1).getY();

            distance += Math.sqrt( a*a + b*b);
        }


        return distance + distanceFirstAndLast(punkts);
    }

    private static double distanceFirstAndLast(List<Punkt> punkts) {
        int n = punkts.size();
        return distanceAandB(punkts.get(0), punkts.get(n -1 ));
    }

    public static double distanceAandB(Punkt A, Punkt B) {
        int a = A.getX() - B.getX();
        int b = A.getY() - B.getY();
        return Math.sqrt( a*a + b*b);
    }
}
