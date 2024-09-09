package guckflix.backend.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {

    @RequestMapping("/objTest")
    public Long test(@ModelAttribute Test test) {
        System.out.println(test.getId());
        System.out.println(test.getId2());
        return null;
    }
}

@Getter
@Setter
class Test {
    Integer i;
    Long id;
    long id2;
}
