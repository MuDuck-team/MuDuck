package MuDuck.MuDuck.board.service;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.repository.BoardRepository;
import MuDuck.MuDuck.boardCategory.repository.BoardCategoryRepository;
import MuDuck.MuDuck.category.repository.CategoryRepository;
import MuDuck.MuDuck.utils.CustomBeanUtils;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private static final HashMap<String, String> sortByDictWhenAll = new HashMap<String, String>() {
        {
            put("recent", "createdAt");
            put("like", "likes");
            put("view", "views");
        }
    };
    private static final HashMap<String, String> sortByDict = new HashMap<String, String>() {
        {
            put("recent", "created_at");
            put("like", "likes");
            put("view", "views");
        }
    };
    private final static int SIZE = 8;


    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final CustomBeanUtils<Board> beanUtils;

    public Page<Board> findBoards(int page, String sortBy, String categoryName){
        if (categoryName.equals("전체")){
            return boardRepository.findWithoutDeleteBoard(PageRequest.of(page, SIZE, Sort.by(sortByDictWhenAll.get(sortBy)).descending()));
        } else { // "자유주제", "공연정보/후기", "시설정보"
            long categoryId = categoryRepository.findCategoryIdByCategoryName(categoryName);
            List<Long>  boardIds = boardCategoryRepository.findAllIdByCategoryId(categoryId);
            return boardRepository.findByBoardId(boardIds, PageRequest.of(page, SIZE, Sort.by(sortByDict.get(sortBy)).descending()));
        }
    }
}
