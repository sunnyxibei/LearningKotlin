package single

import kotlin.properties.Delegates

/**
 * 延迟加载&线程安全的单例模式实现
 */
class ApiHelper private constructor() {

    companion object {
        //延迟属性（lazy properties）
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { ApiHelper() }
    }

    init {
        val mUriMap = HashMap<String, String>()
        mUriMap["dev"] = "dev.mobvoi.com"
        mUriMap["release"] = "release.mobvoi.com"

        //Map映射
        val release by mUriMap
        println("release = $release")
    }

    private val defaultPath = "/tickids/old"
    //Delegates.observable()，数据变化之后回调
    var path by Delegates.observable(initialValue = defaultPath) { property, oldValue, newValue ->
        println("$oldValue -> $newValue")
    }

    private val defaultDebug = true
    //Delegates.vetoable()，数据变化之前回调，并根据onChange的结果（Boolean）确定是否执行setValue
    var debug by Delegates.vetoable(initialValue = defaultDebug) { property, oldValue, newValue ->
        println("$oldValue -> $newValue")
        return@vetoable true
    }
}