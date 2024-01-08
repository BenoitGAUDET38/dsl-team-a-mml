grammar Midiml;


/******************
 ** Parser rules **
 ******************/

root        :   title settings tracks EOF;

title       :   'titre :' name=TITRE;

settings    :   (instrument|initialTempo|globalRythme)+;
    instrument  :   'instrument' name=INSTRUMENT;
    initialTempo:   'tempo' tempo=VALUE 'bpm';
    globalRythme:   'rythme' rythme=RYTHME;

tracks      :   partition+;
    partition   :   '{'  (changeTempo|changeRythme|bar)+  '}';
    changeTempo :   tempo=VALUE 'bpm';
    changeRythme:   rythme=RYTHME;
    bar         :   '|' ('nom:' name=TITRE)? ( noteCh=noteChaine? | (reused=TITRE manipulation?));
    manipulation:   ajout | remplacement | suppression;
    ajout       :   '(AJOUT, ' position=VALUE ', ' noteModification=noteChaine ')';
    remplacement:   '(REMPLACEMENT, ' position=VALUE ', ' noteModification=noteChaine ')';
    suppression :   '(SUPPRESSION, ' debut=VALUE (', ' fin=VALUE)? ')';
    noteChaine  :   note=(PIANONOTE|BATTERIENOTE) ':' duree=DUREE prochaineNote=noteChaine?;


/*****************
 ** Lexer rules **
 *****************/

INSTRUMENT      :   'PIANO' | 'BATTERIE' ;
PIANONOTE       :   SILENCE | 'DO' | 'DO_D'| 'RE' | 'RE_D' | 'MI' | 'FA' | 'FA_D' | 'SOL' | 'SOL_D' | 'LA' | 'LA_D' | 'SI';
BATTERIENOTE    :   SILENCE | 'B' | 'BD' | 'SD' | 'CH' | 'OH' | 'CC' | 'RC';
DUREE           :   'N' | 'BL' | 'C' | 'D_C' | 'N_P' | 'BL_P' | 'C_P';
SILENCE         :   'SILENCE';
RYTHME          :   '3/4' | '4/4';
VALUE           :   NUMBER;
TITRE           :   (LOWERCASE)+;
METHODE         :   'AJOUT' | 'SUPPRESSION' | 'REMPLACEMENT';



/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NUMBER              : [1-9]([0-9]+)?;
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
