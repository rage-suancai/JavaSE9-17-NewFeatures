package Java11NewFeatures1;

import java.util.function.Consumer;

/**
 * Java11新特性
 * Java11是继Java8之后的又一个TLS长期维护版本 在Java17出现之前 一直都是此版本作为广泛使用的版本 其中比较关键的是用于Lambda的形参局部变量语法
 *
 * 用于Lambda的形参局部变量语法
 * 在Java10我们认识了var关键字 它能够直接使用局部变量自动进行类推断 不过它不支持在lambda中使用:
 *                  Consumer<String> consumer = (var str) -> {
 *
 *                  };
 *
 * 但是实际上这里是完全可以进行类型推断的 所以在Java11 终于是支持了 这样编写就不会报错了:
 *                  Consumer<String> consumer = (var str) -> {
 *
 *                  };
 */
public class Main {

    static void test1() {

        Consumer<String> consumer = (var str) -> {
            System.out.println(str);
        };
        consumer.accept("YXS");

    }

    public static void main(String[] args) {
        test1();
    }

}
