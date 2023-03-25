package MuDuck.MuDuck.board.service;

import MuDuck.MuDuck.board.entity.Board;
import MuDuck.MuDuck.board.entity.Board.BoardStatus;
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

    public void addView(Board board){
        board.setViews(board.getViews() + 1);
        boardRepository.save(board);
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

    public boolean isLiked(long boardId, long memberId){
        Optional<BoardLike> optionalBoardLike = boardLikeRepository.isMemberClickLike(boardId, memberId);

        if(optionalBoardLike.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public Board updateBoard(Board board, long memberId){
        Board originalBoard = findVerifiedBoard(board.getBoardId());
        Board updatedBoard;
        if(originalBoard.getMember().getMemberId() != memberId){ // 수정을 요청한 멤버가 작성자가 아니라면
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER_STATUS);
        }else{ // 수정을 요청한 멤버가 작성자라면
            if(originalBoard.getBoardStatus() == BoardStatus.BOARD_DELETE){ // 이미 삭제 된 게시물에 업데이트 요청하는거라면
                throw new BusinessLogicException(ExceptionCode.BOARD_REMOVED);
            }else {
                board.setViews(originalBoard.getViews());
                board.setLikes(originalBoard.getLikes());
                updatedBoard = beanUtils.copyNonNullProperties(board, originalBoard);
            }
        }
        return boardRepository.save(updatedBoard);
    }

    public void deleteBoard(long boardId, long memberId){
        Board board = findVerifiedBoard(boardId);

        if(board.getMember().getMemberId() != memberId){ // 삭제를 요청하는 멤버가 작성자가 아니라면
            throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER_STATUS);
        }else{ // 삭제를 요청하는 멤버가 작성자라면
            if(board.getBoardStatus() == BoardStatus.BOARD_DELETE){ // 이미 삭제된 상태라면
                throw new BusinessLogicException(ExceptionCode.BOARD_REMOVED);
            }else {
                board.setBoardStatus(BoardStatus.BOARD_DELETE);
            }
        }
        boardRepository.save(board);
    }

    public void addLike(long boardId, Member member) {
        Board board = findVerifiedBoard(boardId);

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.isMemberClickLike(boardId, member.getMemberId());

        if(optionalBoardLike.isEmpty()){ // 해당 게시글에 좋아요를 이전에 안눌렀다면
            BoardLike boardLike = new BoardLike(board, member);
            board.getBoardLikes().add(boardLike);
            board.setLikes(board.getLikes() + 1);
            boardRepository.save(board);
        } else{ // 해당 게시글에 좋아요를 눌렀었다면
            throw new BusinessLogicException(ExceptionCode.BOARD_LIKE_EXISTS);
        }
    }

    public void deleteLike(long boardId, Member member) {
        Board board = findVerifiedBoard(boardId);

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.isMemberClickLike(boardId, member.getMemberId());

        if(optionalBoardLike.isEmpty()) { // 해당 게시글에 좋아요를 이전에 안눌렀다면
            throw  new BusinessLogicException(ExceptionCode.BOARD_LIKE_NOT_FOUND);
        }else { // 해당 게시글에 좋아요를 눌렀었다면
            BoardLike boardLike = optionalBoardLike.get();
            if(boardLike.getMember().getMemberId() == member.getMemberId()) { // 좋아요를 누른 회원과 로그인되어있는 회원이 같은 회원인지 검증
                long boardLikeId = boardLikeRepository.findBoardLikeId(boardId, member.getMemberId());
                boardLikeRepository.deleteById(boardLikeId);

                board.setLikes(board.getLikes() - 1);
                boardRepository.save(board);
            }else { // 좋아요를 누른 회원과 로그인되어있는 회원이 다른 회원인지 검증
                throw new BusinessLogicException(ExceptionCode.INVALID_MEMBER_STATUS);
            }
        }
    }

    @Transactional(readOnly=true)
    private Board findVerifiedBoard(long boardId){
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        return optionalBoard.orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }
}
