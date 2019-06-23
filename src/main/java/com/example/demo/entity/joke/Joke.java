package com.example.demo.entity.joke;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Joke {

    private int id;
    private String content;
}
