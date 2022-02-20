package second.solo.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import second.solo.domain.Account;
import second.solo.domain.Board;

@NoArgsConstructor
@Getter
public class BoardCreateRequestDto {

    private String content;

    @Builder
    public BoardCreateRequestDto(String content) {
        this.content = content;
    }
    public static Board toEntity(BoardCreateRequestDto dto,Account account) {
        return Board.builder()
                .content(dto.getContent())
                .account(account)
                .build();
    }

}
