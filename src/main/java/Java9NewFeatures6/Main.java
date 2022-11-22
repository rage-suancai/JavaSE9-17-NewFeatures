package Java9NewFeatures6;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * 其他小型变动
 * Try-with-resource语法现在不需要再完整的声明一个变量了 我们可以直接将现有的变量丢进去:
 *                  static void test1() throws IOException {
 *
 *                      InputStream inputStream = Files.newInputStream(Paths.get("pom.xml"));
 *                      try (inputStream){ // 单独丢进try中 效果是一样的
 *                          for (int i = 0; i < 100; i++)
 *                              System.out.print((char) inputStream.read());
 *                      }
 *
 *                  }
 *
 * 再java8中引入了Optional类 它很好的解决了判空问题:
 *                  Optional
 *                          .ofNullable(s)
 *                          .ifPresent(str -> System.out.println(str.toLowerCase()));
 *
 * 这种写法就有点像Kotlin或是JS中的语法:
 *                  fun main() {
 *                      test(null)
 *                  }
 *
 *                  fun test(str : String?) { // 传入的String对象可能为null 这里类写为String?
 *                      println(str?.lowercase()) // ?.表示只有不为空才进行调用
 *                  }
 *
 * 在Java9新增了一些更加方便的操作:
 *                  String str = null;
 *                  Optional.ofNullable(str).ifPresentOrElse(s -> { // 通过使用ifPresentOrElse 我们同时处理两种情况
 *                      System.out.println("被包装的元素为: " + s); // 第一种情况和ifPresent是一样的
 *                  }, () -> {
 *                      System.out.println("被包装的元素为null"); // 第二种情况是如果为null的情况
 *                  });
 *
 * 我们也可以使用or()方法快速替换为另一个Optional类:
 *                  Optional.ofNullable(str)
 *                          .or(() -> Optional.of("AAA")) // 如果当前被包装的类不是null 依然返回自己 但是如果是null 那就返回Supplier提供的另一个Optional包装
 *                          .ifPresent(System.out::println);
 *
 * 当然还支持直接转换为Stream 这里就不多说了
 *
 * 在Java8以及之前 匿名内部类是没办法使用钻石运算符进行自动类型推断的:
 *                  public abstract class Test<T> { // 这里我们写一个泛型类
 *
 *                      public T t;
 *
 *                      public Test(T t) {
 *                          this.t = t;
 *                      }
 *
 *                      public abstract T test();
 *
 *                  }
 *
 *                  static void test3() throws IOException {
 *                      // 在低版本这样写是会报错的 因为匿名内部类不支持自动类推断 但是很明显我们这里给的参数是String类型的 所以明明有机会进行类型推断 却还是要我们自己填类型
 *                      Test<String> test = new Test<>("AAA") {
 *                          // 在Java9之后 这样的写法终于可以编译通过了
 *                          @Override
 *                          public String test() {
 *                              return t;
 *                          }
 *                      };
 *
 *                  }
 *
 * 当然除了以上的特性之外还有Java9的多版本JAR包支持 CompletableFutureAPI的改进等 因为不太常用 这里就不做介绍了
 */
public class Main {

    static void test1() throws IOException {

        InputStream inputStream = Files.newInputStream(Paths.get("pom.xml"));
        try (inputStream){
            for (int i = 0; i < 100; i++)
                System.out.print((char) inputStream.read());
        }

    }

    static void test2(String str) {

        /*Optional
                .ofNullable(str)
                .ifPresent(s -> System.out.println(s.toLowerCase()));*/

        /*Optional.ofNullable(str).ifPresentOrElse(s -> {
            System.out.println("被包装的元素为: " + s);
        }, () -> {
            System.out.println("被包装的元素为null");
        });*/

        Optional.ofNullable(str)
                .or(() -> Optional.of("AAA"))
                .ifPresent(System.out::println);

    }

    static void test3() throws IOException {

        Test<String> test = new Test<>("AAA") {
            @Override
            public String test() {
                return t;
            }
        };

    }

    public static void main(String[] args) throws IOException {

        //test1();
        //test2(null);
        test3();

    }

}
