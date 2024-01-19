# Domain Specific Language - Music ML

<div style="text-align: center; margin-top: 50px">
    <img style="width: 50%;" src="https://cdn.discordapp.com/attachments/1179814917688283147/1197472620694753320/2.png?ex=65bb6448&is=65a8ef48&hm=52e8eedd1aa91730be543ee46a14af7d51e6fd937c371f9e2baa78d368212ab7&">
</div>

---
## Team A
### Authors
- [Antoine BUQUET](https://github.com/antoinebqt)
- [Benoit GAUDET](https://github.com/BenoitGAUDET38)
- [Ayoub IMAMI](https://github.com/AyoubIMAMI)
- [Mourad KARRAKCHOU](https://github.com/MouradKarrakchou)
---

## How to run the project

Follow the instructions in the [README](https://github.com/BenoitGAUDET38/dsl-team-a-mml/blob/main/antlr/README.md) of the `antlr` subfolder

---
## External DSL (ANTLR) to play music with Java
### BNF
```java
grammar Midiml;


/******************
 ** Parser rules **
 ******************/

root        :   title? settings tracks EOF;

title       :   'titre ' name=TITRE;

settings          :   (instrument|initialTempo|globalRythme)+;
    initialTempo :   'tempo' tempo=INT 'bpm';
    globalRythme:   'rythme' rythme=RYTHME;

tracks          :   instrument+;
    instrument  :   'instrument' name=INSTRUMENT volume? partition;
    volume      :   'volume' volumeVal=INT;
    partition   :   '{'  (changeTempo|changeRythme|bar|reusedBar)+  '}';
    changeTempo :   tempo=INT 'bpm';
    changeRythme:   rythme=RYTHME;
    bar         :   '|' (name=TITRE '->')? noteCh=noteChaine? REPETITION?;
    reusedBar   :   '|' (name=TITRE '->')? barNameToUse=TITRE ('(' manipulation ')')? REPETITION?;
    manipulation:   (ajout | suppression | modifDuration | modifNumber) (('et' | 'ET') manipulation)?;
    ajout       :   'MODIF AJOUT ' noteToAdd=noteSimple;
    suppression :   'MODIF SUPPR ' noteName=TITRE;
    modifDuration:  'MODIF DUREE ' noteName=TITRE '->' duration=DUREE;
    modifNumber  :  'MODIF NOTE ' noteName=TITRE '->' number=(CLASSIQUENOTE|BATTERIENOTE);
    noteChaine  :   noteSimple prochaineNote=noteChaine?;
    noteSimple  :   note=(CLASSIQUENOTE|BATTERIENOTE) octave=OCTAVE? (':' duree=DUREE)? (':' timing=FLOAT)? ('(' noteName=TITRE ')')?;


/*****************
 ** Lexer rules **
 *****************/

INSTRUMENT      :   'BATTERIE' | 'PIANO' | 'XYLOPHONE' | 'ACCORDEON' | 'HARMONICA' | 'GUITARE' | 'GUITARE ELECTRIQUE DISTORSION' | 'CONTREBASSE' | 'GUITARE BASSE MEDIATOR' | 'GUITARE BASSE FRETLESS' | 'VIOLON' | 'TROMPETTE' | 'TROMBONE' | 'SAXOPHONE ALTO' | 'CLARINETTE' | 'FLUTE' | 'WHISTLE' | 'OCARINA' | 'BANJO';
CLASSIQUENOTE   :   SILENCE | 'DO' | 'DO_D'| 'RE' | 'RE_D' | 'RE_B' | 'MI' | 'MI_B' | 'FA' | 'FA_D' | 'SOL' | 'SOL_B' | 'SOL_D' | 'LA' | 'LA_B' | 'LA_D' | 'SI' | 'SI_B' |;
OCTAVE          :   '-2' | '-1' | '1' | '2' | '3' | '4' | '5' | '6' | '7';
BATTERIENOTE    :   SILENCE | 'BD' | 'SD' | 'CH' | 'HFT' | 'PH' | 'LT' | 'OH' | 'LMT' | 'HMT' | 'CC' | 'HT' | 'RC' | 'MA';
DUREE           :   'N' | 'BL' | 'C' | 'D_C' | 'N_P' | 'BL_P' | 'C_P' | 'R';
SILENCE         :   'SILENCE';
RYTHME          :   '3/4' | '4/4' | '8/8' | '7/8';
REPETITION      :   'x' NUMBER;
INT             :   NUMBER;
TITRE           :   LOWERCASE(LOWERCASE | NUMBER)+;
FLOAT               : '0'..'9'+ '.' [0-9] [0-9]?;
VALUE           :   NUMBER;



/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
ZERO                : '0';
NUMBER              : [1-9]([0-9]+)?;
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
```
### Table of symbols
|                     Usage                     | Notation  |
|:---------------------------------------------:|:---------:|
|  Definition between an element and its syntax |     :     |
|                  Termination                  |     ;     |
|  Definition between a variable and its value  |     =     |
|                   Grouping                    |   (...)   |
|                  Alternation                  |    \|     |
|      Optional repetition (once or more)       |     +     |
|        Optional element (once or none)        |     ?     |

### Script example
```java

```

---
## Scenarios
- [X] Billie Jean, Michael Jackson
- [X] Love is All, Roger Glover

## Extensions
- [X] Support for bar modifications
- [ ] Support for human like errors
- [ ] Support for user interactive input
- [ ] Support for user defined sounds

## Addon

