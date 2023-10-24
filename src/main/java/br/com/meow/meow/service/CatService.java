package br.com.meow.meow.service;

import br.com.meow.meow.model.Cat;
import br.com.meow.meow.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*

CRIEM AS BRANCHES NO GITHUB!!!

Alterar CatRepository se necessário.
Alterar o service, essa classe msm.
Alterar o CatController.

URL PARA ACESSAR O BANCO:
http://localhost:8080/h2-console/

URL PARA ACESSAR O SWAGGER:
http://localhost:8080/swagger-ui/index.html

- findAll   *
- deleteById *
- findById

// adicionar no repository também
- findByName *
- findBySize
- findByAge *

- update
- verifyIfExists

- DOCUMENTAÇÃO NO README

 */
@Service
public class CatService {

    @Autowired
    private CatRepository catRepository;

    public Cat create(Cat cat) {
        return catRepository.save(cat);
    }

    public Optional<Cat> findById(Integer id) {
        return catRepository.findById(id);
    }

    public Cat findByName(String name) {
        return catRepository.findByName(name);
    }

    public List<Cat> findByAge(Integer age) {
        return catRepository.findByAge(age);
    }

    public void deleteById (Integer id) {
        catRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        Cat cat = catRepository.findByName(name);
        if (cat != null) {
            catRepository.delete(cat);
        }
    }

    public List<Cat> findAll() {
        return catRepository.findAll();
    }



 /*   private void verifyDuplicatedEntities(Cat cat) {
        var catExists = catRepository.findById(cat.getName());
        if(catExists!=null && catExists.getId()!=cat.getId()) {
            throw new RuleCatException(String.format("O gato %s já está cadastrado",
                    cat.getNome().toUpperCase()));
        }
    }*/
}
