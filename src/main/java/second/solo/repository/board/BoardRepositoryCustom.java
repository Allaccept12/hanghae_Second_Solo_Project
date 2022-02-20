package second.solo.repository.board;

import second.solo.domain.Board;
import second.solo.dto.response.BoardAllResponseDto;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findAllBoard();

}
