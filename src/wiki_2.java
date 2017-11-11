/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
//import static wiki_search_engine.handler.SAX_Parser;
//import static wiki_search_engine.sax_parser.Extract_text;

/**
 *
 * @author yojana
 */
public class wiki_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        sax_parser.Extract_text("/media/yojana/New Volume1/bigxml.xml");   

        K_way_merge km = new K_way_merge();
        km.merge();   
		merge_idtitle mt=new merge_idtitle();
        mt.merge();
        QueryProcessing.processQuery();
      
    }

}
