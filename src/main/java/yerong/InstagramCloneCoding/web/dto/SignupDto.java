package yerong.InstagramCloneCoding.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import yerong.InstagramCloneCoding.domain.user.User;

@Getter
@Setter
public class SignupDto {

    @Size(min = 2, max = 20)
    @NotBlank
    String username;

    @NotBlank
    String password;
    @NotBlank
    String email;
    @NotBlank
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
