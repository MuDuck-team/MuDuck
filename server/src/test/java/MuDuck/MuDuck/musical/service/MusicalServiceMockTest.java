package MuDuck.MuDuck.musical.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import MuDuck.MuDuck.actor.entity.Actor;
import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.musical.entity.ActorMusical;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.entity.Musical.Age;
import MuDuck.MuDuck.musical.entity.Musical.Genre;
import MuDuck.MuDuck.musical.entity.Musical.MusicalState;
import MuDuck.MuDuck.musical.repository.MusicalRepository;
import MuDuck.MuDuck.theater.entitiy.Theater;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MusicalServiceMockTest {
    @Mock
    private MusicalRepository musicalRepository;
    @InjectMocks
    private MusicalService musicalService;
    private Musical musical;
    private  Actor actor;
    private ActorMusical actorMusical;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        actor = new Actor(1L, "전동석","http://ticketimage.interpark.com/PlayDictionary/DATA/PlayDic/PlayDicUpload/040004/09/07/0400040907_16138_02825.gif");
        musical = new Musical(1L,"테스트","test","http://localhost:8080", Genre.GENRE_CREATED, "테스팅정보", MusicalState.MUSICAL_YET, "2023.03.20", "2023.03.21",
                Age.AGE_19, 162, 20,100,
                new Theater(1L, "오둥이 소극장", 126.978891, 37.5709794, "02-555-5555", "서울특별시 송파구 잠실동 40-1번지 샤롯데씨어터", "서울특별시 송파구 올림픽로 240(잠실동) 샤롯데씨어터"));
        actorMusical = new ActorMusical(1L, "줄리엣", musical, actor);
    }

    @Test
    @DisplayName("작품 등록 테스트")
    public void createMusicalTest() {
        given(musicalRepository.findByMusicalKorName(musical.getMusicalKorName())).willReturn(Optional.of(musical));

        assertThrows(BusinessLogicException.class, () -> musicalService.createMusical(musical));
    }

    @Test
    @DisplayName("작품 정보 수정 테스트")
    public void updateMusicalTest() {
        given(musicalRepository.findByMusicalId(musical.getMusicalId())).willReturn(Optional.of(musical));

        assertThrows(BusinessLogicException.class, () -> musicalService.updateMusical(musical));
    }

}
