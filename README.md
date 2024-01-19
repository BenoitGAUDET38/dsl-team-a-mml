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
## Domain Model

You will find the Domain Model as a class diagram int the [README](https://github.com/BenoitGAUDET38/dsl-team-a-mml/blob/main/kernel/README.md) of the `kernel/ressources` subfolder

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
modifNumber :   'MODIF NOTE ' noteName=TITRE '->' number=(CLASSIQUENOTE|BATTERIENOTE);
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
FLOAT           :   '0'..'9'+ '.' [0-9] [0-9]?;
VALUE           :   NUMBER;



/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
ZERO                : '0';
NUMBER              : [0-9]([0-9]+)?;
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
|              Range of characters              |   [...]   |

### Script example
```java
### Basic scenario
## Billie Jean by Michael Jackson
# Tab used: https://www.songsterr.com/a/wsa/michael-jackson-billie-jean-drum-tab-s10586t1

titre billiejean

tempo 118bpm
rythme 4/4

instrument BATTERIE
{
    | CH:C CH:C CH:C CH:C CH:C CH:C CH:C CH:C BD:C:0.0 SD:C:1.0 BD:C:2.0 SD:C:3.0 x2
    | bar1 -> CH:C CH:C CH:C CH:C CH:C CH:C CH:C CH:C(note1) BD:C:0.0 SD:C:1.0 BD:C:2.0 SD:C:3.0 MA:C:0.0 MA:C:0.5 MA:C:1.0 MA:C:1.5 MA:C:2.0 MA:C:2.5 MA:C:3.0 MA:C:3.5 x3
    | bar1 (MODIF AJOUT PH:D_C:3.5 et MODIF AJOUT PH:D_C:3.75)
    | bar1 (MODIF AJOUT PH:C:0.0)
    | bar1 x26
    | bar1 (MODIF NOTE note1 -> OH)
}

instrument GUITARE BASSE MEDIATOR
{
    | SILENCE:R x2
    | bar1 -> FA_D2:C DO_D2:C MI2:C FA_D2:C MI2:C DO_D2:C SI1:C DO_D2:C x16
    | bar2 -> SI:C FA_D:C LA:C SI:C SILENCE:C FA_D:C MI:C FA_D:C x2
    | bar1 x2
    | bar2 x2
    | bar1 x6
    | bar2 x2
    | bar1 x2
}

instrument GUITARE ELECTRIQUE DISTORSION
volume 80
{
    | SILENCE:R x10
    | bar1 -> FA_D:C_P DO_D:C_P:0.0 LA2:C_P:0.0 SOL_D:C_P:1.5 RE_D:C_P:1.5 SI2:C_P:1.5
    | bar2 -> LA:C_P MI:C_P:0.0 DO_D:C_P:0.0 SOL_D:C_P:1.5 RE_D:C_P:1.5 SI2:C_P:1.5
    | bar1 | bar2 | bar1 | bar2 | bar1 | bar2
    | bar3 -> FA_D:C_P RE:C_P:0.0 SI2:C_P:0.0 FA_D:C_P:1.5 RE:C_P:1.5 SI2:C_P:1.5 x2
    | bar1 | bar2 | bar3 x2 | bar1 | bar2 | bar1 | bar2 | bar1 | bar2 | bar3 x2 | bar1 | bar2
}

instrument VIOLON
{
    | sil -> SILENCE:R x14
    | SILENCE DO_D:C DO_D:C DO_D:C SI2:C LA2:C SI2:C | SILENCE:C LA2:C DO_D:C SI2:D_C LA2:D_C SI2:C LA2:C DO_D
    | bar3 -> SILENCE:C DO_D:C DO_D:C DO_D:C DO_D:C SI2:C LA2:C SI2:C | SILENCE:C LA2:C DO_D:C SI2 LA2:C SOL_D2:C FA_D2:C
    | bar1 -> SILENCE SILENCE:BL FA_D:D_C SOL_D:D_C FA_D:C | bar2 -> SILENCE FA_D:D_C SOL_D:D_C FA_D SILENCE:C FA_D:D_C SOL_D:D_C FA_D:C | sil
    | SILENCE:C FA_D2:C LA2:C SI2 LA2:C SOL_D2:C FA_D2:C | bar1 | bar2 | sil x2 | bar3
    | SILENCE:C LA2:C DO_D:C SI2:D_C LA2:D_C SI2:C LA2:D_C DO_D | bar3
    | SILENCE:C LA2:C DO_D:C SI2:C SI2:C LA2:C SOL_D2:C FA_D2:C | bar1 | bar2
}
```

---
## Scenarios
- [X] Billie Jean, *Michael Jackson*
- [X] Love is All, *Roger Glover*

## Extension: Support for bar modifications
Frequently, certain bars exhibit variations from previously defined ones.
Therefore, it is valuable to enable users to define a new bar based on an existing one.

## Add-on: autocompletion Visual Studio Code extension
This extension displays suggested options as users type, and facilitating the completion of entire terms by default or using just the initial letters.

You will find in the `completion-midiml` subfolder a [README](https://github.com/BenoitGAUDET38/dsl-team-a-mml/blob/main/completion-midiml/README.md) explaining how to install and how to use this extension.
