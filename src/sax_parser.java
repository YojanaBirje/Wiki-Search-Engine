/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;
import java.util.Map.Entry;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author yojana
 */
public class sax_parser {

    public static int page_count = 0,file_no=0,ind;
    
    public static TreeMap<String,String> id_tit = new TreeMap<String,String>();
    public static String a="",b="";
    static long a_l=0,b_l=0;

     public static void Extract_text(String filenm) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean text = false;
                boolean id = false;
                boolean title = false;
                int id_flg = 0;
                StringBuilder currentText = new StringBuilder();
                StringBuilder currentTitle = new StringBuilder();
                StringBuilder currentId = new StringBuilder();
                

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                   if (qName.equalsIgnoreCase("page")) {
                         if(page_count!=0&&page_count%5000==0){
                             tokenize.printMap("/media/yojana/New Volume1/indices/index"+file_no+".txt");
                             System.out.println(file_no+"file writen");
                             tokenize.printtit("/media/yojana/New Volume1/indices/index_it"+file_no+".txt");

                             file_no++;
                            id_tit.clear();
                         }
                        page_count = page_count + 1;
                    } else if (qName.equalsIgnoreCase("title")) {
                        currentId.setLength(0);
                        currentTitle.setLength(0);
                        title = true;
                        id_flg = 0;
                    } else if (qName.equalsIgnoreCase("id") && id_flg == 0) {
                         currentId.setLength(0);
                        id = true;
                        id_flg = 1;
                    } else if (qName.equalsIgnoreCase("text")) {
                         currentText.setLength(0);
                        text = true;
                        
                    }

                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    if (text) {
                        
                        currentText.append(ch, start, length);
                    } else if (title) {
                        currentTitle.append(ch, start, length);
                        
                    } else if (id) {
                        currentId.append(ch, start, length);
                        id_tit.put(currentId.toString(),currentTitle.toString());
                    }

                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    try {
						if (qName.equalsIgnoreCase("text")) {
                            text = false;
                            Parser.ExtractFields(currentText,currentTitle,currentId);

                        } else if (qName.equalsIgnoreCase("title")) {                           
                            title = false;
//                            
                        } else if (qName.equalsIgnoreCase("id")) {
                            id = false;
                        }  

                    } catch (Exception e) {
                    }
                }

            };
            ind=file_no;
            saxParser.parse(filenm, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
