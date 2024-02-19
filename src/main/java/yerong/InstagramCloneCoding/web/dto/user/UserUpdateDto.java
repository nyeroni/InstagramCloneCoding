package yerong.InstagramCloneCoding.web.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import yerong.InstagramCloneCoding.domain.user.User;

@Getter
@Setter
public class UserUpdateDto {

    @NotBlank
    private String name; //필수로 받을 것
    @NotBlank
    private String password; //필수로 받을 것
    private String website;
    private String bio;
    private String phone;
    private String gender;

    public User toEntity(){
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
