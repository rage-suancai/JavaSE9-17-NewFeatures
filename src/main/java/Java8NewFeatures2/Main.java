package Java8NewFeatures2;

import java.util.Optional;

/**
 * Optional类
 * Java8中新引入了Optional特性 来让我们更优雅的除处理空指针异常 我们先来看看下面这个例子:
 *                  public static void hello(String str) { // 现在我们要实现一个方法 将传入的字符串转换为小写并打印
 *                      System.out.println(str.toLowerCase()); // 那太简单了吧 直接转换打印一气呵成
 *                  }
 *
 * 但是这样实现的话 我们少考虑了一个问题 万一给进来的str是null呢? 如果是null的话
 * 在调用toLowerCase方法时岂不是直接空指针异常了? 所以我们还得判空一下:
 *                  static void hello(String str) {
 *
 *                      if (str != null) {
 *                          System.out.println(str.toLowerCase());
 *                      }
 *
 *                  }
 *
 * 但是这样写着就不能一气呵成了 我现在有有强迫症 我就想一行解决 这时 Optional来了 我们可以将任何的变量包装进Optional类中使用:
 *                  Optional
 *                          .ofNullable(str) // 将str包装进Optional
 *                          .ifPresent(s -> { // ifPresent表示只有对象不为null才会执行里面的逻辑
 *                              System.out.println(s);
 *                          });
 *
 * 由于这里只有一句打印 所以我们来优化一下:
 *                  Optional
 *                          .ofNullable(str) // 将str包装进Optional
 *                          .ifPresent(System.out::println);
 *                  // println也是接受一个String参数 返回void 所以这里使用我们前面提到的方法引用的写法
 *
 * 这样 我们就又可以一气呵成了 是不是感觉比之前的写法更优雅
 *
 * 除了在不为空时执行的操作外 还可以直接从Optional中获取被包装的对象:
 *                  System.out.println(Optional.ofNullable(str).get());
 *
 * 不过此时被包装的对象为null时会直接抛出异常 当然 我们还可以指定如果get的对象为null的替代方案:
 *                  System.out.println(Optional.ofNullable(str).ofElse("VVV"));
 *
 * 其他操作还请回顾JavaSE篇
 */
public class Main {

    public static void main(String[] args) {
        hello("ABCDEFG");
    }

    static void hello(String str) {

        /*if (str != null) {
            System.out.println(str.toLowerCase());
        }*/

        /*Optional
                .ofNullable(str)
                .ifPresent(s -> {
                    System.out.println(s);
                });*/

        Optional.ofNullable(str).ifPresent(s -> System.out.println(s.toLowerCase()));

    }

}
