package dispatch;

public class Dispatch {

    static class IE {

    }

    static class Chrome {

    }

    public static class Father {

        public void hardChoice(IE browser) {
            System.out.println("Father choose IE");
        }

        public void hardChoice(Chrome browser) {
            System.out.println("Father choose Chrome");
        }
    }

    public static class Son extends Father {

        @Override
        public void hardChoice(IE browser) {
            System.out.println("Son choose IE");
        }

        @Override
        public void hardChoice(Chrome browser) {
            System.out.println("Son choose Chrome");
        }
    }


    public static void main(String[] args) {
        Father father = new Father();
        father.hardChoice(new IE());

        Father son = new Son();
        son.hardChoice(new Chrome());

    }
}
