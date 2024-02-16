package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.user.Role;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.repository.UserRepository;
import yerong.InstagramCloneCoding.service.AuthService;
import yerong.InstagramCloneCoding.web.dto.SignupDto;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void join(SignupDto signupDto){
        String rawPassword = signupDto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        signupDto.setPassword(encPassword);
        User user = signupDto.toEntity();
        user.setRole(Role.USER);
        userRepository.save(user);
    }
}
