package com.example.lesson60.util;

import com.example.lesson60.model.Publication;
import com.example.lesson60.model.User;
import com.example.lesson60.repository.ComRepository;
import com.example.lesson60.repository.PubRepository;

import com.example.lesson60.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class PreloadDatabaseWithData {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PubRepository pubRepository, ComRepository comRepository) {
        return(args) -> {
            userRepository.deleteAll();
            pubRepository.deleteAll();
            comRepository.deleteAll();
            save(userRepository, pubRepository);
        };
    }

    private void save(UserRepository userRepository, PubRepository pubRepository){
        for(int i = 0; i < 3; i++){
            Publication p = new Publication( UUID.randomUUID().toString(), "001","../image/anon1.jpg",Generator.makeDescription());
            pubRepository.save(p);
        }
//        User user = new User(UUID.randomUUID().toString(), "mike@gmail.com");
        User user = new User("mike@gmail.com", "123");

        userRepository.save(user);
    }
}
