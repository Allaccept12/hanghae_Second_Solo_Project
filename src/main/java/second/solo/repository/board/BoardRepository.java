package second.solo.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import second.solo.domain.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>,BoardRepositoryCustom {

}
