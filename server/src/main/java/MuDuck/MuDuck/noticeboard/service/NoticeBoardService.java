package MuDuck.MuDuck.noticeboard.service;

import MuDuck.MuDuck.noticeboard.entity.NoticeBoard;
import MuDuck.MuDuck.noticeboard.repository.NoticeBoardRepository;
import MuDuck.MuDuck.utils.CustomBeanUtils;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NoticeBoardService {
    private final static int TOP_NUM = 2; // 게시판 목록 상위에 표시할 숫자를 의미한다.

    private final NoticeBoardRepository noticeBoardRepository;
    private final CustomBeanUtils<NoticeBoard> beanUtils;

    public NoticeBoardService(NoticeBoardRepository noticeBoardRepository,
            CustomBeanUtils<NoticeBoard> beanUtils) {
        this.noticeBoardRepository = noticeBoardRepository;
        this.beanUtils = beanUtils;
    }

    public List<NoticeBoard> getTopNoticeBoard(){
        return noticeBoardRepository.findTopNoticeBoardBy(TOP_NUM);
    }
}
