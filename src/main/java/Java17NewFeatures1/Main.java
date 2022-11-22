package Java17NewFeatures1;

/**
 * Java17新特性
 * Java17作为新的LTS长期维护版本 我们来看看都更新了什么(不包含浏览特性 包括switch第二次增强 哈哈 果然还是强度不够 都连续加强两个版本了)
 *
 * 密封类型
 * 密封类型可以说是Java17正式推出的又一重磅类型 它在Java15首次提出并测试了两个版本
 *
 * 在Java中 我们可以通过继承(extends关键字)来实现类的能力复用 扩展与增强 但有时候可能并不是所有的类我们都希望能够被继承
 * 所以 我们需要对继承关系有一些限制的控制手段 密封类的作用就是限制类的继承
 *
 * 实际上在之前我们如果不希望别人继承我们的类 可以直接添加final关键字:
 *                  public final class A { // 添加final关键字后 不允许对此类继承
 *
 *                  }
 *
 * 这样有一个缺点 如果添加了final关键字 那么无论是谁 包括我们自己也是没办法实现继承的
 * 但是现在我们有一个需求 只允许我们自己写的类继承A 这时该咋写? 在Java17之前想要实现就很麻烦
 *
 * 但是现在我们可以使用密封类型来实现这个功能:
 *                  // 在class关键字前添加sealed关键字 表示此类型为密封类型 permits后面跟上允许继承的类型 多个子类使用逗号隔开
 *                  public sealed class A permits B{
 *
 *                  }
 *
 * 密封类有以下要求:
 *      > 可以基于普通类 抽象类 接口 也可以是继承自其他接口抽象类的子类或是实现其他接口的类等
 *      > 必须有子类继承 且不能是匿名内部类或是lambda的形式
 *      > sealed写在原来final的位置 但是不能和final non-sealed关键字同时出现 只能选择其一
 *      > 继承的子类必须显式标记为final sealed或是non-sealed类型
 *
 * 标准的声明格式如下:
 *                  public sealed [abstract] [class/interface] 类名 [extends 父类] [implements 接口, ...] permits [子类， ...] {
 *                      // 里面的该咋写咋写
 *                  }
 *
 * 注意子类格式为:
 *                  public [final/sealed/non-sealed] class 子类 extends 父类 { // 必须继承自父类
 *                      // final类型: 任何类不能再继承当前类 到此为止 已经封死了
 *                      // sealed类型: 同父类 需要指定由哪些类继承
 *                      // non-sealed类型: 重新开放为普通类 任何类都可以继承
 *                  }
 *
 * 比如现在我们写了这些类:
 *                  public sealed class A permits B{ // 指定B继承A
 *
 *                  }
 *
 *                  public final class B extends A { // 在子类final 彻底封死
 *
 *                  }
 *
 * 我们可以看懂其他的类无论是继承A还是继承B都无法通过编译:
 *                  public class C extends B {
 *
 *                  }
 *
 *                  public class C extends A {
 *
 *                  }
 *
 * 但是如果此时我们主动将B设定为non-sealed类型:
 *                  public non-sealed class B extends A {
 *
 *                  }
 *
 * 这样就可以正常继承了 因为B指定了non-sealed主动放弃了密封特性 这样就显得非常灵活了
 *
 * 当然我们也可以通过反射来过去类是否为密封类型:
 *                  Class<A> a = A.class;
 *                  System.out.println(a.isSealed()); // 是否为密封
 *
 * 至此 Java9-17的主要新特性就讲解完毕了
 */
public class Main {

    static void test1() {

        Class<A> a = A.class;
        System.out.println(a.isSealed());

    }

    public static void main(String[] args) {
        test1();
    }

}
