package example.webcontent.Models;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by frost on 05.12.2017.
 */

public class News {
    static SimpleDateFormat FORMATTER =
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
    private String title;
    private Date dateTime;
    private URL link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return FORMATTER.format(this.dateTime);
    }

    public void setDateTime(String dateTime) {
        try {
            this.dateTime = FORMATTER.parse(dateTime.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public URL getLink() {
        return link;
    }

    public void setLink(String link) {
        try {
            this.link = new URL(link);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
