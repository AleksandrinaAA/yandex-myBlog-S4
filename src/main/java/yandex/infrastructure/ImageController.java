package yandex.infrastructure;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping
public class ImageController {

    //todo Fix
//    @GetMapping(value = "/images/{name}", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public Resource getImage(@PathVariable("name") String name) throws Exception {
//        Path path = Paths.get("/images/" + name);
//        return new UrlResource(path.toUri());
//    }
}

