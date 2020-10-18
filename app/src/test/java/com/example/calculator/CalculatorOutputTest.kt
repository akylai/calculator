package com.example.calculator

import com.example.calculator.calculator.CalculatorOutputInterfaceVıew
import com.example.calculator.calculator.CalculatorOutputPresenter
import org.junit.Test
import org.mockito.*
import org.mockito.BDDMockito.then

class CalculatorOutputTest {
    private val onPresenter = CalculatorOutputPresenter
    private val onMockView = Mockito.mock(CalculatorOutputInterfaceVıew::class.java)

    @Test
    fun `1 plus 1 is 2`(){
        onPresenter.attach(onMockView)

        onPresenter.add("1")

        then(onMockView).should().setEquation("1")

        onPresenter.add("+")

        then(onMockView).should().setEquation("1+")

        onPresenter.add("1")

        then(onMockView).should().setEquation("1+1")

        then(onMockView).should().setOutcome("2")

        onPresenter.clear()

    }

    @Test
    fun `2 plus 2 minus 1 is 3`(){
        onPresenter.attach(onMockView)

        onPresenter.add("2")

        then(onMockView).should().setEquation("2")

        onPresenter.add("+")

        then(onMockView).should().setEquation("2+")

        onPresenter.add("2")

        then(onMockView).should().setEquation("2+2")

        onPresenter.add("-")

        then(onMockView).should().setEquation("2+2-")

        onPresenter.add("1")

        then(onMockView).should().setEquation("2+2-1")

        then(onMockView).should().setOutcome("3")

        onPresenter.clear()


    }


}