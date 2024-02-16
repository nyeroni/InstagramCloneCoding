package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.repository.UserRepository;
import yerong.InstagramCloneCoding.service.AuthService;
import yerong.InstagramCloneCoding.web.dto.SignupDto;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public void join(SignupDto signupDto){
        User user = signupDto.toEntity();
        userRepository.save(user);
    }
}
