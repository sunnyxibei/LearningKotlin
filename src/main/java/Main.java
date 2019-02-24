import java.util.ArrayList;
import java.util.List;

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
 * <p>
 * 更好的总结：
 * super和extends的意义更多是在于模板方法的定义中，模板方法希望放大参数类型从而可以接受更多中的泛型参数。
 * extends是只读的，读出的类型具体。
 * super是读写的，但是写入时只能是更具体的类型，读取时类型只能泛化为Object
 * <p>
 * PECS原则（https://blog.csdn.net/fangfengzhen115/article/details/78973258）：
 * 如果要从集合中读取类型T的数据，并且不能写入，可以使用 ? extends 通配符；(Producer Extends)
 * 如果要从集合中写入类型T的数据，并且不需要读取，可以使用 ? super 通配符；(Consumer Super)
 * 如果既要存又要取，那么就不要使用任何通配符。
 */
public class Main {

    /**
     * 为什么要使用?extend E 和 ?super E
     * 在以下两个方法{@link #produceFruit(List)} {@link #consumeFruit(List)}中
     * 如果不使用类型限定，那么无法处理List<Banana>|List<Apple>的场景
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        produceFruit(apples);

        List<Banana> bananas = new ArrayList<>();
        bananas.add(new Banana());
        produceFruit(bananas);

        List<Fruit> fruits = new ArrayList<>();
        produceFruit(fruits);

        consumeFruit(fruits);
        //consumeFruit(apples); error下限是Fruit
        //consumeFruit(bananas); error下限是Fruit
    }

    /**
     * 这里可以很清楚地理解，为什么使用?extend Fruit做限定时不允许add元素
     * 传进来的参数实际上是List<Banana>，如果add的话，有可能添加进来一个Apple，导致类型混乱
     *
     * @param fruits
     */
    private static void produceFruit(List<? extends Fruit> fruits) {
        //fruits.add(new Fruit()); error to add emelent
        //fruits.add(new Apple());
        //fruits.add(new Banana());
        Fruit fruit = fruits.get(0);
    }

    /**
     * add方法可以添加元素，且必须为Fruit及其子类，不能添加其父类元素
     * 子类Apple和Banana一定是Fruit，所以添加时没有问题
     * 添加父类元素时，原因同上，无法准确判断是否是同一类型，可能导致类型混乱
     * get方法获取的元素，是Fruit及其父类，所以无法确定类型，只能强制类型转换
     *
     * @param fruits
     */
    private static void consumeFruit(List<? super Fruit> fruits) {
        fruits.add(new Fruit());
        fruits.add(new Apple());
        fruits.add(new Banana());
    }
}
