package game
import java.lang.management.ManagementFactory
import java.util.concurrent.TimeUnit

class Army{

    private val troops = mutableListOf<Warrior>()


    val champions
    get() = sequence<Warrior> {
        for (champion in troops)
            while (champion.isAlive)
                yield(champion)
    }

    fun addUnits(quantity: Int, factory: () -> Warrior){
         repeat(quantity){
             addUnit(factory())
         }
    }

    private fun addUnit(unit: Warrior){
        troops.add(unit)
    }


}