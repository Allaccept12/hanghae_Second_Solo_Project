package second.solo.dto.response;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import second.solo.domain.Account;
import second.solo.domain.Likes;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class AccountLoginResponseDto {

    private Long account_id;

    private String account_email;

    private String account_name;

    private List<AccountLikesBoardListDto> like_board;

    private String token;

    @Builder
    public AccountLoginResponseDto(final Long account_id, final String account_email, final String account_name,final List<AccountLikesBoardListDto> board_id) {
        this.account_id = account_id;
        this.account_email = account_email;
        this.account_name = account_name;
        this.like_board = board_id;

    }
    public void tokenSet(String token) {
        this.token = token;
    }

    public static AccountLoginResponseDto of(final List<Likes> likesEntity,final Account accountEntity) {
        return AccountLoginResponseDto.builder()
                .account_id(accountEntity.getId())
                .account_email(accountEntity.getEmail())
                .account_name(accountEntity.getUsername())
                .board_id(likesEntity.stream()
                        .map(AccountLikesBoardListDto::new)
                        .collect(Collectors.toList()))
                .build();

    }


}
