package br.com.meow.meow;

import br.com.meow.meow.model.Cat;
import br.com.meow.meow.repository.CatRepository;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MeowApplication {
	public static void main(String[] args) {
		SpringApplication.run(MeowApplication.class, args);
	}
}
