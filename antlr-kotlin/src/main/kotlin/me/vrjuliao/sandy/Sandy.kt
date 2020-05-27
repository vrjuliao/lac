package me.vrjuliao.sandy
/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material,
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose.
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
 ***/

import me.vrjuliao.langsandbox.*
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileInputStream

class Sandy {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
//            println("asdhfklafjklsdhls")
            val inputStream = if (args.isNotEmpty()) {
                FileInputStream(args[0])
            } else {
                System.`in`
            }
            val input = CharStreams.fromStream(inputStream)
            val lexer = SandyLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = SandyParser(tokens)
            val tree = parser.sandyFile()  // parse; start at prog <label id="code.tour.main.6"/>

            val eval = SandyEval()
            eval.visitSandyFile(tree)
        }
    }
}