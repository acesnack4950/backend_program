package me.scpark.springdeveloper;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloworldController {
    // 메소드 추가 방식
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @DeleteMapping("/test")
    public String deleteTest() { return "Delete Test response!!!!";}

    @PostMapping("/test")
    public String postTest() { return "Post Test response!!!!!";}

    @PutMapping("/test")
    public String putTest() { return "Put Test response!!!!";}
}

