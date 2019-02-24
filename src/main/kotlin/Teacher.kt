import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Teacher {
    fun printName() {
        println(name)
    }

    var name: String by DelegateProperty()
}

class DelegateProperty : ReadWriteProperty<Teacher, String> {
    var delegateName = "Miss He"
    override fun getValue(thisRef: Teacher, property: KProperty<*>): String {
        return delegateName
    }

    override fun setValue(thisRef: Teacher, property: KProperty<*>, value: String) {
        delegateName = value
    }
}
