package game

fun fight(warrior1: Warrior, warrior2: Warrior): Boolean{
    var(attacker, defender) = warrior1 to warrior2
    while (attacker.isAlive){
        attacker hits defender
        attacker = defender.also { defender = attacker }
    }
    return warrior1.isAlive
}

fun fight2(army1: Army, army2: Army): Boolean {
    var res = true
    for((attacker, defender) in army1.champions zip army2.champions){
        res =  fight(attacker, defender)
    }
    return res
}
/* while(army1.isAlive && army2.isAlive){
fight(army1.firstAlive, army2.firstAlive)} */