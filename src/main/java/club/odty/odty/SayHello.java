package club.odty.odty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
public class SayHello {
    //    @RequestMapping(value = {"/{name}/hi", "/hello/{name}"}, method = RequestMethod.GET)
    @GetMapping(value = "/hello/{name}")
    public String sayHello(@PathVariable("name") String name, @RequestParam(required = true, defaultValue = "-1") String id) {
        return "Hello , ";
    }

    @GetMapping(value = "/")
    public String sayHello(@RequestParam(required = true, defaultValue = "-1") String id) {
        return "Hello , ";
    }
}
