package second.solo.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(columnDefinition = "integer default 0")
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    public Board(String content, Account account) {
        this.content = content;
        this.account = account;
    }

    public void addLike() {
        this.likes += 1;
    }

    public void subLike() {
        this.likes -= 1;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
