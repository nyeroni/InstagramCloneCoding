package yerong.InstagramCloneCoding.service;


import yerong.InstagramCloneCoding.web.dto.SignupDto;

public interface AuthService {

    void join(SignupDto signupDto);
}
