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

INSTRUMENT      :   'BATTERIE' | 'PIANO' | 'XYLOPHONE' | 'ACCORDEON' | 'HARMONICA' | 'GUITARE' | 'GUITARE ELECTRIQUE DISTORSION' | 'CONTREBASSE' | 'GUITARE BASSE MEDIATOR' | 'VIOLON' | 'TROMPETTE' | 'TROMBONE' | 'ALTO' | 'CLARINETTE' | 'FLUTE' | 'WHISTLE' | 'OCARINA' | 'BANJO';
CLASSIQUENOTE   :   SILENCE | 'DO' | 'DO_D'| 'RE' | 'RE_D' | 'MI' | 'FA' | 'FA_D' | 'SOL' | 'SOL_D' | 'LA' | 'LA_D' | 'SI';
OCTAVE          :   '-2' | '-1' | '1' | '2' | '3' | '4' | '5' | '6' | '7';
BATTERIENOTE    :   SILENCE | 'BD' | 'SD' | 'CH' | 'PH' | 'OH' | 'CC' | 'RC' | 'MA';
DUREE           :   'N' | 'BL' | 'C' | 'D_C' | 'N_P' | 'BL_P' | 'C_P' | 'R';
SILENCE         :   'SILENCE';
RYTHME          :   '3/4' | '4/4';
REPETITION      :   'x' NUMBER;
INT             :   NUMBER;
TITRE           :   LOWERCASE(LOWERCASE | NUMBER)+;
FLOAT               : '0'..'9'+ '.' ('0'|'25'|'5'|'75');
VALUE           :   NUMBER;
METHODE         :   'AJOUT' | 'SUPPRESSION' | 'DUREE' | 'NOTE';



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
