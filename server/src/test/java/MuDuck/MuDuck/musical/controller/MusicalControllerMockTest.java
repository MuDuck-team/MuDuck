package MuDuck.MuDuck.musical.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.musical.dto.ActorMusicalResponseDto;
import MuDuck.MuDuck.musical.dto.MusicalDto;
import MuDuck.MuDuck.musical.dto.MusicalDto.ResponseMusicals;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.Musical.Age;
import MuDuck.MuDuck.musical.entity.Musical.Genre;
import MuDuck.MuDuck.musical.mapper.MusicalMapper;
import MuDuck.MuDuck.musical.service.MusicalService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

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

    @BeforeEach
    void init() {
        response = MusicalDto.ResponseMusical.builder()
                .id(1L)
                .musicalKorName("테스트")
                .musicalEngName("test")
                .poster("localhosttesting")
                .genre(Genre.GENRE_ORIGINAL)
                .musicalInfo("테스트테스트")
                .openDate("2023.03.22")
                .closeDate("2023.03.22")
                .age(Age.AGE_19)
                .runningTime(168)
                .intermission(20)
                .actorMusicals(detail)
                .views(1)
                .build();

        musical = Musical.builder()
                .musicalId(1L)
                .musicalKorName("테스트")
                .musicalEngName("test")
                .poster("localhosttesting")
                .genre(Genre.GENRE_ORIGINAL)
                .musicalInfo("테스트테스트")
                .openDate("2023.03.22")
                .closeDate("2023.03.22")
                .age(Age.AGE_19)
                .runningTime(168)
                .intermission(20)
                .actorMusicals(actorMusicals)
                .views(1)
                .build();

        for(int i = 2; i <= 23; i++){

            musical.setMusicalId((long)i);
            musical.setMusicalKorName("테스트"+i);
            musical.setViews(i);

            ResponseMusicals response1 = ResponseMusicals.builder()
                    .musicalId((long)i)
                    .musicalKorName("테스트"+i)
                    .poster("localhosttesting")
                    .actorMusicals(listing)
                    .build();

            musicals.add(musical);
            responses.add(response1);

            musical.setMusicalId(1L);
        }

        actorMusical = actorMusical.builder()
                .musicalActorId(1L)
                .actor(new Actor(1L,"차지연","123123"))
                .musical(musical)
                .role("안나")
                .build();

       // listing.add(actorMusical);
    }

    @Test
    @WithMockUser
    public void findMusicalsTest() throws Exception{
        //given
        Page<Musical> musicalPage = new PageImpl<>(musicals);
        given(musicalService.findMusicals(Mockito.anyInt(),Mockito.anyInt())).willReturn(musicalPage);
        given(musicalMapper.musicalsToMusicalResponseDtos(Mockito.anyList())).willReturn(
                (List<ResponseMusicals>) response);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/musicals")
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        MvcResult mvcResult = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(document("get-musicals",
                                getResponsePreProcessor(),
                                responseFields(
                                        List.of(
                                                fieldWithPath("[].id").type(JsonFieldType.NUMBER)
                                                        .description("뮤지컬 아이디"),
                                                fieldWithPath("[].musicalKorName").type(JsonFieldType.STRING)
                                                        .description("뮤지컬 국문 이름"),
                                                fieldWithPath("[].poster").type(JsonFieldType.STRING)
                                                        .description("뮤지컬 포스터"),
                                                fieldWithPath("[].actorMusicals").type(JsonFieldType.ARRAY) // List로 받기
                                                        .description("출연자들")
                                        )
                                )
                        )
                ).andReturn();
        List list = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("$");
        assertThat(list.size()).isEqualTo(responses.size());
    }

}