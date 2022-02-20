package second.solo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.dto.request.BoardCreateRequestDto;
import second.solo.dto.response.BoardAllResponseDto;
import second.solo.repository.account.AccountRepository;
import second.solo.repository.board.BoardRepository;
import second.solo.repository.likes.LikesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public List<BoardAllResponseDto> allBoardSearch() {
        List<Board> boardList = boardRepository.findAllBoard();
        return boardList.stream()
                .map(b -> BoardAllResponseDto.of(b,b.getLikeCount()))
                .collect(Collectors.toList());
    }

    public Long createBoard(BoardCreateRequestDto dto, Account account) {
        accountRepository.findById(account.getId()).orElseThrow(() -> new ApiRequestException("존재하지 않는 사용자 입니다."));
        return boardRepository.save(BoardCreateRequestDto.toEntity(dto,account)).getId();
    }
}













