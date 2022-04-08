package testapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "TEST1 OK";
    }

    @PostMapping("/test2")
    public String test2() {
	return "TEST2 OK";
    }
}
