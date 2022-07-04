package com.edu.ecom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name")
    @NotBlank
    private String categoryName;

    @NotBlank
    private String description;

    @Column(name = "image_url")
    @NotBlank
    private String imageUrl;
}
