package Java9NewFeatures4;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 集合类新增工厂方法
 * 在之前 如果我们想要快速创建一个Map只能:
 *                  HashMap<String, Integer> map = new HashMap<>(); // 要快速使用Map 需要先创建一个Map对象 然后再添加数据
 *                  map.put("AAA", 19);
 *                  map.put("BBB", 23);
 *                  System.out.println(map);
 *
 * 而在Java9之后 我们可以直接通过of方法来快速创建了:
 *                  Map<String, Integer> map = Map.of("AAA", 18, "BBB", 20); // 直接一句搞定
 *                  System.out.println(map);
 *
 * 是不是感觉非常方便 of方法还被重载了很多次 分别适用于快速创建包含0~10对键值对的Map
 *
 * 但是注意 通过这种方式创建的Map和通过Arrays创建的List比较类似 也是无法进行修改的
 *
 * 当然 除了Map之外 其他的集合类都要相应的of方法:
 *                  Set<Object> set = Set.of("BBB", "CCC", "AAA"); // 注意Set中元素顺序并不一定是你的添加顺序
 *                  List<Object> list = List.of("AAA", "CCC", "BBB"); // 好耶 再也不用Arrays了
 */
public class Main {

    static void test1() {

        /*HashMap<String, Integer> map = new HashMap<>();
        map.put("AAA", 19);
        map.put("BBB", 23);
        System.out.println(map);*/

        /*Map<String, Integer> map = Map.of("AAA", 18, "BBB", 20);
        System.out.println(map);*/

        Set<Object> set = Set.of("BBB", "CCC", "AAA");
        System.out.println(set);
        List<Object> list = List.of("AAA", "CCC", "BBB");
        System.out.println(list);

    }

    public static void main(String[] args) {
        test1();
    }

}
