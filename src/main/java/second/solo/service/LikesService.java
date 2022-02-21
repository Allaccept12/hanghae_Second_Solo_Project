package second.solo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.domain.Likes;
import second.solo.dto.response.BoardLikeCountResponseDto;
import second.solo.config.repository.account.AccountRepository;
import second.solo.config.repository.board.BoardRepository;
import second.solo.config.repository.likes.LikesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

    private final LikesRepository likesRepository;
    private final AccountRepository accountRepository;
    private final BoardRepository boardRepository;

    public BoardLikeCountResponseDto likeBoard(Long boardId, Account account) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new ApiRequestException("게시글이 존재하지 않습니다."));
        Likes likes= Likes.builder()
                .account(account)
                .board(findBoard)
                .build();
        likesRepository.save(likes);
        return new BoardLikeCountResponseDto(findBoard.getLikeCount());
    }

    public BoardLikeCountResponseDto unLikeBoard(Long boardId, Long accountId) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new ApiRequestException("게시글이 존재하지 않습니다."));
        List<Likes> likeByAccountWithBoard = likesRepository.findLikeByAccountWithBoard(accountId, findBoard.getId())
                .orElseThrow(() -> new ApiRequestException("이미 해제 되었거나,좋아요 해제 에러. 천천히 다시 시도 해주세요."));
        if (likeByAccountWithBoard.isEmpty())
            throw new ApiRequestException("이미 해제 되었거나,좋아요 해제 에러. 천천히 다시 시도 해주세요.");

        likeByAccountWithBoard.get(0).deleteLike();
        likesRepository.deleteById(likeByAccountWithBoard.get(0).getId());
        return new BoardLikeCountResponseDto(findBoard.getLikeCount());
    }
}