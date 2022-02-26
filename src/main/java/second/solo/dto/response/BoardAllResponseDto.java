package second.solo.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import second.solo.domain.Board;
import second.solo.domain.Likes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardAllResponseDto {

    private Long board_id;

    private Long account_id;

    private String account_name;

    private String content;

    private int like;

    private List<BoardLikesAccountIdListDto> like_account;

    private LocalDateTime time;

    private String img_url;

    private String board_status;

    @Builder
    public BoardAllResponseDto(final Long board_id, final Long account_id, final String account_name, final String content,
                               final LocalDateTime time ,final String img_url,
                               final String board_status,final int like,final List<BoardLikesAccountIdListDto> likeAccount) {
        this.board_id = board_id;
        this.account_id = account_id;
        this.account_name = account_name;
        this.content = content;
        this.like_account = likeAccount;
        this.like = like;
        this.time = time;
        this.img_url = img_url;
        this.board_status = board_status;
    }

    public static BoardAllResponseDto from(final Board entity) {
        return BoardAllResponseDto.builder()
                .board_id(entity.getId())
                .account_id(entity.getAccount().getId())
                .account_name(entity.getAccount().getUsername())
                .content(entity.getContent())
                .like(entity.getLikeList().size())
                .time(entity.getModified())
                .likeAccount(entity.getLikeList().stream()
                        .map(BoardLikesAccountIdListDto::new)
                        .collect(Collectors.toList()))
                .board_status(entity.getBoardStatus())
                .img_url(entity.getImgUrl())
                .build();
    }
}











