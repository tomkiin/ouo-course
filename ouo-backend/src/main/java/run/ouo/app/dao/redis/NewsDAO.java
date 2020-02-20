package run.ouo.app.dao.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class NewsDAO {
    private static final String HASH_NAME = "ouo_news";

    private final RedisTemplate template;

    public NewsDAO(@Qualifier("redisTemplate") RedisTemplate template) {
        this.template = template;
    }

    public void addNews(String title, String body) {
        template.opsForHash().put(HASH_NAME, title, body);
    }


    public Map<String, String> getAllNews() {
        return template.opsForHash().entries(HASH_NAME);
    }

    public void clear() {
        template.opsForHash().getOperations().delete(HASH_NAME);
    }
}
