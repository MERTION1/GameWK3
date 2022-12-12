package game

fun main() {
    val myArmy = Army()
    myArmy.addUnits(3, ::Knight)

    val enemyArmy = Army()
    enemyArmy.addUnits(3, ::Warrior)

    val army3 = Army()
    army3.addUnits(20, ::Warrior)
    army3.addUnits(5, ::Knight)

    val army4 = Army()
    army4.addUnits(30, ::Warrior)

    check(fight2(myArmy, enemyArmy) == true)
    check(fight2(army3, army4) == false)
    println("OK")
}