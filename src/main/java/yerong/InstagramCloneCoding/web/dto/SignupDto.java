package yerong.InstagramCloneCoding.web.dto;

import lombok.Getter;
import lombok.Setter;
import yerong.InstagramCloneCoding.domain.user.User;

@Getter
@Setter
public class SignupDto {

    String username;
    String password;
    String email;
    String name;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
