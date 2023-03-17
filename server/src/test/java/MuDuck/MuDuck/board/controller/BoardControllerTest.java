package MuDuck.MuDuck.board.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.entity.Board.BoardStatus;
import MuDuck.MuDuck.board.mapper.BoardMapper;
import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.entity.Member.MemberRole;
import MuDuck.MuDuck.member.entity.Member.MemberStatus;
import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto;
import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto.Response;
import MuDuck.MuDuck.noticeboard.entity.NoticeBoard;
import MuDuck.MuDuck.noticeboard.mapper.NoticeBoardMapper;
import MuDuck.MuDuck.noticeboard.service.NoticeBoardService;
import MuDuck.MuDuck.response.BoardMultipleResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest(BoardController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @MockBean
    private BoardMapper boardMapper;

    @MockBean
    private NoticeBoardService noticeBoardService;

    @MockBean
    private NoticeBoardMapper noticeBoardMapper;

    @Autowired
    private Gson gson;

    @Test
    @WithMockUser
    public void getBoardTest() throws Exception {
        // given
        Member member1 = new Member(1L, "wth0086@gmail.com", "사진주소1", "닉네임1", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null);
        Member member2 = new Member(2L, "wth0086@naver.com", "사진주소2", "닉네임2", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null);

        NoticeBoard noticeBoard = NoticeBoard.builder().noticeBoardId(1L).title("공지글 제목입니다")
                .body("공지글 내용입니다").build();

        Board board1 = new Board(1L, "제목입니다", "내용입니다", 30, 30, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member1, null);
        Board board2 = new Board(2L, "제목입니다2", "내용입니다2", 40, 50, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member2, null);

        List<NoticeBoard> noticeBoards = List.of(noticeBoard);

        List<NoticeBoardDto.Response> noticeBoardResponse = List.of(
                new Response(1L, "1시간 전", "공지글 제목입니다"));
        List<BoardDto.Response> boardResponse = List.of(
                new BoardDto.Response(1L, 1L, "닉네임1", "1시간 전", "유저프로필주소", "제목입니다", 30, 30, 30),
                new BoardDto.Response(2L, 2L, "닉네임2", "3시간 전", "유저프로필사진2", "제목입니다2", 45, 50, 60));

        Page<Board> pageBoards = new PageImpl<>(List.of(board1, board2),
                PageRequest.of(0, 8, Sort.by("createdAt")), 2);

        BoardMultipleResponse boardMultipleResponse = new BoardMultipleResponse(
                noticeBoardResponse, boardResponse, pageBoards);

        given(boardService.findBoards(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageBoards);
//        given(pageBoards.getContent()).willReturn(pageBoards.getContent());
        given(noticeBoardService.getTopNoticeBoard()).willReturn(noticeBoards);
        given(noticeBoardMapper.noticeBoardsToNoticeBoardResponseDtos(
                Mockito.anyList())).willReturn(noticeBoardResponse);
        given(boardMapper.boardsToBoardResponseDtos(Mockito.anyList())).willReturn(boardResponse);

        // when
        String page = "1";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);

        ResultActions actions = mockMvc.perform(
                get("/board").params(queryParams).accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.noticeBoards").isArray())
                .andExpect(jsonPath("$.boards").isArray())
                .andDo(document(
                        "get-board",
                        getResponsePreProcessor(),
                        requestParameters(List.of(parameterWithName("page").description("페이지 번호"))),
                        responseFields(
                                List.of(
                                        fieldWithPath("noticeBoards").type(JsonFieldType.ARRAY)
                                                .description("공지사항 목록 key 값"),
                                        fieldWithPath("noticeBoards[].id").type(JsonFieldType.NUMBER)
                                                .description("공지사항 글 식별자"),
                                        fieldWithPath("noticeBoards[].lastCreatedAt").type(
                                                        JsonFieldType.STRING)
                                                .description("공지사항 글 작성 시간으로부터 지난 시간"),
                                        fieldWithPath("noticeBoards[].title").type(
                                                JsonFieldType.STRING).description("공지사항 글 제목"),
                                        fieldWithPath("boards").type(JsonFieldType.ARRAY)
                                                .description("게시판 목록 key 값"),
                                        fieldWithPath("boards[].id").type(JsonFieldType.NUMBER)
                                                .description("게시글 식별자"),
                                        fieldWithPath("boards[].memberId").type(JsonFieldType.NUMBER)
                                                .description("유저 식별자"),
                                        fieldWithPath("boards[].nickname").type(JsonFieldType.STRING)
                                                .description("유저 닉네임"),
                                        fieldWithPath("boards[].lastCreatedAt").type(
                                                        JsonFieldType.STRING)
                                                .description("게시글 작성 시간으로부터 지난 시간"),
                                        fieldWithPath("boards[].userProfile").type(
                                                        JsonFieldType.STRING)
                                                .description("유저 프로필 사진 저장 주소"),
                                        fieldWithPath("boards[].title").type(JsonFieldType.STRING)
                                                .description("게시글 제목"),
                                        fieldWithPath("boards[].view").type(JsonFieldType.NUMBER)
                                                .description("조회수"),
                                        fieldWithPath("boards[].commentCount").type(
                                                JsonFieldType.NUMBER).description("댓글 개수"),
                                        fieldWithPath("boards[].boardLike").type(JsonFieldType.NUMBER)
                                                .description("좋아요 수"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT)
                                                .description("페이지 정보 key 값"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER)
                                                .description("현재 있는 페이지 정보"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER)
                                                .description("한 페이지에 표시하는 글 개수"),
                                        fieldWithPath("pageInfo.totalElements").type(
                                                JsonFieldType.NUMBER).description("총 게시글 개수"),
                                        fieldWithPath("pageInfo.totalPages").type(
                                                JsonFieldType.NUMBER).description("총 페이지 수")

                                )
                        )

                ));
    }
}