package MuDuck.MuDuck.recommendplace.controller;

import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import MuDuck.MuDuck.map.dto.MapDto;
import MuDuck.MuDuck.map.entity.Map;
import MuDuck.MuDuck.map.mapper.MapMapper;
import MuDuck.MuDuck.map.service.MapService;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.service.MemberService;
import MuDuck.MuDuck.recommendplace.dto.RecommendPlaceDto;
import MuDuck.MuDuck.recommendplace.entity.RecommendPlace;
import MuDuck.MuDuck.recommendplace.mapper.RecommendPlaceMapper;
import MuDuck.MuDuck.response.RecommendPlaceMultipleResponse;
import java.security.Principal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommend-place")
@RequiredArgsConstructor
@Validated
public class RecommendPlaceController {
    private final MapMapper mapMapper;
    private final RecommendPlaceMapper recommendPlaceMapper;
    private final MapService mapService;
    private final MemberService memberService;
    @PostMapping
    public ResponseEntity postRecommendPlace(@RequestBody @Valid RecommendPlaceMultipleResponse post, Principal principal){

        // 각각 객체를 생성
        RecommendPlaceDto.Post recommendPlacePost = post.getRecommendPlace();

        MapDto.Post postMap = post.getMap();

        // entity 로 변경
        Map mapEntity = mapMapper.postMapToMap(postMap);

        RecommendPlace recommendPlaceEntity = recommendPlaceMapper.postDtoToRecommendPlace(recommendPlacePost);

        // 해당 로그인 유저와 넘어온 아이디가 일치하는 지 확인
        Member byEmail = memberService.findByEmail(principal.getName());

        if(byEmail.getMemberId() == recommendPlaceEntity.getMember().getMemberId()){
            // 해당 값을 서비스에 전달
            RecommendPlace recommendPlace = mapService.postOrGetMap(mapEntity, recommendPlaceEntity);

            RecommendPlaceDto.Response response = recommendPlaceMapper.recommendPlaceToResponse(recommendPlace);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            throw new BusinessLogicException(ExceptionCode.NOT_SAME_USER);
        }
    }
}
