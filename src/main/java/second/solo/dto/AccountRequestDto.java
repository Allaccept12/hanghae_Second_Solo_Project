package second.solo.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountRequestDto {

    private String email;

    private String password;

    public AccountRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
