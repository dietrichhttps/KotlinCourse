import monster.Monster
import price.AngleGrinder
import price.ChainSaw
import price.Drill
import price.Price

fun main() {
    val tools = listOf(
        Drill(
            "Makita",
            "f100",
            100,
            30.0,
            2,
            Price(10, 100, 20),
            10,
            2,
            2,
            2
        ),
        AngleGrinder(
            "Makita",
            "t500",
            100,
            30.0,
            2,
            Price(100, 200, 100),
            20,
            20,
            isSmoothStart = true,
            isDustProtection = false
        ),
        ChainSaw(
            "Makita",
            "m900",
            100,
            30.0,
            2,
            Price(1000, 200, 200),
            20,
            10,
            10,
            10.0
        )
    )
    for (tool in tools) {
        tool.turnOn()
    }
}