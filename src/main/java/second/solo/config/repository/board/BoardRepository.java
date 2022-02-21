package second.solo.config.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import second.solo.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>,BoardRepositoryCustom {

}