package second.solo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import second.solo.advice.Success;
import second.solo.dto.request.BoardCreateRequestDto;
import second.solo.dto.request.BoardUpdateRequestDto;
import second.solo.jwt.UserDetailsImpl;
import second.solo.service.BoardServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    @GetMapping("/{lastBoardId}")
    public ResponseEntity<Success> pagedBoardSearch(@PathVariable Long lastBoardId) {

        return new ResponseEntity<>(new Success("전체 게시글 조회.", boardServiceImpl.pagedBoardSearch(lastBoardId)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Success> createBoard(@RequestBody BoardCreateRequestDto dto,
                                               @AuthenticationPrincipal UserDetailsImpl accountDetails) {
        return new ResponseEntity<>(new Success("게시글 등록.", boardServiceImpl.createBoard(dto,accountDetails.getUser())),HttpStatus.OK);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<Success> updateBoard(@RequestBody BoardUpdateRequestDto dto,
                                               @AuthenticationPrincipal UserDetailsImpl accountDetails,
                                               @PathVariable Long boardId) {
        return new ResponseEntity<>(new Success("게시글 수정.", boardServiceImpl.updateBoard(boardId,accountDetails.getUser().getId(), dto)),HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Success> deleteBoard(@AuthenticationPrincipal UserDetailsImpl accountDetails,
                                               @PathVariable Long boardId) {
        boardServiceImpl.deleteBoard(boardId,accountDetails.getUser().getId());
        return new ResponseEntity<>(new Success("게시글 삭제.",""),HttpStatus.OK);
    }
}
