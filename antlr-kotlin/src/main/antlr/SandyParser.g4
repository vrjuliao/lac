parser grammar SandyParser;

options { tokenVocab=SandyLexer; }

sandyFile : lines=line+ ;

line      : statement (NEWLINE | EOF) ;

statement : varDeclaration
          | assignment
          | print;

print : PRINT LPAREN expression RPAREN # printStatement;

varDeclaration : VAR assignment # varDeclarationStatement;

assignment : ID ASSIGN expression # assignmentStatement;

expression : left=expression operator=(DIVISION|ASTERISK) right=expression # binaryOperation
           | left=expression operator=(PLUS|MINUS) right=expression        # binaryOperation
//           | value=expression AS targetType=type                           # typeConversion
           | LPAREN expression RPAREN                                      # parenExpression
           | ID                                                            # varReference
           | MINUS expression                                              # minusExpression
           | INTLIT                                                        # intLiteral;
//           | DECLIT                                                        # decimalLiteral ;

//type : INT     # integer;
//     | DECIMAL # decimal ;