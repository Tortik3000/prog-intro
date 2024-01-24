package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> list) {
        super(list);
    }

    public void toBBCode(StringBuilder test) {
        toBBCode(test, "[list=1]", "[/list]");
    }
}
