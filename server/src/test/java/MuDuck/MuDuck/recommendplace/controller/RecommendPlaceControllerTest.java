package MuDuck.MuDuck.recommendplace.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getRequestPreProcessor;
import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MuDuck.MuDuck.map.dto.MapDto;
import MuDuck.MuDuck.map.entity.Map;
import MuDuck.MuDuck.map.mapper.MapMapper;
import MuDuck.MuDuck.map.service.MapService;
import MuDuck.MuDuck.member.dto.MemberDto;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.entity.Member.MemberRole;
import MuDuck.MuDuck.member.service.MemberService;
import MuDuck.MuDuck.recommendplace.dto.RecommendPlaceDto;
import MuDuck.MuDuck.recommendplace.dto.RecommendPlaceDto.Response;
import MuDuck.MuDuck.recommendplace.entity.RecommendPlace;
import MuDuck.MuDuck.recommendplace.mapper.RecommendPlaceMapper;
import MuDuck.MuDuck.response.RecommendPlaceMultipleResponse;
import com.google.gson.Gson;
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
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(RecommendPlaceController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class RecommendPlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MapMapper mapMapper;
    @MockBean
    private RecommendPlaceMapper recommendPlaceMapper;
    @MockBean
    private MapService mapService;

    @MockBean
    private MemberService memberService;
    @Autowired
    private Gson gson;
    private final String TEST_USER_EMAIL = "test@test.com";
    private final String TEST_USER_ROLE = "USER";

    @Test
    @DisplayName("한줄평 및 별점 등록 테스트")
    @WithMockUser(username = TEST_USER_EMAIL)
    void postRecommendPlace() throws Exception {

        MapDto.Post mapPost = MapDto.Post.builder()
                .theaterId(1L)
                .placeId(2L)
                .placeName("오둥이식당")
                .longitude(126.978891)
                .latitude(37.5709794)
                .categoryGroupCode("FD6")
                .phone("02-555-5555")
                .address("서울 서초구 서초동 700")
                .roadAddress("서울 서초구 남부순환로 2406")
                .placeUrl("http://place.map.kakao.com/10753713")
                .build();

        RecommendPlaceDto.Post rpPost = RecommendPlaceDto.Post.builder()
                .memberId(1L)
                .score(4.8)
                .oneLine("한줄평입네다")
                .build();

        Member member = Member
                .builder()
                .memberId(1L)
                .email(TEST_USER_EMAIL)
                .memberRole(MemberRole.USER)
                .build();

        RecommendPlace recommendPlace = RecommendPlace.builder()
                .member(member)
                .build();

        RecommendPlaceMultipleResponse multipleResponse = new RecommendPlaceMultipleResponse(mapPost, rpPost);

        String content = gson.toJson(multipleResponse);

        RecommendPlaceDto.Response rpResponse = new Response(1L,1L, 1L, 4.5,"한줄평입니다.");

        // given
        given(mapMapper.postMapToMap(Mockito.any())).willReturn(Map.builder().build());
        given(recommendPlaceMapper.postDtoToRecommendPlace(Mockito.any())).willReturn(
                recommendPlace);
        given(memberService.findByEmail(Mockito.anyString()))
                .willReturn(member);
        given(mapService.postOrGetMap(Mockito.any(), Mockito.any())).willReturn(
                RecommendPlace.builder().build());
        given(recommendPlaceMapper.recommendPlaceToResponse(Mockito.any()))
                .willReturn(rpResponse);

        // when
        ResultActions actions = mockMvc.perform(
                post("/recommend-place")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content).with(csrf())
        );

        // Rest Docs 에서 정규식 표현을 위해 선언
        // MapDto.Post 정규식
        ConstraintDescriptions mapDtoPostConstraints = new ConstraintDescriptions(MapDto.Post.class);
        List<String> theaterIdDescriptions = mapDtoPostConstraints.descriptionsForProperty("theaterId");
        List<String> placeIdDescriptions = mapDtoPostConstraints.descriptionsForProperty("placeId");
        List<String> placeUrlDescriptions = mapDtoPostConstraints.descriptionsForProperty("placeUrl");
        List<String> placeNameDescriptions = mapDtoPostConstraints.descriptionsForProperty("placeName");
        List<String> longitudeDescriptions = mapDtoPostConstraints.descriptionsForProperty("longitude");
        List<String> latitudeDescriptions = mapDtoPostConstraints.descriptionsForProperty("latitude");
        List<String> categoryGroupCodeDescriptions = mapDtoPostConstraints.descriptionsForProperty("categoryGroupCode");
        List<String> phoneDescriptions = mapDtoPostConstraints.descriptionsForProperty("phone");
//        List<String> addressDescriptions = mapDtoPostConstraints.descriptionsForProperty("address");
//        List<String> roadAddressDescriptions = mapDtoPostConstraints.descriptionsForProperty("roadAddress");
        
        // RecommendPlace.Post 정규식
        ConstraintDescriptions rpDtoPostConstraints = new ConstraintDescriptions(RecommendPlaceDto.Post.class);
        List<String> memberIdDescriptions = rpDtoPostConstraints.descriptionsForProperty("memberId");
        List<String> scoreDescriptions = rpDtoPostConstraints.descriptionsForProperty("score");
        List<String> oneLineDescriptions = rpDtoPostConstraints.descriptionsForProperty("oneLine");

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rpResponse.getId()))
                .andExpect(jsonPath("$.memberId").value(rpResponse.getMemberId()))
                .andExpect(jsonPath("$.mapId").value(rpResponse.getMapId()))
                .andExpect(jsonPath("$.score").value(rpResponse.getScore()))
                .andExpect(jsonPath("$.oneLine").value(rpResponse.getOneLine()))
                .andDo(document("post-recommendPlace",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                          List.of(
                                  fieldWithPath("map").type(JsonFieldType.OBJECT).description("해당 지도 정보").attributes(key("regexp").value("")),
                                  fieldWithPath("map.theaterId").type(JsonFieldType.NUMBER).description("해당 지도의 극장 아이디").attributes(key("regexp").value(theaterIdDescriptions)),
                                  fieldWithPath("map.placeId").type(JsonFieldType.NUMBER).description("해당 지도의 카카오 아이디").attributes(key("regexp").value(placeIdDescriptions)),
                                  fieldWithPath("map.placeName").type(JsonFieldType.STRING).description("해당 지도의 이름").attributes(key("regexp").value(placeNameDescriptions)),
                                  fieldWithPath("map.longitude").type(JsonFieldType.NUMBER).description("해당 지도의 경도").attributes(key("regexp").value(longitudeDescriptions)),
                                  fieldWithPath("map.latitude").type(JsonFieldType.NUMBER).description("해당 지도의 위도").attributes(key("regexp").value(latitudeDescriptions)),
                                  fieldWithPath("map.categoryGroupCode").type(JsonFieldType.STRING).description("해당 지도의 카테고리 코드").attributes(key("regexp").value(categoryGroupCodeDescriptions)),
                                  fieldWithPath("map.phone").type(JsonFieldType.STRING).description("해당 지도의 전화번호").attributes(key("regexp").value(phoneDescriptions)),
                                  fieldWithPath("map.address").type(JsonFieldType.STRING).description("해당 지도의 지번주소").attributes(key("regexp").value("")),
                                  fieldWithPath("map.roadAddress").type(JsonFieldType.STRING).description("해당 지도의 도로명주소").attributes(key("regexp").value("")),
                                  fieldWithPath("map.placeUrl").type(JsonFieldType.STRING).description("해당 지도의 상세정보 URL").attributes(key("regexp").value(placeUrlDescriptions)),
                                  fieldWithPath("recommendPlace").type(JsonFieldType.OBJECT).description("해당 한줄평 및 평점 정보").attributes(key("regexp").value("")),
                                  fieldWithPath("recommendPlace.memberId").type(JsonFieldType.NUMBER).description("작성한 회원 아이디").attributes(key("regexp").value(memberIdDescriptions)),
                                  fieldWithPath("recommendPlace.score").type(JsonFieldType.NUMBER).description("작성한 별점").attributes(key("regexp").value(scoreDescriptions)),
                                  fieldWithPath("recommendPlace.oneLine").type(JsonFieldType.STRING).description("작성한 한줄평").attributes(key("regexp").value(oneLineDescriptions))
                          )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("해당 한줄평 아이디"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("해당 회원 아이디"),
                                        fieldWithPath("mapId").type(JsonFieldType.NUMBER).description("해당 지도 아이디"),
                                        fieldWithPath("score").type(JsonFieldType.NUMBER).description("내가 설정한 별점"),
                                        fieldWithPath("oneLine").type(JsonFieldType.STRING).description("내가 작성한 한줄평")
                                )
                            )
                        )
                );
    }
}