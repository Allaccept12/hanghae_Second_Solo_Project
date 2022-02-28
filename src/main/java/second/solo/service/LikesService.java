package second.solo.service;

import second.solo.domain.Account;
import second.solo.dto.response.BoardLikeCountResponseDto;

public interface LikesService {

    BoardLikeCountResponseDto likeBoard(Long boardId, Account account);

    BoardLikeCountResponseDto unLikeBoard(Long boardId, Long accountId);

}
