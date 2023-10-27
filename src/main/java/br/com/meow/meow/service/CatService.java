package br.com.meow.meow.service;

import br.com.meow.meow.api.CatAPI;
import br.com.meow.meow.dto.CatAPIDTO;
import br.com.meow.meow.model.Cat;
import br.com.meow.meow.repository.CatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/*

CRIEM AS BRANCHES NO GITHUB!!!

DICAS:
Alterar CatRepository se necessário.
Alterar o service, essa classe msm.
Alterar o CatController.

URL PARA ACESSAR O BANCO:
http://localhost:8080/h2-console/

URL PARA ACESSAR O SWAGGER:
http://localhost:8080/swagger-ui/index.html

 !! Size da pra ser um ENUM futuramente !!

 **
============= TAREFAS ================
- findBySize
- findByBreed OK!
 ** Se for fazer os de cima, PRECISA que fazer no repository também. CatRepository. **

********
- update ----->> IMPORTANTE !!!!

- ARRUMAR O RETORNO DOS MÉTODOS PARA DTO!!
- DOCUMENTAÇÃO NO README

- Vou ter que mudar depois o retorno de runtime exception pra outro tipo de exception
 */
@Service
public class CatService {

    @Autowired
    private CatRepository catRepository;

    private Logger logger = Logger.getLogger(CatService.class.getName());

    public Cat create(Cat cat) {
        verifyDuplicatedContent(cat);

        logger.info("Creating a cat! Cat Name: " + cat.getName());
        return catRepository.save(cat);
    }
 
    public List<Cat> findBySize(int size) {
        return catRepository.findBySize(size);
    }

    public Optional<Cat> findById(Integer id) {
        logger.info("Finding a cat by id! id: " + id);
        return catRepository.findById(id);
    }

    public Optional<Cat> findByName(String name) {
        logger.info("Finding a cat by name! name: " + name);
        return catRepository.findByName(name);
    }

    public List<Cat> findByAge(Integer age) {
        logger.info("Finding a cat by age! age: " + age);
        return catRepository.findByAge(age);
    }

    public List<Cat> findByBreed(String breed) {
        logger.info("Finding a cat by breed! breed: " + breed);
        return catRepository.findByBreed(breed);
    }

    public void deleteById(Integer id) {
        logger.info("Deleting a cat by id! id: " + id);
        catRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        logger.info("Deleting a cat by name! name: " + name);
        Cat cat = catRepository.findByName(name).get();
        if (cat != null) {
            catRepository.delete(cat);
        }
    }

    // Retirar ou Ajustar para devolver ou não o fato.
    // Lembrar que o GetFact está sendo puxado no FindById (Controller)
    @Autowired
    private CatAPI catAPI;
    public List<Cat> findAll() {
        logger.info("Finding All cats!");
        CatAPIDTO catFact = catAPI.findAFact();
        System.out.println("Cat fact: " + catFact.getFact());
        return catRepository.findAll();
    }
    public Cat setFactForCat(Cat cat) {
        CatAPIDTO catFact = catAPI.findAFact();
        cat.setFact(catFact.getFact());
        return cat;
    }
    public Cat update(Cat cat) {
        if (cat == null) throw new RuntimeException("Null Object");

        logger.info("Updating a cat! Cat Name: " + cat.getName());

        Cat newCat = catRepository.findById(cat.getId()).orElseThrow(() -> new RuntimeException("No records found for this ID!"));

        newCat.setName(cat.getName());
        newCat.setAge(cat.getAge());
        newCat.setBreed(cat.getBreed());
        newCat.setSize(cat.getSize());
        newCat.setEnabled(true);

        return catRepository.save(newCat);
    }


    @Transactional
    public void disableCat(Integer id) {
        logger.info("Disabling one cat by id! id: " + id);

        catRepository.disableCat(id);
    }



    private void verifyDuplicatedContent(Cat cat) {
        Optional<Cat> catName = catRepository.findByName(cat.getName());
        if (catName.isPresent() && catName.get().getId() != cat.getId()) {
            throw new RuntimeException("Duplicated content found");
        }
    }


    private Cat verifyIfExists(Integer id) {
        Optional<Cat> cat = findById(id);
        if(cat.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return cat.get();
    }

 /*

        private void verifyDuplicatedEntities(Cat cat) {
        var catExists = catRepository.findById(cat.getName());
        if(catExists!=null && catExists.getId()!=cat.getId()) {
            throw new RuleCatException(String.format("O gato %s já está cadastrado",
                    cat.getNome().toUpperCase()));
        }
    }*/
}
