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
import second.solo.domain.Likes;
import second.solo.dto.request.BoardCreateRequestDto;
import second.solo.dto.request.BoardUpdateRequestDto;
import second.solo.dto.response.BoardAllResponseDto;
import second.solo.dto.response.CreatedBoardIdDto;
import second.solo.repository.board.BoardRepository;
import second.solo.repository.likes.LikesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final LikesRepository likesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardAllResponseDto> pagedBoardSearch(Long lastBoardId) {
        Pageable paging = PageRequest.of(0,5);
        Page<Board> boardList = boardRepository.findByBoardAtLimit(lastBoardId,paging);
        return boardList.getContent().stream()
                .map(BoardAllResponseDto::from)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public CreatedBoardIdDto createBoard(BoardCreateRequestDto dto, Account account) {
        return new CreatedBoardIdDto(boardRepository.save(BoardCreateRequestDto.toEntity(dto,account)).getId());
    }

    @Override
    @Transactional
    public void deleteBoard(Long boardId, Long accountId) {
        List<Likes> byBoardId = likesRepository.findByBoardId(boardId);
        likesRepository.deleteAll(byBoardId);
        boardRepository.delete(boardValidation(boardId, accountId));
    }

    @Override
    @Transactional
    public Long updateBoard(Long boardId, Long accountId, BoardUpdateRequestDto dto) {
        Board findBoard = boardValidation(boardId, accountId);
        findBoard.updateContent(dto.getContent(),dto.getImg_url(),dto.getBoard_status());
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













