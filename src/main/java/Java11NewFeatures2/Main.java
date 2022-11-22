package Java11NewFeatures2;

/**
 * 针对于String类的方法增强
 * 在Java11为String新增一些更加方便的操作:
 *                  var str = "AB\nC\nD";
 *                  System.out.println(str.isBlank()); // isBlank方法用于判断是否字符串为空或者是仅包含空格
 *                  str
 *                          .lines() // 根据字符串中的\n换行符进行切割 分为多个字符串 并转换为Stream进行操作
 *                          .forEach(System.out::println);

 * 我们还可以通过repeat()方法来让字符串重复拼接:
 *                  String str = "ABCD"; // 比如现在我们有一个ABCD 但是现在我们想要一个ABCDABCD这样的基于原本字符串
 *                  System.out.println(str.repeat(2)); // 一个repeat就搞定了

 * 我们也可以快速地进行空格去除操作:
 *                  String str = "A B C D";
 *                  System.out.println(str.strip()); // 去除首尾空格
 *                  System.out.println(str.stripLeading()); // 去除首部空格
 *                  System.out.println(str.stripTrailing()); // 去除尾部空格
 */
public class Main {

    static void test1() {

        /*var str = "AB\nC\nD";
        System.out.println(str.isBlank());
        str
                .lines()
                .forEach(System.out::println);*/

        /*String str = "ABCD";
        System.out.println(str.repeat(2));*/

        String str = " A B C D ";
        System.out.println(str.strip());
        System.out.println(str.stripLeading());
        System.out.println(str.stripTrailing());

    }

    public static void main(String[] args) {
        test1();
    }

}
