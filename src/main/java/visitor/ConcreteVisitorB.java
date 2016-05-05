package visitor;

public class ConcreteVisitorB implements Visitor {

    @Override
    public void visit(ConcreteElementA able) {
        able.operate02();
    }

    @Override
    public void visit(ConcreteElementB able) {
        able.operateB();
    }
}
