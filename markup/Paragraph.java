package markup;

import java.util.List;

public class Paragraph extends AbstractMarkdown implements ListElements {

    public Paragraph(List<ParagraphElements> list) {
        super(list);
    }
    public void toMarkdown(StringBuilder text) {
        toMarkdown(text,"");
    }

    public void toBBCode(StringBuilder text){
        toBBCode(text,"","");
    }
}
