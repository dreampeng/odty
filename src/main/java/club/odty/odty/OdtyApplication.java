package club.odty.odty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class OdtyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdtyApplication.class, args);
    }

    @Value("${odtyUrl}")
    private String odtyUrl;
    @Autowired
    private Website odty;
}
