package second.solo.service;

import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.dto.request.BoardCreateRequestDto;
import second.solo.dto.request.BoardUpdateRequestDto;
import second.solo.dto.response.BoardAllResponseDto;
import second.solo.dto.response.CreatedBoardIdDto;

import java.util.List;

public interface BoardService {

    List<BoardAllResponseDto> pagedBoardSearch(Long lastBoardId);

    CreatedBoardIdDto createBoard(BoardCreateRequestDto dto, Account account);

    void deleteBoard(Long boardId, Long accountId);

    Long updateBoard(Long boardId, Long accountId, BoardUpdateRequestDto dto);
}
