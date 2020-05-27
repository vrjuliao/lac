grammar Legalease;

policy: first_deny | first_allow ;

first_deny: 'DENY' 'EXCEPT' allow+
          | 'DENY' 
          | deny
          ;

first_allow: 'ALLOW' 'EXCEPT' deny+
           | 'ALLOW' 
           | allow
           ;

deny: 'DENY'  attribute+ 'EXCEPT' allow+
    | 'DENY'  attribute+
    ;

allow: 'ALLOW'  attribute+ 'EXCEPT' deny+
     | 'ALLOW'  attribute+
     ;

attribute: ID value (',' value)* 
         | ID
         ;

value: ID (':' ID)* ;

ID    :    [a-zA-Z0-9]+;
WS    :    [ \t\r\n]+ -> skip ;
