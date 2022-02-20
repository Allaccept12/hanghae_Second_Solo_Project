package second.solo.repository.likes;

import second.solo.domain.Board;

import java.util.List;

public interface LikesRepositoryCustom {

    List<Long> findAccountLikeBoard(Long account_id);

    List<Long> getBoardLikeCount(List<Board> boardList);


}
