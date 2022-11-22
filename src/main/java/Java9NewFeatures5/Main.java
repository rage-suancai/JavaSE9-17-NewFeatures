package Java9NewFeatures5;

import java.util.stream.Stream;

/**
 * 改进的Stream API
 * 还记得我们之前再JavaSE中学习的Stream流吗? 当然这里不是指进行IO操作的流 而是JDK1.8新增的StreamAPI 通过它大大方便了我们的编程
 *                  Stream
 *                           .of("A", "B", "B", "C") // 这里我们可以直接将一些元素封装到Stream中
 *                           .filter(S -> S.equals("B")) // 通过过滤器过滤
 *                           .distinct() // 去重
 *                           .forEach(System.out::println); // 最后打印
 *
 * 自从有了Stream 我们对于集合的一些操作就大大简化了 对集合中元素的批量处理 只需要在Steam中一气呵成(具体的详细操作请回顾JavaSE篇)
 *
 * 如此方便的框架 在Java9得到了进一步的增强:
 *                  Stream
 *                          .of(null) // 如果传入null会报错
 *                          .forEach(System.out::println);
 *                  Stream
 *                          .ofNullable(null) // 使用新增的ofNullable方法 这样就不会了 不过这样的话流里面就没东西了
 *                          .forEach(System.out::println);
 *
 * 还有 我们可以通过迭代快速生成一组数据(实际上Java8就有了 这里新增的是允许结束迭代的):
 *                  Stream
 *                          // Java8只能像这样生成无限的流 第一个参数是种子 就是后面的UnaryOperator的参数i一开始的值 最后会返回一个值作为i的新值
 *                             每一轮都会执行UnaryOperator并生成一个新值流中 这个是源源不断的 如果不加limit()进行限制的话 将无限生成下去
 *                          .iterate(0, i -> i + 1)
 *                          .limit(20) // 这里限制生成20个
 *                          .forEach(System.out::println);
 *
 *                  Stream
 *                          // 不知道怎么写 参考一下: for(int i = 0; i < 20; i++)
 *                          .iterate(0, i -> i < 20, i -> i + 1) // 快速生成一组0-19的int数据 中间可以添加一个断言 表示声明时候结束生成
 *                          .forEach(System.out::println);
 *
 * Stream还新增了对数据的截断操作 比如我们希望在读取到某个元素时截断 不再继续操作后面的元素:
 *                  Stream
 *                          .iterate(0, i -> i + 1)
 *                          .limit(20)
 *                          .takeWhile(i -> i < 10) // 当i小于10时正常通过 一旦大于大于10直接截断
 *                          .forEach(System.out::println);
 *
 *                  Stream
 *                          .iterate(0, i -> i + 1)
 *                          .limit(20)
 *                          .dropWhile(i -> i < 10) // 和上面相反 上来就是截断状态 只有当满足条件再开始通过
 *                          .forEach(System.out::println);
 */
public class Main {

    static void test1() {

        /*Stream
                .of("A", "B", "B", "C")
                .filter(S -> S.equals("B"))
                .distinct()
                .forEach(System.out::println);*/

        /*Stream
                .of(null)
                .forEach(System.out::println);
        Stream
                .ofNullable(null)
                .forEach(System.out::println);*/

        /*Stream
                .iterate(0, i -> i + 1)
                .limit(20)
                .forEach(System.out::println);*/

        Stream
                .iterate(0, i -> i < 20, i -> i + 1)
                .forEach(System.out::println);

    }

    static void test2() {

        Stream
                .iterate(0, i -> i + 1)
                .limit(20)
                .takeWhile(i -> i < 10)
                .forEach(System.out::println);

        System.out.println();

        Stream
                .iterate(0, i -> i + 1)
                .limit(20)
                .dropWhile(i -> i < 10)
                .forEach(System.out::println);

    }

    public static void main(String[] args) {
        //test1();
        test2();
    }

}
