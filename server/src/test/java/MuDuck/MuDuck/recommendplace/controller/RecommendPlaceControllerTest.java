package MuDuck.MuDuck.recommendplace.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getRequestPreProcessor;
import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import MuDuck.MuDuck.map.dto.MapDto;
import MuDuck.MuDuck.map.entity.Map;
import MuDuck.MuDuck.map.mapper.MapMapper;
import MuDuck.MuDuck.map.service.MapService;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.entity.Member.MemberRole;
import MuDuck.MuDuck.member.service.MemberService;
import MuDuck.MuDuck.recommendplace.dto.RecommendPlaceDto;
import MuDuck.MuDuck.recommendplace.dto.RecommendPlaceDto.Patch;
import MuDuck.MuDuck.recommendplace.dto.RecommendPlaceDto.Response;
import MuDuck.MuDuck.recommendplace.entity.RecommendPlace;
import MuDuck.MuDuck.recommendplace.mapper.RecommendPlaceMapper;
import MuDuck.MuDuck.recommendplace.service.RecommendPlaceService;
import MuDuck.MuDuck.response.RecommendPlaceMultipleResponse;
import com.google.gson.Gson;
import java.util.List;
import java.util.Objects;
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
    private Member member;
    private RecommendPlaceDto.Response rpResponse;
    @MockBean
    private RecommendPlaceService recommendPlaceService;

    @BeforeEach
    void init(){
        member = Member
                .builder()
                .memberId(1L)
                .email(TEST_USER_EMAIL)
                .memberRole(MemberRole.USER)
                .build();

        rpResponse = new Response(1L,1L, 1L, 4.5,"한줄평입니다.");
    }

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

        RecommendPlace recommendPlace = RecommendPlace.builder()
                .member(member)
                .build();

        RecommendPlaceMultipleResponse multipleResponse = new RecommendPlaceMultipleResponse(mapPost, rpPost);

        String content = gson.toJson(multipleResponse);


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

    @Test
    @DisplayName("평점및 한줄평 등록 시 로그인된 회원과 요청 회원이 다를 경우")
    @WithMockUser
    void postRecommendPlaceException() throws Exception {
        // given
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
                .memberId(2L)
                .score(4.8)
                .oneLine("한줄평입네다")
                .build();

        RecommendPlace recommendPlace = RecommendPlace.builder()
                .member(Member.builder().memberId(2L).build())
                .build();

        RecommendPlaceMultipleResponse multipleResponse = new RecommendPlaceMultipleResponse(mapPost, rpPost);


        String content = gson.toJson(multipleResponse);

        given(mapMapper.postMapToMap(Mockito.any())).willReturn(Map.builder().build());
        given(recommendPlaceMapper.postDtoToRecommendPlace(Mockito.any())).willReturn(
                recommendPlace);
        given(memberService.findByEmail(Mockito.anyString()))
                .willReturn(member);
        // when / then
        ResultActions actions =
                mockMvc.perform(
                        post("/recommend-place")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content).with(csrf())
                        )
                        .andExpect(status().isUnauthorized())
                        .andExpect(
                                (result) ->
                                        assertTrue(result.getResolvedException() instanceof BusinessLogicException))
                        .andExpect(
                                (result) ->
                                        assertEquals(ExceptionCode.NOT_SAME_USER.getMessage(), result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("mapId와 memberId로 한줄평 조회")
    @WithMockUser(username = TEST_USER_EMAIL)
    void getRecommendPlaceTest() throws Exception {
        long placeId = 1L;
        // given
        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);
        given(mapService.findMapToPlaceId(Mockito.anyLong())).willReturn(Map.builder().build());
        given(recommendPlaceService.findRecommendPlaceToMemberIdAndMapId(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(RecommendPlace.builder().build());
        given(recommendPlaceMapper.recommendPlaceToResponse(Mockito.any())).willReturn(rpResponse);

        // when
        ResultActions getActions =
                mockMvc.perform(
                        get("/recommend-place/maps/{place-id}/members/{member-id}", placeId, member.getMemberId())
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        getActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rpResponse.getId()))
                .andExpect(jsonPath("$.memberId").value(rpResponse.getMemberId()))
                .andExpect(jsonPath("$.mapId").value(rpResponse.getMapId()))
                .andExpect(jsonPath("$.score").value(rpResponse.getScore()))
                .andExpect(jsonPath("$.oneLine").value(rpResponse.getOneLine()))
                .andDo(document("get-recommend-place",
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("place-id").description("해당 장소 카카오맵 API 아이디"),
                                parameterWithName("member-id").description("회원 아이디")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("한줄평 아이디"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 아이디"),
                                        fieldWithPath("mapId").type(JsonFieldType.NUMBER).description("지도 아이디"),
                                        fieldWithPath("score").type(JsonFieldType.NUMBER).description("별점(평점)"),
                                        fieldWithPath("oneLine").type(JsonFieldType.STRING).description("한줄평")
                                )
                            )
                        )
                );
    }

    @Test
    @DisplayName("평점 및 한줄평 조회시 로그인된 회원과 요청 회원이 다를 경우")
    @WithMockUser
    void getRecommendPlaceExceptionTest() throws Exception {
        // given
        long placeId = 1L;
        long anotherId = 2L;
        given(memberService.findByEmail(Mockito.anyString()))
                .willReturn(member);
        given(mapService.findMapToPlaceId(Mockito.anyLong())).willReturn(Map.builder().build());
        given(recommendPlaceService.findRecommendPlaceToMemberIdAndMapId(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(RecommendPlace.builder().build());
        given(recommendPlaceMapper.recommendPlaceToResponse(Mockito.any())).willReturn(rpResponse);

        // when / then
        ResultActions getActions =
                mockMvc.perform(
                        get("/recommend-place/maps/{place-id}/members/{member-id}", placeId, anotherId)
                                .accept(MediaType.APPLICATION_JSON)
                )
                        .andExpect(status().isUnauthorized())
                        .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessLogicException))
                        .andExpect(result -> assertEquals(ExceptionCode.NOT_SAME_USER.getMessage(), result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("평점 및 한줄평 업데이트 테스트")
    @WithMockUser(username = TEST_USER_EMAIL)
    void patchRecommendPlaceTest() throws Exception {
        // given
        long rpId = 1L;
        long mapId = 1L;
        RecommendPlace recommendPlace = RecommendPlace.builder().member(member).build();

        RecommendPlaceDto.Patch patch = new Patch(4.5, "한줄평입니다.");

        String content = gson.toJson(patch);

        given(recommendPlaceMapper.patchDtoToRecommendPlace(Mockito.any())).willReturn(RecommendPlace.builder().build());
        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);
        given(recommendPlaceService.findRecommendPlaceToMemberIdAndMapId(Mockito.anyLong(),Mockito.anyLong()))
                .willReturn(recommendPlace);

        given(recommendPlaceService.updateRecommendPlace(Mockito.any())).willReturn(RecommendPlace.builder().build());
        given(recommendPlaceMapper.recommendPlaceToResponse(Mockito.any())).willReturn(rpResponse);

        // when
        ResultActions actions = mockMvc.perform(
                patch("/recommend-place/{recommend-place-id}/maps/{map-id}/members/{member-id}",
                        rpId, mapId, recommendPlace.getMember().getMemberId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content).with(csrf())
        );
        
        // RecommendPlaceDto.Patch 정규식 표현
        ConstraintDescriptions rpPatchDtoConstraints = new ConstraintDescriptions(MapDto.Post.class);
        List<String> scoreDescriptions = rpPatchDtoConstraints.descriptionsForProperty("score");
        List<String> oneLineIdDescriptions = rpPatchDtoConstraints.descriptionsForProperty("oneLine");

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(rpResponse.getId()))
                .andExpect(jsonPath("$.memberId").value(rpResponse.getMemberId()))
                .andExpect(jsonPath("$.mapId").value(rpResponse.getMapId()))
                .andExpect(jsonPath("$.score").value(rpResponse.getScore()))
                .andExpect(jsonPath("$.oneLine").value(rpResponse.getOneLine()))
                .andDo(document(
                        "update-recommend-place",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                List.of(
                                        parameterWithName("recommend-place-id").description("변경할 평점및 한줄평 아이디"),
                                        parameterWithName("map-id").description("선택한 지도 아이디"),
                                        parameterWithName("member-id").description("작성하는 회원의 아이디")
                                )
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("score").type(JsonFieldType.NUMBER).description("변경할 평점").attributes(key("regexp").value(scoreDescriptions)),
                                        fieldWithPath("oneLine").type(JsonFieldType.STRING).description("변경할 평점").attributes(key("regexp").value(oneLineIdDescriptions))
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("해당 평점 및 한줄평의 아이디"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 아이디"),
                                        fieldWithPath("mapId").type(JsonFieldType.NUMBER).description("선택한 지도 아이디"),
                                        fieldWithPath("score").type(JsonFieldType.NUMBER).description("변경된 평점"),
                                        fieldWithPath("oneLine").type(JsonFieldType.STRING).description("변경된 한줄평")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("평점 및 한줄평 업데이트시 로그인 된 유저와 요청 유저가 다를 경우")
    @WithMockUser
    void patchRecommendPlaceExceptionTest() throws Exception {
        // given
        long rpId = 1L;
        long mapId = 1L;
        long anotherId = 2L;
        RecommendPlace recommendPlace = RecommendPlace.builder().member(member).build();

        RecommendPlaceDto.Patch patch = new Patch(4.5, "한줄평입니다.");

        String content = gson.toJson(patch);

        given(recommendPlaceMapper.patchDtoToRecommendPlace(Mockito.any())).willReturn(RecommendPlace.builder().build());
        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);
        given(recommendPlaceService.findRecommendPlaceToMemberIdAndMapId(Mockito.anyLong(),Mockito.anyLong()))
                .willReturn(recommendPlace);

        given(recommendPlaceService.updateRecommendPlace(Mockito.any())).willReturn(RecommendPlace.builder().build());
        given(recommendPlaceMapper.recommendPlaceToResponse(Mockito.any())).willReturn(rpResponse);

        // when / then
        ResultActions actions = mockMvc.perform(
                patch("/recommend-place/{recommend-place-id}/maps/{map-id}/members/{member-id}",
                        rpId, mapId, anotherId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content).with(csrf())
        )
                .andExpect(status().isUnauthorized())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof BusinessLogicException))
                .andExpect(result -> assertEquals(ExceptionCode.NOT_SAME_USER.getMessage(),
                        result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("변경 요청한 글이 본인 글이 아닐 경우 테스트")
    @WithMockUser
    void patchRecommendPlaceExceptionTest2() throws Exception {
        // given
        long rpId = 1L;
        long mapId = 1L;
        Member anotherMember = Member.builder().memberId(2L).build();
        RecommendPlace recommendPlace = RecommendPlace.builder().member(anotherMember).build();

        RecommendPlaceDto.Patch patch = new Patch(4.5, "한줄평입니다.");

        String content = gson.toJson(patch);

        given(recommendPlaceMapper.patchDtoToRecommendPlace(Mockito.any())).willReturn(RecommendPlace.builder().build());
        given(memberService.findByEmail(Mockito.anyString())).willReturn(member);
        given(recommendPlaceService.findRecommendPlaceToMemberIdAndMapId(Mockito.anyLong(),Mockito.anyLong()))
                .willReturn(recommendPlace);

        given(recommendPlaceService.updateRecommendPlace(Mockito.any())).willReturn(RecommendPlace.builder().build());
        given(recommendPlaceMapper.recommendPlaceToResponse(Mockito.any())).willReturn(rpResponse);

        // when / then
        ResultActions actions = mockMvc.perform(
                patch("/recommend-place/{recommend-place-id}/maps/{map-id}/members/{member-id}",
                        rpId, mapId, recommendPlace.getMember().getMemberId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content).with(csrf())
        )
                .andExpect(status().isUnauthorized())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof BusinessLogicException))
                .andExpect(result -> assertEquals(ExceptionCode.NOT_SAME_USER.getMessage(),
                        result.getResolvedException().getMessage()));
    }
}