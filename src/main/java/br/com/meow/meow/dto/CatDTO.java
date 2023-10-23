package br.com.meow.meow.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Data
public class CatDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    private Integer id;

    private String name;

    private Integer age;

    private String size;
}
