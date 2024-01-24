package md2html;

import java.io.*;
import java.util.*;

public class Md2Html {
    public static final List<String> marks = List.of("*", "_", "**", "__", "`", "```", "--", "");
    public static Map<String, String[]> Html = new HashMap<>();

    // :NOTE: не должны быть static
    public static StringBuilder text;
    public static int point = 0;

    public static void main(String[] args) {
        Html.put("*", new String[]{"<em>", "</em>"});
        Html.put("_", new String[]{"<em>", "</em>"});
        Html.put("**", new String[]{"<strong>", "</strong>"});
        Html.put("__", new String[]{"<strong>", "</strong>"});
        Html.put("--", new String[]{"<s>", "</s>"});
        Html.put("", new String[]{"", ""}); // :NOTE: странное
        Html.put("`", new String[]{"<code>", "</code>"});
        Html.put("```", new String[]{"<pre>", "</pre>"});
        ArrayList<String> blocks = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(args[0]), "UTF-8"
        ))) {
            String str = in.readLine();
            StringBuilder block = new StringBuilder();

            while (str != null) {
                if (str.isEmpty() & !block.isEmpty()) {
                    blocks.add(block.toString());
                    block.setLength(0);
                }
                if (!block.isEmpty()) {
                    block.append(System.lineSeparator());
                }
                block.append(str);
                str = in.readLine();
            }

            if (!block.isEmpty()) {
                blocks.add(block.toString());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1]), "UTF-8"
        ))) {
            for (String bl : blocks) {
                point = 0;
                text = new StringBuilder();
                headInitial(bl);
                out.write(text.toString());
                out.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println();
        }
    }

    public static void headInitial(String block) {
        if (block.charAt(point) == '#') {
            int countHead = 0;
            while (block.charAt(point) == '#' & countHead <= 6) {
                countHead += 1;
                point += 1;
            }
            if (block.charAt(point) == ' ') {
                point += 1;
                text.setLength(0);
                text.append("<h");
                text.append(countHead);
                text.append(">");
                initial("", block);
                text.append("</h");
                text.append(countHead);
                text.append(">");
                return;

            } else {
                point -= countHead;
            }
        }

        text.setLength(0);
        text.append("<p>");
        initial("", block);
        text.append("</p>");
    }

    public static void initial(String mark, String block) {
        text.append(Html.get(mark)[0]);
        outer:
        while (point < block.length()) {
            if (point > 0) {
                if (block.charAt(point) == '\\') {
                    point += 1;
                    text.append(block.charAt(point));
                    point += 1;
                    continue outer;
                }
            }

            if (point < block.length() - 1) {
                if (!mark.isEmpty()) {
                    if (block.length() - 2 > point) {
                        if (block.substring(point, point + 3).equals(mark)) {
                            point += 3;
                            text.append(Html.get(mark)[1]);
                            return;
                        }
                    }

                    if (block.length() - 1 > point) {
                        if (block.substring(point, point + 2).equals(mark)) {
                            point += 2;
                            text.append(Html.get(mark)[1]);
                            return;
                        }
                    }

                    if (block.substring(point, point + 1).equals(mark)) {
                        if (block.length() - 1 > point) {
                            // :NOTE: дубль block.substring(point, point + 2)
                            if (marks.contains(block.substring(point, point + 2))) {
                                point += 2;
                                initial(block.substring(point - 2, point), block);
                                continue outer;

                            }
                        }
                        if (block.length() - 2 > point) {
                            String currentTag = block.substring(point, point + 3);
                            if (marks.contains(currentTag)) {
                                point += 3;
                                initial(currentTag, block);
                                continue outer;
                            }
                        }
                        point += 1;
                        text.append(Html.get(mark)[1]);
                        return;
                    }

                }

                if (!mark.equals("```")) {

                    for (int i = 3; i >= 1; i--) {
                        if (point < block.length() - i + 1) {
                            if (marks.contains(block.substring(point, point + i)) & !Character.isWhitespace(block.charAt(point + 1))) {
                                point += i;
                                initial(block.substring(point - i, point), block);
                                continue outer;
                            }
                        }
                    }

                }

            }
            if (block.charAt(point) == '<') {
                text.append("&lt;");
            } else if (block.charAt(point) == '>') {
                text.append("&gt;");
            } else if (block.charAt(point) == '&') {
                text.append("&amp;");
            } else {
                text.append(block.charAt(point));
            }
            point += 1;

        }
        text.append(Html.get(mark)[1]);
    }
}
