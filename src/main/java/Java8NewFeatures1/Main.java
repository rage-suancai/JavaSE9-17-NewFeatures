package Java8NewFeatures1;

import java.util.Arrays;

/**
 * Java8关键特性回顾
 * 在开始之前 我们先来回顾一下Java8中学习的Lambda表达式和Optional类 有关StreamAPI请各位小伙伴回顾一下JavaSE篇教程 这里不再进行介绍
 *
 * Lambda表达式
 * 在Java8之前 我们在某些情况下可能需要用到匿名内部类 比如:
 *                  // 现在我们想创建一个线程来搞事情
 *                  Thread thread = new Thread(new Runnable() { // 创建一个实现Runnable的匿名内部类
 *                      @Override
 *                      public void run() { // 具体的实现逻辑
 *                          System.out.println("Fuck World");
 *                      }
 *                  });
 *                  thread.start();
 *
 * 在创建Thread时 我们需要传入一个Runnable接口的实现类 来指定具体的在新的线程中要执行的任务 相关的逻辑需要我们在run()方法中实现
 * 这时为了方便 我们就直接使用匿名内部类的方式传入一个实现 但是这样的写法实在是太过臃肿了
 *
 * 在Java8之后 我可以对类似于这种匿名内部类的写法 进行缩减 实际上我们进行观察会发现 真正有用的那一部分代码 实际上就是我们对run()方法具体的实现
 * 而其他的部分实际上在任何地方编写都是一模一样的 那么我们能否针对于这种情况进行优化呢? 我们现在只需要一个简短的lambda表达式即可:
 *                  Thread thread = new Thread(() -> {
 *                      System.out.println("Fuck World"); // 只需要留下我们需要具体实现的方法体
 *                  });
 *                  thread.start();
 *
 * 我们可以发现 原本需要完整编写包括类 方法在内的所有内容 全部不再需要 而是直接使用类似于() -> {代码语句}的形式进行替换即可 是不是感觉瞬间代码清爽了N倍?
 *
 * 当然这只是一种写法而已 如果各位不好理解 可以将其视为之前匿名内部类写法的一种缩短
 *
 *      但是注意 它的底层其实并不是简简单单的语法糖替换 而是通过invokedynamic指令实现的 不难发现 匿名内部类会在编译时创建一个单独的class文件
 *      但是lambda却不会 间接说明编译之后lambda并不是以匿名内部类的形式存在的:
 *                  // 现在我们想新创一个线程来做事情
 *                  Thread thread = new Thread(() -> {
 *                      throw new UnsupportedOperationException(); // 这里我们抛个异常看看
 *                  });
 *                  thread.start();
 *
 *                  Exception in thread "Thread-0" java.lang.UnsupportedOperationException
 * 	                    at NewFeatures1.Main.lambda$test1$0(Main.java:55)
 * 	                    at java.base/java.lang.Thread.run(Thread.java:833)
 *
 *      可以看到 实际上是Main类中的lambda$main$0()方法抛出的异常 但是我们的Main类中压根没有这个方法 很明显是自动生成的 所以 与其说Lambda是匿名内部类的语法糖
 *      不如说是我们为所需要的接口提供了一个方法作为它的实现 比如Runnable接口需要一个方法体对它的run()方法进行实现 而这里我们就通过lambda的形式给了它一个方法体
 *      这样就万事俱备了 而之后创建实现类就只需要交给JVM去处理就好了
 *
 * 我们来看一下Lambda表达式的具体规范:
 *      > 标准格式为: ([参数类型 参数名称,]...) -> {代码语句, 包括返回值}
 *      > 和匿名内部类不同 Lambda仅仅支持接口 不支持抽象类
 *      > 接口内部必须有仅有一个抽象方法(可以有多个方法 但是必须保证其他方法有默认实现 必须留一个抽象方法出来)
 *
 * 比如我们之前使用的Runable类:
 *                  @FunctionalInterface // 添加了此注解的接口 都支持lambda表达式 符合函数式接口定义
 *                  public interface Runnable {
 *                      public abstract void run(); // 有且仅有一个抽象方法 此方法返回值为void 且没有参数
 *                  }
 *
 * 因此 Runable的匿名内部类实现 就可以简写为:
 *                  Runnable runnable = () -> {};
 *
 * 我们也可以写一个玩玩:
 *                  @FunctionalInterface
 *                  public interface Test { // 接口类型
 *
 *                      String test(Integer i); // 只有这一个抽象方法 且接收一个int类型参数 返回一个String类型结果
 *
 *                  }
 *
 * 它的Lambda表达式的实现就可以写为:
 *                  Test test = (Integer i) -> {return i + "";}; // 这里我们就简单将i转换为字符串形式
 *
 * 不过可以进行优化 实首先方法参数类型是可省略的:
 *                  Test test = (i) -> {return i + "";};
 *
 * 由于只有一个参数 可以不用添加小括号(多个参数时需要):
 *                  Test test = i -> {return i + "";};
 *
 * 由于仅有返回语句这一行 所以可以直接写最后终返回的结果 并且无需花括号:
 *                  Test test = i -> i + " ";
 *
 * 这样 相比我们之前直接去编写一个匿名内部类 是不是简洁了很多很多 当然 除了我们手动编写接口中抽象方法的方法之外 如果已经有实现好的方法 是可以直接拿过来用的 比如:
 *                  String test(Integer i); // 接口中的定义
 *
 *                  public static String impl(Integer i) { // 现在有一个静态方法 刚好匹配接口中抽象方法的返回值和参数列表
 *                      return "我是已经存在的实现" + i;
 *                  }
 *
 * 所以 我们可以直接将此方法 作为lambda表达式的方法体实现(其实这就是一种方法引用 引用了一个方法过来
 * 这也是为什么前面说是我们为所需要的接口提供了一个方法作为它的实现 是不是越来越体会到这句话的精髓了)
 *                  public static void main(String[] args) {
 *                      Test test = Main::impl; // 使用 类名::方法名称的形式来直接引用一个已有的方法作为实现
 *                  }
 *
 *                  public static String impl(Integer i) {
 *                      return "我是已经存在的实现" + i;
 *                  }
 *
 * 比如我们现在需要对一个数组进行排序:
 *                  Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3, 7, 8, 5}; // 来个数组
 *                  Arrays.sort(array, new Comparator<Integer>() { // Arrays.sort()可以由我们自己指定排序规则 只需要实现Comparator方法即可
 *                      @Override
 *                      public int compare(Integer o1, Integer o2) {
 *                          return o1 - o2;
 *                      }
 *                  });
 *                  System.out.println(Arrays.toString(array)); // 按从小到大的顺序排列
 *
 * 但是我们发现 Integer类中有一个叫做compare的静态方法:
 *                  public static int compare(int x, int y) {
 *                      return (x < y) ? -1 : ((x == y) ? 0 : 1);
 *                  }
 *
 * 这个方法是一个静态方法 但是它却和Comparator需要实现的方法返回值和参数定义一模一样 所以 懂得都懂:
 *                  Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3, 7, 8, 5};
 *                  Arrays.sort(array, Integer::compare); // 直接指定一手 效果和上面是一模一样
 *                  System.out.println(Arrays.toString(array));
 *
 * 那么要不是静态方法而是普通的成员方法呢? 我们注意到Comparator要求我们实现的方法为:
 *                  public int compare(Integer o1, Integer o2) {
 *                      return o1 - o2;
 *                  }
 *
 * 其中o1和o2都是Integer类型的 我们发现Integer类中有一个compareTo方法:
 *                  public int compareTo(Integer anotherInteger) {
 *                      return compare(this.value, anotherInteger.value);
 *                  }
 *
 * 只不过这个方法并不是静态的 而是对象所有:
 *                  Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3 ,7, 8, 5};
 *                  Arrays.sort(array, new Comparator<Integer>() {
 *                      @Override
 *                      public int compare(Integer o1, Integer o2) {
 *                          return o1.compareTo(o2); // 这样进行比较也行 和上面效果依然是一样的
 *                      }
 *                  });
 *                  System.out.println(Arrays.toString(array));
 *
 * 但是此时我们会发现 IDEA提示我们可以缩写 这是为什么呢? 实际上 当我们使用非静态方法时 会使用抽象方法参数列表的第一个作为目标对象
 * 后续参数作为目标对象成员方法的参数 也就是说 此时 o1作为目标对象 o2作为参数 正好匹配了compareTo方法 所以 直接缩写:
 *                  Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3, 7, 8, 5};
 *                  Arrays.sort(array, Integer::compareTo); // 注意这里调用的不是静态方法
 *                  System.out.println(Arrays.toString(array));
 *
 * 成员方法也可以让对象本身不成为参与的那一方 仅仅引用方法:
 *                   int reserve(Integer a, Integer b) { // 现在Main类中有一个刚好匹配的方法
 *                       return b.compareTo(a);
 *                   }
 *
 *                   public static void main(String[] args) {
 *
 *                       Main mainObject = new Main();
 *                       Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3, 7, 8, 5};
 *                       Arrays.sort(array, mainObject::reserve); // 使用Main类的成员方法 但是mainObject对象并未参与进来 只是借用了一下刚好匹配的方法
 *                       System.out.println(Arrays.toString(array));
 *
 *                   }
 *
 * 当然 类的构造方法同样可以作为方法引用传递:
 *                  @FunctionalInterface
 *                  public interface Test {
 *
 *                      String test(String i); // 现在我们需要一个参数为String返回值为String的实现
 *
 *                  }
 *
 * 我们发现 String类中刚好有一个:
 *                  @IntrinsicCandidate
 *                  public String(String original) { // 由于String类的构造方法返回的肯定是一个String类对象 且此构造方法需要一个String类的对象 所以 正好匹配了接口中的
 *                      this.value = original.value;
 *                      this.coder = original.coder;
 *                      this.hash = original.hash;
 *                  }
 *
 * 于是乎:
 *                  public static void main(String[] args) {
 *                      Test test = String::new; // 没错 构造方法直接使用new关键字就行
 *                  }
 *
 * 当然除了上面提到的这些情况可以使用方法引用之外 还有很多地方都可以 还请各位小伙伴自行探索了
 * Java8也为我们提供了一些内置的函数式接口供我们使用: Consumer Function Supplier等 具体请回顾一下JavaSE篇
 */
