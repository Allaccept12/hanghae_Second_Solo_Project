package second.solo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import second.solo.advice.Success;
import second.solo.jwt.UserDetailsImpl;
import second.solo.service.LikesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/like")
public class
LikesController {

    private final LikesService likeService;


    @PostMapping("/{boardId}")
    public ResponseEntity<Success> likeBoard(@PathVariable Long boardId,
                                             @AuthenticationPrincipal UserDetailsImpl accountDetails) {
        return new ResponseEntity<>(new Success("해당 게시글 좋아요",likeService.likeBoard(boardId,accountDetails.getUser())), HttpStatus.OK);
    }
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Success> unLikeBoard(@PathVariable Long boardId,
                                             @AuthenticationPrincipal UserDetailsImpl accountDetails) {
        return new ResponseEntity<>(new Success("해당 게시글 좋아요 해지",likeService.unLikeBoard(boardId,accountDetails.getUser().getId())), HttpStatus.OK);
    }
}
















