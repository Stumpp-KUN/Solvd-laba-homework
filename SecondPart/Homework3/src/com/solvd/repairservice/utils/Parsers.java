package solvd.repairservice.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import solvd.repairservice.models.Car;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Parsers {
    private Logger logger= LogManager.getRootLogger();

    public boolean parseStAX(String path){
        try {
            XMLStreamReader xmlStreamReader= XMLInputFactory.newInstance().createXMLStreamReader(path,new FileInputStream(path));
            while(xmlStreamReader.hasNext()){
                xmlStreamReader.next();
                if(xmlStreamReader.isStartElement()){
                    logger.info(xmlStreamReader.getLocalName());
                }
                else if(xmlStreamReader.isEndElement()){
                    logger.info("/"+xmlStreamReader.getLocalName());
                }
                else if(xmlStreamReader.hasText()&&xmlStreamReader.getText().trim().length()>0){
                    logger.info("   "+xmlStreamReader.getText());
                }

            }
        } catch ( FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean parserSAX(String path) throws ParserConfigurationException, SAXException, IOException {
       DefaultHandler handler=new DefaultHandler() {
           @Override
           public void characters(char[] ch, int start, int length) throws SAXException {
               logger.info("   "+new String(ch, start, length));
           }

           @Override
           public void endElement(String uri, String localName, String qName) throws SAXException {
               logger.info("/"+ qName);
           }

           @Override
           public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
               logger.info(qName);
           }


       };

        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parser=factory.newSAXParser();
        parser.parse(new File(path),handler);
        return true;
    }
}
