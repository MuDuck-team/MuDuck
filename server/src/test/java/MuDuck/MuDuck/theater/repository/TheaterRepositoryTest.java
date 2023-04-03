package MuDuck.MuDuck.theater.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import MuDuck.MuDuck.theater.entitiy.Theater;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TheaterRepositoryTest {

//    @Autowired
//    private TheaterRepository theaterRepository;
//
//    private Theater theater;
//
//    private List<Theater> theaterList = new ArrayList<>();
//
//    @BeforeEach
//    void init(){
//
//        theater = Theater.builder()
//                .theaterId(1L)
//                .placeName("오둥이극장")
//                .longitude(12.1234)
//                .latitude(13.1234)
//                .phone("02-555-5555")
//                .address("서울시 어쩌구 저쩌구")
//                .roadAddress("서울시 어쩌구 저쩌구")
//                .build();
//
//        for(int i = 2; i <= 4; i++){
//
//            theater.setTheaterId((long)i);
//            theater.setPlaceName("오둥이극장"+i);
//
//            theaterList.add(theater);
//
//            theater.setTheaterId(1L);
//        }
//    }
//

//    @Test
//    @DisplayName("극장 정보 저장 테스트")
//    void saveTheater(){
//        // given init()에서 실행
//
//        // when
//        Theater saverTheater = theaterRepository.save(theater);
//
//        // then
//        assertNotNull(theater);
//        assertThat(theater.getTheaterId()).isEqualTo(saverTheater.getTheaterId());
//        assertThat(theater.getPlaceName()).isEqualTo(saverTheater.getPlaceName());
//        assertThat(theater.getLongitude()).isEqualTo(saverTheater.getLongitude());
//        assertThat(theater.getLatitude()).isEqualTo(saverTheater.getLatitude());
//        assertThat(theater.getPhone()).isEqualTo(saverTheater.getPhone());
//        assertThat(theater.getAddress()).isEqualTo(saverTheater.getAddress());
//        assertThat(theater.getRoadAddress()).isEqualTo(saverTheater.getRoadAddress());
//    }

//    @Test
//    @DisplayName("해당 극장 조회 테스트")
//    void getTheater(){
//        // given init()에서 실행
//
//        Theater saverTheater = theaterRepository.save(theater);
//
//        // when
//        Optional<Theater> getTheater = theaterRepository.findById(theater.getTheaterId());
//
//        assertNotNull(getTheater);
//        assertThat(saverTheater.getTheaterId()).isEqualTo(getTheater.get().getTheaterId());
//    }

//    @Test
//    @DisplayName("전체 극장 조회 테스트")
//    void getTheaters(){
//        // given
//        theaterRepository.saveAll(theaterList);
//
//        List<Theater> theaters = theaterRepository.findAll();
//
//        assertNotNull(theaters);
//        assertThat(theaterList.size()).isEqualTo(theaters.size());
//    }

}