package Java12_16NewFeatures2;

/**
 * 文本块
 * 如果你学习过Python 一定知道三引号:
 *                  multi_line = """
 *                                 nice to meet you!
 *                                   nice to meet you!
 *                                      nice to meet you!
 *                                """
 *                  print multi_line
 *
 * 没错 Java13也带了这样的特性 皆在方便我们编写复杂字符串 这样就不用再去用那么多的转义字符了:
 *                  var s = """
 *                          dajdhuawhda
 *                          iawhdawidawi
 *                          jdiwhadiwahw"ndwaidhwai"
 *                          """;
 *
 * 可以看到 Java中也可以使用这样的三引号来表示字符串了 并且我们可以随意在里面使用特殊字符 包括双引号等 但是最后编译出来的结果实际上还是会变成一个之前这样使用了转义字符的字符串
 *                  public class Main {
 *                      public Main() {
 *                      }
 *
 *                      static void test1() {
 *                          String s = "dajdhuawhda\niawhdawidawi\njdiwhadiwahw\"ndwaidhwai\"\n";
 *                          System.out.println(s);
 *                      }
 *
 *                      public static void main(String[] args) {
 *                          test1();
 *                      }
 *                  }
 *
 * 仔细想想 这样我们写SQL或是HTML岂不是就舒服多了?
 *
 * 注意: 文本块表达式在Java15才正式开放使用 所以我们项目的代码级别需要调整到15以上
 */
public class Main {

    static void test1() {

        var s = """
                dajdhuawhda
                iawhdawidawi
                jdiwhadiwahw"ndwaidhwai"
                """;
        System.out.println(s);

    }

    public static void main(String[] args) {
        test1();
    }

}
