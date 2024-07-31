package com.tigasinestor.products.services;

import com.tigasinestor.products.errors.PresentException;
import com.tigasinestor.products.model.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getAllProductsDesired();

    int getProductAvergaePrice();

    Product getById(Long id) throws PresentException;

    Product changeDesiredProduct(Product product, Long id) throws PresentException;

    boolean deleteProductById(Product product, Long id) throws PresentException;
}
