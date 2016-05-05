package visitor;


public class ConcreteElementA implements Visitable{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operate01() {
        System.out.println("operate01 ConcreteElementA ...");
    }

    public void operate02() {
        System.out.println("operate02 ConcreteElementA ...");
    }
}
