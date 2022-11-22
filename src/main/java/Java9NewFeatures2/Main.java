package Java9NewFeatures2;

/**
 * JShell交互式编程
 * Java9为我们通过了一种交互式编程工具JShell 你还别说 真有Python那味
 *                  C:\Users\AKAtravis-yxs>jshell
 *                  |  欢迎使用 JShell -- 版本 14.0.2
 *                  |  要大致了解该版本, 请键入: /help intro
 *
 *                  jshell>
 *
 * 环境配置完成后 我们只需要输入jshell命令即可开启交互式编程了 它支持我们一条一条命令进行操作
 *                  jshell> int a = 10
 *                  a ==> 10
 *
 *                  jshell> int b = 10
 *                  b ==> 10
 *
 *                  jshell> int c = a + b
 *                  c ==> 20
 *
 *                  jshell>
 *
 * 我们一次输入一行(可以不加分号) 先定义一个a=10和b=10 然后定义c并得到a+b的结果 可以看到还是非常方便的 但是注意语法还是和Java是一样的
 *                  jshell> public int min(int a, int b) {
 *                     ...>     return a < b ? a : b;
 *                     ...> }
 *                  |  已创建 方法 min(int,int)
 *
 *                  jshell> int c = min(10, 20)
 *                  c ==> 10
 *
 * 我们也可以快速创建一个方法供后续的调用 当我们按下Tab键还开头进行自动补全:
 *                  Class                    ClassCastException       ClassCircularityError
 *                  ClassFormatError         ClassLoader              ClassNotFoundException
 *                  ClassValue
 *
 *                  签名:
 *                  java.lang.Class<T>
 *
 *                  <再次按 Tab 可查看文档>
 *                  jshell> Class
 *
 * 除了直接运行我们写进去的代码之外 它还支持使用命令 输入help来查看命令列表:
 *                  jshell> /help
 *                  |  键入 Java 语言表达式, 语句或声明。
 *                  |  或者键入以下命令之一:
 *                  |  /list [<名称或 id>|-all|-start]
 *                  |       列出您键入的源
 *                  |  /edit <名称或 id>
 *                  |       编辑源条目
 *                  |  /drop <名称或 id>
 *                  |       删除源条目
 *                  |  /save [-all|-history|-start] <文件>
 *                  |       将片段源保存到文件
 *
 * 比如我们可以使用/vars命令来展示当前定义的变量列表:
 *                  jshell> /vars
 *                  |    int a = 10
 *                  |    int b = 10
 *                  |    int c = 20
 *
 * 当我们不想使用jshell时 直接输入/exit退出即可:
 *                  jshell> /exit
 *                  |  再见
 */
public class Main {

    public static void main(String[] args) {

    }

}
