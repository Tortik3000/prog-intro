package markup;

import java.util.List;

public class Strong extends AbstractMarkdown implements ParagraphElements{
    public Strong(List<ParagraphElements> list) {
        super(list);
    }

    public void toMarkdown(StringBuilder text) {
        toMarkdown(text,"__");
    }

    public void toBBCode(StringBuilder text){
        toBBCode(text,"[b]","[/b]");
    }
}
