package markup;

import java.util.List;

public abstract class AbstractList implements ListElements {
    protected List<ListItem> elements;

    public AbstractList(List<ListItem> list) {
        elements = list;
    }

    public void toBBCode(StringBuilder text,String leftBBCode, String rightBBCode) {
        text.append(leftBBCode);
        for (ListItem el : elements) {
            el.toBBCode(text);
        }
        text.append(rightBBCode);
    }
}
