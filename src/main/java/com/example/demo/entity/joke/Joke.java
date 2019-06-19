package com.example.demo.entity.joke;

import org.springframework.stereotype.Component;

@Component
public class Joke {

    private int id;
    private String content;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "joke{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
