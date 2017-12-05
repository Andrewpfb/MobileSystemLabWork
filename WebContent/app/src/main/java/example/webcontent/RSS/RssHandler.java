package example.webcontent.RSS;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import static example.webcontent.RSS.BaseFeedParser.*;

import java.util.ArrayList;
import java.util.List;

import example.webcontent.Models.News;

/**
 * Created by frost on 05.12.2017.
 */

public class RssHandler extends DefaultHandler {
    private List<News> messages;
    private News currentMessage;
    private StringBuilder builder;

    public List<News> getMessages(){
        return this.messages;
    }
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        super.endElement(uri, localName, name);
        if (this.currentMessage != null){
            if (localName.equalsIgnoreCase(TITLE)){
                currentMessage.setTitle(builder.toString());
            } else if (localName.equalsIgnoreCase(LINK)){
                currentMessage.setLink(builder.toString());
            } else if (localName.equalsIgnoreCase(PUB_DATE)){
                currentMessage.setDateTime(builder.toString());
            } else if (localName.equalsIgnoreCase(ITEM)){
                messages.add(currentMessage);
            }
            builder.setLength(0);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        messages = new ArrayList<News>();
        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, name, attributes);
        if (localName.equalsIgnoreCase(ITEM)){
            this.currentMessage = new News();
        }
    }
}
