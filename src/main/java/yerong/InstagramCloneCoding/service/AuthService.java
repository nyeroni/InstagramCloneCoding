package yerong.InstagramCloneCoding.service;


import yerong.InstagramCloneCoding.web.dto.auth.SignupDto;

public interface AuthService {

    void join(SignupDto signupDto);
    boolean isUsernameAvailable(String username);
}
