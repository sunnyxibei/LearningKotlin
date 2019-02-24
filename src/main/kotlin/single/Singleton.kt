package single

/**
 * 带参数的单例实现
 * 基本和Java相同
 * 使用了Elvis操作符
 */
class Singleton private constructor(private val property: Int) {

    companion object {
        @Volatile
        private var instance: Singleton? = null

        fun getInstance(property: Int) =
            instance ?: synchronized(this) {
                instance ?: Singleton(property).also { instance = it }
            }
    }
}