package second.solo.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import second.solo.domain.Likes;

@NoArgsConstructor
@Getter
public class BoardLikesAccountIdListDto {

    private Long account_id;

    public BoardLikesAccountIdListDto(final Likes likes) {
        this.account_id = likes.getAccount().getId();
    }
}
