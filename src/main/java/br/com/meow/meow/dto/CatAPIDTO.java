package br.com.meow.meow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CatAPIDTO {

    @JsonProperty("fact")
    private String teste;

}
