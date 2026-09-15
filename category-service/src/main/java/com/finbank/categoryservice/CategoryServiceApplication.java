package com.finbank.categoryservice;

import com.finbank.categoryservice.entity.Category;
import com.finbank.categoryservice.repository.CategoryRepository;
import jdk.jfr.Enabled;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@Enabled
@SpringBootApplication
public class CategoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                Category category = new Category();
                category.setName("Thuốc giảm đau hạ sốt");
                categoryRepository.save(category);
            }
        };
    }
}
