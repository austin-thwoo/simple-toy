package com.sims.soft.domain.user.domain;



import com.sims.soft.domain.models.Address;
import com.sims.soft.domain.user.domain.common.UserType;
import com.sims.soft.domain.user.dto.request.UserRegisterCommand;
import com.sims.soft.global.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_USER")
public class User extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String nickName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Embedded
    private Address address;

//TODO
//   private String useremail
//   인증로직 api
    /*
     * 내가 쓴 게시글 목록 api 필요할시
     * */
//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Board> boards = new ArrayList<>();


    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "T_USER_ROLES")
    @Builder.Default
    private final List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public static User create(UserRegisterCommand registerCommand) {
        return User.builder()
                .userName(registerCommand.getUsername())
                .nickName(registerCommand.getNickName())
                .password(registerCommand.getUsername())
                .phoneNumber(registerCommand.getPhoneNumber())
                .userType(registerCommand.getUserType())
                .address(registerCommand.getAddress())
                .build();
    }

    public void addRole(String role) {
        this.roles.add(role);
    }

    public void update(UserRegisterCommand command) {
        this.nickName=command.getNickName();
        this.phoneNumber= command.getPhoneNumber();
        this.address=command.getAddress();

    }


        public void changePassword(String encodedPassword) {
            this.password = encodedPassword;


    }
}

