package com.example.calculator.calculator

import android.util.Log
import bsh.Interpreter
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

object CalculatorOutputPresenter {
    private var onView: CalculatorOutputInterfaceView? = null

    private var onCurrentEquation: String = ""

    private var onCurrentOutcome: String = ""

    private val onInterpreter = Interpreter()

    fun attach(view: CalculatorOutputInterfaceView){
        onView = view
    }

    fun detach(){
        onView = null
    }

    fun add(item:String){
        onCurrentEquation = onCurrentEquation.plus(item)
        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun remove(){
        onCurrentEquation= if(onCurrentEquation.length > 1){
            onCurrentEquation.substring(0, onCurrentEquation.length - 1)
        }else{
            ""
        }
        updateEquation()
        calculateOutcome()
        updateOutcome()
    }

    fun solve(){
        if(onCurrentOutcome.isNotEmpty()){
            onCurrentEquation = onCurrentOutcome
            onCurrentOutcome = ""
        }
        updateEquation()
        updateOutcome()

    }

    fun clear(){
        onCurrentEquation = ""
        onCurrentOutcome = ""
        updateEquation()
        updateOutcome()
    }

    private fun calculateOutcome(){
        if(onCurrentEquation.isNotEmpty()){
//            try{
//                onInterpreter.eval("result = $onCurrentEquation")
//                val result = onInterpreter.get("result")
//
//                if (result != null && result is Int){
//                    onCurrentOutcome = result.toString()
//                }
//            }catch (e:Exception){
//                onCurrentOutcome = ""
//            }
            try{

                val ex = ExpressionBuilder(onCurrentEquation.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if(result == longRes.toDouble())
                    onCurrentOutcome = longRes.toString()
                else
                    onCurrentOutcome = result.toString()
            } catch (e: Exception) {
                Log.d("Ошибка","сообщение: ${e.message}")
            }

        }else{
            onCurrentOutcome = ""
        }
    }

    private fun updateEquation(){
        onView?.setEquation(onCurrentEquation)
    }
    private fun updateOutcome(){
        onView?.setOutcome(onCurrentOutcome)
    }
}