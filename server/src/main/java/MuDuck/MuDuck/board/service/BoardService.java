package MuDuck.MuDuck.board.service;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.repository.BoardRepository;
import MuDuck.MuDuck.boardCategory.entity.BoardCategory;
import MuDuck.MuDuck.boardCategory.repository.BoardCategoryRepository;
import MuDuck.MuDuck.boardLike.entity.BoardLike;
import MuDuck.MuDuck.boardLike.repository.BoardLikeRepository;
import MuDuck.MuDuck.category.entity.Category;
import MuDuck.MuDuck.category.repository.CategoryRepository;
import MuDuck.MuDuck.exception.BusinessLogicException;
import MuDuck.MuDuck.exception.ExceptionCode;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.utils.CustomBeanUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final BoardLikeRepository boardLikeRepository;
    private final CustomBeanUtils<Board> beanUtils;

    public Board createBoard(Board board){
        Board createdBoard = boardRepository.save(board);
        return createdBoard;
    }

    public Page<Board> findBoards(int page, String sortBy, String categoryName){
        if (categoryName.equals("전체")){
            return boardRepository.findWithoutDeleteBoard(PageRequest.of(page, SIZE, Sort.by(sortByDictWhenAll.get(sortBy)).descending()));
        } else { // "자유주제", "공연정보/후기", "시설정보"
            long categoryId = categoryRepository.findCategoryIdByCategoryName(categoryName);
            List<Long>  boardIds = boardCategoryRepository.findAllIdByCategoryId(categoryId);
            return boardRepository.findByBoardId(boardIds, PageRequest.of(page, SIZE, Sort.by(sortByDict.get(sortBy)).descending()));
        }
    }

    public Board findBoard(long boardId){
        return findVerifiedBoard(boardId);
    }

    // 게시글이 갖고 있는 카테고리 중 1차 카테고리만 반환해준다.
    public String findCategory(Board board){
        List<BoardCategory> boardCategories = board.getBoardCategories();
        for(BoardCategory boardCategory : boardCategories){
            Category category = boardCategory.getCategory();
            if(category.getParent() == null){ // 부모가 없어야 1차 카테고리다.
                return category.getCategoryName();
            }
        }
        return ""; // 카테고리가 없는 경우 해당 경우가 발생하면 오류가 발생한 것임
    }

    public boolean isLiked(Member member){
        Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByMemberMemberId(member.getMemberId());
        if(optionalBoardLike.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Transactional(readOnly=true)
    private Board findVerifiedBoard(long boardId){
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        return optionalBoard.orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }
}