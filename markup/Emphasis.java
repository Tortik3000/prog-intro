package markup;

import java.util.List;

public class Emphasis extends AbstractMarkdown implements ParagraphElements{
    public Emphasis(List<ParagraphElements> list) {
        super(list);
    }

    public void toMarkdown(StringBuilder text) {
        toMarkdown(text,"*");
    }

    public void toBBCode(StringBuilder text){
        toBBCode(text,"[i]","[/i]");
    }
}
