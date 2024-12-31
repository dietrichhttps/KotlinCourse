package collections

interface MyMutableMap<K, V> : MyMap<K, V> {

    override val size: Int

    fun put(key: K, value: V): V?

    override operator fun get(key: K): V?

    override fun containsKey(key: K): Boolean

    override fun containsValue(value: V): Boolean

    fun clear()

    fun remove(key: K): V?

    override val keys: MySet<K>

    override val values: MyCollection<V>
}