package br.com.meow.meow.repository;

import br.com.meow.meow.model.Cat;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {

    Optional<Cat> findByName(String name);
    List<Cat> findByAge(Integer age);
    List<Cat> findByBreed(String breed);

    @Modifying
    @Query("UPDATE Cat c SET c.enabled = false WHERE c.id =:id")
    void disableCat(@Param("id") Integer id);

}
