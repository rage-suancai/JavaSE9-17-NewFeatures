package Java12_16NewFeatures4;

/**
 * 空指针异常的改进
 * 相信各位小伙伴在调试代码时 经常遇到空指针异常 比如下面的这个例子:
 *                  int length = a.length() + b.length(); // 可能给进来的a或是b为null
 *                  System.out.println(length);
 *
 * 那么为空时 就会直接:
 *                  Exception in thread "main" java.lang.NullPointerException Create breakpoint
 * 	                    at con.test/Java12_16NewFeatures4.Main.test1(Main.java:19)
 * 	                    at con.test/Java12_16NewFeatures4.Main.main(Main.java:25)
 *
 * 但是由于我们这里a和b都调用了length()方法 虽然空指针异常告诉我们问题出现在这一行
 * 但是到底是a为null还是b为null呢? 我们是没办法直接得到的(遇到过这种问题的扣个1吧 只能调试 就很头疼)
 *
 * 但是当我们在Java14或更高版本运行时:
 *                  Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "b" is null
 * 	                    at con.test/Java12_16NewFeatures4.Main.test1(Main.java:25)
 * 	                    at con.test/Java12_16NewFeatures4.Main.main(Main.java:31)
 *
 * 这里会明确指出是哪一个变量调用出现了空指针 是不是感觉特别人性化
 */
public class Main {

    static void test1(String a, String b) {

        int length = a.length() + b.length();
        System.out.println(length);

    }

    public static void main(String[] args) {
        test1("yxs", null);
    }

}
