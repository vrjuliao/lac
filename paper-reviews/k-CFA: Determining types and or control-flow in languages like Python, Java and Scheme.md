# k-CFA: Determining types and/or control-flow in languages like Python, Java and Scheme
[Link](http://matt.might.net/articles/implementation-of-kcfa-and-0cfa/)

## Introduction

To answer the following questions, it's necessary to use a CFA algorithm.
- Is it possible to assign a type to an expression in a dynamic language like
Python or JavaScript?
- Is it possible to predict which method will be invoked under dynamic
dispatch in an object-oriented language like Java?
- Is it possible to know which function will be called at a higher-order call
site in a language like Scheme?

Conservatives CFAs (Control Flow Analysis) infers the expressions types
before to predict the correct flow.
But this article discuss the difficulty of analyzing dynamic or high-order
languages using CFAs.
Then, a functional reference of CFAs is purposed as a solution (k-CFA).

## The control-flow problem
> **higher-order control-flow problem**: the precise target of a function
call may not be obvious.

In general, control-flow problem implies in a data-flow problem for
object-oriented languages that use dynamic method dispatching.
Once the function call `object.method()` occurs, the control-flow must know if
the method `method()` is in `object`, and `method()` is a data from `object`.
When `object` is a parameter received by a function, and inside this function
`object.method()` is called, the control-flow would be determined firstly
checking the existence of `method()` in `object`.

## The value-flow problemas

It is a generalization of control-flow problem.
Control-flow problem asks: "Which procedures may the expression `f` be in the
call `(f x)`?".
Value-flow problem asks: "For which values may the the expression `f` and `x`
evaluate?".

### Solution: simulate the program

