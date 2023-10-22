package br.com.meow.meow.repository;

import br.com.meow.meow.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

    Cat findByName(String name);

}
