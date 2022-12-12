package game
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

import kotlin.test.assertEquals

import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class FightTest{


    @Test
    fun `Vampire restore his health while fighting with Rookie not higher than his health limit`() {

        class Rookie: Warrior(){
            override val attack: Int
                get() = 1
        }

        val vampire = Vampire()

        fight(vampire, Vampire())
        assertEquals(6, vampire.health)

        repeat(10) {fight(vampire, Rookie())}
        assertEquals(Param.Vampire.Health, vampire.health)

    }

    @Test
    fun `When Defender fights against Rookie his health won't increase`() {

        class Rookie: Warrior(){
            override val attack: Int
                get() = 1
        }

        val rookie = Rookie()
        val defender = Defender()

        fight(rookie, defender)
        assertEquals(Param.Defender.Health, defender.health)

    }


    @Test
    fun `When Defender fights against Vampire, Defender wins with health 22`() {
        val vampire = Vampire()
        val defender = Defender()

        val res = fight(defender, vampire)

        assertAll(
            { assertTrue(res) },
            {assertEquals(-1, vampire.health)},
            {assertEquals(22, defender.health)}
        )
    }

    @Test
    fun `When Warrior fights against Defender then Defender wins with health 9`() {
        val warrior = Warrior()
        val defender = Defender()

        val res = fight(warrior, defender)
        assertFalse(res)
        assertEquals(9, defender.health)

    }

    @Test
    fun `Vampire vs Vampire`() {
        val vampire = Vampire()

        fight(vampire, Vampire())
        //println(vampire.health)

        assertEquals(4, vampire.health)
    }

    /* BATTLE TEST BETWEEN ARMIES */

    @Test
    internal fun `ARMY with 1 warrior CAN'T win against ARMY with 2 warriors`() {
        // given
        val army1 = Army()
        val army2 = Army()
        // when
        army1.addUnits(1,::Warrior)
        army2.addUnits(2,::Warrior)
        // then
        val res = fight2(army1,army2)
        Assertions.assertEquals(false, res)
    }


    /* BATTLE TEST BETWEEN WARRIOR AND KNIGHT */


    @Test
    fun `Warrior loses when fights against Knight`() {
        // given
        val carl = Warrior()
        val jim = Knight()
        // when
        val res = fight(carl, jim)
        // then
        Assertions.assertEquals(false, res)
    }

    @Test
    fun `Knight wins when fights against Warrior`() {
        // given
        val carl = Warrior()
        val jim = Knight()
        // when
        val res = fight(jim, carl)
        // then
        Assertions.assertEquals(true, res)
    }

    @Test
    fun `First Warrior is alive after fights against Warrior`() {
        // given
        val carl = Warrior()
        val jim = Knight()
        // when
        fight(carl, jim)
        // then
        Assertions.assertEquals(false, carl.isAlive)
    }

    @Test
    fun `Knight is alive after fights against Warrior`() {
        // given
        val carl = Knight()
        val jim = Warrior()
        // when
        fight(jim, carl)
        // then
        Assertions.assertEquals(true, carl.isAlive)
    }

    @Test
    fun `Second Warrior is not alive after fights against Warrior`() {
        // given
        val carl = Warrior()
        val jim = Warrior()
        // when
        fight(carl, jim)
        // then
        Assertions.assertEquals(false, jim.isAlive)
    }

    @Test
    fun `Knight is alive after fights against Warrior even that he attacks second`() {
        // given
        val carl = Warrior()
        val jim = Knight()
        // when
        fight(carl, jim)
        // then
        Assertions.assertEquals(true, jim.isAlive)
    }

    @Test
    fun `Knight is not alive after second battle against Warrior`() {
        // given
        val carl = Warrior()
        val jim = Knight()
        val thirdFighter = Warrior()
        // when
        fight(carl, jim)
        fight(jim, thirdFighter)
        // then
        Assertions.assertEquals(false, jim.isAlive)
    }



}