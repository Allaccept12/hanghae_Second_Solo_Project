package second.solo.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import second.solo.domain.Account;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AccountResponseDto {

    private Long account_id;

    private String account_email;

    private String account_name;

    private List<LikesResponseDto> board_id;

    public AccountResponseDto(Account account) {
        this.account_id = account.getId();
        this.account_email = account.getEmail();
        this.account_name = account.getUsername();
        this.board_id = account.getLikesList()
                .stream()
                .map(LikesResponseDto::new)
                .collect(Collectors.toList());
    }
}
