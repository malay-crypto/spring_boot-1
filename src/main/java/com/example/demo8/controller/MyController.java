package com.example.demo8.controller;


import com.example.demo8.dao.Service;
import com.example.demo8.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = "https://react-spring-1.onrender.com")
//https://react-spring-1.onrender.com
public class MyController {

    @Autowired
    Service sc;

    @GetMapping("/")
    public List<Product> show(){

        return sc.show();
    }

    @PostMapping(value = "/add")
    public Product add( @ModelAttribute Product p) throws IOException {
        String upload_folder="upload/";
        System.out.println("p= "+p);

        String n=p.getName();
        String c=p.getCategory();
        int pr=p.getPrice();
        String imageName=p.getImage().getOriginalFilename();
        System.out.println("image name "+imageName);

        Product p1=new Product();
        p1.setName(n);
        p1.setCategory(c);
        p1.setPrice(pr);
        p1.setImageName(imageName);


        Path ph= Paths.get(upload_folder);
        System.out.println("path="+ph);
        if(!Files.exists(ph)){
            Files.createDirectory(ph);

        }
        MultipartFile file=p.getImage();
        Path destination=Paths.get(upload_folder+imageName);

        System.out.println("destination : "+destination);
        file.transferTo(destination);


        return sc.add(p1);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
       sc.delete(id);
    }
    @PutMapping(value = "/edit",consumes = "multipart/form-data")
    public void edit( @ModelAttribute Product p) throws IOException {

        String upload_folder="upload/";
        System.out.println("p= "+p);

        String n=p.getName();
        String c=p.getCategory();
        int pr=p.getPrice();
        String imageName=p.getImage().getOriginalFilename();
        System.out.println("image name "+imageName);

        Product p1=new Product();
        p1.setId(p.getId());
        p1.setName(n);
        p1.setCategory(c);
        p1.setPrice(pr);
        p1.setImageName(imageName);


        Path ph= Paths.get(upload_folder);
        System.out.println("path="+ph);
        if(!Files.exists(ph)){
            Files.createDirectory(ph);

        }
        MultipartFile file=p.getImage();
        Path destination=Paths.get(upload_folder+imageName);

        System.out.println("destination : "+destination);
        file.transferTo(destination);



        sc.edit(p1);
    }


}
