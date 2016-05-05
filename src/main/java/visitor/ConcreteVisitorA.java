package visitor;

public class ConcreteVisitorA implements Visitor {

    @Override
    public void visit(ConcreteElementA able) {
        able.operate01();
    }

    @Override
    public void visit(ConcreteElementB able) {
        able.operateA();
    }
}
