/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * @author yojana
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
//
class key_posting1 {

     String posting;
     String key;
    int doc_no;
}

public class K_way_merge {

    Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {

        @Override
        public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
            Integer v1 = e1.getValue();
            Integer v2 = e2.getValue();
            if (v1.compareTo(v2) < 0) {
                return 1;
            } else if (v1.compareTo(v2) == 0) {
                return 0;
            } else {
                return -1;
            }

        }
    };

    private static BufferedReader[] readers;
    private static Boolean[] isEmpty;

    class Comp implements Comparator<key_posting1> {

        @Override
        public int compare(key_posting1 x, key_posting1 y) {
            return x.key.compareTo(y.key);
        }
    }

    public StringBuilder sortbyfreq(String postings) {
        // String res = "";
        StringBuilder res=new StringBuilder();
        int idxOfNextWord = 0, i = 0;
        ArrayList<String> post = new ArrayList<String>();

        for (; i < postings.length(); i++) {
            if (postings.charAt(i) == ';') {
                post.add(postings.substring(idxOfNextWord, i));
                idxOfNextWord = i + 1;
            }
        }

        post.add(postings.substring(idxOfNextWord));
        HashMap< String, Integer> temp = new HashMap<String, Integer>();
        for (int k = 0; k < post.size(); k++) {
            String ii = post.get(k);
            int j = ii.indexOf("F");
            if (j == -1) {
                continue;
            }
            String term = ii.substring(0, j);
            String imp = ii.substring(j + 1);
            int freq;
            if (imp.contains("I")) {

                freq = Integer.parseInt(imp.split("I")[0]);
                term += "I" + imp.split("I")[1];
            } else {

                freq = Integer.parseInt(imp);
            }
            temp.put(term, freq);
        }
        Set<Entry<String, Integer>> entries = temp.entrySet();
        List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries);
        // sorting HashMap by values using comparator
        Collections.sort(listOfEntries, valueComparator);

        Boolean flag = true;

        for (Map.Entry<String,Integer> entry : listOfEntries)
        {
            
            if (flag) 
            {
                res.append(entry.getKey());
                flag = false;
            } else 
                res.append(";" + entry.getKey());
        }

        temp.clear();

        return res;

    }

    public void merge() {
        try {
            int no_temp_indexes /*= sax_parser.file_no*/, isnull = 0;

           no_temp_indexes = 3528;
            System.out.println("Merging :" + no_temp_indexes + " Files");

            readers = new BufferedReader[no_temp_indexes + 1];
            isEmpty = new Boolean[no_temp_indexes + 1];
            Comparator<key_posting1> compares = new Comp();

            int no_entries = 0, ter_entry = 0, pri_entries = 0;

            for (int i = 0; i < no_temp_indexes; i++) {
                readers[i] = new BufferedReader(new InputStreamReader(new FileInputStream("/media/yojana/New Volume1/indices/index" + i + ".txt")));
                isEmpty[i] = false;
            }

            System.out.println("it bufferread complete:");
            PrintWriter writer = new PrintWriter("/media/yojana/New Volume1/indexes/mainindex.txt");
            PrintWriter writer_sec = new PrintWriter("/media/yojana/New Volume1/indexes/secondaryindex.txt");
            PrintWriter writer_ter = new PrintWriter("/media/yojana/New Volume1/indexes/tertiaryindex.txt");

			//	System.out.println("Starting Merge");
            String line = "";
            long sec_offset = 0, ter_offset = 0;

            PriorityQueue<key_posting1> pq_entries = new PriorityQueue<key_posting1>(no_temp_indexes + 1, compares);

            for (int j = 0; j < no_temp_indexes; ++j) {
                if (!isEmpty[j]) {

                    line = readers[j].readLine();
                    if (line != null) {
                        int index = line.indexOf(":");
                        String key = line.substring(0, index), posting = line.substring(index + 1);
                        key_posting1 t = new key_posting1();
                        t.doc_no=j;
                        t.key=key;
                        t.posting=posting;

                        pq_entries.add(t);
                        ++no_entries;
                    } else {
                        ++isnull;
                        isEmpty[j] = true;
//				System.out.print("Completed File"+j);
//				System.out.print("at: "+no_entries+"\n");
                        readers[j].close();
                    }

                }

            }

            key_posting1 k_p1 = pq_entries.poll();
            int out_last = k_p1.doc_no;
            String out_posting = k_p1.posting, out_key = k_p1.key;
            String lout_posting = null, lout_key = null;

            while (isnull < no_temp_indexes) {
                if (isEmpty[out_last]) {
                    key_posting1 ltop = pq_entries.poll();
                    out_last = ltop.doc_no;
                    lout_posting = ltop.posting;
                    lout_key = ltop.key;

                } else {
                    line = readers[out_last].readLine();
                    if (line == null) {
                        if (!isEmpty[out_last]) {
                            isnull++;
                        }
//                    System.out.println("Completed File"+out_last+"at "+no_entries);
                        isEmpty[out_last] = true;
                        readers[out_last].close();
                    } else {
                        int index = line.indexOf(":");
                        String key = line.substring(0, index), posting = line.substring(index + 1);
                        key_posting1 t = new key_posting1();
                        t.doc_no=out_last;
                        t.key=key;
                        t.posting=posting;
                        pq_entries.add(t);
                        ++no_entries;

                        //System.out.println("Finised entering");
                        key_posting1 ltop1 = pq_entries.poll();

                        lout_posting = ltop1.posting;
                        lout_key = ltop1.key;
                        out_last = ltop1.doc_no;
                    }
                }
                if (lout_key.equals(out_key) && out_key != null) {
                    // System.out.print("\tEqual case");
                    if(out_posting.length()==0)
                        out_posting = lout_posting;
                    else
                        out_posting = out_posting +";"+ lout_posting;//check
                } else {
                    if (pri_entries % 50 == 0) {
                        writer_ter.write(out_key + ":" + ter_offset + "\n");
                        writer_ter.flush();
                    }

                    //Write into secondary index
                    writer_sec.write(out_key + ":" + sec_offset + "\n");
                    writer_sec.flush();
                    ter_offset += out_key.getBytes().length + String.valueOf(sec_offset).getBytes().length + 2;

                    StringBuilder sb = sortbyfreq(out_posting);
//                String sb=out_posting;
                    writer.write(out_key + ":" + sb.toString() + "\n");
                    writer.flush();
                    sec_offset += out_key.getBytes().length + sb.toString().getBytes().length+2;
                    pri_entries++;
                    //System.out.println("next is: "+lout_word+"last was"+out_word);
                    sb.setLength(0);
                    out_key = lout_key;
                    out_posting = lout_posting;
                    lout_key = "";
                    lout_posting = "";

                }
            }
            writer.close();
            writer_sec.close();
            writer_ter.close();

            System.out.print(" Records Written into merged file: " + no_entries + "\n");

        } catch (IOException e) {
            System.out.print(" Error in merging the files ");
        }
    }

}
