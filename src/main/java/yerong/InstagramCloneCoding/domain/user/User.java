package yerong.InstagramCloneCoding.domain.user;

import jakarta.persistence.*;
import lombok.*;
import yerong.InstagramCloneCoding.domain.BaseTimeEntity;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@ToString
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String bio; //자기소개
    private String website;
    private String phone;
    private String gender;

    public void setRole(Role role){
        this.role = role;
    }
    public void updateName(String name){
        this.name = name;
    }
    public void updatePassword(String password){
        this.password = password;
    }
    public void updateBio(String bio){
        this.bio = bio;
    }
    public void updateWebsite(String website){
        this.website = website;
    }
    public void updatePhone(String phone){
        this.phone = phone;
    }
    public void updateGender(String gender){
        this.gender = gender;
    }


}
