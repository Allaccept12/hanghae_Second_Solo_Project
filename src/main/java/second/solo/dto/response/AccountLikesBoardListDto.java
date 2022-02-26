package second.solo.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import second.solo.domain.Likes;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class AccountLikesBoardListDto {

    private Long likes_id;

    public AccountLikesBoardListDto(final Likes likes) {
        this.likes_id = likes.getBoard().getId();
    }
}
