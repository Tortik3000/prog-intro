import java.util.*;
import java.io.*;

public class Scanner implements AutoCloseable {
    private final Reader reader;

    private final int BUFFER_SIZE = 1024;

    private final char[] buffer = new char[BUFFER_SIZE];

    private int lenBuffer;

    private int position;


    private boolean nextLine = true;

    private final String LINE_SEPARATOR = System.lineSeparator();

    Scanner(InputStream in) throws IOException {
        this.reader = new InputStreamReader(in, "UTF-8");
        bufferRead();
    }

    Scanner(String fileName, String encoding) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(fileName), encoding);
        bufferRead();
    }

    Scanner(String str) throws IOException {
        this.reader = new BufferedReader(new StringReader(str));
        bufferRead();
    }

    public void close() throws IOException {
        reader.close();
    }

    void bufferRead() throws IOException {
        lenBuffer = reader.read(buffer);
        position = 0;
    }

    boolean isSeparator(String str) {
        if (str.length() < LINE_SEPARATOR.length()) {
            return false;
        }
        return (LINE_SEPARATOR.equals(str.substring(str.length() - LINE_SEPARATOR.length())));
    }


    boolean isToken(char chr, Tokenable tk) {
        return tk.isToken(chr);
    }


    boolean isEndLine(Tokenable tk) throws IOException {
        StringBuilder lastSymbols = new StringBuilder();

        while (position < lenBuffer) {
            lastSymbols.append(buffer[position]);
            if (lastSymbols.length() > 3) {
                lastSymbols.deleteCharAt(0);
            }
            if (isSeparator(lastSymbols.toString())) {
                position += 1;
                nextLine = false;
                return true;
            } else if (isToken(buffer[position], tk)) {
                return false;
            }
            position += 1;
            if (position == lenBuffer) {
                bufferRead();
            }
        }
        return true;
    }

    public String next(Tokenable tk) throws IOException {
        StringBuilder word = new StringBuilder();
        if (!isEndLine(tk)) {

            while (isToken(buffer[position], tk)) {
                word.append(buffer[position]);
                position += 1;
                if (position == lenBuffer) {
                    bufferRead();
                    if (lenBuffer == -1) {
                        break;
                    }
                }
            }
            return word.toString();

        } else {
            throw new NoSuchElementException();
        }
    }


    boolean hasNextLine() throws IOException {
        if (nextLine) {
            return true;
        }
        if (position < lenBuffer) {
            nextLine = true;
            return true;
        } else {
            bufferRead();
            if (lenBuffer >= 0) {
                nextLine = true;
                return true;
            } else {
                return false;
            }
        }
    }

    String nextLine() throws IOException {
        StringBuilder line = new StringBuilder();
        StringBuilder sep = new StringBuilder();
        if (hasNextLine()) {
            while (!(isSeparator(sep.toString()))) {

                sep.append(buffer[position]);
                if (sep.length() > 3) {
                    sep.deleteCharAt(0);
                }

                line.append(buffer[position]);

                position += 1;
                if (position == lenBuffer) {
                    bufferRead();
                    if (lenBuffer < 0) {
                        break;
                    }
                }

            }
            position += 1;
            nextLine = false;
            return line.toString();
        } else {
            throw new NoSuchElementException();
        }
    }
}
interface Tokenable{
    boolean isToken(char chr);
}