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

    private List<Long> likes_id;

    private String token;

    @Builder
    public AccountLoginResponseDto(Long account_id, String account_email, String account_name, List<Long> board_id) {
        this.account_id = account_id;
        this.account_email = account_email;
        this.account_name = account_name;
        this.likes_id = board_id;

    }
    public void tokenSet(String token) {
        this.token = token;
    }

    public static AccountLoginResponseDto of(List<Likes> likesEntity, Account accountEntity) {
        return AccountLoginResponseDto.builder()
                .account_id(accountEntity.getId())
                .account_email(accountEntity.getEmail())
                .account_name(accountEntity.getUsername())
                .board_id(likesEntity.stream()
                        .map(l -> l.getBoard().getId())
                        .collect(Collectors.toList()))
                .build();

    }


}
