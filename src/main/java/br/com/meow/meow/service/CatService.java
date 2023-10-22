package br.com.meow.meow.service;

import br.com.meow.meow.model.Cat;
import br.com.meow.meow.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*

CRIEM AS BRANCHES NO GITHUB!!!

Alterar CatRepository se necessário.
Alterar o service, essa classe msm.
Alterar o CatController.

URL PARA ACESSAR O BANCO:
http://localhost:8084/h2-console/

- findAll
- deleteById
- findById

// adicionar no repository também
- findByName
- findBySize
- findByAge

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

    public Cat findById(Integer id) {
        return catRepository.findById(id).get();
    }

}
