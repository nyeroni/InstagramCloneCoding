package yerong.InstagramCloneCoding;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewControllerTest {

    @GetMapping("/auth/signup")
    public String signupPage() {
        return "views/auth/signup";
    }

    @GetMapping("/auth/signin")
    public String signinPage() {
        return "views/auth/signin";
    }

    @GetMapping("/image/story")
    public String storyPage() {
        return "views/image/story";
    }

    @GetMapping("/image/popular")
    public String popularPage() {
        return "views/image/popular";
    }

    @GetMapping("/image/upload")
    public String uploadPage() {
        return "views/image/upload";
    }

    @GetMapping("/user/profile")
    public String profilePage() {
        return "views/user/profile";
    }

    @GetMapping("/user/update")
    public String updatePage() {
        return "views/user/update";
    }
}