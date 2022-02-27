package second.solo.repository.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import second.solo.domain.Account;
import second.solo.domain.Likes;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long>,LikesRepositoryCustom {

    @Query("select l from Likes l where l.account.id = :accountId and l.board.id = :boardId")
    Optional<Likes> findByAccount(@Param("accountId") Long accountId, @Param("boardId") Long boardId);

    @Query("select i from Likes i where i.board.id = :boardId")
    List<Likes> findByBoardId(@Param("boardId") Long boardId);
}
