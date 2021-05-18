# Scalar and Array Privatization using Data Dependence Identifier

## Introduction
The goal is to use de DDI (Data Dependence Identifier) algorithm for privatize
scalars and arrays.
This privatization consists of create a copy of some data where the current
thread is the owner of such data.
So, parallelizing compiler can paralelize serial code.
Such parallelization depends on the generated Intermediary Representation (IR)
used by each parallelizing compiler, then the paper discuss briefly each
existent approach of IR comparing it to the privatization by DDI.

### Data Dependence Identifier tool
Models a program, for identify the ownership of each variable, by a graph where
each node represents the variable and the edges consists of the access mode
of this variable on the memory.
The categorization of memory access is given by:
- **Memory Access Instruction (MAI)**\
  Instructions that access the memory to execute operations (arithmetic or
  conditional for instance).

The memory access is divided into three other subcategories:
1. MA-READ
2. MA-WRITE
3. MA-READWRITE (MARW)

Another important point is the parametrization of a program, where a
parametrized is:
- Set $I$, finite, of instruction $\{i_1, i_2, \ldots, l_n\}$
- Set $V$, finite, of memory allocations (variables) $\{v_1, v_2, \ldots, v_p\}$
- Set $MAI$, finite, of MA instructions where $MAI \subseteq I$.
- Set $HU$ representing the hardware units.
- Set $PR$ of constant values initialized in the program.

**Directed graph representation of a program**\
The key difference between the DDI and the others algorithms consists in how
a program is transformed in a intermediary representation.
The DDI transforms a program $P$ in a directed graph $G_P$.
Every instruction executed by the program is indexed by a positive integer $i$
and these instructions are composed by a pair $(R, W)$.

This transformation is given as follow:
- The graph nodes are the set $V \cup \{PR,HR\}$.
- Every pair $(R,W) \in MAI$ the correspondent edge is added to the graph,
  from the **reading** node to the **writing** one.
- Every edge is labeled with the correspondent index of its instruction.

In nested loops, instructions are labeled by the following example:\
Suppose two nested loops, $L_1$ (most external) and $L_2$ the $m^{th}$
instruction inside these two loops are labeled by $m.j.k$ when $L_1$ and $L_2$
ar in the e $j$ and $k$ iterations, respectively.

> I cannot understand if this graph is built in compiling time or in the 
  running one.
  To identify the iteration number, does it not necessary to run the program?

## Scalar Privatization
Before to explore Scalar Privatization, let us define the concept of
*First Write and then Read* operations.
Firstly, we shall consider loop scopes, as the following:
```cpp
for(i=1; i<= 2; i++){
  t = A[i] * 5;
  B[i] = t;
}
```
The variable `t` suffers a *First Write then Read* operation, since for each
iteration, the first operations over `t` is writing and then a reading.
But, furthermore, `t` consideres only values that belongs to the current
iteration.
In other words, `t` cannot access data from a previous iteration.

Since a iteration does not inherit data from the previous one, it is possible
to parallelize each iteration.
There are two manners to do that:
1. Converting a scalar variable to an array where each array position
  corresponds to an iteration.
2. Privatizing a scalar variable where each thread creates its own copy
  without share memory instead.

The second option above is our **Scalar Privatization**.
And a scalar $x$ can be privatized if the following conditions satisfy:
- $x$ is accessed in each iteration of the loop.
- Each iteration of the loop performs *first Write and then Read* operations
  over $x$.

A scalar $x$ can be privatized whether in a loop $l$, and its correspondent
execution graph $G_l$, for each incoming edge $n.k$ in $x$ there is an out
coming edge $m.k$ where $m > n$.
It configures a *first Write and then Read* operation.