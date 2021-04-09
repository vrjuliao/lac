# Dala: A Simple Capability-Based Dynamic Language Design For Data Race-Freedom

## Abstract
Dala is a programming model, based on three main capabilities to prevent data
race between objects.
These capabilities are:
  1. Immutable objects can be shared freely
  2. Isolated mutable objects can be transferred between threads but not
     aliased.
  3. Local objects can be aliased within their owing thread but not
  dereferenced by other threads.

These capabilities ensure data-race freedom in a different way than languages
like Erlang, Clojure, JavaScript, etc.
These languages make a copy of objects during the thread communication or proxy
back to their owning thread.


## Introduction
- Non-orthogonal languages: implements implicitly properties in order to ensure
data race-freedom.
- Orthogonal languages: many object-oriented languages make data race a
afterthought.

Pony, Rust, Encore and Reflmm languages  deliver efficient concurrency: it
means that these languages allow large object graphs to be shared or passed
around safely by pointer.\
E, Newspeak, AmbientTalk and Erlang maintain data race-freedom implicitly,
where objects are copied between threads.
It is called simply concurrency.

Dala delivers a design that provides simplicity and performance, to work with
dynamically or statically typed languages.
It is shown by the introducing of the three core capabilities, with a untyped
object-based language environment.

## Background: Perils of Concurrent Programming
### Balancing safety, Complexity and Performance
Concurrency mechanisms can be divided in three groups:

**Complex and Efficient**: Pass a large data structure across actors by
reference wit a fast synchronous access by the receiving actor.
The cost of this efficiency is complexity: many capabilities should be added to
these languages.
Because of that, implementing a polymorphic code free of data race is a
complex work.

**Simple and Inefficient**: Avoid data races by deep copying objects in
messages.
The inefficiency is due to the CPU cost to copy the data in messages and
increase the memory pressure.
Deep copy loses objects identity, then it is solved by other data structure for
synchronizing data changes (more overhead).

**Complex and Inefficient**: Implements a proxy that allows objects owned by a
process to directly reference another.
Since all interaction of each process is between asynchronous messages, this
approach is slower than others.
Many process can point to an object, but the access is made by the the owner
process, and this owner deliver the data to the requesting process.

### Safe One-Size-Fits-All Concurrency
When a language has a set of rules to implement a safety concurrent program.
Then, to implement a data race-freedom program using such language, it is
necessary following the language guide.
But it can be a problem, because a non-safe program can be written.
In C/C++ we may use pthreads with locks and unlocks mutexes, it is the rule
for data race freedom.
If we chose implement threads regardless of mutexes, or using
mutexes/signals incorretecly instead, this programs is no longer safe.

### Safety May Beget Unsafety


## SUMMARY
Starts showing the data race-freedom world:\
**The problems**: share complex objects between actors/threads/process (hash maps
it the most common example used in the paper).\
**Solutions**: Using other languages that grant data race-freedom, the paper
shows the approaches used by these ones.
Here the examples are among Rust, Erlang, E and others.
All compared languages are capability-based, as well as Dala.

The next section, provides a categorization of each language, according to
the efficiency and complexity of the data race-freedom system implementation.
For this case, we have: Efficient & Complex, Inefficient & Complex, Inefficient
& Simple.


- ECOOP 2021 paper (Review 400 words)
1. A summary of the paper, in your own words.
2. Points in the paper that you liked.
   1. I'd heard about Rust to be better than C++ in memory safe controlling,
      but I didn't understand.
      Now, I see what exactly people was saying about.
3. Points in the paper that you did not like.
   1. In section 2, the author point three data race-freedom solutions:
    Efficient & Complex, Inefficient & Complex, Inefficient & Simple.
    But the author did not say where Dala is in.
4. Things in the paper that were hard to understand.
   1. Section 2.1: lines 123 until 127:
   Why do hash maps have this problem?