package MuDuck.MuDuck.mainPage.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
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
import MuDuck.MuDuck.musical.dto.ActorMusicalResponseDto;
import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseMusicals;
import MuDuck.MuDuck.musical.dto.MusicalDto.SingleResponseDto;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.Musical.Age;
import MuDuck.MuDuck.musical.entity.Musical.Genre;
import MuDuck.MuDuck.musical.entity.Musical.MusicalState;
import MuDuck.MuDuck.musical.mapper.MusicalMapper;
import MuDuck.MuDuck.musical.service.MusicalService;
import MuDuck.MuDuck.theater.entitiy.Theater;
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
import org.springframework.test.web.servlet.MvcResult;
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
    private MusicalService musicalService;
    @MockBean
    private MusicalMapper musicalMapper;
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

    @Test
    @WithMockUser
    @DisplayName("메인페이지 추천 작품")
    public void getRecommendMusicals() throws Exception{
        //given
        Musical musical = new Musical(1L,"테스트","Test","localhost_test_poster", Genre.GENRE_ORIGINAL,"testData",
                MusicalState.MUSICAL_YET,"2023.05.05","2023.06.06", Age.AGE_0, 123, 135, 20,
                new Theater(1L,"testPlace",1.0,1.0,"tset phone","test ADD","test Road ADD"));
        ActorMusicalResponseDto.listing listing = ActorMusicalResponseDto.listing.builder()
                .actorId(1L)
                .actorName("테스트 배우")
                .role("태스트 역할")
                .build();
        List<ActorMusicalResponseDto.listing> listings = new ArrayList<>();
        listings.add(listing);
        MusicalDto.ResponseMusicals responseMusicals = ResponseMusicals.builder()
                .musicalId(1L)
                .musicalKorName("테스트작품")
                .poster("localhost_test_poster")
                .actorMusicals(listings)
                .build();
        List<MusicalDto.ResponseMusicals> responseMusicalsList = new ArrayList<>();
        responseMusicalsList.add(responseMusicals);
        given(musicalMapper.musicalsToMusicalResponseDtos(Mockito.anyList())).willReturn(responseMusicalsList);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/main-page/musicals", musical.getMusicalId())
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-recommend-musicals",
                                getResponsePreProcessor(),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER)
                                                        .description("뮤지컬 아이디"),
                                                fieldWithPath("data[].musicalKorName").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 국문 이름"),
                                                fieldWithPath("data[].poster").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 포스터"),
                                                fieldWithPath("data[].actors").type(JsonFieldType.ARRAY)
                                                        .description("출연자 정보"),
                                                fieldWithPath("data[].actors[].id").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("배우 번호"),
                                                fieldWithPath("data[].actors[].actorName").type(
                                                                JsonFieldType.STRING)
                                                        .description("배우 이름"),
                                                fieldWithPath("data[].actors[].role").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 배역")
                                        )
                                )
                        )
                );

    }

}
