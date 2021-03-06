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
    private String img_url;
    private String board_status;

    @Builder
    public BoardCreateRequestDto(final String content,final String img_url, final String board_status) {
        this.content = content;
        this.img_url = img_url;
        this.board_status = board_status;
    }

    public static Board toEntity(final BoardCreateRequestDto dto,final Account account) {
        return Board.builder()
                .content(dto.getContent())
                .account(account)
                .boardStatus(dto.getBoard_status())
                .imgUrl(dto.getImg_url())
                .build();
    }

}
