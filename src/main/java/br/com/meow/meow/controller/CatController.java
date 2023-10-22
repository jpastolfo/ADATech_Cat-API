package br.com.meow.meow.controller;

import br.com.meow.meow.model.Cat;
import br.com.meow.meow.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatService catService;

    @PostMapping(value = "/create")
    public ResponseEntity<Cat> create(@RequestBody Cat cat) {
        Cat catCreated = catService.create(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body(cat);
    }

}