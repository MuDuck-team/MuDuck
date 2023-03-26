package MuDuck.MuDuck.map.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MuDuck.MuDuck.map.dto.AvgDto;
import MuDuck.MuDuck.map.dto.AvgDto.Response;
import MuDuck.MuDuck.map.groupcode.GroupCode;
import MuDuck.MuDuck.map.mapper.MapMapper;
import MuDuck.MuDuck.map.service.MapService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
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

@WebMvcTest(MapController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MapControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MapService mapService;

    @MockBean
    private MapMapper mapMapper;
    private Map<String, List<AvgDto.Response>> responseMap = new HashMap<>();

    @BeforeEach
    void init(){
        for(GroupCode gc:GroupCode.values()){

            List<AvgDto.Response> responses = new ArrayList<>();

            for(int i = 1; i <= 5; i++){

                AvgDto.Response avgResponse = new Response(i, i,
                        "오둥이"+gc.getName()+i, "서울시 어쩌구 저쩌구",
                        "도로명 주소입니다.", "02-555-5555",
                        4.6, 10,
                        126.973265, 37.572695,
                        "http://place.map.kakao.com/1934809750", gc.getCode());

                responses.add(avgResponse);
            }

            responseMap.put(gc.getName(), responses);
        }

    }

    @Test
    @WithMockUser
    @DisplayName("해당 극장 아이디를 가진 지도 정보 get Test")
    void getMapsToTheaterId() throws Exception {
        long theaterId = 1L;
        // given
        given(mapService.getMapsWithAvgScoreAndTotalReviews(Mockito.anyLong()))
                .willReturn(new HashMap<>());

        given(mapMapper.mapAvgEntity(Mockito.anyMap())).willReturn(responseMap);

        // when
        ResultActions actions = mockMvc.perform(
                get("/maps/theater/{theater-id}", theaterId)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.restaurants").isArray())
                .andExpect(jsonPath("$.cafes").isArray())
                .andExpect(jsonPath("$.parkings").isArray())
                .andDo(document(
                        "get-map-include-avg-score-total-reviews",
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("theater-id").description("극장 아이디")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("cafes").type(JsonFieldType.ARRAY).description("별점 기준 상위 5개 카페 정보 배열"),
                                        fieldWithPath("restaurants").type(JsonFieldType.ARRAY).description("별점 기준 상위 5개 음식점 정보 배열"),
                                        fieldWithPath("parkings").type(JsonFieldType.ARRAY).description("별점 기준 상위 5개 주자창 정보 배열"),
                                        fieldWithPath("cafes[].id").type(JsonFieldType.NUMBER).description("해당 카페정보의 아이디"),
                                        fieldWithPath("cafes[].placeId").type(JsonFieldType.NUMBER).description("해당 카페의 카카오 맵 아이디"),
                                        fieldWithPath("cafes[].name").type(JsonFieldType.STRING).description("해당 카페정보의 이름"),
                                        fieldWithPath("cafes[].address").type(JsonFieldType.STRING).description("해당 카페정보의 주소"),
                                        fieldWithPath("cafes[].roadAddress").type(JsonFieldType.STRING).description("해당 카페정보의 도로명주소"),
                                        fieldWithPath("cafes[].phone").type(JsonFieldType.STRING).description("해당 카페정보의 전화번호"),
                                        fieldWithPath("cafes[].avgScore").type(JsonFieldType.NUMBER).description("해당 카페정보의 평균별점"),
                                        fieldWithPath("cafes[].totalReviews").type(JsonFieldType.NUMBER).description("해당 카페정보의 총 리뷰수"),
                                        fieldWithPath("cafes[].longitude").type(JsonFieldType.NUMBER).description("해당 카페정보의 경도"),
                                        fieldWithPath("cafes[].latitude").type(JsonFieldType.NUMBER).description("해당 카페정보의 위도"),
                                        fieldWithPath("cafes[].placeUrl").type(JsonFieldType.STRING).description("해당 카페정보의 상세정보 Url"),
                                        fieldWithPath("cafes[].categoryGroupCode").type(JsonFieldType.STRING).description("해당 카페정보의 카테고리 코드 "
                                                + "CE7 : 카페 | FD6 : 음식점 | PK6 : 주자창"),
                                        fieldWithPath("restaurants[].id").type(JsonFieldType.NUMBER).description("해당 음식점정보의 아이디"),
                                        fieldWithPath("restaurants[].placeId").type(JsonFieldType.NUMBER).description("해당 음식점정보의 카카오 맵 아이디"),
                                        fieldWithPath("restaurants[].name").type(JsonFieldType.STRING).description("해당 음식점정보의 이름"),
                                        fieldWithPath("restaurants[].address").type(JsonFieldType.STRING).description("해당 음식점정보의 주소"),
                                        fieldWithPath("restaurants[].roadAddress").type(JsonFieldType.STRING).description("해당 음식점정보의 도로명주소"),
                                        fieldWithPath("restaurants[].phone").type(JsonFieldType.STRING).description("해당 음식점정보의 전화번호"),
                                        fieldWithPath("restaurants[].avgScore").type(JsonFieldType.NUMBER).description("해당 음식점정보의 평균별점"),
                                        fieldWithPath("restaurants[].totalReviews").type(JsonFieldType.NUMBER).description("해당 음식점정보의 총 리뷰수"),
                                        fieldWithPath("restaurants[].longitude").type(JsonFieldType.NUMBER).description("해당 음식점정보의 경도"),
                                        fieldWithPath("restaurants[].latitude").type(JsonFieldType.NUMBER).description("해당 음식점정보의 위도"),
                                        fieldWithPath("restaurants[].placeUrl").type(JsonFieldType.STRING).description("해당 음식점정보의 상세정보 Url"),
                                        fieldWithPath("restaurants[].categoryGroupCode").type(JsonFieldType.STRING).description("해당 음식점정보의 카테고리 코드 "
                                                + "CE7 : 카페 | FD6 : 음식점 | PK6 : 주자창"),
                                        fieldWithPath("parkings[].id").type(JsonFieldType.NUMBER).description("해당 주자창정보의 아이디"),
                                        fieldWithPath("parkings[].placeId").type(JsonFieldType.NUMBER).description("해당 주자창정보의 카카오 맵 아이디"),
                                        fieldWithPath("parkings[].name").type(JsonFieldType.STRING).description("해당 주자창정보의 이름"),
                                        fieldWithPath("parkings[].address").type(JsonFieldType.STRING).description("해당 주자창정보의 주소"),
                                        fieldWithPath("parkings[].roadAddress").type(JsonFieldType.STRING).description("해당 주자창정보의 도로명주소"),
                                        fieldWithPath("parkings[].phone").type(JsonFieldType.STRING).description("해당 주자창정보의 전화번호"),
                                        fieldWithPath("parkings[].avgScore").type(JsonFieldType.NUMBER).description("해당 주자창정보의 평균별점"),
                                        fieldWithPath("parkings[].totalReviews").type(JsonFieldType.NUMBER).description("해당 주자창정보의 총 리뷰수"),
                                        fieldWithPath("parkings[].longitude").type(JsonFieldType.NUMBER).description("해당 주자창정보의 경도"),
                                        fieldWithPath("parkings[].latitude").type(JsonFieldType.NUMBER).description("해당 주자창정보의 위도"),
                                        fieldWithPath("parkings[].placeUrl").type(JsonFieldType.STRING).description("해당 주자창정보의 상세정보 Url"),
                                        fieldWithPath("parkings[].categoryGroupCode").type(JsonFieldType.STRING).description("해당 주자창정보의 카테고리 코드 "
                                                + "CE7 : 카페 | FD6 : 음식점 | PK6 : 주자창")
                                )
                        )
                ));

    }
}