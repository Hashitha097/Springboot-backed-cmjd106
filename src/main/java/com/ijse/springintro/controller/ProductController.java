package com.ijse.springintro.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springintro.dto.ProductReqDTO;
import com.ijse.springintro.entity.Product;
import com.ijse.springintro.service.CategoryService;
import com.ijse.springintro.service.ProductService;
import com.ijse.springintro.entity.Category;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        return ResponseEntity.status(200).body(products);
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductReqDTO productReqDTO) {
        Product product = new Product();
        product.setName(productReqDTO.getName());
        product.setPrice(productReqDTO.getPrice());
        product.setDescription(productReqDTO.getDescription());

       //Category category = categoryService.getCategoryById(productReqDTO.getCategoryId());

       //Product.setCategory(category);

        Product createProduct = productService.createProduct(product);

        return ResponseEntity.status(201).body(createProduct);
    }


    @PutMapping("/product/$(productId)")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody ProductReqDTO productReqDTO) {
        Product product = new Product();
        product.setName(productReqDTO.getName());
        product.setPrice(productReqDTO.getPrice());
        product.setDescription(productReqDTO.getDescription());

       //find category by categoryId in productReqDTO and assign it to new Product.
       Category category = categoryService.getCategoryById(productReqDTO.getCategoryId());
       product.setCategory(category);

        Product updateProduct = productService.updateProduct(productId, product);


          try {
            productService.updateProduct(productId, product);
        } catch (Exception error) {
            System.out.println(error);
        }


        return ResponseEntity.status(200).body(updateProduct);

    }

     @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.status(200).body("Product Deleted");
    }

}
