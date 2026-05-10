package com.mycompany.ecom.Service.Impl;

import com.mycompany.ecom.Dto.ProductDto;
import com.mycompany.ecom.Entity.ProductEntity;
import com.mycompany.ecom.Exception.ProductAlreadyExistsException;
import com.mycompany.ecom.Exception.ProductNotFoundException;
import com.mycompany.ecom.Repository.ProductRepository;
import com.mycompany.ecom.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.info("Create Product Request Started");
        if (productRepository.existsByName(productDto.getName())) {
            log.error("Product Already Exists");
            throw new ProductAlreadyExistsException("Product Already Exists");
        }
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(productDto, entity);
        ProductEntity savedEntity = productRepository.save(entity);
        ProductDto responseDto = new ProductDto();
        BeanUtils.copyProperties(savedEntity, responseDto);
        log.info("Product Saved Successfully");
        return responseDto;
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.info("Fetching Product By Id : {}", id);
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product Not Found");
                    return new ProductNotFoundException("Product Not Found");
                });
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Fetching All Products");
        List<ProductEntity> entities = productRepository.findAll();
        return entities.stream().map(entity -> {
            ProductDto dto = new ProductDto();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        log.info("Update Product Request Started");
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product Not Found");
                    return new ProductNotFoundException("Product Not Found");
                });
        entity.setName(productDto.getName());
        entity.setDescription(productDto.getDescription());
        entity.setPrice(productDto.getPrice());
        entity.setManufactureDate(productDto.getManufactureDate());
        ProductEntity updatedEntity = productRepository.save(entity);
        ProductDto responseDto = new ProductDto();
        BeanUtils.copyProperties(updatedEntity, responseDto);
        log.info("Product Updated Successfully");
        return responseDto;
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Delete Product Request Started");
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product Not Found");
                    return new ProductNotFoundException("Product Not Found");
                });
        productRepository.delete(entity);

        log.info("Product Deleted Successfully");
    }
}