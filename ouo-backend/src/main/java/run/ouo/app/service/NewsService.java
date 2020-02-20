package run.ouo.app.service;

import run.ouo.app.manager.NewsManager;
import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.model.vo.response.table.NewsItemVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService extends BaseService {
    private final NewsManager manager;

    public NewsService(NewsManager manager) {
        this.manager = manager;
    }

    public ResultVO getAllNews() {
        List<NewsItemVO> voList = NewsItemVO.fromNewsBOList(manager.getAllNews());
        voList.sort((a, b) -> b.getDate().compareTo(a.getDate()));

        return success(voList);
    }
}
