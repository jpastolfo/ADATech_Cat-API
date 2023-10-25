package br.com.meow.meow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/*
    Size da pra ser um ENUM futuramente
 */
@Entity
@Data
@Table(name = "cats")
public class Cat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank(message="NAME")
    private String name;

    @Column(name = "age", nullable = false)
    @NotNull(message="AGE")
    private Integer age;

    @Column(name = "size", nullable = false)
    @NotBlank(message="SIZE")
    private String size;

    @Column(name = "breed")
    @NotBlank(message="BREED")
    private String breed;

    @Column(nullable = false)
    private boolean enabled;
}
