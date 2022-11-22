package Java12_16NewFeatures1;

/**
 * Java12-16新特性
 * 由于Java版本的更新迭代速度自Java9开始为半年更新一次(Java8到Java9隔了整整三年) 所以各个版本之间的更新内容比较少 剩余的6个版本 我们就多个版本放在一起进行讲解了
 *
 * Java12-16这五个版本并非长期支持版本 所以很多特性都是一种处于实验性功能 12/13版本引入了一些实验性功能 并根据反馈进行调整 最后在后续版本中正式开放使用 其实就是体验服的那种感觉
 *
 * 新的switch语法
 * 在Java12引入全新的switch语法 让我们使用switch语句更加的灵活 比如我们想要编写一个根据成绩得到等级的方法:
 *                  // 传入分数(范围0 - 100)返回对应的等级:
 *                          100-90 优秀
 *                          70-80 良好
 *                          60-70 及格
 *                          0-60 寄
 *                  public static String grade(int score) {
 *
 *                  }
 *
 * 现在我们想要使用switch来实现这个功能(不会吧不会吧 不会有人想半天怎么用switch实现吧) 之前的写法是:
 *                  score /= 10; // 既然分数段都是整数 那就直接整除10
 *                  String res = null;
 *                  switch (score) {
 *                      case 10: case 9:
 *                          res = "优秀"; // 不同的分数段就可以返回不同的等级了
 *                          break; // 别忘了break 不然会贯穿到后面
 *                      case 8: case 7:
 *                          res = "良好";
 *                          break;
 *                      case 6:
 *                          res = "及格";
 *                          break;
 *                      default:
 *                          res = "寄了";
 *                          break;
 *                  }
 *                  return res;
 *
 * 但是现在我们可以使用新的特性了:
 *                  score /= 10; // 既然分数段都是整数 那就直接整除10
 *                  return switch (score) { // 增强版switch语法
 *                      case 10, 9 -> "优秀"; // 语法那是相当的简洁 而且也不需要我们自己考虑break或是return来结束了switch(有时候就容易忘记 这样的话就算忘记也没事了)
 *                      case 8, 7 -> "良好";
 *                      case 6 -> "及格";
 *                      default -> "寄了";
 *                  };
 *
 * 不过最后编译出来的样子 貌似还是和之前是一样的
 *
 * 这种全新的switch语法称为switch表达式 它的意义不仅仅体现在语法的精简上 我们来看看它的详细规则:
 *                  var res = switch (obj) { // 这里和之前的switch语句是一样的 但是注意这样的switch是有返回值的 所以可以被变量接收
 *                      case [匹配值, ...] -> "优秀"; // case后直接添加匹配值 匹配值可以存在多个 需要使用逗号隔开 使用 -> 来返回如果匹配此case语句的结果
 *                      case ... // 根据不同的分支 可以存在多个case
 *                      default -> "不及格"; // 注意: 表达式要求必须涵盖所有的可能 所以是需要添加default的
 *                  }
 *
 * 那么如果我们并不是能够马上返回 而是需要做点什么其他的工作才能返回结果呢?
 *                  var res = switch (obj) {
 *                      case [匹配值, ...] -> "优秀";
 *                      default -> { // 我们可以使用花括号来将整套逻辑括起来
 *                          // ... 我是其他要做的事情
 *                          yield "不及格"; // 注意: 处理完成后需要返回最后终结果 但是这样并不是使用return 而是yield关键字
 *                      }
 *                  }
 *
 * 当然 也可以像这样:
 *                  var res = switch () { // 增强版switch语法
 *                      case [匹配值, ...];
 *                          yield "AAA"; // 传统的: 写法 通过yield指定返回结果 同样不需要break
 *                      default:
 *                              System.out.println("默认情况");
 *                          yield "BBB";
 *                  }
 *
 * 这种全新的语法 可以说极大地方便了我们的编码 不仅代码简短 而且语义明确 唯一遗憾的是依然不支持区间匹配
 *
 * 注意: switch表达式在Java14才正式开放使用 所以我们项目的代码级别需要调整到14以上
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(grade(59));
    }

    public static String grade(int score) {

        /*score /= 10;
        String res = null;
        switch (score) {
            case 10: case 9:
                res = "优秀";
                break;
            case 8: case 7:
                res = "良好";
                break;
            case 6:
                res = "及格";
                break;
            default:
                res = "寄了";
                break;
        }
        return res;*/

        score /= 10;
        return switch (score) {
            case 10, 9 -> "优秀";
            case 8, 7 -> "良好";
            case 6 -> "及格";
            default -> "寄了";
        };

    }

}
