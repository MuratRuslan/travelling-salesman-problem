package kg.ksucta.ig15.salesman.dao;

import kg.ksucta.ig15.salesman.model.Punkt;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PunktRepositoryCsv implements Repository<Punkt> {

    private final String fileName;

    public PunktRepositoryCsv(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Punkt> findAll() {
        List<Punkt> list = new LinkedList<>();

        BufferedReader br;

        try {

            br = new BufferedReader(new FileReader(fileName));

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] next = sCurrentLine.split(";");
                list.add(new Punkt(Integer.parseInt(next[0]), Integer.parseInt(next[1])));
            }
            return list;

        } catch (IOException e) {
            System.out.println("file not found!");
        }

        return null;
    }


    @Override
    public void save(List<Punkt> punkte) {
        List<String> outList = punkte.stream().map( p -> p.toString()).collect(Collectors.toList());

        Path file = Paths.get("out_"+fileName);
        try {
            Files.write(file, outList, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
