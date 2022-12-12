package game
import game.Param

open class Warrior(health: Int = Param.Warrior.Health){

    private val initialHealth = health
    var health: Int = health
        private set(value){
            field = value.coerceAtMost(initialHealth)
        }


    open val attack: Int
        get() = Param.Warrior.Attack

    val isAlive: Boolean
        get() = health > 0

    open infix fun hits(opponent: Warrior){
        opponent.receiveDamage(attack)
    }
                                                      // base implementation for receiving damage
    protected open fun receiveDamage(damage: Int) {       //so it can be overridden and be
        health -= damage                               //visible for classes that extend Warrior
    }

    protected fun healBy(healthPoints: Int){
        health +=healthPoints
    }

}

class Knight: Warrior(Param.Knight.Health){
    override val attack: Int
        get() = Param.Knight.Attack
}

class Defender: Warrior(health = Param.Defender.Health){
    override val attack: Int
        get() = Param.Defender.Attack

    private val defence: Int
        get() = Param.Defender.Defense

    override fun receiveDamage(damage: Int) {
        super.receiveDamage(damage - defence)
    }
}

class Vampire: Warrior(health = Param.Vampire.Health){
    override val attack: Int
        get() = Param.Vampire.Attack

    private val vampirism: Int
        get() = Param.Vampire.Vampirism

    override fun hits(opponent: Warrior) {
        val healthBefore = opponent.health
        super.hits(opponent)
        val damageDealt = healthBefore - opponent.health
        val healPoints = damageDealt * vampirism/100
        healBy(healPoints)


    }
}




















































