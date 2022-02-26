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

    private String img_url;

    private String board_status;

    @Builder
    public BoardUpdateRequestDto(String content,String img_url, String board_status) {
        this.content = content;
    }
    public static Board toEntity(BoardUpdateRequestDto dto, Account account) {
        return Board.builder()
                .content(dto.getContent())
                .imgUrl(dto.getImg_url())
                .boardStatus(dto.getBoard_status())
                .account(account)
                .build();
    }
}
