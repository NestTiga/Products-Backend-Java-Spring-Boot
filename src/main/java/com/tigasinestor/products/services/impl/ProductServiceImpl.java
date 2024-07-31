package com.tigasinestor.products.services.impl;

import com.tigasinestor.products.dao.repositories.ProductRepository;
import com.tigasinestor.products.errors.PresentException;
import com.tigasinestor.products.messages.GlobalMessages;
import com.tigasinestor.products.model.entities.Product;
import com.tigasinestor.products.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findByActive(true);
    }

    @Override
    public List<Product> getAllProductsDesired() {
        return productRepository.findByStateActiveAndDesired();
    }

    @Override
    public int getProductAvergaePrice() {
        return productRepository.promedioPrecioQuery();
    }

    @Override
    public Product getById(Long id) throws PresentException {
        Optional<Product> findProduct = productRepository.findById(id);
        if (findProduct.isPresent()) {
            return findProduct.get();
        } else {
            throw new PresentException(GlobalMessages.PRODUCT_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product changeDesiredProduct(Product product, Long id) throws PresentException {
        Optional<Product> findProduct = productRepository.findById(id);

        if (findProduct.isPresent()) {
            Product updateProduct = findProduct.get();
            updateProduct.setCode(product.getCode());
            updateProduct.setName(product.getName());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setActive(product.isActive());
            updateProduct.setDesired(product.isDesired());

            return productRepository.save(updateProduct);

        } else {
            throw new PresentException(GlobalMessages.PRODUCT_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean deleteProductById(Product product, Long id) throws PresentException {
        Optional<Product> findProduct = productRepository.findById(id);

        if (findProduct.isPresent()) {
            Product updateProduct = findProduct.get();
            updateProduct.setCode(product.getCode());
            updateProduct.setName(product.getName());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setActive(product.isActive());
            updateProduct.setDesired(product.isDesired());

            productRepository.save(updateProduct);
            return true;

        } else {
            throw new PresentException(GlobalMessages.PRODUCT_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        }
    }
}
