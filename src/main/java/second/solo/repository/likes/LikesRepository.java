package second.solo.repository.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import second.solo.domain.Account;
import second.solo.domain.Likes;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long>,LikesRepositoryCustom {


    Optional<Likes> findByAccount(Account accountId);

    @Query("select i from Likes i where i.board.id = :boardId")
    List<Likes> findByBoardId(@Param("boardId") Long boardId);
}
