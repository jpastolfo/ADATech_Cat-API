package br.com.meow.meow.controller;


import br.com.meow.meow.dto.CatDTO;
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
import java.util.Optional;

@RestController
@RequestMapping("/cats")
@Tag(name = "meow meow", description = "meow")
public class CatController {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "[500](https://http.cat/status/500)";

    @Autowired
    private CatService catService;

    @PostMapping("/aiticuticuti")
    public ResponseEntity<Cat> create(Cat cat) {
        Cat newCat = catService.create(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCat);
    }

    @GetMapping("/PSIPSIPSIPSI/{id}")
    @Operation(summary = "Acho que vi um gatinho...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "[302](https://http.cat/302)",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cat.class)) }),
            @ApiResponse(responseCode = "400", description = "[400](https://http.cat/400)",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "[404](https://http.cat/404)",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "[500](https://http.cat/500)",
            content = @Content) })
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Optional<Cat> catFound = catService.findById(id);

            return catFound.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadê o gatinho? \n Sumiu")
                    : ResponseEntity.ok().body(catFound);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }


    @GetMapping("/PSIPSIPSIPSIPSI/{name}")
    @Operation(summary = "Tenho quase certeza que vi um gatinho...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "[302](https://http.cat/302)",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cat.class)) }),
            @ApiResponse(responseCode = "400", description = "400",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "404",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "500",
                    content = @Content) })
    public ResponseEntity<?> findByName(@PathVariable String name) {
        try {
            Optional<Cat> catFound = catService.findByName(name);

            return catFound.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadê o gatinho? \n Sumiu")
                    : ResponseEntity.ok().body(catFound);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }


    @GetMapping("/PSIPSIPSIPSIPSIPSI/{age}")
    @Operation(summary = "Você também viu um gatinho??...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "[302](https://http.cat/302)",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cat.class)) }),
            @ApiResponse(responseCode = "400", description = "400",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "404",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "500",
                    content = @Content) })
    public ResponseEntity<?> findByAge(@PathVariable Integer age) {
        try {
            List<Cat> catsFound = catService.findByAge(age);
            if (!catsFound.isEmpty()) {
                return ResponseEntity.ok().body(catsFound);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum gatinho encontrado com essa idade.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }




    @DeleteMapping("/meeaOOOOWW!/{id}")
    @Operation(summary = "Remove um gatinho...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "301", description = "[301](https://http.cat/301)",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cat.class)) }),
            @ApiResponse(responseCode = "400", description = "[400](https://http.cat/400)",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "[404](https://http.cat/404)",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "[500](https://http.cat/500)",
                    content = @Content) })
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        try {
            catService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }


    @DeleteMapping("/meeaOOOOOOOWW!/{name}")
    @Operation(summary = "Remove um gatinho pelo nome...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "301", description = "[301](https://http.cat/301)",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cat.class)) }),
            @ApiResponse(responseCode = "400", description = "[400](https://http.cat/400)",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "[404](https://http.cat/404)",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "[500](https://http.cat/500)",
                    content = @Content) })
    public ResponseEntity<?> deleteByName(@PathVariable String name) {
        catService.deleteByName(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/gatinhogatinhogatinho")
    @Operation(summary = "Chamando todos os lordes da terra", description = "Chamando todos os gatineos")
    public ResponseEntity<List<Cat>> findAllCats() {
        List<Cat> allCats = catService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCats);
    }

    @PatchMapping(value = "/disable/{id}")
    public void disableCat(@PathVariable(value = "id") Integer id) {
        catService.disableCat(id);
    }

    @PutMapping(value = "/update")
    public Cat update(@RequestBody Cat cat) {
        return catService.update(cat);
    }

}