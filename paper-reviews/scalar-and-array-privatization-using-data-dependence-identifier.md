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

## Array privatization

Even for an array $A$ that have only *first Write and then Read* and does not
share element access between different iterations of a loop $L$, it is not
possible parallelize such loop for a shared $A$.
The solution is to create a copy of $A$ for each parallel execution, where this
copy only belongs to that thread, in other words, privatizing the array.
For nested loops, there are some conditions to be satisfied to decide which
nesting loop can be parallelized.
Such decision follows the Theorem 2:
1. .  
  a. Since there is an incoming edge $n.x.j$ and the out coming one as $m.y.p$,
  when $n<m$ for $y=x, j=p$ we identify a *first Write and then Read* operation
  inside the deepest loop.\
  \
  b. Once the item 1.a is not satisfied, when a specific position $u$ of the
  array $A$ has an incoming edge $n.x.j$ and the out coming being $m.y.p$,
  for $x=y$, we have that $u$ is accessed by the same iteration of the extern
  loop. In addition, when $p \geq j$ from the point of view of the extern loop $u$ is always *first Written and then Read*.
  So we can privatize the $A$ for the extern loop.

2. If there is either a reading or a writing operation for every $u \in A$, the
   *first Write and then Read* is true by default.

# Reviewing

## Novelty
Grade 2.

The key novelty of this paper, was introduced in the last paper about the DDI,
then it can be considered as an increment of the DDI one.
DDI maps instructions through a directed graph where these instructions are
separated in two groups: Read and Write.
Finally, the paper discuss about an observation over the nodes and edges in the
DDI graph.
The conditions that become the array or scalar privatization possible, are not
a novelty, actually it can be considered an insight instead.

## Correctness
Grade 3.

Theorem proofs are pretty correct, but there is an attention point:
The paper introduce the DDI as an compiling tool, but the examples and
explanation are given as an interpreter, or something made in runtime.
Despite that, algorithms and functionalities are easy to understand.
One example of that is expressed in the loops explanation, the DDI creates one
node in the graph for each instruction and iteration executed inside the loop.
So, as a compiling tool, the program should not explain it in this way.

## Rigour of the evaluation
Grade 3.

As said in the correctness, a rigorous evaluation would consider explain the
algorithms in a different manner than a runtime $-$ maybe through a statical
analysis framework.
Even though the paper introduce only an insight (from my point of view), the
subjects are presented using algorithms, theorems and easy-to-read examples,
that make the reading a pretty of intuitive.
Besides, the text structure was well defined, the section **Scalar**
**privatization** is almost suffice to understand the **Array privatization**,
so this organization deserve congratulations.
Lastly, the text is composed by short/objective/clear phrases, that do not tire
the reader.

## Treatment of related work
Grade 2.

The paper states that DDI treat the data dependence as a never seen before way,
because of that the related works are used only for enumerating other works
involving array/scalar privatization or intermediary representations.
There are no comparisons between the DDI and the other works, and these related
works are superficially shown, do not showing why DDI is a good approach.
Only the first paper of the page contains citations of related works, maybe
when the graph construction was introduced, the paper could cite the main
difference between such graph and any other graphical representation of data
dependence.