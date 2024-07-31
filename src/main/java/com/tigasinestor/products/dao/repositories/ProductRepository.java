package com.tigasinestor.products.dao.repositories;

import com.tigasinestor.products.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActive(boolean active); // All products that are active

    @Query("SELECT p FROM Product p WHERE p.active = true")
    List<Product> findByStateActive(); // query personalizada para buscar productos activos

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.desired = true")
    List<Product> findByStateActiveAndDesired(); // query personalizada para buscar productos activos y deseados

    @Query("SELECT AVG(p.price) FROM Product p WHERE p.active = true")
    int promedioPrecioQuery(); // query personalizada para obtener el promedio de los precios de los productos
}
