package br.com.meow.meow.service;

import br.com.meow.meow.api.CatAPI;
import br.com.meow.meow.dto.CatAPIDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatServiceAPI {

    @Autowired
    private CatAPI catAPI;

    public CatAPIDTO findAFact(){
        return catAPI.findAFact();
    }

}
