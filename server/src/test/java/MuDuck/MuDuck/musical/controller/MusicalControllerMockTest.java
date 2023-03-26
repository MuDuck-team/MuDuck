package MuDuck.MuDuck.musical.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getRequestPreProcessor;
import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.actorMusical.service.ActorMusicalService;
import MuDuck.MuDuck.musical.dto.ActorMusicalResponseDto;
import MuDuck.MuDuck.actorMusical.repository.ActorMusicalRepository;
import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.MusicalBoards;
import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseMusicalBoards;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseMusicals;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.Musical.Age;
import MuDuck.MuDuck.musical.entity.Musical.Genre;
import MuDuck.MuDuck.musical.entity.Musical.MusicalState;
import MuDuck.MuDuck.musical.mapper.MusicalMapper;
import MuDuck.MuDuck.musical.repository.MusicalRepository;
import MuDuck.MuDuck.musical.service.MusicalService;
import MuDuck.MuDuck.theater.dto.TheaterDto;
import MuDuck.MuDuck.theater.entitiy.Theater;
import MuDuck.MuDuck.theater.mapper.TheaterMapper;
import MuDuck.MuDuck.theater.service.TheaterService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest(MusicalController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MusicalControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MusicalService musicalService;

    @MockBean
    private MusicalMapper musicalMapper;

    @MockBean
    private MusicalRepository musicalRepository;

    @MockBean
    private TheaterService theaterService;

    @MockBean
    private TheaterMapper theaterMapper;

    @MockBean
    private ActorMusicalService actorMusicalService;

    @MockBean
    private ActorMusicalRepository actorMusicalRepository;

    @Autowired
    private Gson gson;

    private Musical musical;
    private List<Musical> musicals = new ArrayList<>();
    private MusicalDto.ResponseMusical response;
    private List<MusicalDto.ResponseMusicals> responses = new ArrayList<>();
    private ActorMusical actorMusical;
    private List<ActorMusical> actorMusicals = new ArrayList<>();
    private List<ActorMusicalResponseDto.detail> detail = new ArrayList<>();
    private List<ActorMusicalResponseDto.listing> listing = new ArrayList<>();
    private Theater theater;
    private TheaterDto.ResponseUsedMusical responseTheater;
    private MusicalBoards musicalBoards;
    private List<MusicalBoards> musicalBoardsList;
    private MusicalDto.ResponseMusicalBoards responseMusicalBoards;
    private Category responseCategory;

    @BeforeEach
    void init() {
        actorMusical = actorMusical.builder()
                .musicalActorId(1L)
                .actor(new Actor(1L, "차지연", "123123"))
                .musical(musical)
                .role("안나")
                .build();
        actorMusicals.add(actorMusical);

        for (ActorMusical actorMusical : actorMusicals) {
            ActorMusicalResponseDto.listing listingItem = ActorMusicalResponseDto.listing.builder()
                    .actorId(actorMusical.getActor().getActorId())
                    .actorName(actorMusical.getActor().getActorName())
                    .role(actorMusical.getRole())
                    .build();
            listing.add(listingItem);
        }

        theater = Theater.builder()
                .theaterId(1L)
                .placeName("오둥이극장")
                .longitude(12.1234)
                .latitude(13.1234)
                .phone("02-555-5555")
                .address("서울시 어쩌구 저쩌구")
                .roadAddress("서울시 어쩌구 저쩌구")
                .build();

        responseTheater = TheaterDto.ResponseUsedMusical.builder()
                .theaterId(1L)
                .theaterName("오둥이극장")
                .build();

        response = MusicalDto.ResponseMusical.builder()
                .musicalId(1L)
                .musicalKorName("테스트")
                .musicalEngName("test")
                .poster("localhosttesting")
                .genre(Genre.GENRE_ORIGINAL)
                .musicalState(MusicalState.MUSICAL_ONAIR)
                .musicalInfo("테스트테스트")
                .openDate("2023.03.22")
                .closeDate("2023.03.22")
                .age(Age.AGE_19)
                .runningTime(168)
                .intermission(20)
                .views(1)
                .theaterId(1L)
                .build();

        musical = Musical.builder()
                .musicalId(1L)
                .musicalKorName("테스트")
                .musicalEngName("test")
                .poster("localhosttesting")
                .genre(Genre.GENRE_ORIGINAL)
                .musicalInfo("테스트테스트")
                .musicalState(MusicalState.MUSICAL_ONAIR)
                .openDate("2023.03.22")
                .closeDate("2023.03.22")
                .age(Age.AGE_19)
                .runningTime(168)
                .intermission(20)
                .views(1)
                .theater(theater)
                .build();

        for (int i = 2; i <= 5; i++) {

            musical.setMusicalId((long) i);
            musical.setMusicalKorName("테스트" + i);
            musical.setViews(i);

            ResponseMusicals response1 = ResponseMusicals.builder()
                    .musicalId((long) i)
                    .musicalKorName("테스트" + i)
                    .poster("localhosttesting")
                    .actorMusicals(listing)
                    .build();

            musicals.add(musical);
            responses.add(response1);

            musical.setMusicalId(1L);
        }


    }

    @Test
    @WithMockUser
    @DisplayName("작품 전체 목록 조회")
    public void getMusicalsTest() throws Exception {
        //given
        MultiValueMap<String, String>
                pageMultiValueMap = new LinkedMultiValueMap<>();
        pageMultiValueMap.add("page", "1");
        pageMultiValueMap.add("size", "20");
        Page<Musical> musicalPage = new PageImpl<>(musicals,
                PageRequest.of(1, 20, Sort.by("musical_id")), 22);
        given(musicalService.findMusicals(Mockito.anyInt(), Mockito.anyInt())).willReturn(
                musicalPage);
        given(musicalMapper.musicalsToMusicalResponseDtos(Mockito.anyList())).willReturn(responses);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/musicals")
                                .params(pageMultiValueMap)
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        MvcResult mvcResult = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.musicals").isArray())
                .andDo(document("get-musicals",
                                getResponsePreProcessor(),
                                requestParameters(
                                        List.of(parameterWithName("page").description("페이지"),
                                                parameterWithName("size").description("크기")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("musicals[].id").type(JsonFieldType.NUMBER)
                                                        .description("뮤지컬 아이디"),
                                                fieldWithPath("musicals[].musicalKorName").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 국문 이름"),
                                                fieldWithPath("musicals[].poster").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 포스터"),
                                                fieldWithPath("musicals[].actors").type(JsonFieldType.ARRAY)
                                                        .description("출연자 정보"),
                                                fieldWithPath("musicals[].actors[].id").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("배우 번호"),
                                                fieldWithPath("musicals[].actors[].actorName").type(
                                                                JsonFieldType.STRING)
                                                        .description("배우 이름"),
                                                fieldWithPath("musicals[].actors[].role").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 배역"),

                                                fieldWithPath("pageInfo").type(JsonFieldType.OBJECT)
                                                        .description("페이지 정보"),
                                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER)
                                                        .description("페이지 정보"),
                                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER)
                                                        .description("사이즈 정보"),
                                                fieldWithPath("pageInfo.totalElements").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("전체 조회 건 수"),
                                                fieldWithPath("pageInfo.totalPages").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("전체 페이지 수")
                                        )
                                )
                        )
                ).andReturn();
        List list = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("$.musicals");
        assertThat(list.size()).isEqualTo(responses.size());
    }

    @Test
    @WithMockUser
    @DisplayName("장르에 따른 작품 전체 목록 조회")
    public void getMusicalGenresTest() throws Exception {
        //given
        MultiValueMap<String, String>
                pageMultiValueMap = new LinkedMultiValueMap<>();
        pageMultiValueMap.add("page", "1");
        pageMultiValueMap.add("size", "20");
        Page<Musical> musicalPage = new PageImpl<>(musicals,
                PageRequest.of(1, 20, Sort.by("musical_id")), 22);
        given(musicalService.findMusicalGenres(Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt())).willReturn(
                musicalPage);
        given(musicalMapper.musicalsToMusicalResponseDtos(Mockito.anyList())).willReturn(responses);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/musicals")
                                .params(pageMultiValueMap)
                                .param("genre", "GENRE_ORIGINAL")
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        MvcResult mvcResult = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.musicals").isArray())
                .andDo(document("get-musicals-genre",
                        getResponsePreProcessor(),
                        requestParameters(
                                List.of(parameterWithName("genre").description(
                                                "장르 선택(GENRE_LICENSED:라이센스(default), GENRE_CREATED : 창작, GENRE_ORIGINAL : 오리지널"),
                                        parameterWithName("page").description("페이지"),
                                        parameterWithName("size").description("크기")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("musicals[].id").type(JsonFieldType.NUMBER)
                                                .description("뮤지컬 아이디"),
                                        fieldWithPath("musicals[].musicalKorName").type(
                                                        JsonFieldType.STRING)
                                                .description("뮤지컬 국문 이름"),
                                        fieldWithPath("musicals[].poster").type(
                                                        JsonFieldType.STRING)
                                                .description("뮤지컬 포스터"),
                                        fieldWithPath("musicals[].actors").type(JsonFieldType.ARRAY)
                                                .description("출연자 정보"),
                                        fieldWithPath("musicals[].actors[].id").type(
                                                        JsonFieldType.NUMBER)
                                                .description("배우 번호"),
                                        fieldWithPath("musicals[].actors[].actorName").type(
                                                        JsonFieldType.STRING)
                                                .description("배우 이름"),
                                        fieldWithPath("musicals[].actors[].role").type(
                                                        JsonFieldType.STRING)
                                                .description("뮤지컬 배역"),

                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT)
                                                .description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER)
                                                .description("페이지 정보"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER)
                                                .description("사이즈 정보"),
                                        fieldWithPath("pageInfo.totalElements").type(
                                                        JsonFieldType.NUMBER)
                                                .description("전체 조회 건 수"),
                                        fieldWithPath("pageInfo.totalPages").type(
                                                        JsonFieldType.NUMBER)
                                                .description("전체 페이지 수"))
                        ))).andReturn();
        List list = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("$.musicals");
        assertThat(list.size()).isEqualTo(responses.size());
    }

    @Test
    @WithMockUser
    @DisplayName("필터별 작품 전체 목록 조회")
    public void getMusicalFiltersTest() throws Exception {
        //given
        MultiValueMap<String, String>
                pageMultiValueMap = new LinkedMultiValueMap<>();
        pageMultiValueMap.add("page", "1");
        pageMultiValueMap.add("size", "20");
        Page<Musical> musicalPage = new PageImpl<>(musicals,
                PageRequest.of(1, 20, Sort.by("musical_id")), 22);
        given(musicalService.findMusicalFilters(Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt())).willReturn(
                musicalPage);
        given(musicalMapper.musicalsToMusicalResponseDtos(Mockito.anyList())).willReturn(responses);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/musicals")
                                .params(pageMultiValueMap)
                                .param("sort", "musicalKorName")
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        MvcResult mvcResult = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.musicals").isArray())
                .andDo(document("get-musicals-filter",
                                getResponsePreProcessor(),
                                requestParameters(
                                        List.of(parameterWithName("sort").description(
                                                        "필터(openDate:최신순(default), musicalKorName:이름순, views:조회수순)"),
                                                parameterWithName("page").description("페이지"),
                                                parameterWithName("size").description("크기")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("musicals[].id").type(JsonFieldType.NUMBER)
                                                        .description("뮤지컬 아이디"),
                                                fieldWithPath("musicals[].musicalKorName").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 국문 이름"),
                                                fieldWithPath("musicals[].poster").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 포스터"),
                                                fieldWithPath("musicals[].actors").type(JsonFieldType.ARRAY)
                                                        .description("출연자 정보"),
                                                fieldWithPath("musicals[].actors[].id").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("배우 번호"),
                                                fieldWithPath("musicals[].actors[].actorName").type(
                                                                JsonFieldType.STRING)
                                                        .description("배우 이름"),
                                                fieldWithPath("musicals[].actors[].role").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 배역"),

                                                fieldWithPath("pageInfo").type(JsonFieldType.OBJECT)
                                                        .description("페이지 정보"),
                                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER)
                                                        .description("페이지 정보"),
                                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER)
                                                        .description("사이즈 정보"),
                                                fieldWithPath("pageInfo.totalElements").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("전체 조회 건 수"),
                                                fieldWithPath("pageInfo.totalPages").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("전체 페이지 수")
                                        )
                                )
                        )
                ).andReturn();
        List list = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("$.musicals");
        assertThat(list.size()).isEqualTo(responses.size());
    }

    @Test
    @WithMockUser
    @DisplayName("공연 상태별 작품전체 목록 조회")
    public void getMusicalStatesTest() throws Exception {
        //given
        MultiValueMap<String, String>
                pageMultiValueMap = new LinkedMultiValueMap<>();
        pageMultiValueMap.add("page", "1");
        pageMultiValueMap.add("size", "20");
        Page<Musical> musicalPage = new PageImpl<>(musicals,
                PageRequest.of(1, 20, Sort.by("musical_id")), 22);
        given(musicalService.findMusicalStates(Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt())).willReturn(
                musicalPage);
        given(musicalMapper.musicalsToMusicalResponseDtos(Mockito.anyList())).willReturn(responses);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/musicals")
                                .params(pageMultiValueMap)
                                .param("state", "MUSICAL_ONAIR")
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        MvcResult mvcResult = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.musicals").isArray())
                .andDo(document("get-musicals-state",
                                getResponsePreProcessor(),
                                requestParameters(
                                        List.of(parameterWithName("state").description(
                                                        "공연 상태(MUSICAL_ONAIR:공연중(default), MUSICAL_YET:개막예정, MUSICAL_FINISH:공연종료)"),
                                                parameterWithName("page").description("페이지"),
                                                parameterWithName("size").description("크기")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("musicals[].id").type(JsonFieldType.NUMBER)
                                                        .description("뮤지컬 아이디"),
                                                fieldWithPath("musicals[].musicalKorName").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 국문 이름"),
                                                fieldWithPath("musicals[].poster").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 포스터"),
                                                fieldWithPath("musicals[].actors").type(JsonFieldType.ARRAY)
                                                        .description("출연자 정보"),
                                                fieldWithPath("musicals[].actors[].id").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("배우 번호"),
                                                fieldWithPath("musicals[].actors[].actorName").type(
                                                                JsonFieldType.STRING)
                                                        .description("배우 이름"),
                                                fieldWithPath("musicals[].actors[].role").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 배역"),

                                                fieldWithPath("pageInfo").type(JsonFieldType.OBJECT)
                                                        .description("페이지 정보"),
                                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER)
                                                        .description("페이지 정보"),
                                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER)
                                                        .description("사이즈 정보"),
                                                fieldWithPath("pageInfo.totalElements").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("전체 조회 건 수"),
                                                fieldWithPath("pageInfo.totalPages").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("전체 페이지 수")
                                        )
                                )
                        )
                ).andReturn();
        List list = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("$.musicals");
        assertThat(list.size()).isEqualTo(responses.size());
    }

    @Test
    @WithMockUser
    @DisplayName("특정 작품 정보 조회")
    public void getMusicalTest() throws Exception {
        //given
        given(musicalService.findMusical(Mockito.anyLong())).willReturn(musical);
        given(theaterService.getTheater(Mockito.anyLong())).willReturn(theater);
        given(musicalMapper.musicalToMusicalResponseDto(Mockito.any(Musical.class))).willReturn(
                response);
        given(theaterMapper.theaterToMusicalResponse(Mockito.any(Theater.class))).willReturn(
                responseTheater);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/musicals/{musical-id}", musical.getMusicalId())
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.musical.id").value(response.getMusicalId()))
                .andExpect(jsonPath("$.musical.musicalKorName").value(response.getMusicalKorName()))
                .andExpect(jsonPath("$.musical.musicalEngName").value(response.getMusicalEngName()))
                .andExpect(jsonPath("$.musical.poster").value(response.getPoster()))
                .andExpect(jsonPath("$.musical.genre").value(response.getGenre()))
                .andExpect(jsonPath("$.musical.musicalState").value(response.getMusicalState()))
                .andExpect(jsonPath("$.musical.musicalInfo").value(response.getMusicalInfo()))
                .andExpect(jsonPath("$.musical.openDate").value(response.getOpenDate()))
                .andExpect(jsonPath("$.musical.closeDate").value(response.getCloseDate()))
                .andExpect(jsonPath("$.musical.age").value(response.getAge()))
                .andExpect(jsonPath("$.musical.runningTime").value(response.getRunningTime()))
                .andExpect(jsonPath("$.musical.intermission").value(response.getIntermission()))
                .andExpect(jsonPath("$.musical.views").value(response.getViews()))
                .andExpect(jsonPath("$.musical.theaterId").value(response.getTheaterId()))

                .andExpect(jsonPath("$.theater.theaterId").value(responseTheater.getTheaterId()))
                .andExpect(jsonPath("$.theater.theaterName").value(responseTheater.getTheaterName()))

                .andDo(document("get-musical",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        parameterWithName("musical-id").description("조회할 특정 공연 번호")
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("musical.id").type(JsonFieldType.NUMBER)
                                                        .description("뮤지컬 아이디"),
                                                fieldWithPath("musical.musicalKorName").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 국문 이름"),
                                                fieldWithPath("musical.musicalEngName").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 영문 이름"),
                                                fieldWithPath("musical.poster").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 포스터"),
                                                fieldWithPath("musical.genre").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 장르"),
                                                fieldWithPath("musical.musicalState").type(
                                                                JsonFieldType.STRING)
                                                        .description("공연 상태"),
                                                fieldWithPath("musical.musicalInfo").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 줄거리"),
                                                fieldWithPath("musical.openDate").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 시작일자"),
                                                fieldWithPath("musical.closeDate").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 종료일자"),
                                                fieldWithPath("musical.age").type(
                                                                JsonFieldType.STRING)
                                                        .description("뮤지컬 관람연령"),
                                                fieldWithPath("musical.runningTime").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("뮤지컬 공연시간"),
                                                fieldWithPath("musical.intermission").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("뮤지컬 휴게시간"),
                                                fieldWithPath("musical.views").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("뮤지컬 조회수"),
                                                fieldWithPath("musical.theaterId").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("공연 장소"),

                                                fieldWithPath("theater.theaterId").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("극장 번호"),
                                                fieldWithPath("theater.theaterName").type(
                                                                JsonFieldType.STRING)
                                                        .description("극장 이름")
                                        )
                                )
                        )
                );
    }

    @Test
    @WithMockUser
    @DisplayName("특정 작품의 게시글 조회")
    public void getBoardsTest() throws Exception {
        //given
        //musical.setMusicalId(1L);
        musicalBoards = new MusicalBoards() {
            @Override
            public String getTitle() {
                return "This Is TEST";
            }

            @Override
            public String getNickname() {
                return "테스형";
            }

            @Override
            public String getCreatedAt() {
                return "2023.03.25 15:15:15";
            }

            @Override
            public Integer getViews() {
                return 153;
            }

            @Override
            public Integer getLikes() {
                return 864;
            }
        };

        musicalBoardsList = List.of(musicalBoards);

        responseCategory = new Category() {
            @Override
            public String getCategoryName() {
                return "테스트임당";
            }
        };

        responseMusicalBoards = ResponseMusicalBoards.builder()
                .musicalId(musical.getMusicalId())
                .boards(musicalBoardsList)
                .category(responseCategory)
                .build();

        given(musicalMapper.boardsToMusicalResponseDtos(Mockito.any(),Mockito.anyList(),Mockito.any())).willReturn(responseMusicalBoards);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/musicals/{musical-id}/board", musical.getMusicalId())
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.musicalId").value(musical.getMusicalId()))

                .andExpect(jsonPath("$.boards").isArray())

                .andExpect(jsonPath("$.category.categoryName").value(responseCategory.getCategoryName()))

                .andDo(document("get-musical-boards",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        parameterWithName("musical-id").description("조회할 특정 공연 번호")
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath(".musicalId").type(JsonFieldType.NUMBER)
                                                        .description("뮤지컬 아이디"),

                                                fieldWithPath("boards[].title").type(
                                                                JsonFieldType.STRING)
                                                        .description("게시글 제목"),
                                                fieldWithPath("boards[].nickname").type(
                                                                JsonFieldType.STRING)
                                                        .description("작성자 닉네임"),
                                                fieldWithPath("boards[].createdAt").type(
                                                                JsonFieldType.STRING)
                                                        .description("게시글 작성 시간"),
                                                fieldWithPath("boards[].likes").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("게시글 좋아요 수"),
                                                fieldWithPath("boards[].views").type(
                                                                JsonFieldType.NUMBER)
                                                        .description("게시글 조회 수"),

                                                fieldWithPath("category.categoryName").type(
                                                                JsonFieldType.STRING)
                                                        .description("카테고리 이름")
                                        )
                                )
                        )
                );
    }
}