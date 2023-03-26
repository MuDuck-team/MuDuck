package MuDuck.MuDuck.myPage.controller;

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
import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.comment.service.CommentService;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.entity.Member.MemberRole;
import MuDuck.MuDuck.member.entity.Member.MemberStatus;
import MuDuck.MuDuck.member.service.MemberService;
import MuDuck.MuDuck.myPage.dto.MyPageDto;
import MuDuck.MuDuck.myPage.mapper.MyPageMapper;
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

@WebMvcTest(MyPageController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MyPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @MockBean
    private MemberService memberService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private MyPageMapper myPageMapper;

    @Test
    @DisplayName("마이페이지 내가 쓴 글 확인 기능 Controller Test")
    @WithMockUser
    public void getMyBoards() throws Exception {
        // given
        Member member1 = new Member(1L, "wth0086@gmail.com", "사진주소1", "닉네임1", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");
        Member member2 = new Member(2L, "wth0086@naver.com", "사진주소2", "닉네임2", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        Board board1 = new Board(1L, "제목입니다", "내용입니다", 30, 30, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member1, null);
        Board board2 = new Board(2L, "제목입니다2", "내용입니다2", 40, 50, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member2, null);

        List<MyPageDto.Response> responses = List.of(
                MyPageDto.Response.builder()
                        .id(board1.getBoardId())
                        .title(board1.getTitle())
                        .createdAt("2023.03.25")
                        .build(),
                MyPageDto.Response.builder()
                        .id(board2.getBoardId())
                        .title(board2.getTitle())
                        .createdAt("2023.03.26")
                        .build()
        );

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member1);
        given(boardService.getMyBoards(Mockito.any())).willReturn(List.of(board1, board2));
        given(myPageMapper.boardsToMyPageResponseDtos(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                get("/my-page/boards").accept(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(document("get-MyPageBoards",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("[].id").type(JsonFieldType.NUMBER)
                                                .description("게시물 식별자"),
                                        fieldWithPath("[].title").type(JsonFieldType.STRING)
                                                .description("게시물 제목"),
                                        fieldWithPath("[].createdAt").type(JsonFieldType.STRING)
                                                .description("게시물 생성일")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("마이페이지 내가 좋아요 누른 글 확인 기능 Controller Test")
    @WithMockUser
    public void getMyLikedBoards() throws Exception {
        // given
        Member member1 = new Member(1L, "wth0086@gmail.com", "사진주소1", "닉네임1", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");
        Member member2 = new Member(2L, "wth0086@naver.com", "사진주소2", "닉네임2", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        Board board1 = new Board(1L, "제목입니다", "내용입니다", 30, 30, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member1, null);
        Board board2 = new Board(2L, "제목입니다2", "내용입니다2", 40, 50, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member2, null);

        List<MyPageDto.Response> responses = List.of(
                MyPageDto.Response.builder()
                        .id(board1.getBoardId())
                        .title(board1.getTitle())
                        .createdAt("2023.03.25")
                        .build(),
                MyPageDto.Response.builder()
                        .id(board2.getBoardId())
                        .title(board2.getTitle())
                        .createdAt("2023.03.26")
                        .build()
        );

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member1);
        given(boardService.getMyBoards(Mockito.any())).willReturn(List.of(board1, board2));
        given(myPageMapper.boardsToMyPageResponseDtos(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                get("/my-page/liked-boards").accept(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(document("get-MyPageLikedBoards",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("[].id").type(JsonFieldType.NUMBER)
                                                .description("게시물 식별자"),
                                        fieldWithPath("[].title").type(JsonFieldType.STRING)
                                                .description("게시물 제목"),
                                        fieldWithPath("[].createdAt").type(JsonFieldType.STRING)
                                                .description("게시물 생성일")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("마이페이지 내가 쓴 댓글 확인 기능 Controller Test")
    @WithMockUser
    public void getMyComments() throws Exception {
        // given
        Member member1 = new Member(1L, "wth0086@gmail.com", "사진주소1", "닉네임1", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");
        Member member2 = new Member(2L, "wth0086@naver.com", "사진주소2", "닉네임2", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        Board board1 = new Board(1L, "제목입니다", "내용입니다", 30, 30, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member1, null);
        Board board2 = new Board(2L, "제목입니다2", "내용입니다2", 40, 50, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member2, null);

        Comment comment1 = Comment.builder().body("댓글입니다11.").member(member1).board(board1).build();
        Comment comment2 = Comment.builder().body("댓글입니다22.").member(member2).board(board2).build();

        List<MyPageDto.commentsResponse> responses = List.of(
                MyPageDto.commentsResponse.builder()
                        .boardId(board1.getBoardId())
                        .body(comment1.getBody())
                        .createdAt("2023.03.25")
                        .build(),
                MyPageDto.commentsResponse.builder()
                        .boardId(board2.getBoardId())
                        .body(comment2.getBody())
                        .createdAt("2023.03.26")
                        .build()
        );

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member1);
        given(boardService.getMyBoards(Mockito.any())).willReturn(List.of(board1, board2));
        given(myPageMapper.commentsToMyPageCommentsResponseDtos(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(
                get("/my-page/comments").accept(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(document("get-MyPageComments",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("[].boardId").type(JsonFieldType.NUMBER)
                                                .description("게시물 식별자"),
                                        fieldWithPath("[].body").type(JsonFieldType.STRING)
                                                .description("댓글 내용"),
                                        fieldWithPath("[].createdAt").type(JsonFieldType.STRING)
                                                .description("댓글 생성일")
                                )
                        )
                ));
    }
}
