package second.solo.dto.request;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import second.solo.domain.Account;


@NoArgsConstructor
@Getter
public class AccountLoginRequestDto {

    private String email;

    private String password;

    private String username;

    @Builder
    public AccountLoginRequestDto(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
