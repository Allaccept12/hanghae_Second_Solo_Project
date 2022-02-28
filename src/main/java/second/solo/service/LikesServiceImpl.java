package second.solo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.domain.Likes;
import second.solo.dto.response.BoardLikeCountResponseDto;
import second.solo.repository.board.BoardRepository;
import second.solo.repository.likes.LikesRepository;


@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService{

    private final LikesRepository likesRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public BoardLikeCountResponseDto likeBoard(Long boardId, Account account) {
        if (likesRepository.findByAccount(account.getId(),boardId).isPresent()) {
            throw new ApiRequestException("이미 좋아요 누른 게시글 입니다.");
        }
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new ApiRequestException("게시글이 존재하지 않습니다."));
        Likes likes= Likes.builder()
                .account(account)
                .board(findBoard)
                .build();
        likesRepository.save(likes);
        return new BoardLikeCountResponseDto(findBoard.getLikeList().size());
    }

    @Override
    @Transactional
    public BoardLikeCountResponseDto unLikeBoard(Long boardId, Long accountId) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new ApiRequestException("게시글이 존재하지 않습니다."));

        Likes likeByAccountWithBoard = likesRepository.findLikeByAccountWithBoard(accountId, findBoard.getId())
                .orElseThrow(() -> new ApiRequestException("이미 해제 되었거나,좋아요 해제 에러. 천천히 다시 시도 해주세요."));

        likeByAccountWithBoard.deleteLike();
        likesRepository.deleteById(likeByAccountWithBoard.getId());
        return new BoardLikeCountResponseDto(findBoard.getLikeList().size());
    }
}
