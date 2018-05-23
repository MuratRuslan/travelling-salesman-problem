package kg.ksucta.ig15.salesman.dao;


import java.util.List;

public interface Repository<T> {

    List<T> findAll();

    void save(List<T> objects);
}
