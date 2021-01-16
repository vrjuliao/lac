# Operating System Concepts - Silberschatz

## How to use Hapi as a protection tool in operating systems
Exploring Hapi as a Right Access Management tool for contoling the access
matrix.

**Section 14.4**\
In the approach which operating systems implement Capability Lists for each
Domain, for instance, the Domain $D$ can `read` the file $F$, domains can
embed its own capablily list.
This capability list may be described as a Hapi file.
So when a Domain need to do some action with another file or domain, its
capability list (Hapi file) is read.
A good thing is that, as Hapi can import files, we might import some
declarations of lattices, attributes and policies from another domains.

**Hapi Capabillity-Based System**
Suppose we have three domains $D_1, D_2$ and $D_3$.
This approach consider domains as objects, then whether $D_1$ needs access
$D_2$, since $D_1$ has access right to do that, it can be done.
In this system Lattices and attributes must be declared globally, and each
domain embed a Hapi file with its policy rules.
With this global declaration any  Hapi file that belongs to domains can import
the whole system structure, and global policies can also be imported.

Global policies are used as the first protection level, the goal is to specify
policies for all domains.
For instance, if some binary `PROGRAM` can be `executed` for every `DOMAIN`,
that policy will be placed on global Hapi file like below:

```
ALLOW {
  Domain
  Action: Execute
  File: PROGRAM
}
```

But when some file (resource) is denied this policy should be written in the
domain Hapi file.
With this each domain implements its own capability list. 

Each domain Hapi file must cointain a shared policy rule for allowing an
inherited use.
**Example**\
$D_1$: the user `John`.\
$D_2$: the program `cat`.\
Since `cat` just read files, `John` can read any file using cat inside John's
domain.
But it is possible only if `cat` policy imports the John shared policy, where
John specifies that any file inside `/home/john/*` can be read.

Other observable approach is when `cat` domain specifies that can read `John`'s
domain, even if the inherited policy don't allow that.
In this case, `cat` domain Hapi file needs inherit the shared policy of the
caller, and after implement the John's domain reading:

```
# cat domain hapi file
import caller
import global

main =
  global:shared	
  DENY
  EXCEPT {
    caller:shared
    ALLOW {
      Domain: john
      Action: read
      File: /home/john/*
    }
  }
```

The case above is interesting when a third domain `Richard`, that don't have
access right to `read` in `John`'s domain , wants to execute `cat` for `John`'s
files.
When `cat` is called by `Richaerd`, the policies checking must investigate:
1. If `John`'s files can be accessed globally, using `global:shared`.
2. If `Richard` has received access right to `John`'s domain (`claller:shared`)
3. Or else, if `cat` command can be executed for any file inside `John`'s
  domain, with the remaining policy description.


**Section 14.8**\

- Hapi can solve `Revocation of Acess Rights`? 
  read the section 14.7 triyng answer that question.
