package br.com.meow.meow.api;

import br.com.meow.meow.dto.CatAPIDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "cat-client", url = "https://catfact.ninja/")
public interface CatAPI {

    @GetMapping("/fact")
    public CatAPIDTO findAFact();

}
