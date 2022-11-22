package Java9NewFeatures3;

public interface Test {

    default void test() {
        System.out.println("我是test方法默认实现");
        inner();
    }

    private static void inner() {
        System.out.println("我是接口中的私有方法");
    }

    static void xx() {
        inner();
    }

}
