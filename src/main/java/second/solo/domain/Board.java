package second.solo.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "BOARD_TAB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
    private final List<Likes> likeList = new ArrayList<>();

    @Lob
    private String imgUrl;

    private String boardStatus;


    @Builder
    public Board(String content, Account account, String imgUrl, String boardStatus) {
        this.content = content;
        this.account = account;
        this.imgUrl = imgUrl;
        this.boardStatus = boardStatus;
    }

    public void updateContent(String content,String imgUrl, String boardStatus) {
        this.content = content;
        this.imgUrl = imgUrl;
        this.boardStatus = boardStatus;
    }
}
