package second.solo.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.dto.request.BoardCreateRequestDto;
import second.solo.dto.request.BoardUpdateRequestDto;
import second.solo.dto.response.BoardAllResponseDto;
import second.solo.dto.response.CreatedBoardIdDto;
import second.solo.repository.account.AccountRepository;
import second.solo.repository.board.BoardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardAllResponseDto> allBoardSearch(Long lastBoardId) {
        Pageable paging = PageRequest.of(0,5);
        Page<Board> boardList = boardRepository.findAllBoard(lastBoardId,paging);
        return boardList.getContent().stream()
                .map(b -> BoardAllResponseDto.of(b,b.getLikeCount()))
                .collect(Collectors.toList());
    }
    @Transactional
    public CreatedBoardIdDto createBoard(BoardCreateRequestDto dto, Account account) {
        return new CreatedBoardIdDto(boardRepository.save(BoardCreateRequestDto.toEntity(dto,account)).getId());
    }

    @Transactional
    public void deleteBoard(Long boardId, Long accountId) {
        boardRepository.delete(boardValidation(boardId, accountId));
    }

    @Transactional
    public Long updateBoard(Long boardId, Long accountId, BoardUpdateRequestDto dto) {
        Board findBoard = boardValidation(boardId, accountId);
        findBoard.updateContent(dto.getContent());
        return findBoard.getId();
    }

    private Board boardValidation(Long boardId, Long accountId) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new ApiRequestException("존재 하지않는 게시글 입니다."));
        if(!findBoard.getAccount().getId().equals(accountId)) {
            throw new ApiRequestException("게시글 작성자가 아닙니다.");
        }
        return findBoard;
    }


}













