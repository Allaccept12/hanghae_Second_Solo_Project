package second.solo.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import second.solo.domain.Account;

@Getter
@NoArgsConstructor
public class AccountRegisterRequestDto {

    private String account_email;

    private String password;

    private String password_check;

    private String account_name;

    @Builder
    public AccountRegisterRequestDto(final String email, final String password, final String passwordCheck, final String username) {
        this.account_email = email;
        this.password = password;
        this.password_check = passwordCheck;
        this.account_name = username;
    }

    public void encodedPassword(final String password) {
        this.password = password;
    }

    public static Account toEntity(final AccountRegisterRequestDto dto) {
        return Account.builder()
                .email(dto.getAccount_email())
                .password(dto.getPassword())
                .username(dto.getAccount_name())
                .build();
    }
}
