package br.com.meow.meow.service;

import br.com.meow.meow.model.Cat;
import br.com.meow.meow.repository.CatRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatServiceTest {

    @InjectMocks
    CatService catService;

    @Mock
    CatRepository catRepository;

    @Test
    public void testCreate() {
        Cat cat = new Cat();
        cat.setName("Test Cat");

        // Configura o mock para retornar o mesmo gato quando o método save for chamado
        when(catRepository.save(any(Cat.class))).thenReturn(cat);

        Cat createdCat = catService.create(cat);
        verify(catRepository, times(1)).save(cat);

        assertEquals(cat, createdCat);
    }

    @Test
    public void testFindCatById() {
        Cat cat = new Cat();
        cat.setId(1);
        cat.setName("Fluffy");

        // Mock the behavior of the repository
        when(catRepository.findById(1)).thenReturn(Optional.of(cat));

        Optional<Cat> foundCat = catService.findById(1);

        assertEquals("Fluffy", foundCat.get().getName());
        assertEquals(1, foundCat.get().getId().intValue());
    }

    @Test
    public void testFindCatByName() {
        Cat cat = new Cat();
        cat.setId(1);
        cat.setName("Fluffy");

        // Mock the behavior of the repository
        when(catRepository.findByName("Fluffy")).thenReturn(Optional.of(cat));

        Optional<Cat> foundCat = catService.findByName("Fluffy");

        assertEquals("Fluffy", foundCat.get().getName());
        assertEquals(1, foundCat.get().getId().intValue());
    }

    @Test
    public void testFindCatsByAge() {
        Cat cat1 = new Cat();
        cat1.setId(1);
        cat1.setName("Whiskers");
        cat1.setAge(3);

        Cat cat2 = new Cat();
        cat2.setId(2);
        cat2.setName("Fluffy");
        cat2.setAge(3);

        List<Cat> catsWithAge = Arrays.asList(cat1, cat2);

        // Mock the behavior of the repository
        when(catRepository.findByAge(3)).thenReturn(catsWithAge);

        List<Cat> foundCats = catService.findByAge(3);

        assertEquals(2, foundCats.size());
        assertEquals("Whiskers", foundCats.get(0).getName());
        assertEquals("Fluffy", foundCats.get(1).getName());
    }

    @Test
    public void testDeleteCatById() {
        Cat cat = new Cat();
        cat.setId(1);
        cat.setName("Fluffy");

        catService.deleteById(1);

        verify(catRepository, times(1)).deleteById(1);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;

        Cat cat = new Cat();
        cat.setId(id);
        cat.setName("Test Cat");
        cat.setAge(2);
        cat.setBreed("Breed");
        cat.setSize("Size");

        Cat existingCat = new Cat();
        existingCat.setId(id);
        existingCat.setName("Existing Cat");
        existingCat.setAge(1);
        existingCat.setBreed("Breed");
        existingCat.setSize("Size");

        // Configura o mock para retornar o gato existente quando o método findById for chamado
        when(catRepository.findById(id)).thenReturn(Optional.of(existingCat));

        when(catRepository.save(any(Cat.class))).thenReturn(cat);

        Cat updatedCat = catService.update(cat);

        verify(catRepository, times(1)).findById(id);
        verify(catRepository, times(1)).save(any(Cat.class));

        assertEquals(cat, updatedCat);
    }

    @Test
    public void testVerifyDuplicatedContent() throws NoSuchMethodException, IllegalAccessException {
        Cat cat1 = new Cat();
        cat1.setId(1);
        cat1.setName("Fluffy");

        // Mock the behavior of the repository
        when(catRepository.findByName("Fluffy")).thenReturn(Optional.of(cat1));

        // Use reflection to access the private method
        Method method = CatService.class.getDeclaredMethod("verifyDuplicatedContent", Cat.class);
        method.setAccessible(true);

        try {
            method.invoke(catService, cat1);
        } catch (InvocationTargetException e) {
            assertEquals("Duplicated content found", e.getCause().getMessage());
        }
    }
}