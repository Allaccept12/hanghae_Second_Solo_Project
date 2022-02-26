package second.solo.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import second.solo.domain.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    Page<Board> findByBoardAtLimit(Long lastBoardId, Pageable paging);

}
