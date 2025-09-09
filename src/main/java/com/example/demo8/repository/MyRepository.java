package com.example.demo8.repository;

import com.example.demo8.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MyRepository extends MongoRepository<Product,String> {


}
