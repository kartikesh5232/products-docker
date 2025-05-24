
package com.example.productService.controller;

import com.example.productService.entity.product;
import com.example.productService.entity.productUploadResponse;

import com.example.productService.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class productController {

    @Autowired
    private productService prodService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
                                         @RequestParam("description") String description, @RequestParam("price") int price,
                                         @RequestParam("category") String category
    ) throws IOException {


        productUploadResponse response = prodService.uploadProduct(file,title, description, price, category);


        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/test")
    public String test() {
        return "test product";

    }


    @GetMapping("/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable("name") String name){
        byte[] image = prodService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

    @GetMapping("/getproduct/{id}")
    public ResponseEntity<?> getImageInfoByName(@PathVariable("id") Long id) {
        System.out.println("getImageInfoByName");
      product prod = prodService.getProducts( id);
//        byte[] image = products.get(1).getImageData();
//        return ResponseEntity.status(HttpStatus.OK)
//              .contentType(MediaType.valueOf("image/png"))
//                .body(image);

        return ResponseEntity.status(HttpStatus.OK).body(prod);

    }

    @GetMapping("/getallproduct")
    public ResponseEntity<?> getImageInfoByName() {
        List<product> products = prodService.getAllProducts();
//        byte[] image = products.get(1).getImageData();
//        return ResponseEntity.status(HttpStatus.OK)
//              .contentType(MediaType.valueOf("image/png"))
//                .body(image);

      return ResponseEntity.status(HttpStatus.OK).body(products);

    }

}