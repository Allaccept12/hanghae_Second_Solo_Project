package second.solo.config.repository.board;

import second.solo.domain.Board;
import second.solo.dto.response.BoardAllResponseDto;

import java.util.List;
import java.util.Optional;

public interface BoardRepositoryCustom {

    List<Board> findAllBoard();

}
