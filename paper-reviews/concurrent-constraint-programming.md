# Concurrent Constraint Programming

[text](https://people.csail.mit.edu/rinard/paper/popl90.pdf)

## Introduction
The concurrent constraint programming paradigm consist of two key operations:
- $\downarrow$ or *Ask*: Is an operation that read the data
- $\leftarrow$ or *Tell*: writing data.

These operations are compared to the programming languages based on Imperative
Paradigm, where given a sequence of constraints, CC programs ask which
values comply with such constraints.
The state of each asking is propagated to the other operations, composing a
monotonically refined solution.

Although being similar to logic programming, this approach allows concurrent
computation.
Imagine multiples constrains being asked at the same time, but some constraints
depends on the previous *Told* values, then such *Ask* operation waits the
*Tell* operation before continue its execution.