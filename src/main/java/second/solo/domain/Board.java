package second.solo.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(columnDefinition = "integer default 0")
    private int likeCount;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime modified;

    @Builder
    public Board(String content, Account account) {
        this.content = content;
        this.account = account;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void upLikes() {
        this.likeCount += 1;
    }
    public void downLikes() {
        this.likeCount -= 1;
    }
}
