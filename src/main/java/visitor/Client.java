package visitor;

public class Client {

    public static void main(String[] args) {

        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.add(new ConcreteElementA());
        objectStructure.add(new ConcreteElementB());

        Visitor visitorA = new ConcreteVisitorA();
        objectStructure.action(visitorA);

        Visitor visitorB = new ConcreteVisitorB();
        objectStructure.action(visitorB);
    }
}
