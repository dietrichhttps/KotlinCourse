class WareHouse {
    fun getPack(deliveryObject: DeliveryObject): Pack {
        val length = deliveryObject.length + 1
        val width = deliveryObject.width + 1
        val height = deliveryObject.height + 1
        val weight = 0.3
        return Pack(length, width, height, weight = weight)
    }

    fun packCargo(deliveryObject: DeliveryObject): Cargo {
        val pack = getPack(deliveryObject)
        return Cargo(
            pack.length,
            pack.width,
            pack.height,
            pack.type,
            deliveryObject.weight,
            deliveryObject.weight + pack.weight
        )
    }
}