/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author yojana
 */
public class tokenize {

    static HashMap<String, HashMap<String, HashMap<String, Integer>>> map1 = new HashMap<>();
    static HashMap<String, HashMap<String, Integer>> map2;
    static HashMap<String, Integer> map3;

    public static String[] SET_VALUES = new String[]{"a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero", "coord", "gr", "com", "tr", "td", "nbsp", "http", "https", "www"};

    public static HashSet<String> stop_words = new HashSet<>(Arrays.asList(SET_VALUES));
    static porter p = new porter();

    public static boolean isNumeric(String s) {
        return java.util.regex.Pattern.matches("\\d+", s);
    }

    public static void handle_fields(StringBuilder str, String doc_id_st, String c) {
        int wt=1;
        if(c=="I")
            wt=25;
        else if(c=="B")
            wt=5;
        else if(c=="T")
            wt=10000;
        else if(c=="C")
            wt=20;
        else if(c=="E")
            wt=1;
        else if(c=="R")
            wt=1;
        String doc_id = doc_id_st;
        StringTokenizer words = new StringTokenizer(str.toString(), " ");

        while (words.hasMoreTokens()) {
            String word = (words.nextToken()).toLowerCase();
            if (!stop_words.contains(word) && word.length() < 16) {
                word = p.stripAffixes(word);

                if (word != null && ((word.length() > 3 && word.length() < 15) || (isNumeric(word) && word.length() > 0 && word.length() < 8))) {
                    if (map1.containsKey(word)) {
                        map2 = map1.get(word);
                        if (map2.containsKey(doc_id)) {
                            map3 = map2.get(doc_id);
                            if (map3.containsKey(c)) {
                                map3.put(c, map3.get(c) + 1);
                            } else {
                                map3.put(c, 1);
                            }
                            if (map3.containsKey("F")) {
                                map3.put("F", map3.get("F") + wt);
                            }

                        } else {
                            map3 = new HashMap<>();
                            map3.put(c, 1);
                            map3.put("F", wt);
                            map2.put(doc_id, map3);
                        }
                    } else {
                        map3 = new HashMap<>();
                        map3.put(c, 1);
                        map3.put("F", wt);
                        map2 = new HashMap<>();
                        map2.put(doc_id, map3);
                        map1.put(word, map2);
                    }
                }
            }

        }

    }

    public static void handle_Title(StringBuilder str, String doc_id_st, String c) {
//        System.out.println(doc_id);
        StringTokenizer words = new StringTokenizer(str.toString(), " ");
        String doc_id = doc_id_st;

        while (words.hasMoreTokens()) {
            String word = (words.nextToken()).toLowerCase();
            if (word != null && word.length() > 0) {
                word = p.stripAffixes(word);
                if (word != null && word.length() > 0) {
                if (map1.containsKey(word)) {
                    map2 = map1.get(word);
                    if (map2.containsKey(doc_id)) {
                        map3 = map2.get(doc_id);
                        if (map3.containsKey(c)) {
                            map3.put(c, map3.get(c) + 1);
                        } else {
                            map3.put(c, 1);
                        }

                        if (map3.containsKey("F")) {
                            map3.put("F", map3.get("F") + 10000);
                        }
//                        
                    } else {
                        map3 = new HashMap<>();
                        map3.put(c, 1);
                        map3.put("F", 10000);
                        map2.put(doc_id, map3);
                    }
                } else {
                    map3 = new HashMap<>();
                    map3.put(c, 1);
                    map3.put("F", 10000);
                    map2 = new HashMap<>();
                    map2.put(doc_id, map3);
                    map1.put(word, map2);
                }
                }
            }
        }
    }

    public static void printMap(String filenm) {
        TreeMap<String, HashMap<String, HashMap<String, Integer>>> treemap = new TreeMap<>();
        treemap.putAll(map1);

        try {
            PrintWriter writer = new PrintWriter(filenm, "UTF-8");
            for (HashMap.Entry<String, HashMap<String, HashMap<String, Integer>>> Entry1 : treemap.entrySet()) {
                if(Entry1.getKey()!=null&& Entry1.getKey().length()>0 && Entry1.getKey().length()>0){
                writer.print(Entry1.getKey() + ":");
                int flg = 0;
                for (HashMap.Entry<String, HashMap<String, Integer>> Entry2 : Entry1.getValue().entrySet()) {
                    if (flg != 0) {
                        writer.print(";");
                    }

                    if(Entry2.getKey().length()>0 &&  Entry2.getValue().size()>0 ){
                    flg = 1;
                    writer.print(Entry2.getKey());
                    for (HashMap.Entry<String, Integer> Entry3 : Entry2.getValue().entrySet()) {

                        writer.print(Entry3.getKey() + Entry3.getValue());
                    }
                    }

                }
                writer.print("\n");
                }
//               
            }
            writer.close();

        } catch (IOException e) {
            // do something
        }
        map1.clear();
    }

    public static void printtit(String filenm) {
        BufferedWriter bw_it = null;
        FileWriter fw_it = null;

        try {
            PrintWriter writer = new PrintWriter(filenm, "UTF-8");
            for (Map.Entry<String, String> Entry1 : sax_parser.id_tit.entrySet()) {
                if(Entry1.getKey().length()>0 && Entry1.getValue().length()>0){
                writer.write(Entry1.getKey() + ":" + Entry1.getValue() + '\n');
                writer.flush();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
