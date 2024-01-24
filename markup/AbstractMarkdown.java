package markup;

import java.util.List;

public abstract class AbstractMarkdown  {
    protected List<ParagraphElements> elements;

    public AbstractMarkdown(List<ParagraphElements> list) {
        elements = list;
    }

    public void toMarkdown(StringBuilder text, String mark) {
        text.append(mark);
        for (ParagraphElements el : elements) {
            el.toMarkdown(text);
        }
        text.append(mark);
    }

    public void toBBCode(StringBuilder text, String leftBBCode, String rightBBCode) {
        text.append(leftBBCode);
        for (ParagraphElements el : elements) {
            el.toBBCode(text);
        }
        text.append(rightBBCode);
    }
}
