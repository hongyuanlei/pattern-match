package dom4j;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;

public class Dom4jTest {

    public class MyVisitor extends VisitorSupport {
        public void visit(Attribute node) {
            System.out.println("属性 : " + node.getName() + " = " + node.getValue());
        }

        public void visit(Element node) {
            if (node.isTextOnly()) {
                System.out.println("节点: " + node.getName() + " = " + node.getText());
            } else {
                System.out.println("节点：" + node.getName());
            }
        }
    }

    public void test() {

        ClassLoader classLoader = getClass().getClassLoader();
        SAXReader saxReader = new SAXReader();
        File file = new File(classLoader.getResource("test.xml").getFile());
        try {
            Document doc = saxReader.read(file);
            doc.accept(new Dom4jTest().new MyVisitor());
        } catch (DocumentException de) {
            de.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        new Dom4jTest().test();
    }

}
