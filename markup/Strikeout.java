package markup;

import java.util.List;

public class Strikeout extends AbstractMarkdown implements ParagraphElements{

    public Strikeout(List<ParagraphElements> list) {
        super(list);
    }
    public void toMarkdown(StringBuilder text) {
        toMarkdown(text,"~");
    }

    public void toBBCode(StringBuilder text){
        toBBCode(text,"[s]","[/s]");
    }

}
