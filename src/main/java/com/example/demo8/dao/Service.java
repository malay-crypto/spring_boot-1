package com.example.demo8.dao;


import com.example.demo8.model.Product;
import com.example.demo8.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Service {


    @Autowired
    MyRepository repo;

    public List<Product> show(){
        return repo.findAll();
    }

    public Product add(Product p){
        return repo.save(p);

    }

    public void delete(String id){
        repo.deleteById(id);
    }
    public void edit(Product p){
        repo.save(p);
    }


}
