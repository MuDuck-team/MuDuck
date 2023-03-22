package MuDuck.MuDuck.board.entity;

import MuDuck.MuDuck.audit.Auditable;
import MuDuck.MuDuck.boardCategory.entity.BoardCategory;
import MuDuck.MuDuck.boardLike.entity.BoardLike;
import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@Entity
public class Board extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private int views = 0;

    @Column(nullable = false)
    private int likes = 0;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 30)
    @Builder.Default
    private BoardStatus boardStatus = BoardStatus.BOARD_POST;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @Builder.Default
    private List<BoardLike> boardLikes = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<BoardCategory> boardCategories = new ArrayList<>();

    public enum BoardStatus {
        BOARD_POST("글게시"),
        BOARD_DELETE("글삭제");

        @Getter
        private String status;

        BoardStatus(String status) {
            this.status = status;
        }
    }

}
