package Java12_16NewFeatures3;

/**
 * 新的instanceof语法
 * 在Java14 instanceof迎来了一波小更新(哈哈 这版本instanceof又加强了 版本强势语法)
 *
 * 比如我们之前要重写一个类的equals方法:
 *                  public class Student {
 *
 *                      private final String name;
 *
 *                      public Student(String name) {
 *                          this.name = name;
 *                      }
 *
 *                      @Override
 *                      public boolean equals(Object o) {
 *
 *                          if (o instanceof Student) { // 首先判断是否为Student类型
 *                              Student student = (Student) o; // 如果是 那么就类型转换
 *                              return student.name.equals(this.name); // 最后对比属性是否一样
 *                          }
 *                          return false;
 *
 *                      }
 *
 *                  }
 *
 * 在之前我们一直采用这种先判断类型 然后类型转换 最后才能使用的方式 但是这个版本instanceof加强之后 我们就不需要了 我们可以直接将student替换为模式变量:
 *                  public class Student {
 *
 *                      private final String name;
 *
 *                      public Student(String name) {
 *                          this,name = name;
 *                      }
 *
 *                      @Override
 *                      public boolean equals(Object obj) {
 *                          if (o instanceof Student student) { // 在比较完成的屁股后面 直接写变量名字 而这个变量就是类型转换之后的
 *                              return student.name.equals(this.name); // 下面直接用 是不是贼方便
 *                          }
 *                          return false;
 *                      }
 *
 *                  }
 *
 * 在使用instanceof判断类型成立后 会自动强制转换类为指定类型 简化了我们手动转换的步骤
 *
 * 注意: 新的instanceof语法在Java16才正式开始使用 所以我们项目的代码级别需要调整到16以上
 */
public class Main {

    public static void main(String[] args) {

    }

}
