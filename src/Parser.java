/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author yojana
 */
public class Parser {
    public static void ExtractFields(StringBuilder currentText, StringBuilder currentTitle, StringBuilder currentId) {

        int i = 0;
        int open_curly = 0;
        int length_text = currentText.length();

        StringBuilder Infobox = new StringBuilder();
        StringBuilder Categories = new StringBuilder();
        StringBuilder References = new StringBuilder();
        StringBuilder External_links = new StringBuilder();
        StringBuilder Body_text = new StringBuilder();
        while (i < length_text) {

            if (currentText.charAt(i) == '{' && (i + 9) <= length_text && currentText.charAt(i + 1) == '{' && currentText.substring(i, i + 9).equals("{{Infobox")) {
                open_curly = 2;
                i += 9;
                while (i < length_text) {
                    if ((currentText.charAt(i) >= 'A' && currentText.charAt(i) <= 'Z') || (currentText.charAt(i) >= 'a' && currentText.charAt(i) <= 'z')) {
                        Infobox.append(currentText.charAt(i));
                    } else if (currentText.charAt(i) == '{') {
                        open_curly++;
                    } else if (currentText.charAt(i) == '}') {
                        open_curly--;
                        if (open_curly == 0) {
                            break;
                        }
                    } else {
                        Infobox.append(' ');
                    }
                    i++;
                }
            } else if (currentText.charAt(i) == '{' && (i + 6) <= length_text && currentText.charAt(i + 1) == '{' && currentText.substring(i, i + 6).equalsIgnoreCase("{{cite")) {

                i += 6;
                while (i < length_text) {
                    if (currentText.charAt(i) == '}' && i + 1 < length_text && currentText.charAt(i + 1) == '}') {
                        break;
                    }
                    i++;
                }
//                           
            } else if (currentText.charAt(i) == '{' && (i + 6) <= length_text && currentText.charAt(i + 1) == '{' && currentText.substring(i, i + 6).equals("{{file")) {

                i += 6;
                while (i < length_text) {
                    if (currentText.charAt(i) == '}' && i + 1 < length_text && currentText.charAt(i + 1) == '}') {
                        break;
                    }
                    i++;
                }
//                           
            } else if (currentText.charAt(i) == '[' && (i + 7) <= length_text && currentText.charAt(i + 1) == '[' && currentText.substring(i, i + 7).equals("[[Image")) {

                i += 7;
                while (i < length_text) {
                    if (currentText.charAt(i) == ']' && i + 1 < length_text && currentText.charAt(i + 1) == ']') {
                        break;
                    }
                    i++;
                }
//                           
            } else if ((currentText.charAt(i) == '=' && i + 14 <= length_text && currentText.charAt(i + 1) == '=' && currentText.substring(i, i + 14).contains("==References==")) || (currentText.charAt(i) == '=' && i + 16 <= length_text && currentText.charAt(i + 1) == '=' && currentText.substring(i, i + 16).contains("== References =="))) {
                if (currentText.substring(i, i + 14).contains("==References==")) {
                    i += 14;
                } else {
                    i += 16;
                }
//                           
                while (i < length_text) {
                    if ((currentText.charAt(i) >= 'A' && currentText.charAt(i) <= 'Z') || (currentText.charAt(i) >= 'a' && currentText.charAt(i) <= 'z') ) {
                        References.append(currentText.charAt(i));
                    } else if ((currentText.charAt(i) == '=' && i + 1 < length_text && currentText.charAt(i + 1) == '=')) {
                        break;
                    } else if ((currentText.charAt(i) == '[' && i + 11 <= length_text && currentText.charAt(i + 1) == '[') && currentText.substring(i, i + 11).contains("[[Category:")) {
                        break;
                    } else {
                        References.append(' ');
                    }

                    i++;
                }
//                           
            } else if (currentText.charAt(i) == '=' && i + 18 <= length_text && currentText.charAt(i + 1) == '=' && currentText.substring(i, i + 18).contains("==External_links==")) {
                i += 18;

                while (i < length_text) {
                    if ((currentText.charAt(i) >= 'A' && currentText.charAt(i) <= 'Z') || (currentText.charAt(i) >= 'a' && currentText.charAt(i) <= 'z') ) {
                        External_links.append(currentText.charAt(i));
                    } else if ((currentText.charAt(i) == '=' && i + 1 < length_text && currentText.charAt(i + 1) == '=')) {
                        break;
                    } else if ((currentText.charAt(i) == '[' && i + 11 <= length_text && currentText.charAt(i + 1) == '[') && currentText.substring(i, i + 11).contains("[[Category:")) {
                        break;
                    } else {
                        External_links.append(' ');
                    }

                    i++;
                }
//                           
            } else if (currentText.charAt(i) == '[' && i + 11 <= length_text && currentText.charAt(i + 1) == '[' && currentText.substring(i, i + 11).contains("[[Category:")) {

                i += 11;

                while (i < length_text) {
                    if ((currentText.charAt(i) >= 'A' && currentText.charAt(i) <= 'Z') || (currentText.charAt(i) >= 'a' && currentText.charAt(i) <= 'z') ) {
                        Categories.append(currentText.charAt(i));
                    } else if ((currentText.charAt(i) == ']' && i + 1 < length_text && currentText.charAt(i + 1) == ']')) {
                        break;
                    } else {
                        Categories.append(' ');
                    }

                    i++;
                }

            } else {
                if ((currentText.charAt(i) >= 'A' && currentText.charAt(i) <= 'Z') || (currentText.charAt(i) >= 'a' && currentText.charAt(i) <= 'z') || (currentText.charAt(i) >= '0' && currentText.charAt(i) <= '9')) {
                    Body_text.append(currentText.charAt(i));

                } else {
                    Body_text.append(' ');
                }
                i++;

            }


        }
        tokenize.handle_Title(currentTitle, currentId.toString(), "T");
        tokenize.handle_fields(Infobox, currentId.toString(), "I");
        tokenize.handle_fields(Body_text, currentId.toString(), "B");
        tokenize.handle_fields(References, currentId.toString(), "R");
        tokenize.handle_fields(External_links, currentId.toString(), "E");
        tokenize.handle_fields(Categories, currentId.toString(), "C");
        currentTitle.setLength(0);
        Infobox.setLength(0);

        References.setLength(0);
        External_links.setLength(0);
        Categories.setLength(0);

    }
    
}
