package example.webcontent.RSS;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import example.webcontent.Models.News;

/**
 * Created by frost on 05.12.2017.
 */

public class SaxFeedParser extends BaseFeedParser {

    public SaxFeedParser(String feedUrl){
        super(feedUrl);
    }

    public List<News> parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            RssHandler handler = new RssHandler();
            parser.parse(this.getInputStream(), handler);
            return handler.getMessages();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
