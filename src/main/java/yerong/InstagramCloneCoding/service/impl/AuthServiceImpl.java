package yerong.InstagramCloneCoding.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.InstagramCloneCoding.domain.user.Role;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.repository.user.UserRepository;
import yerong.InstagramCloneCoding.service.AuthService;
import yerong.InstagramCloneCoding.web.dto.auth.SignupDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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
    @Override
    @Transactional
    public boolean isUsernameAvailable(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()){
            log.info("====userToString===" + byUsername.toString());
            return false;
        }
        else{
            return true;
        }

    }
}
