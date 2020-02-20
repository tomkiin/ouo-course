package run.ouo.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * 设置标准时间
 */
@Component
public class TimeZoneConfig {
    @Value("${spring.jackson.time-zone}")
    private String timeZone;

    @PostConstruct  // 依赖注入完毕后自动执行
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
    }
}
