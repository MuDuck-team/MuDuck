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
import MuDuck.MuDuck.recommendplace.service.RecommendPlaceService;
import MuDuck.MuDuck.response.RecommendPlaceMultipleResponse;
import java.security.Principal;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final RecommendPlaceService recommendPlaceService;
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

    @GetMapping("/maps/{place-id}/members/{member-id}")
    public ResponseEntity getRecommendPlace(@PathVariable("place-id") @Positive long placeId,
            @PathVariable("member-id") @Positive long memberId, Principal principal){

        // 해당 로그인 유저와 넘어온 아이디가 일치하는 지 확인
        Member byEmail = memberService.findByEmail(principal.getName());

        if(byEmail.getMemberId() == memberId){

            // 같을 경우 해당 map 이 있는지 검증
            Map mapToPlaceId = mapService.findMapToPlaceId(placeId);

            // 검증 통과 시 해당 글 검색
            RecommendPlace recommendPlaceToMapIdAndMemberId = recommendPlaceService.findRecommendPlaceToMemberIdAndMapId(
                    memberId, mapToPlaceId.getMapId());

            RecommendPlaceDto.Response response = recommendPlaceMapper.recommendPlaceToResponse(recommendPlaceToMapIdAndMemberId);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }else{
            // 틀릴 경우
            throw new BusinessLogicException(ExceptionCode.NOT_SAME_USER);
        }
        
    }

    @PatchMapping("/{recommend-place-id}/maps/{map-id}/members/{member-id}")
    public ResponseEntity patchRecommendPlace(
            @PathVariable("recommend-place-id") @Positive long rpId,
            @PathVariable("map-id") @Positive long mapId,
            @PathVariable("member-id") @Positive long memberId,
            @RequestBody @Valid RecommendPlaceDto.Patch patch, Principal principal){

        // entity 로 변경
        RecommendPlace recommendPlace = recommendPlaceMapper.patchDtoToRecommendPlace(patch);
        recommendPlace.setRecommendPlaceId(rpId);

        // 로그인한 사용자와 동일한 ID인지 검증
        Member byEmail = memberService.findByEmail(principal.getName());
        if(byEmail.getMemberId() == memberId){
            
            // 해당 작성 글이 본인 글인지 확인
            RecommendPlace recommendPlaceToId = recommendPlaceService.findRecommendPlaceToMemberIdAndMapId(memberId, mapId);
            
            // 아닐 경우 예외발생
            if(recommendPlaceToId.getMember().getMemberId() != memberId){
                throw new BusinessLogicException(ExceptionCode.NOT_SAME_USER);
            }

            // 동일하다면 업데이트 진행
            RecommendPlace updateRecommendPlace = recommendPlaceService.updateRecommendPlace(
                    recommendPlace);

            RecommendPlaceDto.Response response = recommendPlaceMapper.recommendPlaceToResponse(updateRecommendPlace);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }else{

            throw new BusinessLogicException(ExceptionCode.NOT_SAME_USER);
        }
    }
}