public class Main {

    static void test1() {

        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Fuck World");
            }
        });
        thread.start();*/

        /*Thread thread = new Thread(() -> {
            System.out.println("Fuck World");
        });
        thread.start();*/

        Thread thread = new Thread(() -> {
            throw new UnsupportedOperationException();
        });
        thread.start();

    }

    static void test2() {

        /*Test test = (Integer i) -> {
            return i + "";
        };*/

        /*Test test = (i) -> {
            return i + "";
        };*/

        /*Test test = i -> {
           return i + "";
        };*/

        Test test = i -> i + "";

    }

    static void test3() {

        /*Test test = new Test() {
            @Override
            public String test(Integer i) {
                return impl(i);
            }
        };*/

        //Test test = i -> impl(i);
        //Test test = Main::impl;

    }
    static String impl(Integer i) {
        return "我是已经存在的实现" + i;
    }

    static void test4() {

        /*Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3, 7, 8, 5};
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(Arrays.toString(array));*/

        /*Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3, 7, 8, 5};
        Arrays.sort(array, Integer::compare);
        System.out.println(Arrays.toString(array));*/

        /*Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3 ,7, 8, 5};
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(array));*/

        Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3, 7, 8, 5};
        Arrays.sort(array, Integer::compareTo);
        System.out.println(Arrays.toString(array));

    }

    public static void main(String[] args) {

        //test1();
        //test2();
        //test3();
        //test4();

        /*Main mainObject = new Main();
        Integer[] array = new Integer[] {4, 6, 1, 9, 2, 0, 3, 7, 8, 5};
        Arrays.sort(array, mainObject::reserve);
        System.out.println(Arrays.toString(array));*/

        Test test = String::new;

    }

    int reserve(Integer a, Integer b) {
        return b.compareTo(a);
    }

}
