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
    public BoardUpdateRequestDto(final String content,final String img_url, final String board_status) {
        this.content = content;
        this.img_url = img_url;
        this.board_status = board_status;
    }
}
