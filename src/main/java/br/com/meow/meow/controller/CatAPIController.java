package br.com.meow.meow.controller;

import br.com.meow.meow.dto.CatAPIDTO;
import br.com.meow.meow.service.CatServiceAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catsAPI")
@Tag(name = "meow meow API", description = "meow API")
public class CatAPIController {

    @Autowired
    CatServiceAPI catServiceAPI;
    @GetMapping("/catAPI")
    public CatAPIDTO testeAPI(){
        return catServiceAPI.testeAPI();
    }

}
