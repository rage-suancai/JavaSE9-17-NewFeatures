package Java9NewFeatures3;

/**
 * 接口中的private方法
 * 在Java8中 接口中的方法支持添加default关键字来添加默认实现:
 *                  public interfa
 *
 *                      default void test() {
 *                          System.out.println("我是test方法默认实现");
 *                      }
 *
 *                  }
 *
 * 而在Java9中 接口再次得到强化 现在接口中可以存在私有方法了:
 *                  public interface Test {
 *
 *                      default void test() {
 *                          System.out.println("我是test方法默认实现");
 *                          this.inner(); // 接口中方法的默认实现可以直接调用接口中的私有方法
 *                      }
 *
 *                      private void inner() { // 声明一个私有方法
 *                          System.out.println("我是接口中的私有方法");
 *                      }
 *
 *                  }
 *
 * 注意: 私有方法必须要提供方法体 因为权限为私有的 也只有这里能进行方法的具体实现了 并且此方法只能被接口中的其他私有方法或是默认实现调用
 */
public class Main {

    public static void main(String[] args) {

        Test.xx();

    }

}
