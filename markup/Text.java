package markup;

public class Text implements ParagraphElements {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }

    public void toBBCode(StringBuilder sb) {
        sb.append(text);
    }
}
