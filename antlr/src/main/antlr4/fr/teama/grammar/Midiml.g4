grammar Midiml;


/******************
 ** Parser rules **
 ******************/

root            :   declaration bricks states EOF;

declaration     :   'application' name=TITLE;

bricks          :   (instrument|initialTempo|globalRythme)+;
    instrument  :   'instrument' name=INSTRUMENT;
    initialTempo :   'tempo' tempo=TEMPO 'bpm';
    globalRythme:   'rythme' rythme=RYTHME;

states          :   partition+;
    partition   :   '{'  (changeTempo|globalRythme|mesure)+  '}';
    changeTempo :   tempo=TEMPO 'bpm';
    mesure      :   '|' noteCh=noteChaine;
    noteChaine  :   note=(PIANONOTE|BATTERIENOTE) ':' duree=DUREE prochaineNote=noteChaine?;


/*****************
 ** Lexer rules **
 *****************/

INSTRUMENT      :   'PIANO' | 'BATTERIE' ;
PIANONOTE       :   'DO' | 'DO_D'| 'RE' | 'RE_D' | 'MI' | 'FA' | 'FA_D' | 'SOL' | 'SOL_D' | 'LA' | 'LA_D' | 'SI';
BATTERIENOTE    :   'B' | 'BD' | 'SD' | 'CH' | 'OH' | 'CC' | 'RC';
DUREE           :   'N' | 'BL' | 'C'| 'D_C' | 'N_P' | 'BL_P' | 'C_P';
RYTHME          :   '3/4' | '4/4';
TEMPO           :   NUMBER;
TITLE           :   (LOWERCASE)+;



/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NUMBER              : [1-9]([0-9])+;
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
