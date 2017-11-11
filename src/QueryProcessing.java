/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author yojana
 */
class term_class_var {

    String word;
    String offset;

    public term_class_var(String word, String offset) {
        this.offset = offset;
        this.word = word;
    }
}

public class QueryProcessing {

    static porter p = new porter();
//    static HashMap<String, HashMap<String, Long>> global_hm = new HashMap<>();
    public static String[] SET_VALUES = new String[]{"a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero", "coord", "gr", "com", "tr", "td", "nbsp", "http", "https", "www"};
    static int wT = 10000, wB = 5, wC = 20, wE = 1, wR = 1, wI = 25;
    public static HashSet<String> stop_words = new HashSet<String>(Arrays.asList(SET_VALUES));
    static HashMap<String, Long> result_map = new HashMap<String, Long>();
    static ArrayList<String> titles = new ArrayList<String>();
    static ArrayList<term_class_var> terms = new ArrayList<term_class_var>();
    static ArrayList<term_class_var> doc_ids = new ArrayList<term_class_var>();
    static Long N = 2440575L;
    static Long startTime;

    static Comparator<term_class_var> c = new Comparator<term_class_var>() {
        public int compare(term_class_var u1, term_class_var u2) {
            return u1.word.compareTo(u2.word);
        }
    };

    static Comparator<Entry<String, Long>> valueComparator = new Comparator<Entry<String, Long>>() {

        @Override
        public int compare(Entry<String, Long> e1, Entry<String, Long> e2) {
            Long v1 = e1.getValue();
            Long v2 = e2.getValue();
            if (v1.compareTo(v2) < 0) {
                return 1;
            } else if (v1.compareTo(v2) == 0) {
                return 0;
            } else {
                return -1;
            }

        }
    };

