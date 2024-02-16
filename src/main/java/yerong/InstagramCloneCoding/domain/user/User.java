package yerong.InstagramCloneCoding.domain.user;

import jakarta.persistence.*;
import lombok.*;
import yerong.InstagramCloneCoding.domain.BaseTimeEntity;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;

    private String bio; //자기소현
    private String website;
    private String phone;
    private String gender;
}
