package MuDuck.MuDuck.board.service;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.repository.BoardRepository;
import MuDuck.MuDuck.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final CustomBeanUtils<Board> beanUtils;

    public BoardService(BoardRepository boardRepository, CustomBeanUtils<Board> beanUtils) {
        this.boardRepository = boardRepository;
        this.beanUtils = beanUtils;
    }

    public Page<Board> findBoards(int page, int size){
        return boardRepository.findWithoutDeleteBoard(PageRequest.of(page, size, Sort.by("createdAt").descending()));
    }
}
