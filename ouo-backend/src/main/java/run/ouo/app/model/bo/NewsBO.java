package run.ouo.app.model.bo;

import lombok.Data;

@Data
public class NewsBO {
    private String title;
    private String url;
    private String date;

    public NewsBO(String title, String date, String url) {
        this.title = title;
        this.date = date;
        this.url = url;
    }
}
