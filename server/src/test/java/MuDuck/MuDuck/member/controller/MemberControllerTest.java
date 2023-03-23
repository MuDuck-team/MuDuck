package MuDuck.MuDuck.member.controller;

import static MuDuck.MuDuck.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.member.dto.MemberDto;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.entity.Member.MemberRole;
import MuDuck.MuDuck.member.mapper.MemberMapper;
import MuDuck.MuDuck.member.service.MemberService;
import com.google.gson.Gson;
import java.util.List;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper memberMapper;

    @Autowired
    private Gson gson;

    private final String TEST_USER_EMAIL = "test@test.com";
    private final String TEST_USER_ROLE = "USER";

    private Object principal = new Object();

    private UserDetails userDetails = null;
    private String username = "";

    private MemberDto.Response response;

    private Member member;

    @BeforeEach
    void init(){
        principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails = (UserDetails)principal;
        username = userDetails.getUsername();

        response = MemberDto.Response.builder()
                .id(1L)
                .nickname("김오둥")
                .profileImageUrl("http://k.kakaocdn.net/dn/uGDxN/btrNu67LG5T/tlsvzNzHBY0Ly9kbJ3IYOk/img_640x640.jpg")
                .role("ROLE_USER")
                .build();

        member = Member.builder()
                .memberId(1L)
                .email(TEST_USER_EMAIL)
                .nickName("김오둥")
                .picture("http://k.kakaocdn.net/dn/uGDxN/btrNu67LG5T/tlsvzNzHBY0Ly9kbJ3IYOk/img_640x640.jpg")
                .memberRole(MemberRole.USER)
                .build();
    }

    @Test
    @WithMockUser(username = TEST_USER_EMAIL,roles = TEST_USER_ROLE)
    @DisplayName("로그인된 유저 일 경우 로그인된 유저 정보 가져오기")
    void getMyInfo() throws Exception {
        //given init 에서 진행
        given(memberService.findByEmail(Mockito.anyString()))
                .willReturn(Member.builder().build());
        given(memberMapper.memberToResponseDto(Mockito.any()))
                .willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/members/my-info")
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        assertThat(username).isEqualTo(TEST_USER_EMAIL);
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.nickname").value(response.getNickname()))
                .andExpect(jsonPath("$.profileImageUrl").value(response.getProfileImageUrl()))
                .andExpect(jsonPath("$.role").value(response.getRole()))
                .andDo(document(
                        "get-my-info",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원 아이디"),
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("회원 닉네임"),
                                        fieldWithPath("profileImageUrl").type(JsonFieldType.STRING).description("프로필사진 경로"),
                                        fieldWithPath("role").type(JsonFieldType.STRING).description("회원 역할")
                                )
                        )
                ));

    }

    @Test
    @WithMockUser(username = TEST_USER_EMAIL,roles = TEST_USER_ROLE)
    @DisplayName("로그인한 본인 아이디로 정보를 가져올 경우")
    void getMember() throws Exception {
        // given
        long memberId = 1L;
        given(memberService.getMember(Mockito.anyLong()))
                .willReturn(member);
        given(memberMapper.memberToResponseDto(Mockito.any()))
                .willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/members/{member-id}", memberId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        assertThat(username).isEqualTo(TEST_USER_EMAIL);
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.nickname").value(response.getNickname()))
                .andExpect(jsonPath("$.profileImageUrl").value(response.getProfileImageUrl()))
                .andExpect(jsonPath("$.role").value(response.getRole()))
                .andDo(document(
                        "get-member",
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원 아이디"),
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("회원 닉네임"),
                                        fieldWithPath("profileImageUrl").type(JsonFieldType.STRING).description("프로필사진 경로"),
                                        fieldWithPath("role").type(JsonFieldType.STRING).description("회원 역할")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("회원 정보 요청 시 로그인한 아이디와 다른 아이디로 정보를 가져올 경우 테스트")
    @WithMockUser(username = "test2@test.com", roles = TEST_USER_ROLE)
    void getMemberException() throws Exception {
        // given
        long memberId = 1L;

        given(memberService.getMember(Mockito.anyLong()))
                .willReturn(member);

        given(memberMapper.memberToResponseDto(Mockito.any()))
                .willReturn(response);

        // when / then
        ResultActions actions =
                mockMvc.perform(
                        get("/members/{member-id}", memberId)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(
                        // assert로 예외를 검사하는 람다 사용
                        (result) -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(
                                BusinessLogicException.class)));

    }

    @Test
    @WithMockUser(username = TEST_USER_EMAIL, roles = TEST_USER_ROLE)
    @DisplayName("회원 정보 수정 테스트")
    void patchMember() throws Exception {
        long memberId = 1L;

        MemberDto.Patch patch = MemberDto.Patch.builder()
                .nickname("김오둥")
                .profileImageUrl("http://k.kakaocdn.net/dn/uGDxN/btrNu67LG5T/tlsvzNzHBY0Ly9kbJ3IYOk/img_640x640.jpg")
                .build();

        String content = gson.toJson(patch);

        // given
        given(memberService.getMember(Mockito.anyLong()))
                .willReturn(member);
        // mapper는  나중에 따로 테스트를 해보자.
        given(memberMapper.memberPatchDtoToMember(Mockito.any()))
                .willReturn(Member.builder().build());
        given(memberService.updateMember(Mockito.any()))
                .willReturn(Member.builder().build());
        given(memberMapper.memberToResponseDto(Mockito.any()))
                .willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/members/{member-id}", memberId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

        // Rest Docs 에서 정규식 표현을 위해 선언
        ConstraintDescriptions patchMemberConstraints = new ConstraintDescriptions(MemberDto.Patch.class);
        List<String> nicknameDescriptions = patchMemberConstraints.descriptionsForProperty("nickname");
        List<String> profileImageUrlDescriptions = patchMemberConstraints.descriptionsForProperty("profileImageUrl");

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.nickname").value(response.getNickname()))
                .andExpect(jsonPath("$.profileImageUrl").value(response.getProfileImageUrl()))
                .andExpect(jsonPath("$.role").value(response.getRole()))
                .andDo(document("update-member",
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                    fieldWithPath("nickname").type(JsonFieldType.STRING).description("변경할 닉네임").optional().attributes(key("regexp").value(nicknameDescriptions)),
                                    fieldWithPath("profileImageUrl").type(JsonFieldType.STRING).description("변경할 프로필 사진 경로").optional().attributes(key("regexp").value(profileImageUrlDescriptions))
                                )
                            ),
                        responseFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원 아이디"),
                                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("회원 닉네임"),
                                        fieldWithPath("profileImageUrl").type(JsonFieldType.STRING).description("프로필사진 경로"),
                                        fieldWithPath("role").type(JsonFieldType.STRING).description("회원 역할")
                                )

                            )
                        )
                );

    }

    @Test
    @WithMockUser(username = "test2@test.com", roles = TEST_USER_ROLE)
    @DisplayName("회원정보 수정 시 로그인된 회원아이디와 요청아이디가 다를 경우 테스트")
    void patchMemberExceptionTest() throws Exception {
        long memberId = 1L;

        MemberDto.Patch patch = MemberDto.Patch.builder()
                .nickname("김오둥")
                .profileImageUrl("http://k.kakaocdn.net/dn/uGDxN/btrNu67LG5T/tlsvzNzHBY0Ly9kbJ3IYOk/img_640x640.jpg")
                .build();

        String content = gson.toJson(patch);

        // given
        given(memberService.getMember(Mockito.anyLong()))
                .willReturn(member);
        // mapper는  나중에 따로 테스트를 해보자.
        given(memberMapper.memberPatchDtoToMember(Mockito.any()))
                .willReturn(Member.builder().build());
        given(memberService.updateMember(Mockito.any()))
                .willReturn(Member.builder().build());
        given(memberMapper.memberToResponseDto(Mockito.any()))
                .willReturn(response);

        // when / then
        ResultActions actions =
                mockMvc.perform(
                        patch("/members/{member-id}", memberId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                ).andExpect(

                        (result) -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(
                                BusinessLogicException.class)));;
    }
}