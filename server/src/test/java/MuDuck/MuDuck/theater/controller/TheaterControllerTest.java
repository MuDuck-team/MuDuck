package MuDuck.MuDuck.theater.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MuDuck.MuDuck.theater.dto.TheaterDto;
import MuDuck.MuDuck.theater.dto.TheaterDto.Response;
import MuDuck.MuDuck.theater.entitiy.Theater;
import MuDuck.MuDuck.theater.mapper.TheaterMapper;
import MuDuck.MuDuck.theater.service.TheaterService;
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
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(TheaterController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class TheaterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TheaterService theaterService;

    @MockBean
    private TheaterMapper theaterMapper;

    @Autowired
    private Gson gson;

    private Theater theater;

    private List<Theater> theaterList = new ArrayList<>();

    private TheaterDto.Response response;

    private List<TheaterDto.Response> responses = new ArrayList<>();

    @BeforeEach
    void init(){

        response = TheaterDto.Response.builder()
                .id(1L)
                .placeName("오둥이극장")
                .longitude(12.1234)
                .latitude(13.1234)
                .phone("02-555-555")
                .address("서울시 어쩌구 저쩌구")
                .roadAddress("서울시 어쩌구 저쩌구")
                .build();

        theater = Theater.builder()
                .theaterId(1L)
                .placeName("오둥이극장")
                .longitude(12.1234)
                .latitude(13.1234)
                .phone("02-555-5555")
                .address("서울시 어쩌구 저쩌구")
                .roadAddress("서울시 어쩌구 저쩌구")
                .build();

        for(int i = 1; i <= 4; i++){

            theater.setTheaterId((long)i);
            theater.setPlaceName("오둥이극장"+i);

            Response response1 = Response.builder()
                    .id((long)i)
                    .placeName("오둥이극장"+i)
                    .longitude(12.1234)
                    .latitude(13.1234)
                    .phone("02-555-555")
                    .address("서울시 어쩌구 저쩌구")
                    .roadAddress("서울시 어쩌구 저쩌구")
                    .build();

            theaterList.add(theater);
            responses.add(response1);

            theater.setTheaterId(1L);
        }
    }

    @Test
    @WithMockUser
    void getTheaters() throws Exception {
        // given
        given(theaterService.getTheaters()).willReturn(new ArrayList<>());
        given(theaterMapper.theaterListToTheaterResponseList(Mockito.anyList()))
                .willReturn(responses);
        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/theaters")
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        MvcResult mvcResult = actions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(document("get-theaters",
                                getResponsePreProcessor(),
                                responseFields(
                                        List.of(
                                                fieldWithPath("[].id").type(JsonFieldType.NUMBER)
                                                        .description("극장 아이디"),
                                                fieldWithPath("[].placeName").type(JsonFieldType.STRING)
                                                        .description("극장 이름"),
                                                fieldWithPath("[].longitude").type(JsonFieldType.NUMBER)
                                                        .description("극장 경도"),
                                                fieldWithPath("[].latitude").type(JsonFieldType.NUMBER)
                                                        .description("극장 위도"),
                                                fieldWithPath("[].phone").type(JsonFieldType.STRING)
                                                        .description("극장 번호"),
                                                fieldWithPath("[].address").type(JsonFieldType.STRING)
                                                        .description("극장 지번 주소"),
                                                fieldWithPath("[].roadAddress").type(JsonFieldType.STRING)
                                                        .description("극장 도로명 주소")
                                        )
                                )
                        )
                ).andReturn();
        List list = JsonPath.parse(mvcResult.getResponse().getContentAsString()).read("$");
        assertThat(list.size()).isEqualTo(responses.size());
    }

    @Test
    @WithMockUser
    void getTheater() throws Exception {
        // given
        given(theaterService.getTheater(Mockito.anyLong()))
                .willReturn(theater);
        given(theaterMapper.theaterToTheaterResponse(Mockito.any(Theater.class)))
                .willReturn(response);
        // when
        ResultActions actions =
                mockMvc.perform(
                  get("/theaters/{theater-id}", theater.getTheaterId())
                          .accept(MediaType.APPLICATION_JSON)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.placeName").value(response.getPlaceName()))
                .andExpect(jsonPath("$.longitude").value(response.getLongitude()))
                .andExpect(jsonPath("$.latitude").value(response.getLatitude()))
                .andExpect(jsonPath("$.phone").value(response.getPhone()))
                .andExpect(jsonPath("$.address").value(response.getAddress()))
                .andExpect(jsonPath("$.roadAddress").value(response.getRoadAddress()))
                .andDo(document("get-theater",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                              parameterWithName("theater-id").description("극장 아이디(식별자)")
                            ),
                        responseFields(
                            List.of(
                                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("극장 아이디"),
                                    fieldWithPath("placeName").type(JsonFieldType.STRING).description("극장 이름"),
                                    fieldWithPath("longitude").type(JsonFieldType.NUMBER).description("극장 경도"),
                                    fieldWithPath("latitude").type(JsonFieldType.NUMBER).description("극장 위도"),
                                    fieldWithPath("phone").type(JsonFieldType.STRING).description("극장 번호"),
                                    fieldWithPath("address").type(JsonFieldType.STRING).description("극장 지번 주소"),
                                    fieldWithPath("roadAddress").type(JsonFieldType.STRING).description("극장 도로명 주소")
                                )
                            )
                        )
                );
    }

}