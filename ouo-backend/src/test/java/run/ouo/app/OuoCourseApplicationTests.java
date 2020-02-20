package run.ouo.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import run.ouo.app.manager.NewsManager;

@SpringBootTest
class OuoCourseApplicationTests {
    @Autowired
    NewsManager manager;

    @Test
    void test() {
        manager.crawlNews();

    }
}
