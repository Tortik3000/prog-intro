package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> list) {
        super(list);
    }

    public void toBBCode(StringBuilder test) {
        toBBCode(test, "[list]", "[/list]");
    }
}
