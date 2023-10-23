package br.com.meow.meow.controller;


import br.com.meow.meow.model.Cat;
import br.com.meow.meow.service.CatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
@Tag(name = "meow meow", description = "meow")
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping("/PSIPSIPSIPSI/{id}")
    @Operation(summary = "Acho que vi um gatinho...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "https://http.cat/200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cat.class)) }),
            @ApiResponse(responseCode = "400", description = "https://http.cat/400",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "https://http.cat/404",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "https://http.cat/500",
            content = @Content) })
    public ResponseEntity<Cat> findById(@PathVariable Integer id) {
        Cat catFound = catService.findById(id).get();
        return ResponseEntity.ok().body(catFound);
    }

    @GetMapping("/gatinhogatinhogatinho")
    @Operation(summary = "teste", description = "teste")
    public ResponseEntity<List<Cat>> findAllCats() {
        List<Cat> allCats = catService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCats);
    }

    @PostMapping("/aiticuticuti")
    public ResponseEntity<Cat> create(Cat cat) {
        Cat newCat = catService.create(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCat);
    }

}