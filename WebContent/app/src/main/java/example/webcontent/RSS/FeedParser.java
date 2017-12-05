package example.webcontent.RSS;

/**
 * Created by frost on 05.12.2017.
 */

import java.util.List;

import example.webcontent.Models.News;

public interface FeedParser {
    List<News> parse();
}
