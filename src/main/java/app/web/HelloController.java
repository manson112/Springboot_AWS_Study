package app.web;

import app.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

    @GetMapping("/test")
    public String test() {
        return "TEST1 OK";
    }

    @PostMapping("/test2")
    public String test2() {
	return "TEST2 OK";
    }
}
