package br.com.meow.meow.model;

import jakarta.persistence.*;
import lombok.*;

/*
    Size da pra ser um ENUM futuramente
 */
@Entity
@Data
@Table(name = "tb_cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String size;

}
