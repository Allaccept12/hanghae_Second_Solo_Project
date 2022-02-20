package second.solo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import second.solo.domain.Board;
import second.solo.dto.response.BoardAllResponseDto;
import second.solo.repository.board.BoardRepository;
import second.solo.repository.likes.LikesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    
    private final LikesRepository likesRepository;


//    public List<BoardAllResponseDto> allBoardSearch() {
//        List<Board> boardList = boardRepository.findAllBoard();
//        List<Long> accountLikeBoard = likesRepository.getBoardLikeCount(boardList);
//    }
}













