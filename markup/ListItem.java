package markup;

import java.util.List;

public class ListItem {
    private final List<ListElements> elements;

    public ListItem(List<ListElements> elements) {
        this.elements = elements;
    }

    void toBBCode(StringBuilder text){
        text.append("[*]");
        for (ListElements el : elements){
            el.toBBCode(text);
        }
    }
}