    public static void readTer(ArrayList<term_class_var> a, String in_file) {
        try {
            File file = new File(in_file);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                String str = stringBuffer.toString();
               // System.out.println(str);

                String[] words = str.split(":");
                String key = words[0];
                String val = words[1];
                term_class_var t = new term_class_var(key, val);
                a.add(t);
                stringBuffer.setLength(0);
            }

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processQuery() throws FileNotFoundException, IOException {
        String in_file = "/media/yojana/New Volume1/index/tertiarytitleindex.txt";
        readTer(doc_ids, in_file);
        in_file = "/media/yojana/New Volume1/index/tertiaryindex.txt";
        readTer(terms, in_file);
        while (true) {
            titles.clear();
            result_map.clear();
            System.out.println("Enter Query");
            Scanner scanner = new Scanner(System.in);
            String query = scanner.nextLine();
            query=query.toLowerCase();
            startTime = System.currentTimeMillis();

            StringBuilder str = new StringBuilder();
            int len = query.length();
            int i = 0;
            while (i < len) {
                if (query.charAt(i) == ':' || query.charAt(i) == ' ' || (query.charAt(i) >= 'A' && query.charAt(i) <= 'Z') || (query.charAt(i) >= 'a' && query.charAt(i) <= 'z') || (query.charAt(i) >= '0' && query.charAt(i) <= '9')) {
                    str.append(query.charAt(i));
                } else {
                    str.append(' ');
                }
                i++;
            }
            int field = 0;
            if (str.toString().contains(":")) {
                field = 1;
            }
            StringTokenizer words = new StringTokenizer(str.toString(), " ");
            ArrayList<String> myList = new ArrayList<String>();
            ArrayList<String> wtList = new ArrayList<String>();

            ArrayList<String> st_list = new ArrayList<String>();
            char flg = 'n';
            while (words.hasMoreTokens()) {
                String word = (words.nextToken()).toLowerCase();
                if (word.length() > 0) {
                    if (stop_words.contains(word)) {
                        if (!st_list.contains(word)) {
                            st_list.add(word);
                        }
                    }

                    if (field == 1) {

                        if (word.contains("b:") || (!word.contains(":") && flg == 'b')) {

                            flg = 'b';
                            if (word.contains(":")) {
                                word = word.substring(word.indexOf(':'));
                            }
                            word = p.stripAffixes(word);
                            myList.add(word);

                            wtList.add("B");
                        }
                        if (word.contains("c:") || (!word.contains(":") && flg == 'c')) {

                            flg = 'c';
                            if (word.contains(":")) {
                                word = word.substring(word.indexOf(':'));
                            }
                            word = p.stripAffixes(word);
                            myList.add(word);
                            wtList.add("C");
                        }
                        if (word.contains("r:") || (!word.contains(":") && flg == 'r')) {
                            flg = 'r';
                            if (word.contains(":")) {
                                word = word.substring(word.indexOf(':'));
                            }
                            word = p.stripAffixes(word);
                            myList.add(word);
                            wtList.add("R");
                        }
                        if (word.contains("e:") || (!word.contains(":") && flg == 'e')) {
                            flg = 'e';
                            if (word.contains(":")) {
                                word = word.substring(word.indexOf(':'));
                            }
                            word = p.stripAffixes(word);
                            myList.add(word);
                            wtList.add("E");
                        }
                        if (word.contains("t:") || (!word.contains(":") && flg == 't')) {
                            flg = 't';
                            if (word.contains(":")) {
                                word = word.substring(word.indexOf(':'));
                            }
                            word = p.stripAffixes(word);
                            myList.add(word);
                            wtList.add("T");
                        }
                        if (word.contains("i:") || (!word.contains(":") && flg == 'i')) {
                            flg = 'i';
                            if (word.contains(":")) {
                                word = word.substring(word.indexOf(':'));
                            }
                            word = p.stripAffixes(word);
                            myList.add(word);
                            wtList.add("I");
                        }
                    } else {
                        word = p.stripAffixes(word);
                        myList.add(word);
                        wtList.add("n");
                    }

                }
            }
            if (st_list.size() != myList.size()) {
                for (int j = 0; j < st_list.size(); j++) {
                    if (myList.contains(st_list.get(j))) {
                        wtList.remove(myList.indexOf(st_list.get(j)));
                        myList.remove(st_list.get(j));

                    }
                }
            }

            FindDoc_id(myList, wtList);
            FindResult();
        }
    }

    public static void FindDoc_id(ArrayList<String> myList, ArrayList<String> wtList) throws FileNotFoundException, IOException {

        String pri_file = "/media/yojana/New Volume1/index/mainindex.txt";
        String sec_file = "/media/yojana/New Volume1/index/secondaryindex.txt";
        int flg_tit = 0;

        for (int i = 0; i < myList.size(); i++) {
            wT = 10000;
            wB = 5;
            wC = 20;
            wE = 1;
            wR = 1;
            wI = 25;
            String key = (String) myList.get(i);
            int match = 1;
            int index = Collections.binarySearch(terms, new term_class_var((String) myList.get(i), "0"), c);
            if (index < 0) {
                index = (-index - 2);
                match = 0;
            }
            long sec_ind = Long.parseLong(terms.get(index).offset);
            int is_there = secondary(sec_ind, (String) myList.get(i), (String) wtList.get(i), match, sec_file, pri_file, flg_tit);
        }
    }

    public static int secondary(Long sec_ind, String term, String f, int match, String file, String pri_file, int flg_tit) throws FileNotFoundException, IOException {

        RandomAccessFile raf = new RandomAccessFile(file, "r");
        raf.seek(sec_ind);
        long pri_ind = -1;
        if (match == 1) {
            String str = raf.readLine();
            StringTokenizer words = new StringTokenizer(str.toString(), ":");
            String term1 = words.nextToken();
            pri_ind = Long.parseLong(words.nextToken());
        } else {
            for (int i = 0; i < 50; i++) {
                String str = raf.readLine();
                if (str.length() > 0) {
                    StringTokenizer words = new StringTokenizer(str.toString(), ":");
                    String term1 = words.nextToken();
                    if (term.equals(term1)) {
                        pri_ind = Long.parseLong(words.nextToken());
                        break;
                    }
                }
            }

        }
        if (pri_ind != -1) {

            primary(f, pri_ind, term, pri_file, flg_tit);
            return 1;
        }
        return -1;
    }

    public static void primary(String f, Long pri_ind, String term, String file, int flg_tit) throws FileNotFoundException, IOException {
        long sTime = System.currentTimeMillis();
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        raf.seek(pri_ind);

        StringBuilder sb = new StringBuilder();
        char c1 = (char) raf.readByte();
        int cnt = 0;
        while (c1 != '\n' && cnt < 10000) {

            if (c1 == ';') {
                cnt++;
            }
            sb.append(c1);
            c1 = (char) raf.readByte();

        }
        String str = sb.toString();

        if (flg_tit == 0) {
            processPosting(str, f);
        } else {
            titles.add(str.substring(str.indexOf(':') + 1));
        }
    }

    public static void processPosting(String str, String f) {

        String[] tokens = str.split("\\:");

        StringTokenizer posting_list = new StringTokenizer(tokens[1].toString(), ";");
        HashMap<String, Long> tmp = new HashMap<String, Long>();
        int champs = 0;
        wT = 30000;
        if (f.equals("n")) {
            wT = 10000;
        } else if (f.equals("B")) {

            wB = 1000;

        } else if (f.equals("C")) {
            wC = 1000;
        } else if (f.equals("R")) {
            wR = 1000;
        } else if (f.equals("E")) {
            wE = 1000;
        } else if (f.equals("I")) {

            wI = 1000;
        } else if (f.equals("T")) {
            wT = 50000;
        }

        while (posting_list.hasMoreTokens()) {
            String doc_list = (posting_list.nextToken());
            int len = doc_list.length();
            int i = 0;
            String doc_id = "";
            Long weight = 0L;
            while (true) {

                while (i < len && doc_list.charAt(i) >= '0' && doc_list.charAt(i) <= '9') {
                    doc_id = doc_id + doc_list.charAt(i);
                    i++;
                }
                if (i < len && doc_list.charAt(i) == 'T') {
                    i++;
                    String freq = "";
                    while (i < len && doc_list.charAt(i) >= '0' && doc_list.charAt(i) <= '9') {
                        freq = freq + doc_list.charAt(i);
                        i++;
                    }
                    int fq = Integer.parseInt(freq);
                    weight = weight + wT * fq;
                }
                if (i < len && doc_list.charAt(i) == 'I') {
                    i++;
                    String freq = "";
                    while (i < len && doc_list.charAt(i) >= '0' && doc_list.charAt(i) <= '9') {
                        freq = freq + doc_list.charAt(i);
                        i++;
                    }
                    int fq = Integer.parseInt(freq);
                    weight = weight + wI * fq;

                }
                if (i < len && doc_list.charAt(i) == 'C') {
                    i++;
                    String freq = "";
                    while (i < len && doc_list.charAt(i) >= '0' && doc_list.charAt(i) <= '9') {
                        freq = freq + doc_list.charAt(i);
                        i++;
                    }
                    int fq = Integer.parseInt(freq);
                    weight = weight + wC * fq;

                }
                if (i < len && doc_list.charAt(i) == 'B') {
                    i++;
                    String freq = "";
                    while (i < len && doc_list.charAt(i) >= '0' && doc_list.charAt(i) <= '9') {
                        freq = freq + doc_list.charAt(i);
                        i++;
                    }
                    int fq = Integer.parseInt(freq);
                    weight = weight + wB * fq;

                }
                if (i < len && doc_list.charAt(i) == 'R') {
                    i++;
                    String freq = "";
                    while (i < len && doc_list.charAt(i) >= '0' && doc_list.charAt(i) <= '9') {
                        freq = freq + doc_list.charAt(i);
                        i++;
                    }
                    int fq = Integer.parseInt(freq);
                    weight = weight + wR * fq;

                }
                if (i < len && doc_list.charAt(i) == 'E') {
                    i++;
                    String freq = "";
                    while (i < len && doc_list.charAt(i) >= '0' && doc_list.charAt(i) <= '9') {
                        freq = freq + doc_list.charAt(i);
                        i++;
                    }
                    int fq = Integer.parseInt(freq);
                    weight = weight + wE * fq;
                }
                if (i == len) {
                    break;
                }
            }
            if (!result_map.containsKey(doc_id)) {
                result_map.put(doc_id, weight);
            } else {
                result_map.put(doc_id, result_map.get(doc_id) +10*weight);
            }
            champs++;
        }

    }

    public static void retrive_titles(List<Entry<String, Long>> listOfEntries) throws IOException {

        String pri_file = "/media/yojana/New Volume1/index/titleindex.txt";
        int flg_tit = 1;

        int count = 0;
        for (Entry<String, Long> entry : listOfEntries) {
            int match = 1;
            String key = entry.getKey();
            // System.out.println("doc_id="+key);
            int index = Collections.binarySearch(doc_ids, new term_class_var(key, "0"), c);
            if (index < 0) {
                index = (-index - 2);
                match = 0;
            }
            long sec_ind = Long.parseLong(doc_ids.get(index).offset);
            int is_there = secondary(sec_ind, key, "n", match, "/media/yojana/New Volume1/index/secondarytitleindex.txt", pri_file, flg_tit);
//            hm.put(key, entry.getValue());
//            result_map.put(, hm);             
            count++;
            if (count == 10) {
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + 1 + " " + titles.get(i));
            if (i == 9) {
                break;
            }
        }
        long totalTime = endTime - startTime;
        System.out.println("Resaponse time =  " + totalTime + "msec");

    }

    public static void FindResult() throws IOException {
        Set<Entry<String, Long>> entries = result_map.entrySet();
        List<Entry<String, Long>> listOfEntries = new ArrayList<Entry<String, Long>>(entries);
        // sorting HashMap by values using comparator
        Collections.sort(listOfEntries, valueComparator);
        for (Entry<String, Long> entry : listOfEntries) {
        }
        // find titles
        retrive_titles(listOfEntries);
    }

}
