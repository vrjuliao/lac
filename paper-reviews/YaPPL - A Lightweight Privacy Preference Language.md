# YaPPL - A Lightweight Privacy Preference Language
[Paper][1]

## Abstract
YaPPL is a language to introduce the GDPR requirements and the techniques of
privacy policy into the IoT world wide.

## 1 - Introduction
There are two core problems with the data collecting on the IoT systems:
- The individual consent: A person must to know which data is shared with the
  system.
- Fulfill form-related consent: A person could specify which data are allowed
  to be gotten by the system.

IoT systems don't have an user interface to provide this communication with
the user.
So to solve that, the paper propose an approach of three key points:
1. A service to specify the policies about data sharing.
2. A policy provider to store the user polices specified in the first service.
3. A proxy service to persist the policies in data collecting actions.

These three points are integrated in a new language (YaPPL), that allows an
user to write their own policies, being GDPR-compliant (1st approach).
Because YaPPL is a language must be written in a file, the second approach
is satisfied, once files could be audited and checked.
The third approach is the tool that read an YaPPL file and provide the proxy
between IoT devices and data collecting machines.

## 3 YaPPL
### 3.1 Formalization of Consent

Consent is a structure that maps an user **U** to an data processing purpose
**P**.
So if we have an map concept **C** and a set of data **D** collected by the
system, a new data collection **D'** will be produced.
This new collection corresponds of **D** after to apply the rules given by the
user.
**D'** is released by a transformation **T**, where is specified how the released
data will be organized in the new collection.
Lastly, there are the **rule** entity composed by a 4-tuple (D, U, P, T), which
the return **D'** applying the transformation **T** in the collection **D**
considering the purposes **P** specified by the user **U**.

### 3.2 Requirements & Design Decisions
YaPPL have three main nonfunctional requirements:

1. Readability: Then, a JSON file is used to write policies, once JSON is fast
   to compute and, at the same time, is good to read by humans.
2. Extendability: new purposes, users and transformations could be parametrized.
3. Efficiency: IoT devices have a very limited processing power. Then it should
   be chosen alternatives to make the computing of policies more efficient.

YAML is more (human) readable than JSON, but this paper conclude that JSON is
more efficient to read by a computer.
JSON still is a good alternative, since this one is easy to real though.

**Extendability**: At the first moment, YaPPL requires parametrization files to
specify which entities will be used.
By default, the entities are **Users**, **Purpose** and **Transformation**, but
there is a configuration file that allow changes in this datatypes.
Thus, YaPPL is adaptable according to the required domain.


YaPPL policy example:
```json
{
  "id " : 4493,
  "preference": [{
    "rule": {
      "purpose" : {
        "permitted": ["statistics" , "planology" ] ,
        "excluded" : ["commercial"]
      } ,
      "utilizer": {
        "permitted":["wikimedia","tuberlin"],
        "excluded":["netatmo","gate5"]
      } ,
      "transformation":[
        {
          "attribute":"temperature",
          "trfunc":"minmax_hourly"
        }
      ],
      "validfrom":"2017−10−09T00:00:00.00Z",
      "expdate":"0000−00−00T00:00:00.00Z"
    }
  }]
}
```

The same example, written in hapi:
```
data id:
  4493

data purpose:
  statistics, planology, commercial

data utilizer:
  wikimedia, tuberlin, netmap, gate5

data attribute:
  temperature

data trfunc:
  minmax_hourly

main = 
  ALLOW
  EXCEPT
    DENY {
      id = 4493
      purpose = commercial
      utilizer = netatmo
      attribute
      trfunc
    }
    DENY {
      id = 4493
      purpose = commercial
      utilizer = gate5
      attribute
      trfunc
    }
```

[1]: http://www.ise.tu-berlin.de/fileadmin/fg308/publications/2018/Ulbricht_Pallas-2018-YaPPL.pdf