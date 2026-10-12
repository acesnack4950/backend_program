package me.scpark.springdeveloper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    // GET /quiz?code=1
    @GetMapping("/quiz")
    public ResponseEntity<String> quiz(@RequestParam("code") int code) {
        switch (code) {
            case 1:
                return ResponseEntity.status(HttpStatus.CREATED).body("Created!");
            case 2:
                return ResponseEntity.badRequest().body("Bad Request!");
            default:
                return ResponseEntity.ok().body("OK!");
        }
    }

    // POST /quiz  (바디: {"value": 2})
    @PostMapping("/quiz")
    public ResponseEntity<String> quiz2(@RequestBody Code code) {
        switch (code.value()) {
            case 1:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden!");
            default:
                return ResponseEntity.ok().body("OK!");
        }
    }
}

// 요청 바디 {"value": 2}를 담는 그릇
record Code(int value) {}