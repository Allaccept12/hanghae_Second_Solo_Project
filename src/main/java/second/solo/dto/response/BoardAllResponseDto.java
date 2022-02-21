package second.solo.dto.response;


import lombok.Builder;
import lombok.Getter;
import second.solo.domain.Board;

import java.time.LocalDateTime;

@Getter
public class BoardAllResponseDto {

    private Long board_id;

    private Long account_id;

    private String account_name;

    private String content;

    private int like;

    private LocalDateTime time;

    @Builder
    public BoardAllResponseDto(Long board_id, Long account_id, String account_name, String content,LocalDateTime time ,int like) {
        this.board_id = board_id;
        this.account_id = account_id;
        this.account_name = account_name;
        this.content = content;
        this.like = like;
        this.time = time;
    }

    public static BoardAllResponseDto of(Board entity, int likes) {

        return BoardAllResponseDto.builder()
                .board_id(entity.getId())
                .account_id(entity.getAccount().getId())
                .account_name(entity.getAccount().getUsername())
                .content(entity.getContent())
                .like(likes)
                .time(entity.getModified())
                .build();
    }
}











