package kg.ksucta.ig15.salesman;

import kg.ksucta.ig15.salesman.algorithms.AlgorithmCardiogram;
import kg.ksucta.ig15.salesman.algorithms.AlgorithmNearestNeighbour;
import kg.ksucta.ig15.salesman.algorithms.AlgorithmShortestPath;
import kg.ksucta.ig15.salesman.dao.PunktRepositoryCsv;
import kg.ksucta.ig15.salesman.dao.Repository;
import kg.ksucta.ig15.salesman.model.Punkt;
import kg.ksucta.ig15.salesman.model.Weg;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String fileName;

        System.out.println("Willkommen!");
        System.out.println("Mit diesem Programm konnen Sie 'Travelling Salesman Problem' losen.");
        System.out.println("Implementierung ist 'Greedy' Algorithmus");

        System.out.println("-------------------------------------------");
        System.out.println();
        System.out.println();


        System.out.println("Bitte geben Sie Datei Path an: ");
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();

        Repository punktRepository = new PunktRepositoryCsv(fileName);
        List<Punkt> punkts = punktRepository.findAll();
        System.out.println("Pfadlange: " + Weg.distance(punkts));

        AlgorithmShortestPath algorithm = new AlgorithmNearestNeighbour(punkts);
        Weg shortestPath = algorithm.getShortestPath();
        shortestPath.getWeg().forEach(System.out::println);
        System.out.println("Pfadlange: " + shortestPath.distance());

        punktRepository.save(shortestPath.getWeg());
        System.out.println("------------------------");
    }
}
