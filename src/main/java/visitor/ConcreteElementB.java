package visitor;


public class ConcreteElementB implements Visitable{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operateA() {
        System.out.println("operateA ConcreteElementB ...");
    }

    public void operateB() {
        System.out.println("operateB ConcreteElementB ...");
    }
}
