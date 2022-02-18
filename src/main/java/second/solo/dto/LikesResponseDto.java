package second.solo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import second.solo.domain.Likes;

@Data
@NoArgsConstructor
public class LikesResponseDto {

    private Long board_id;

    public LikesResponseDto(Likes likes) {
        this.board_id = likes.getBoard().getId();
    }
}
