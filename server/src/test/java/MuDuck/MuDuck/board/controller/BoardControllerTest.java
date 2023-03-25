package MuDuck.MuDuck.board.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getRequestPreProcessor;
import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MuDuck.MuDuck.board.dto.BoardDto;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentBody;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentHead;
import MuDuck.MuDuck.board.dto.BoardDto.BoardContentResponse;
import MuDuck.MuDuck.board.dto.BoardDto.Patch;
import MuDuck.MuDuck.board.dto.BoardDto.Post;
import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.entity.Board.BoardStatus;
import MuDuck.MuDuck.board.mapper.BoardMapper;
import MuDuck.MuDuck.board.service.BoardService;
import MuDuck.MuDuck.boardCategory.entity.BoardCategory;
import MuDuck.MuDuck.boardCategory.service.BoardCategoryService;
import MuDuck.MuDuck.category.dto.CategoryDto;
import MuDuck.MuDuck.category.entity.Category;
import MuDuck.MuDuck.category.mapper.CategoryMapper;
import MuDuck.MuDuck.category.service.CategoryService;
import MuDuck.MuDuck.comment.dto.CommentDto;
import MuDuck.MuDuck.comment.dto.CommentDto.CommentsHead;
import MuDuck.MuDuck.comment.entity.Comment;
import MuDuck.MuDuck.comment.entity.Comment.CommentStatus;
import MuDuck.MuDuck.comment.mapper.CommentMapper;
import MuDuck.MuDuck.comment.service.CommentService;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.entity.Member.MemberRole;
import MuDuck.MuDuck.member.entity.Member.MemberStatus;
import MuDuck.MuDuck.member.service.MemberService;
import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto;
import MuDuck.MuDuck.noticeboard.dto.NoticeBoardDto.Response;
import MuDuck.MuDuck.noticeboard.entity.NoticeBoard;
import MuDuck.MuDuck.noticeboard.mapper.NoticeBoardMapper;
import MuDuck.MuDuck.noticeboard.service.NoticeBoardService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
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
import org.springframework.restdocs.constraints.ConstraintDescriptions;
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

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryMapper categoryMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private CommentMapper commentMapper;

    @MockBean
    private CommentService commentService;

    @MockBean
    private BoardCategoryService boardCategoryService;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("게시글 목록 가져오기 컨트롤러 테스트")
    @WithMockUser
    public void getBoards() throws Exception {
        // given
        Member member1 = new Member(1L, "wth0086@gmail.com", "사진주소1", "닉네임1", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");
        Member member2 = new Member(2L, "wth0086@naver.com", "사진주소2", "닉네임2", MemberRole.USER,
                MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

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

        Category category1 = Category.builder().categoryId(1L).categoryName("자유주제").build();
        Category category2 = Category.builder().categoryId(2L).categoryName("공연정보/후기").build();
        Category category3 = Category.builder().categoryId(3L).categoryName("시설정보").build();

        List<Category> categories = List.of(category1, category2, category3);

        CategoryDto.Response categoryResponse1 = new CategoryDto.Response(1L, "자유주제", null);
        CategoryDto.Response categoryResponse2 = new CategoryDto.Response(2L, "공연정보/후기", null);
        CategoryDto.Response categoryResponse3 = new CategoryDto.Response(3L, "시설정보", null);

        List<CategoryDto.Response> categoryResponseList = List.of(categoryResponse1,
                categoryResponse2, categoryResponse3);

        given(boardService.findBoards(Mockito.anyInt(), Mockito.anyString(),
                Mockito.anyString())).willReturn(pageBoards);
        given(noticeBoardService.getTopNoticeBoard()).willReturn(noticeBoards);
        given(categoryService.findCategories()).willReturn(categories);

        given(noticeBoardMapper.noticeBoardsToNoticeBoardResponseDtos(
                Mockito.anyList())).willReturn(noticeBoardResponse);
        given(boardMapper.boardsToBoardResponseDtos(Mockito.anyList())).willReturn(boardResponse);
        given(categoryMapper.categoriesToCategoryResponseDtos(Mockito.anyList())).willReturn(
                categoryResponseList);

        // when
        String page = "1";
        String sortBy = "recent";
        String categoryName = "자유주제";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("sortBy", sortBy);
        queryParams.add("categoryName", categoryName);

        ResultActions actions = mockMvc.perform(
                get("/boards").params(queryParams).accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.noticeBoards").isArray())
                .andExpect(jsonPath("$.boards").isArray())
                .andDo(document(
                        "get-boards",
                        getResponsePreProcessor(),
                        requestParameters(
                                List.of(parameterWithName("page").optional().description("페이지 번호"),
                                        parameterWithName("sortBy").optional().description("정렬 기준"),
                                        parameterWithName("categoryName").optional()
                                                .description("카테고리 이름"))),
                        responseFields(
                                List.of(
                                        fieldWithPath("noticeBoards").type(JsonFieldType.ARRAY)
                                                .description("공지사항 목록 key 값"),
                                        fieldWithPath("noticeBoards[].id").type(
                                                        JsonFieldType.NUMBER)
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
                                        fieldWithPath("boards[].memberId").type(
                                                        JsonFieldType.NUMBER)
                                                .description("유저 식별자"),
                                        fieldWithPath("boards[].nickname").type(
                                                        JsonFieldType.STRING)
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
                                        fieldWithPath("boards[].boardLike").type(
                                                        JsonFieldType.NUMBER)
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
                                                JsonFieldType.NUMBER).description("총 페이지 수"),
                                        fieldWithPath("categoryList").type(JsonFieldType.ARRAY)
                                                .description("1차 카테고리 목록"),
                                        fieldWithPath("categoryList[].id").type(
                                                JsonFieldType.NUMBER).description("카테고리 식별자"),
                                        fieldWithPath("categoryList[].categoryName").type(
                                                JsonFieldType.STRING).description("카테고리 이름"),
                                        fieldWithPath("categoryList[].parentId").type(
                                                JsonFieldType.NULL).description("부모 카테고리 식별자")
                                )
                        )

                ));
    }

    @Test
    @DisplayName("게시글에 사용할 카테고리 목록 컨트롤러 테스트")
    @WithMockUser
    public void getCategoryListTest() throws Exception {
        // given
        Category category1 = Category.builder().categoryId(1L).categoryName("자유주제").build();
        Category category2 = Category.builder().categoryId(2L).categoryName("공연정보/후기").build();
        Category category3 = Category.builder().categoryId(3L).categoryName("시설정보").build();

        Category category4 = Category.builder().categoryId(4L).categoryName("2014 레베카")
                .parent(category2).build();
        Category category5 = Category.builder().categoryId(5L).categoryName("2017 레베카")
                .parent(category2).build();
        Category category6 = Category.builder().categoryId(6L).categoryName("2019 헤드윅")
                .parent(category2).build();

        List<Category> categories = List.of(category1, category2, category3, category4, category5,
                category6);

        CategoryDto.Response response1 = new CategoryDto.Response(1L, "자유주제", null);
        CategoryDto.Response response2 = new CategoryDto.Response(2L, "공연정보/후기", null);
        CategoryDto.Response response3 = new CategoryDto.Response(3L, "시설정보", null);

        CategoryDto.Response response4 = new CategoryDto.Response(1L, "2014 레베카", 2L);
        CategoryDto.Response response5 = new CategoryDto.Response(1L, "2017 레베카", 2L);
        CategoryDto.Response response6 = new CategoryDto.Response(1L, "2019 헤드윅", 2L);

        List<CategoryDto.Response> responses = List.of(response1, response2, response3, response4,
                response5, response6);

        given(categoryService.findCategories()).willReturn(categories);
        given(categoryMapper.categoriesToCategoryResponseDtos(Mockito.anyList())).willReturn(
                responses);

        // when
        ResultActions actions = mockMvc.perform(
                get("/boards/category").accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.category").isArray())
                .andExpect(jsonPath("$.mentionedMusical").isArray())
                .andDo(document("get-category",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("category").type(JsonFieldType.ARRAY)
                                                .description("1차 카테고리 목록 key 값"),
                                        fieldWithPath("category[].id").type(JsonFieldType.NUMBER)
                                                .description("카테고리 식별자"),
                                        fieldWithPath("category[].categoryName").type(
                                                JsonFieldType.STRING).description("카테고리 이름"),
                                        fieldWithPath("category[].parentId").type(
                                                JsonFieldType.NULL).description("카테고리 부모카테고리 ID"),
                                        fieldWithPath("mentionedMusical").type(JsonFieldType.ARRAY)
                                                .description("2차 카테고리 목록 key 값"),
                                        fieldWithPath("mentionedMusical[].id").type(
                                                JsonFieldType.NUMBER).description("카테고리 식별자"),
                                        fieldWithPath("mentionedMusical[].categoryName").type(
                                                JsonFieldType.STRING).description("카테고리 이름"),
                                        fieldWithPath("mentionedMusical[].parentId").type(
                                                JsonFieldType.NUMBER).description("카테고리 부모카테고리 ID")
                                )
                        )));
    }

    @Test
    @DisplayName("게시글 가져오기 컨트롤러 테스트")
    @WithMockUser
    public void getBoardContentTest() throws Exception {
        // given
        Member member = new Member(1, "wth0086@naver.com", "프로필이미지저장주소", "VIP석은전동석",
                MemberRole.USER, MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");
        Board board = new Board(1, "제목입니다", "내용입니다", 982, 60, BoardStatus.BOARD_POST, null, null,
                member, null);

        Comment comment1 = new Comment(1, "댓글입니다", CommentStatus.COMMENT_POST, member, board, null,
                null);
        Comment comment2 = new Comment(2, "대댓글입니다", CommentStatus.COMMENT_POST, member, board,
                comment1, null);
        Comment comment3 = new Comment(3, "대댓글입니다2", CommentStatus.COMMENT_POST, member, board,
                comment1, null);

        String category = "자유주제";
        boolean isLiked = true;

        List<Comment> comments = List.of(comment1, comment2, comment3);
        member.setComments(comments);
        board.setComments(comments);

        List<Comment> onlyCommentList = List.of(comment1);

        List<Comment> replyList = List.of(comment2, comment3);
        comment1.setChildren(replyList);

        BoardDto.BoardContentResponse boardContentResponse = BoardContentResponse.builder()
                .id(board.getBoardId())
                .head(BoardContentHead.builder()
                        .memberId(member.getMemberId())
                        .userProfile(member.getPicture())
                        .nickname(member.getNickName())
                        .createdAt("2022.12.21")
                        .view(board.getViews())
                        .like(board.getLikes())
                        .totalComment(board.getComments().size())
                        .category(category)
                        .build())
                .body(BoardContentBody.builder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build())
                .liked(isLiked)
                .build();

        CommentDto.Response replyResponse1 = CommentDto.Response.builder()
                .id(2L)
                .head(CommentsHead.builder()
                        .memberId(member.getMemberId())
                        .userProfile(member.getPicture())
                        .nickname(member.getNickName())
                        .createdAt("2023.03.21 17:30")
                        .build())
                .body("대댓글입니다1")
                .commentStatus("댓글게시")
                .parentId(1L)
                .comments(new ArrayList<>())
                .build();

        CommentDto.Response replyResponse2 = CommentDto.Response.builder()
                .id(3L)
                .head(CommentsHead.builder()
                        .memberId(member.getMemberId())
                        .userProfile(member.getPicture())
                        .nickname(member.getNickName())
                        .createdAt("2023.03.21 18:00")
                        .build())
                .body("삭제된 댓글입니다.")
                .commentStatus("댓글삭제")
                .parentId(1L)
                .comments(new ArrayList<>())
                .build();

        List<CommentDto.Response> replyResponseList = List.of(replyResponse1, replyResponse2);

        CommentDto.Response commentResponse = CommentDto.Response.builder()
                .id(1L)
                .head(CommentsHead.builder()
                        .memberId(member.getMemberId())
                        .userProfile(member.getPicture())
                        .nickname(member.getNickName())
                        .createdAt("2023.03.21 17:00")
                        .build())
                .body("댓글입니다")
                .commentStatus("댓글게시")
                .comments(replyResponseList)
                .build();

        List<CommentDto.Response> commentResponseList = List.of(commentResponse);

        given(boardService.findBoard(Mockito.anyLong())).willReturn(board);
        given(boardService.findCategory(Mockito.any())).willReturn(category);

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);
        given(boardService.isLiked(Mockito.anyLong(), Mockito.anyLong())).willReturn(isLiked);

        given(commentService.getCommentWithoutReply(Mockito.anyList())).willReturn(onlyCommentList);

        given(boardMapper.multiInfoToBoardContentResponse(Mockito.any(), Mockito.any(),
                Mockito.anyString(), Mockito.anyBoolean())).willReturn(boardContentResponse);
        given(commentMapper.commentsToCommentResponseDtos(Mockito.anyList())).willReturn(
                commentResponseList);

        // when
        ResultActions actions = mockMvc.perform(
                get("/boards/{board-id}", board.getBoardId()).accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.boardContent").isMap())
                .andExpect(jsonPath("$.comments").isArray())
                .andDo(document("get-individual-board",
                        getResponsePreProcessor(),
                        pathParameters(parameterWithName("board-id").description("게시글 식별자")),
                        responseFields(List.of(
                                fieldWithPath("boardContent").type(JsonFieldType.OBJECT)
                                        .description("게시글 key 값"),
                                fieldWithPath("boardContent.id").type(JsonFieldType.NUMBER)
                                        .description("게시글 식별자"),
                                fieldWithPath("boardContent.head").type(JsonFieldType.OBJECT)
                                        .description("게시글 Header key 값"),
                                fieldWithPath("boardContent.head.memberId").type(
                                        JsonFieldType.NUMBER).description("게시글 작성자 멤버 식별자"),
                                fieldWithPath("boardContent.head.userProfile").type(
                                        JsonFieldType.STRING).description("게시글 작성자 프로필 사진 주소"),
                                fieldWithPath("boardContent.head.nickname").type(
                                        JsonFieldType.STRING).description("게시글 작성자 닉네임"),
                                fieldWithPath("boardContent.head.createdAt").type(
                                        JsonFieldType.STRING).description("게시글 작성 날짜"),
                                fieldWithPath("boardContent.head.view").type(JsonFieldType.NUMBER)
                                        .description("게시글 조회수"),
                                fieldWithPath("boardContent.head.like").type(JsonFieldType.NUMBER)
                                        .description("게시글 좋아요 수"),
                                fieldWithPath("boardContent.head.totalComment").type(
                                        JsonFieldType.NUMBER).description("게시글 총 댓글 수"),
                                fieldWithPath("boardContent.head.category").type(
                                        JsonFieldType.STRING).description("게시글이 속한 카테고리 이름"),
                                fieldWithPath("boardContent.body").type(JsonFieldType.OBJECT)
                                        .description("게시글 Body key 값"),
                                fieldWithPath("boardContent.body.title").type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                fieldWithPath("boardContent.body.content").type(
                                        JsonFieldType.STRING).description("게시글 내용"),
                                fieldWithPath("boardContent.liked").type(JsonFieldType.BOOLEAN)
                                        .description("회원이 좋아요를 눌렀었는지 여부"),
                                fieldWithPath("comments").type(JsonFieldType.ARRAY)
                                        .description("댓글 목록 key 값"),
                                fieldWithPath("comments[].id").type(JsonFieldType.NUMBER)
                                        .description("댓글 식별자"),
                                fieldWithPath("comments[].head").type(JsonFieldType.OBJECT)
                                        .description("댓글 Header key 값"),
                                fieldWithPath("comments[].head.memberId").type(
                                        JsonFieldType.NUMBER).description("댓글 작성자 멤버 식별자"),
                                fieldWithPath("comments[].head.userProfile").type(
                                        JsonFieldType.STRING).description("댓글 작성자 프로필 사진 주소"),
                                fieldWithPath("comments[].head.nickname").type(JsonFieldType.STRING)
                                        .description("댓글 작성자 닉네임"),
                                fieldWithPath("comments[].head.createdAt").type(
                                        JsonFieldType.STRING).description("댓글 작성 날짜"),
                                fieldWithPath("comments[].body").type(JsonFieldType.STRING)
                                        .description("댓글 내용"),
                                fieldWithPath("comments[].commentStatus").type(JsonFieldType.STRING)
                                        .description("댓글 등록 상태"),
                                fieldWithPath("comments[].parentId").type(JsonFieldType.NULL)
                                        .description("대댓글의 부모 ID 대댓글인 경우만 존재"),
                                fieldWithPath("comments[].comments").type(JsonFieldType.ARRAY)
                                        .description("댓글의 대댓글 목록 key 값"),
                                fieldWithPath("comments[].comments[].id").type(JsonFieldType.NUMBER)
                                        .description("대댓글 식별자"),
                                fieldWithPath("comments[].comments[].head").type(
                                        JsonFieldType.OBJECT).description("대댓글 Header key 값"),
                                fieldWithPath("comments[].comments[].head.memberId").type(
                                        JsonFieldType.NUMBER).description("대댓글 작성자 멤버 식별자"),
                                fieldWithPath("comments[].comments[].head.userProfile").type(
                                        JsonFieldType.STRING).description("대댓글 작성자 프로필 사진 주소"),
                                fieldWithPath("comments[].comments[].head.nickname").type(
                                        JsonFieldType.STRING).description("대댓글 작성자 닉네임"),
                                fieldWithPath("comments[].comments[].head.createdAt").type(
                                        JsonFieldType.STRING).description("대댓글 작성 날짜"),
                                fieldWithPath("comments[].comments[].body").type(
                                        JsonFieldType.STRING).description("대댓글 내용"),
                                fieldWithPath("comments[].comments[].commentStatus").type(
                                        JsonFieldType.STRING).description("대댓글 등록 상태"),
                                fieldWithPath("comments[].comments[].parentId").type(
                                        JsonFieldType.NUMBER).description("대댓글의 부모 ID"),
                                fieldWithPath("comments[].comments[].comments").type(
                                                JsonFieldType.ARRAY)
                                        .description("대댓글은 comments 리스트가 비어있어야한다")
                        ))));
    }

    @Test
    @DisplayName("게시글 등록 컨트롤러 테스트")
    @WithMockUser
    public void postBoardTest() throws Exception {
        // given
        BoardDto.Post post = Post.builder()
                .categoryIds(List.of(2L, 4L))
                .title("제목입니다")
                .content("내용입니다")
                .build();
        String requestBody = gson.toJson(post);

        Member member = new Member(1, "wth0086@naver.com", "프로필이미지저장주소", "VIP석은전동석",
                MemberRole.USER, MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");
        Board board = Board.builder().title("제목입니다").content("내용입니다").member(member).build();

        Category category1 = Category.builder().categoryId(2L).categoryName("공연정보/후기").build();
        Category category2 = Category.builder().categoryId(4L).categoryName("2014 레베카").build();

        List<Long> categoryIds = List.of(2L, 4L);
        List<BoardCategory> boardCategories = List.of(
                BoardCategory.builder().board(board).category(category1).build(),
                BoardCategory.builder().board(board).category(category2).build());

        board.setBoardId(1L);

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);
        given(boardMapper.boardPostToBoard(Mockito.any(), Mockito.any())).willReturn(board);
        given(boardMapper.boardPostToCategoryIds(Mockito.any())).willReturn(categoryIds);
        given(boardCategoryService.getBoardCategories(Mockito.anyList(), Mockito.any())).willReturn(
                boardCategories);
        given(boardService.createBoard(Mockito.any())).willReturn(board);

        // when
        ResultActions actions = mockMvc.perform(
                post("/boards").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(requestBody).with(csrf()));

        // Rest Docs에서 정규식 표현을 위해 선언
        ConstraintDescriptions postBoardConstraints = new ConstraintDescriptions(
                BoardDto.Post.class);
        List<String> idDescriptions = postBoardConstraints.descriptionsForProperty("id");
        List<String> titleDescriptions = postBoardConstraints.descriptionsForProperty("title");
        List<String> contentDescriptions = postBoardConstraints.descriptionsForProperty("content");

        // then
        actions.andExpect(status().isCreated())
                .andDo(document("board-post",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(List.of(
                                fieldWithPath("categoryIds").type(JsonFieldType.ARRAY)
                                        .description("카테고리 식별자 목록")
                                        .attributes(key("regexp").value(idDescriptions)),
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("게시글 제목")
                                        .attributes(key("regexp").value(titleDescriptions)),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("게시물 내용")
                                        .attributes(key("regexp").value(contentDescriptions))
                        )),
                        responseFields(List.of(
                                fieldWithPath("boardId").type(JsonFieldType.NUMBER)
                                        .description("생성된 게시글 식별자")
                        ))));
    }

    @Test
    @DisplayName("게시글 수정 컨트롤러 테스트")
    @WithMockUser
    public void patchBoardTest() throws Exception {
        // given
        BoardDto.Patch requestBody = Patch.builder()
                .title("수정된 제목입니다")
                .content("수정된 내용입니다")
                .build();

        Member member = new Member(1L, "wth0086@naver.com", "프로필이미지저장주소", "VIP석은전동석",
                MemberRole.USER, MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        Board board = new Board(1L, "제목입니다", "내용입니다", 30, 30, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member, null);

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);
        given(boardMapper.boardPatchToBoard(Mockito.any(), Mockito.anyLong())).willReturn(board);

        String requestBodyJson = gson.toJson(requestBody);

        // when
        ResultActions actions = mockMvc.perform(
                patch("/boards/{board-id}", 1L).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(requestBodyJson)
                        .with(csrf()));

        // Rest Docs에서 정규식 표현을 위해 선언
        ConstraintDescriptions patchBoardConstraints = new ConstraintDescriptions(
                BoardDto.Patch.class);
        List<String> titleDescriptions = patchBoardConstraints.descriptionsForProperty("title");
        List<String> contentDescriptions = patchBoardConstraints.descriptionsForProperty("content");

        // then
        actions.andExpect(status().isOk())
                .andDo(document("patch-board",
                        getRequestPreProcessor(),
                        pathParameters(parameterWithName("board-id").description("게시글 식별자")),
                        requestFields(List.of(
                                fieldWithPath("title").type(JsonFieldType.STRING).optional()
                                        .description("게시글 제목")
                                        .attributes(key("regexp").value(titleDescriptions)),
                                fieldWithPath("content").type(JsonFieldType.STRING).optional()
                                        .description("게시글 내용")
                                        .attributes(key("regexp").value(contentDescriptions))
                        ))));

    }

    @Test
    @DisplayName("게시글 삭제하기 컨트롤러 테스트")
    @WithMockUser
    public void deleteBoardTest() throws Exception {
        // given
        Member member = new Member(1, "wth0086@naver.com", "프로필이미지저장주소", "VIP석은전동석",
                MemberRole.USER, MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);

        // when
        ResultActions actions = mockMvc.perform(
                delete("/boards/{board-id}", 1L).accept(MediaType.APPLICATION_JSON).with(csrf()));

        // then
        actions.andExpect(status().isNoContent())
                .andDo(document(
                        "delete-board",
                        pathParameters(parameterWithName("board-id").description("게시글 식별자"))));
    }

    @Test
    @DisplayName("댓글 작성하기 컨트롤러 테스트")
    @WithMockUser
    public void postComment() throws Exception {
        // given
        CommentDto.Post post = CommentDto.Post.builder().body("댓글입니다.").build();

        Member member = new Member(1, "wth0086@naver.com", "프로필이미지저장주소", "VIP석은전동석",
                MemberRole.USER, MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        Board board = new Board(1L, "제목입니다", "내용입니다", 30, 30, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member, null);

        Comment comment = Comment.builder().body("댓글입니다.").member(member).board(board).build();

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);
        given(boardService.findBoard(Mockito.anyLong())).willReturn(board);
        given(commentMapper.commentPostDtoToComment(Mockito.any())).willReturn(comment);

        String requestBody = gson.toJson(post);

        // when
        ResultActions actions = mockMvc.perform(
                post("/boards/{board-id}/comments", 1L).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(requestBody).with(csrf())
        );

        // Rest Docs에서 정규식 표현을 위해 선언
        ConstraintDescriptions patchBoardConstraints = new ConstraintDescriptions(
                CommentDto.Post.class);
        List<String> bodyDescriptions = patchBoardConstraints.descriptionsForProperty("body");

        // then
        actions.andExpect(status().isCreated())
                .andDo(document("post-comment",
                        getRequestPreProcessor(),
                        pathParameters(parameterWithName("board-id").description("게시글 식별자")),
                        requestFields(List.of(
                                fieldWithPath("body").type(JsonFieldType.STRING)
                                        .description("댓글 내용")
                                        .attributes(key("regexp").value(bodyDescriptions)
                                        )))));
    }

    @Test
    @DisplayName("대댓글 작성하기 컨트롤러 테스트")
    @WithMockUser
    public void postReply() throws Exception {
        // given
        CommentDto.Post post = CommentDto.Post.builder().body("대댓글입니다.").build();

        Member member = new Member(1, "wth0086@naver.com", "프로필이미지저장주소", "VIP석은전동석",
                MemberRole.USER, MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        Board board = new Board(1L, "제목입니다", "내용입니다", 30, 30, BoardStatus.BOARD_POST, null,
                new ArrayList<>(), member, null);

        Comment comment = Comment.builder().body("댓글입니다.").member(member).board(board).build();

        Comment reply = Comment.builder().body("대댓글입니다.").member(member).board(board).build();

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);
        given(boardService.findBoard(Mockito.anyLong())).willReturn(board);
        given(commentService.findComment(Mockito.anyLong())).willReturn(comment);
        given(commentMapper.commentPostDtoToComment(Mockito.any())).willReturn(reply);

        String requestBody = gson.toJson(post);

        // when
        ResultActions actions = mockMvc.perform(
                post("/boards/{board-id}/comments/{comment-id}", 1L, 1L).accept(
                                MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(requestBody).with(csrf())
        );

        // then
        // Rest Docs에서 정규식 표현을 위해 선언
        ConstraintDescriptions patchBoardConstraints = new ConstraintDescriptions(
                CommentDto.Post.class);
        List<String> bodyDescriptions = patchBoardConstraints.descriptionsForProperty("body");

        // then
        actions.andExpect(status().isCreated())
                .andDo(document("post-reply",
                        getRequestPreProcessor(),
                        pathParameters(parameterWithName("board-id").description("게시글 식별자"),
                                parameterWithName("comment-id").description("부모 댓글 식별자")),
                        requestFields(List.of(
                                fieldWithPath("body").type(JsonFieldType.STRING)
                                        .description("대댓글 내용")
                                        .attributes(key("regexp").value(bodyDescriptions)
                                        )))));
    }

    @Test
    @DisplayName("댓글 삭제 Controller Test")
    @WithMockUser
    public void deleteCommentTest() throws Exception {
        // given
        Member member = new Member(1, "wth0086@naver.com", "프로필이미지저장주소", "VIP석은전동석",
                MemberRole.USER, MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);

        // when
        ResultActions actions = mockMvc.perform(
                delete("/boards/{board-id}/comments/{comment-id}", 1L, 1L).accept(
                        MediaType.APPLICATION_JSON).with(csrf()));

        // then
        actions.andExpect(status().isNoContent())
                .andDo(document(
                        "delete-comment",
                        pathParameters(parameterWithName("board-id").description("게시글 식별자"),
                                parameterWithName("comment-id").description("댓글 식별자"))));
    }

    @Test
    @DisplayName("게시글 좋아요 기능 Controller Test")
    @WithMockUser
    public void postLikeTest() throws Exception {
        // given
        Member member = new Member(1, "wth0086@naver.com", "프로필이미지저장주소", "VIP석은전동석",
                MemberRole.USER, MemberStatus.MEMBER_ACTIVE, null, null, null, "1234");

        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);

        // when
        ResultActions actions = mockMvc.perform(
                post("/boards/{board-id}/like", 1L).accept(MediaType.APPLICATION_JSON)
                        .with(csrf()));

        // then
        actions.andExpect(status().isOk())
                .andDo(document("post-like",
                        pathParameters(parameterWithName("board-id").description("게시글 식별자"))));
    }

}