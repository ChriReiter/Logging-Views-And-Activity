package at.fh.mappdev.loggingviewsandactivity

import org.junit.Assert.*
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class CalculatorTest {

    @Test
    fun testMultiply2By2() {

        val calculator = Calculator()
        val result = calculator.parse("2 * 2")
        assertThat<Int>(result).isEqualTo(4)
    }

    @Test
    fun testDivide2By2() {
        val calculator = Calculator()
        val result = calculator.parse("2 / 2")
        assertThat<Int>(result).isEqualTo(1)
    }

    @Test
    fun testAdd3To2() {
        val calculator = Calculator()
        val result = calculator.parse("3 + 2")
        assertThat<Int>(result).isEqualTo(5)
    }

    @Test
    fun testSubtract4With2() {
        val calculator = Calculator()
        val result = calculator.parse("4 - 2")
        assertThat<Int>(result).isEqualTo(2)
    }
}