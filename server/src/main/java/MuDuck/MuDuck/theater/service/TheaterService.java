package MuDuck.MuDuck.theater.service;

import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import MuDuck.MuDuck.theater.entitiy.Theater;
import MuDuck.MuDuck.theater.repository.TheaterRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public Theater createTheater(Theater theater){
        // 해당 극장이 있는지 먼저 검증
        verifiedExistsTheater(theater.getTheaterId());

        return theaterRepository.save(theater);
    }

    public Theater patchTheater(Theater theater){
        return null;
    }

    public Page<Theater> getTheaters(Pageable pageable){
        return null;
    }

    public List<Theater> getTheaters(){

        return theaterRepository.findAll();

    }

    public Theater getTheater(long id){

        Theater findTheater = findVerifiedTheater(id);

        return findTheater;
    }

    public void deleteTheater(long id){
        verifiedExistsTheater(id);

        Theater theater = findVerifiedTheater(id);

        //theaterRepository.delete(theater);
    }
    
    // 해당 극장이 있는지 검증
    public void verifiedExistsTheater(long id){

        Optional<Theater> optionalTheater = theaterRepository.findById(id);

        if (optionalTheater.isPresent()){
            throw new BusinessLogicException(ExceptionCode.THEATER_EXISTS);
        }

    }

    // 극장 검증 및 반환
    public Theater findVerifiedTheater(long id){

        Optional<Theater> optionalTheater = theaterRepository.findById(id);

        Theater findTheater = optionalTheater.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.THEATER_NOT_FOUND));

        return findTheater;
    }
}
