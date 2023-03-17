package MuDuck.MuDuck.theater.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.theater.entitiy.Theater;
import MuDuck.MuDuck.theater.repository.TheaterRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TheaterServiceTest {

    @Mock
    private TheaterRepository theaterRepository;

    @InjectMocks
    private TheaterService theaterService;

    private Theater theater;

    @BeforeEach
    void init(){
        theater = Theater.builder()
                .theaterId(1L)
                .placeName("오둥이극장")
                .longitude(12.1234)
                .latitude(13.1234)
                .phone("02-555-5555")
                .address("서울시 어쩌구 저쩌구")
                .roadAddress("서울시 어쩌구 저쩌구")
                .build();
    }

    @Test
    @DisplayName("극장 생성 시 동일한 극장이 있을 경우 검증 테스트")
    void createTheaterException() {
        // given
        given(theaterRepository.findById(Mockito.anyLong()))
                .willReturn(Optional.of(theater));

        // when / then
        assertThrows(BusinessLogicException.class,() -> theaterService.createTheater(theater));
    }

    @Test
    @DisplayName("극장 생성 시 동일한 극장이 없을 경우 저장 테스트")
    void createTheater(){
        // given
        given(theaterRepository.save(Mockito.any())).willReturn(theater);

        // when / then
        assertThat(theater).isEqualTo(theaterService.createTheater(theater));
    }

    @Test
    @DisplayName("조회 시 해당 극장이 없을 경우 검증 테스트")
    void getTheater(){
        // given
        given(theaterRepository.findById(Mockito.anyLong()))
                .willReturn(Optional.empty());

        // when / then
        assertThrows(BusinessLogicException.class,
                () -> theaterService.getTheater(theater.getTheaterId()));
    }

}