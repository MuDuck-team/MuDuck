package MuDuck.MuDuck.mainPage.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.entity.Board.BoardStatus;
import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.mainPage.dto.MainPageDto;
import MuDuck.MuDuck.mainPage.mapper.MainPageMapper;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.entity.Member.MemberRole;
import MuDuck.MuDuck.member.entity.Member.MemberStatus;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(MainPageController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MainPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @MockBean
    private MainPageMapper mainPageMapper;

    @Test
    @DisplayName("메인페이지 데일리 인기글 Controller Test")
    @WithMockUser
    public void getDailyPostsTest() throws Exception {
        // given
        Member member1 = new Member(1L, "wth0086@gmail.com", "사진주소1", "닉네임1", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");
        Member member2 = new Member(2L, "wth0086@naver.com", "사진주소2", "닉네임2", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        Board board1 = new Board(1L, "제목입니다", "내용입니다", 30, 30, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member1, null);
        Board board2 = new Board(2L, "제목입니다2", "내용입니다2", 40, 50, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member2, null);

        List<MainPageDto.postsResponse> responses = List.of(
                MainPageDto.postsResponse.builder()
                        .id(board1.getBoardId())
                        .userProfile(member1.getPicture())
                        .nickName(member1.getNickName())
                        .createdAt("2023.03.26")
                        .title(board1.getTitle())
                        .viewCount(2930)
                        .commentsCount(0)
                        .likeCount(192)
                        .build(),
                MainPageDto.postsResponse.builder()
                        .id(board2.getBoardId())
                        .userProfile(member2.getPicture())
                        .nickName(member2.getNickName())
                        .createdAt("2023.03.26")
                        .title(board2.getTitle())
                        .viewCount(2815)
                        .commentsCount(6)
                        .likeCount(12)
                        .build()
        );

        given(boardService.getDailyPopularPosts()).willReturn(List.of(board1, board2));
        given(mainPageMapper.boardsToMainPostsReponses(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                get("/main-page/daily").accept(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(document(
                    "get-dailyPopularPosts",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("게시물 식별자"),
                                        fieldWithPath("[].userProfile").type(JsonFieldType.STRING).description("유저 프로필 주소"),
                                        fieldWithPath("[].nickName").type(JsonFieldType.STRING).description("유저 닉네임"),
                                        fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("게시물 생성 날짜"),
                                        fieldWithPath("[].title").type(JsonFieldType.STRING).description("게시물 제목"),
                                        fieldWithPath("[].viewCount").type(JsonFieldType.NUMBER).description("게시물 조회수"),
                                        fieldWithPath("[].commentsCount").type(JsonFieldType.NUMBER).description("게시물 댓글수"),
                                        fieldWithPath("[].likeCount").type(JsonFieldType.NUMBER).description("게시물 좋아요 수")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("메인페이지 위클리 인기글 Controller Test")
    @WithMockUser
    public void getWeeklyPostsTest() throws Exception {
        // given
        Member member1 = new Member(1L, "wth0086@gmail.com", "사진주소1", "닉네임1", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");
        Member member2 = new Member(2L, "wth0086@naver.com", "사진주소2", "닉네임2", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        Board board1 = new Board(1L, "제목입니다", "내용입니다", 30, 30, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member1, null);
        Board board2 = new Board(2L, "제목입니다2", "내용입니다2", 40, 50, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member2, null);

        List<MainPageDto.postsResponse> responses = List.of(
                MainPageDto.postsResponse.builder()
                        .id(board1.getBoardId())
                        .userProfile(member1.getPicture())
                        .nickName(member1.getNickName())
                        .createdAt("2023.03.26")
                        .title(board1.getTitle())
                        .viewCount(2930)
                        .commentsCount(0)
                        .likeCount(192)
                        .build(),
                MainPageDto.postsResponse.builder()
                        .id(board2.getBoardId())
                        .userProfile(member2.getPicture())
                        .nickName(member2.getNickName())
                        .createdAt("2023.03.26")
                        .title(board2.getTitle())
                        .viewCount(2815)
                        .commentsCount(6)
                        .likeCount(12)
                        .build()
        );

        given(boardService.getDailyPopularPosts()).willReturn(List.of(board1, board2));
        given(mainPageMapper.boardsToMainPostsReponses(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                get("/main-page/weekly").accept(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(document(
                        "get-weeklyPopularPosts",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("게시물 식별자"),
                                        fieldWithPath("[].userProfile").type(JsonFieldType.STRING).description("유저 프로필 주소"),
                                        fieldWithPath("[].nickName").type(JsonFieldType.STRING).description("유저 닉네임"),
                                        fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("게시물 생성 날짜"),
                                        fieldWithPath("[].title").type(JsonFieldType.STRING).description("게시물 제목"),
                                        fieldWithPath("[].viewCount").type(JsonFieldType.NUMBER).description("게시물 조회수"),
                                        fieldWithPath("[].commentsCount").type(JsonFieldType.NUMBER).description("게시물 댓글수"),
                                        fieldWithPath("[].likeCount").type(JsonFieldType.NUMBER).description("게시물 좋아요 수")
                                )
                        )
                ));
    }
}
