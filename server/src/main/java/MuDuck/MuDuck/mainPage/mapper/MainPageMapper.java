package MuDuck.MuDuck.mainPage.mapper;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.mainPage.dto.MainPageDto;
import MuDuck.MuDuck.member.entity.Member;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainPageMapper {
    default MainPageDto.postsResponse boardToMainPostsResponse(Board board){
        Member member = board.getMember();
        MainPageDto.postsResponse response = MainPageDto.postsResponse.builder()
                .id(board.getBoardId())
                .userProfile(member.getPicture())
                .nickName(member.getNickName())
                .createdAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .title(board.getTitle())
                .viewCount(board.getViews())
                .commentsCount(board.getCommentsSize())
                .likeCount(board.getLikes())
                .build();

        return response;
    }

    List<MainPageDto.postsResponse> boardsToMainPostsReponses(List<Board> boards);
}
