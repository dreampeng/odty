package club.odty.odty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/test")
public class SayHello {
    @Value("${odtyUrl}")
    private String odtyUrl;
    @Autowired
    private Website odty;
    @Autowired
    private EntityManager entityManager;


    //    @RequestMapping(value = {"/{name}/hi", "/hello/{name}"}, method = RequestMethod.GET)
    @GetMapping(value = "/hello/{name}")
    public String sayHello(@PathVariable("name") String name, @RequestParam(required = true, defaultValue = "-1") String id) {
        return "Hello , " + name + " , id : " + id + " , welcome to odty! url : " + odtyUrl + "<br> website name : " + odty.getName() + " url : " + odty.getUrl() + "<br>hashcode : " + entityManager.hashCode();
    }
}
