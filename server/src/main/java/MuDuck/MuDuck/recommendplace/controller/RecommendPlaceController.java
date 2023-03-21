package MuDuck.MuDuck.recommendplace.controller;

import MuDuck.MuDuck.response.RecommendPlaceMultipleResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @PostMapping
    public ResponseEntity postRecommendPlace(@RequestBody @Valid RecommendPlaceMultipleResponse post){



        return null;
    }
}
