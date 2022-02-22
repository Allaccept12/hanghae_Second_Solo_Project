package second.solo.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import second.solo.domain.Account;

@Getter
@NoArgsConstructor
public class AccountRegisterRequestDto {

    private String email;

    private String password;

    private String passwordCheck;

    private String username;

    @Builder
    public AccountRegisterRequestDto(String email, String password, String passwordCheck, String username) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.username = username;
    }

    public void encodedPassword(String password) {
        this.password = password;
    }

    public static Account toEntity(AccountRegisterRequestDto dto) {
        return Account.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .build();
    }
}
