import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 首先明确，泛型的目的，是为了保证数据类型的一致
 * 验证?super E vs ?extend E
 * 对这两个最基础的理解(结论)：
 * <p>
 * < ? extends E >
 * add： 不允许加入 任何 元素！
 * get： 可以获取元素，但是必须使用 E 来接受元素
 * < ? super E >
 * add: 允许添加 E和E的子类 元素
 * get: 可以获取元素，但是类的信息丢失了，所以返回只能使用Object引用来接受
 * 如果需要自己的类型需要强制类型转换
 */
public class Main {

    /**
     * 为什么要使用?extend E 和 ?super E
     * 在以下两个方法{@link #consumeFruit(List)} {@link #produceFruit(List)}中
     * 如果不使用类型限定，那么无法处理List<Banana>|List<Apple>的场景
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Banana> bananas = new ArrayList<>();
        bananas.add(new Banana());
        consumeFruit(bananas);

        List<? super Fruit> fruits = new ArrayList<>();
        produceFruit(fruits);
    }

    /**
     * 这里可以很清楚地理解，为什么使用?extend Fruit做限定时不允许add元素
     * 传进来的参数实际上是List<Banana>，如果add的话，有可能添加进来一个Apple，导致类型混乱
     *
     * @param fruits
     */
    private static void consumeFruit(List<? extends Fruit> fruits) {
        fruits.forEach(new Consumer<Fruit>() {
            @Override
            public void accept(Fruit fruit) {
                System.out.println(fruit.hashCode());
            }
        });
    }

    /**
     * add方法可以添加元素，且必须为Fruit及其子类，不能添加其父类元素
     * 原因同上，添加父类元素时，无法准确判断是否时同一类型，可能导致类型混乱
     * get方法获取的元素，是Fruit及其父类，所以无法确定类型，只能强制类型转换
     *
     * @param fruits
     */
    private static void produceFruit(List<? super Fruit> fruits) {
        fruits.add(new Fruit());
        fruits.add(new Apple());
        fruits.add(new Banana());
    }
}
