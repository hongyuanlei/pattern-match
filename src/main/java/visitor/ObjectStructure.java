package visitor;

import java.util.ArrayList;
import java.util.List;

public class ObjectStructure {

    private List<Visitable> list = new ArrayList<>();

    public void add(Visitable visitable) {
        list.add(visitable);
    }

    public void action(Visitor visitor) {
        list.forEach(v -> v.accept(visitor));
    }
}
