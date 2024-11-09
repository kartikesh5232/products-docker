package com.example.productService.service;

import com.example.productService.entity.product;
import com.example.productService.entity.productUploadResponse;
import com.example.productService.repository.productRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class productService {

    @Autowired
    private productRepo productRepository;


    public productUploadResponse uploadProduct(MultipartFile file, String title,
                                               String description, int price, String category) throws IOException {

        productRepository.save(product.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .title(title)
                .description(description)
                .price(price)
                .category(category)
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return new productUploadResponse("Image uploaded successfully:");

    }


    @Transactional
    public List<product> getAllProducts() {
        List<product> products = productRepository.findAll();

        return products.stream()
                .map(prod -> {
                    product newProd = new product();
                    newProd.setId(prod.getId());
                    newProd.setTitle(prod.getTitle());
                    newProd.setName(prod.getName());
                    newProd.setType(prod.getType());
                    newProd.setDescription(prod.getDescription());
                    newProd.setPrice(prod.getPrice());
                    newProd.setCategory(prod.getCategory());
                    newProd.setImageData(ImageUtil.decompressImage(prod.getImageData()));
                    return newProd;
                })
                .collect(Collectors.toList());


    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<product> dbImage = productRepository.findByName(name);

        if (dbImage.isEmpty()) {
            throw new RuntimeException("Product with name " + name + " not found");
        }

        return ImageUtil.decompressImage(dbImage.get().getImageData());
    }

}


