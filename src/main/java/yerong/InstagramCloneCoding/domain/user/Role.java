package yerong.InstagramCloneCoding.domain.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum Role {
    USER("ROLE_UESR", "회원"),
    ADMIN("ROLE_ADMIN", "관리자");
    private String key;
    private String title;
}