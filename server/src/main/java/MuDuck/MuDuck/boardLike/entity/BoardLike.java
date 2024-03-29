package MuDuck.MuDuck.boardLike.entity;

import MuDuck.MuDuck.audit.Auditable;
import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.member.entity.Member;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class BoardLike extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardLikesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public BoardLike(Board board, Member member){
        this.board = board;
        this.member = member;
    }
}
