package second.solo.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import second.solo.domain.Account;
import second.solo.domain.Board;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {

    private String content;

    @Builder
    public BoardUpdateRequestDto(String content) {
        this.content = content;
    }
    public static Board toEntity(BoardUpdateRequestDto dto, Account account) {
        return Board.builder()
                .content(dto.getContent())
                .account(account)
                .build();
    }
}
