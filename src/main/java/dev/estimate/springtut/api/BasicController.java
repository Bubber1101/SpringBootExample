package dev.estimate.springtut.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/hello")
public class BasicController {

    @GetMapping()
    public ResponseEntity<String> sayHello(@RequestParam("name") String name) {
        return ResponseEntity.of(Optional.of("Hello " + name + "!"));
    }

}
