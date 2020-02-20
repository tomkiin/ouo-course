package run.ouo.app.controller;

import run.ouo.app.model.vo.response.ResultVO;
import run.ouo.app.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/news")
@RestController
public class NewsController extends BaseController {
    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping
    public ResultVO get() {
        return service.getAllNews();
    }
}
