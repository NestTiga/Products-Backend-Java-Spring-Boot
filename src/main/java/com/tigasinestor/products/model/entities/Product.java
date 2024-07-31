package com.tigasinestor.products.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products",
        uniqueConstraints = @UniqueConstraint(
                name = "code_unique",
                columnNames = "code"
        ))
@Builder
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence"
    )
    @GeneratedValue(
            generator = "product_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long productId;


    @NotBlank(message = "Code must not be empty")
    @Length(min = 3, max = 50, message = "Code must be between 3 and 50 characters")
    @Column(nullable = false, length = 50)
    private String code;

    @NotBlank(message = "Name must not be empty")
    @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Description must not be empty")
    @Length(min = 3, max = 250, message = "Description must be between 3 and 250 characters")
    @Column(nullable = false, length = 250)
    private String description;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 5, fraction = 2, message = "Price must have a maximum of 5 integer digits and 2 decimals")
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean active; // estado del producto

    @Column(nullable = false)
    private boolean desired; // deseado
}
