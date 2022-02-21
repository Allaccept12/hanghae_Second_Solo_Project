package second.solo.config.repository.likes;

import second.solo.domain.Board;
import second.solo.domain.Likes;

import java.util.List;
import java.util.Optional;

public interface LikesRepositoryCustom {

    Optional<List<Likes>> findLikeByAccountWithBoard(Long accountId, Long boardId);

}
