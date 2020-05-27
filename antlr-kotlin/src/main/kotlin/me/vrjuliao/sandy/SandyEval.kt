package me.vrjuliao.sandy

import me.vrjuliao.langsandbox.*
import java.util.HashMap
import kotlin.system.exitProcess

class SandyEval: SandyParserBaseVisitor<Int>() {
    /** "memory" for our calculator; variable/value pairs go here */
    var memory: HashMap<String, Int> = HashMap<String, Int>()

    /** PRINT LPAREN expression RPAREN **/
    override fun visitPrintStatement(ctx: SandyParser.PrintStatementContext): Int {
        println(visit(ctx.expression()))
        return 0
    }

    /** VAR assignment **/
    override fun visitVarDeclarationStatement(ctx: SandyParser.VarDeclarationStatementContext): Int{
        val id = ctx.assignment().getChild(0).text
        if(memory.containsKey(id)){
            println("The \"$id\" variable is already declared")
            exitProcess(1)
        }
        memory[id] = visit(ctx.assignment())
        return 0
    }

    /** ID ASSING EXPRESSION */
    override fun visitAssignmentStatement(ctx: SandyParser.AssignmentStatementContext): Int {
        val value = visit(ctx.expression())   // compute value of expression on right
        return value
    }

    /** MINUS expression **/
    override fun visitMinusExpression(ctx: SandyParser.MinusExpressionContext): Int{
        return (-1)*visit(ctx.expression())
    }

    /** INTLIT **/
    override fun visitIntLiteral(ctx: SandyParser.IntLiteralContext): Int{
        return ctx.INTLIT().text.toInt()
    }

    /** LPAREN expression RPAREN **/
    override fun visitParenExpression(ctx: SandyParser.ParenExpressionContext): Int{
        return visit(ctx.expression())
    }

    /** left=expression operator=(DIVISION|ASTERISK) right=expression  **/
    /** left=expression operator=(PLUS|MINUS) right=expression  **/
    override fun visitBinaryOperation(ctx: SandyParser.BinaryOperationContext): Int{
        val left = visit(ctx.left)
        val right = visit(ctx.right)
        return when(ctx.operator.type){
            SandyParser.ASTERISK -> left * right
            SandyParser.DIVISION -> left / right
            SandyParser.PLUS -> left + right
            SandyParser.MINUS -> left - right
            else -> 0
        }
    }

    /** ID **/
    override fun visitVarReference(ctx: SandyParser.VarReferenceContext): Int{
        val id = ctx.ID().text
        if(!memory.containsKey(id)){
            println("Does not exists variable \"$id\"")
            exitProcess(1)
        }
        val value = memory.get(id)
        return if(value!= null){ value } else{ 0 }
    }
}