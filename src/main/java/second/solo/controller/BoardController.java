package second.solo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import second.solo.advice.Success;
import second.solo.dto.request.BoardCreateRequestDto;
import second.solo.jwt.UserDetailsImpl;
import second.solo.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity<Success> allBoardSearch() {
        return new ResponseEntity<>(new Success("",boardService.allBoardSearch()), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Success> createBoard(@RequestBody BoardCreateRequestDto dto, @AuthenticationPrincipal UserDetailsImpl accountDetails) {
        return new ResponseEntity<>(new Success("",boardService.createBoard(dto,accountDetails.getUser())),HttpStatus.OK);
    }

}
