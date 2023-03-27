package MuDuck.MuDuck.musical.service;

import MuDuck.MuDuck.actor.service.ActorService;
import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.repository.BoardRepository;
import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import MuDuck.MuDuck.musical.entity.Category;
import MuDuck.MuDuck.musical.entity.Musical;
import MuDuck.MuDuck.musical.repository.MusicalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MusicalService {

    private final MusicalRepository musicalRepository;
    private final ActorService actorService;
    private final BoardRepository boardRepository;

    public MusicalService(MusicalRepository musicalRepository, ActorService actorService,
            BoardRepository boardRepository) {
        this.musicalRepository = musicalRepository;
        this.actorService = actorService;
        this.boardRepository = boardRepository;
    }

    public Musical createMusical(Musical musical) {
        verifyExistsMusical(musical.getMusicalKorName());
        verifyMusical(musical);
        Musical saveMusical = musicalRepository.save(musical);

        return saveMusical;
    }

    public Musical updateMusical(Musical musical) {
        Musical findMusical = findVerifiedMusical(musical.getMusicalId());

        Optional.ofNullable(musical.getMusicalState())
                .ifPresent(musicalState -> findMusical.setMusicalState(musicalState));

        return musicalRepository.save(findMusical);
    }

    @Transactional(readOnly = true)
    public Musical findMusical(long musicalId) {
        return findVerifiedMusical(musicalId);
    }

    @Transactional(readOnly = true)
    public Page<Musical> findMusicals(int page, int size) {
        return musicalRepository.findAll(PageRequest.of(page, size, Sort.by("musicalId")));
    }

    public Page<Musical> findMusicalGenres(String genre, int page, int size) {
        if (genre.equals("GENRE_LICENSED")) {
            return musicalRepository.findByGenre(genre,
                    PageRequest.of(page, size, Sort.by("musical_id")));
        } else if (genre.equals("GENRE_CREATED")) {
            return musicalRepository.findByGenre(genre,
                    PageRequest.of(page, size, Sort.by("musical_id")));
        } else if (genre.equals("GENRE_ORIGINAL")) {
            return musicalRepository.findByGenre(genre,
                    PageRequest.of(page, size, Sort.by("musical_id")));
        } else {
            throw new BusinessLogicException(ExceptionCode.MUSICAL_NOT_FOUND);
        }
    }

    @Transactional(readOnly = true)
    public Page<Musical> findMusicalFilters(String sort, int page, int size) {
        if (sort.equals("openDate")) {
            return musicalRepository.findAll(
                    PageRequest.of(page, size, Sort.by("openDate").descending()));
        } else if (sort.equals("musicalKorName")) {
            return musicalRepository.findAll(PageRequest.of(page, size, Sort.by("musicalKorName")));
        } else if (sort.equals("views")) {
            return musicalRepository.findAll(
                    PageRequest.of(page, size, Sort.by("views").descending()));
        } else {
            throw new BusinessLogicException(ExceptionCode.MUSICAL_NOT_FOUND);
        }
    }

    @Transactional(readOnly = true)
    public Page<Musical> findMusicalStates(String state, int page, int size) {
        if (state.equals("MUSICAL_ONAIR")) {
            return musicalRepository.findByMusicalState(state,
                    PageRequest.of(page, size, Sort.by("musical_id")));
        } else if (state.equals("MUSICAL_YET")) {
            return musicalRepository.findByMusicalState(state,
                    PageRequest.of(page, size, Sort.by("musical_id")));
        } else if (state.equals("MUSICAL_FINISH")) {
            return musicalRepository.findByMusicalState(state,
                    PageRequest.of(page, size, Sort.by("musical_id")));
        } else {
            throw new BusinessLogicException(ExceptionCode.MUSICAL_NOT_FOUND);
        }
    }

    public Musical findVerifiedMusical(Long musicalId) {
        Optional<Musical> optionalMusical = musicalRepository.findById(musicalId);
        Musical findMusical = optionalMusical.orElseThrow(() -> new BusinessLogicException(
                ExceptionCode.MUSICAL_NOT_FOUND));
        return findMusical;
    }

    public void verifyExistsMusical(String musicalKorName) {
        Optional<Musical> optionalMusical = musicalRepository.findByMusicalKorName(musicalKorName);
        if (optionalMusical.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MUSICAL_EXISTS);
        }
    }

    private void verifyMusical(Musical musical) {
        musical.getActorMusicals().stream()
                .forEach(actorMusical -> actorService.findVerifiedActor(
                        actorMusical.getActor().getActorId()));
    }

    private Musical saveMusical(Musical musical) {
        return musicalRepository.save(musical);
    }

    public Category findCategoryName(long musicalId) {
        return musicalRepository.findCategoryByMusicalId(musicalId);
    }

    public List<Board> findMusicalBoards(long musicalId) {
        return boardRepository.findBoardByMusicalId(musicalId);
    }

    @Transactional(readOnly = true)
    public List<Musical> findRecommendMusicals() {
        return musicalRepository.findRecommendMusicals();
    }
}
