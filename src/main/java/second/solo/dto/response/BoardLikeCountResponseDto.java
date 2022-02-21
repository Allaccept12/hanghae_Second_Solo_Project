package second.solo.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardLikeCountResponseDto {

    private int like;

    public BoardLikeCountResponseDto(int like) {
        this.like = like;
    }
}
