package run.ouo.app.manager;

import org.jsoup.nodes.Element;
import run.ouo.app.dao.redis.NewsDAO;
import run.ouo.app.model.bo.NewsBO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class NewsManager extends BaseManager {
    private static final int CRAWL_INTERVAL = 60 * 60 * 1000;
    private static final int CRAWL_TIMEOUT = 30 * 1000;
    private static final String CRAWL_TARGET_URL = "http://jwc.njupt.edu.cn/1594/list.htm";
    private static final String BASE_URL = "http://jwc.njupt.edu.cn/";

    private final NewsDAO newsDAO;

    public NewsManager(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    public List<NewsBO> getAllNews() {
        Map<String, String> map = newsDAO.getAllNews();

        List<NewsBO> newsList = new ArrayList<>(map.size());
        for (String key : map.keySet()) {
            String value = map.get(key);
            String date = value.substring(0, 10);
            String url = value.substring(10);
            newsList.add(new NewsBO(key, date, url));
        }

        return newsList;
    }

    @Scheduled(fixedDelay = CRAWL_INTERVAL)  // 定时任务
    public void crawlNews() {
        Document pageDoc = fetchPage();
        if (pageDoc == null) {
            return;
        }

        List<NewsBO> newsList = parseNews(pageDoc);
        newsDAO.clear();
        for (NewsBO news : newsList) {
            newsDAO.addNews(news.getTitle(), news.getDate() + news.getUrl());
        }
    }

    private Document fetchPage() {
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(CRAWL_TARGET_URL), CRAWL_TIMEOUT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return doc;
    }

    private List<NewsBO> parseNews(Document pageDoc) {
        Elements elements = pageDoc.body().select("#wp_news_w4 > table > tbody > tr");
        List<NewsBO> newsList = new ArrayList<>();
        for (Element element : elements) {
            Element aTag = element.getElementsByTag("table").get(0)
                    .getElementsByTag("a").get(0);
            Element dataTag = element.getElementsByTag("table").get(0)
                    .getElementsByTag("div").get(0);
            String url = BASE_URL + aTag.attr("href");
            String title = aTag.text();
            String data = dataTag.text();
            newsList.add(new NewsBO(title, data, url));
        }

        return newsList;
    }
}
