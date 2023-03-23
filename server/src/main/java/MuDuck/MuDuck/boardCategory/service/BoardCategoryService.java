package MuDuck.MuDuck.boardCategory.service;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.boardCategory.entity.BoardCategory;
import MuDuck.MuDuck.boardCategory.repository.BoardCategoryRepository;
import MuDuck.MuDuck.category.entity.Category;
import MuDuck.MuDuck.category.service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;
    private final CategoryService categoryService;

    public List<BoardCategory> getBoardCategories(List<Long> categoryIds, Board board) {
        List<BoardCategory> boardCategories = new ArrayList<>();
        for (Long categoryId : categoryIds) {
            Category category = categoryService.findCategory(categoryId);
            BoardCategory boardCategory = BoardCategory.builder().board(board).category(category)
                    .build();
            boardCategories.add(boardCategory);
        }
        return boardCategories;
    }
}
