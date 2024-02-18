package yerong.InstagramCloneCoding.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    @GetMapping({"/", "/image/story"})
    public String story(){
        return "views/image/story";
    }
    @GetMapping("/image/popular")
    public String popular(){
        return "views/image/popular";
    }
    @GetMapping("/image/upload")
    public String upload(){
        return "views/image/upload";
    }
}
