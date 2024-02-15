package yerong.InstagramCloneCoding.web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpController {

    @GetMapping("/get")
    public String get(){
        return "get요청됨";
    }

    @PostMapping("/post")
    public String post(){
        return "post요청됨";
    }

    @PutMapping("/put")
    public String put(){
        return "put요청됨";
    }

    @DeleteMapping("/delete")
    public String delete(){
        return "delete요청됨";
    }
}
