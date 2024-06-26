package com.example.gamecatalog.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.gamecatalog.persistence.entities"})
@EnableJpaRepositories(basePackages = {"com.example.gamecatalog.persistence.repositories"})
@ComponentScan(basePackages = "com.example.gamecatalog")
public class GameCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameCatalogApplication.class, args);
    }

}
