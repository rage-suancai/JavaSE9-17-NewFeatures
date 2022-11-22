package Java10NewFeatures1;

/**
 * Java10新特性
 * Java10主要带来的是一些内部更新 相比Java9带来的直观改变不是很多 其中比较突出的就是局部变量类型推断了
 *
 * 局部变量类型推断
 * 在Java中 我们可以使用自动类型推断:
 *                  //String a = "Fuck World"; // 之前我们定义变量必须指定类型
 *                  var a = "Fuck World"; // 现在我们使用var关键字来自动进行类型判断 因为完全可以从后面的值来判断是什么类型
 *
 * 但是注意 var关键字必须位于有初始值设定的变量上 否则鬼知道你要用什么类型
 *
 * 我们来看看是不是类也能正常获取:
 *                  var a = "Fuck World";
 *                  System.out.println(a.getClass());
 *
 * 这里虽然是有了var关键字进行自动类推断 但是最终还是会变成String类型 得到的Class也是String类型
 * 但是Java终究不像JS那样进行动态推断 这种类型推断仅仅发生在编译期间 到最后编译完成后还是会变成具体类型的:
 *                  public class Main {
 *                      public Main() {
 *                      }
 *
 *                      static void test1() {
 *                          String a = "Fuck World";
 *                          System.out.println(a.getClass());
 *                      }
 *
 *                      public static void main(String[] args) {
 *                          test1();
 *                      }
 *                  }
 *
 * 并且var关键字仅适用于局部变量 我们是没办法在其他地方使用的 比如类的成员变量
 *
 * 有关Java10新增的一些其他改进 这里就不提了
 */
public class Main {

    static void test1() {

        var a = "Fuck World";
        System.out.println(a.getClass());

    }

    public static void main(String[] args) {
        test1();
    }

}
