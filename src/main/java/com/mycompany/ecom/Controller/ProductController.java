package com.mycompany.ecom.Controller;

import com.mycompany.ecom.Dto.ProductDto;
import com.mycompany.ecom.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("Create Product API Called");
        ProductDto response = productService.createProduct(productDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        log.info("Get Product By Id API Called");
        ProductDto response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("Get All Products API Called");
        List<ProductDto> response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        log.info("Update Product API Called");
        ProductDto response = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        log.info("Delete Product API Called");
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product Deleted Successfully");
    }
}