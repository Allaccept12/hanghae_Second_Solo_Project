package second.solo.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreatedBoardIdDto {

    private Long board_id;

    public CreatedBoardIdDto(Long board_id) {
        this.board_id = board_id;
    }
}
