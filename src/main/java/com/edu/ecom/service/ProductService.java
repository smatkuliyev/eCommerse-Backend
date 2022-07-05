package com.edu.ecom.service;

import com.edu.ecom.dto.ProductDto;
import com.edu.ecom.model.Category;
import com.edu.ecom.model.Product;
import com.edu.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : allProducts) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()){
            throw new Exception("product not present");
        }
        Product product = optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }
}
