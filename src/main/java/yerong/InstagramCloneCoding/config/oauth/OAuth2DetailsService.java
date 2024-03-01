package yerong.InstagramCloneCoding.config.oauth;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import yerong.InstagramCloneCoding.config.auth.PrincipalDetails;
import yerong.InstagramCloneCoding.domain.user.Role;
import yerong.InstagramCloneCoding.domain.user.User;
import yerong.InstagramCloneCoding.handler.exception.CustomException;
import yerong.InstagramCloneCoding.repository.user.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("facebook 로그인");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        Map<String, Object> userInfo = oAuth2User.getAttributes();
        String username = "facebook_" + (String) userInfo.get("id");
        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());

        Optional<User> findUserOptional = userRepository.findByUsername(username);

        if(!findUserOptional.isPresent()){ //회원가입
            User user = User.builder()
                    .username(username)
                    .name(name)
                    .email(email)
                    .password(password)
                    .role(Role.USER)
                    .build();
            return new PrincipalDetails(userRepository.save(user), userInfo);
        }
        else{ //로그인
            return new PrincipalDetails(findUserOptional.get(), userInfo);
        }
    }
}
