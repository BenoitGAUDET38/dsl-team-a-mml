"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.deactivate = exports.activate = void 0;
// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
const vscode = __importStar(require("vscode"));
const notes = ['DO', 'DO_D', 'RE', 'RE_D', 'MI', 'FA', 'FA_D', 'SOL', 'SOL_D', 'LA', 'LA_D', 'SI'];
const noteDuration = ['D_C', 'C', 'C_P', 'P', 'N', 'N_P', 'BL', 'BL_P', 'R'];
// This method is called when your extension is activated
// Your extension is activated the very first time the command is executed
function activate(context) {
    // Use the console to output diagnostic information (console.log) and errors (console.error)
    // This line of code will only be executed once when your extension is activated
    console.log('Congratulations, your extension "identifier-test" is now active!');
    const basicTemplateSnippet = vscode.languages.registerCompletionItemProvider('midiml', {
        provideCompletionItems(document, position, token, context) {
            // a completion item that inserts its text as snippet
            const rythmeOptions = ['3/4', '4/4', '6/8', '7/8'];
            const tempoOptions = ['80 bpm', '100 bpm', '120 bpm'];
            const instrumentOptions = ['PIANO', 'BATTERIE', 'XYLOPHONE', 'ACCORDEON', 'HARMONICA', 'GUITARE', 'CONTREBASSE', 'VIOLON', 'TROMPETTE', 'TROMBONE', 'ALTO', 'CLARINETTE', 'FLUTE', 'WHISTLE', 'OCARINA', 'BANJO'];
            // Snippet that will be inserted
            const snippetContent = `titre default-title\n\n# Declaring settings\ntempo \${1|${tempoOptions.join(',')}\|} rythme \${2|${rythmeOptions.join(',')}\|}\n\ninstrument \${3|${instrumentOptions.join(',')}\|}\n{\n    \n}\n`;
            const snippetCompletion = new vscode.CompletionItem('Template Midiml');
            snippetCompletion.insertText = new vscode.SnippetString(snippetContent);
            // return all completion items as array
            return [
                snippetCompletion
            ];
        }
    });
    const characterCompletion = vscode.languages.registerCompletionItemProvider('midiml', {
        provideCompletionItems(document, position, token, context) {
            const instrumentCharacterCompletion = new vscode.CompletionItem('instrument');
            instrumentCharacterCompletion.commitCharacters = [' '];
            instrumentCharacterCompletion.documentation = new vscode.MarkdownString('Press `space` to get `liste of instruments`');
            const rythmeCharacterCompletion = new vscode.CompletionItem('rythme');
            rythmeCharacterCompletion.commitCharacters = [' '];
            rythmeCharacterCompletion.documentation = new vscode.MarkdownString('Press `space` to get `liste of rythmes`');
            const tempoCharacterCompletion = new vscode.CompletionItem('tempo');
            tempoCharacterCompletion.commitCharacters = [' '];
            tempoCharacterCompletion.documentation = new vscode.MarkdownString('Press `space` to get `liste of most used tempos` (replacable by any "number bpm"');
            // return all completion items as array
            return [
                instrumentCharacterCompletion,
                tempoCharacterCompletion
            ];
        }
    });
    const spaceSeparator = vscode.languages.registerCompletionItemProvider('midiml', {
        provideCompletionItems(document, position) {
            // get all text until the `position` and check if it reads `console.`
            // and if so then complete if `log`, `warn`, and `error`
            const linePrefix = document.lineAt(position).text.slice(0, position.character);
            if (linePrefix.endsWith('instrument ')) {
                return [
                    new vscode.CompletionItem('PIANO', vscode.CompletionItemKind.Method),
                    new vscode.CompletionItem('BATTERIE', vscode.CompletionItemKind.Method)
                ];
            }
            else if (linePrefix.endsWith('rythme ')) {
                return [
                    new vscode.CompletionItem('3/4', vscode.CompletionItemKind.Method),
                    new vscode.CompletionItem('4/4', vscode.CompletionItemKind.Method),
                    new vscode.CompletionItem('6/8', vscode.CompletionItemKind.Method)
                ];
            }
            else if (linePrefix.endsWith('tempo ')) {
                return [
                    new vscode.CompletionItem('80 bpm', vscode.CompletionItemKind.Method),
                    new vscode.CompletionItem('100 bpm', vscode.CompletionItemKind.Method),
                    new vscode.CompletionItem('120 bpm', vscode.CompletionItemKind.Method)
                ];
            }
            else if (linePrefix.endsWith('| ')) {
                const notesCompletion = notes.map(note => new vscode.CompletionItem(note, vscode.CompletionItemKind.Method));
                notesCompletion.push(new vscode.CompletionItem('SILENCE', vscode.CompletionItemKind.Method));
                return notesCompletion;
            }
            else if (linePrefix.endsWith('SILENCE ') || linePrefix.endsWith('D_C ') || linePrefix.endsWith('C ') || linePrefix.endsWith('C_P ') || linePrefix.endsWith('P ') || linePrefix.endsWith('N ') || linePrefix.endsWith('N_P ') || linePrefix.endsWith('BL ') || linePrefix.endsWith('BL_P ') || linePrefix.endsWith('R ')) {
                const notesCompletion = notes.map(note => new vscode.CompletionItem(note, vscode.CompletionItemKind.Method));
                notesCompletion.push(new vscode.CompletionItem('SILENCE', vscode.CompletionItemKind.Method));
                notesCompletion.push(new vscode.CompletionItem('|', vscode.CompletionItemKind.Method));
                return notesCompletion;
            }
            return undefined;
        }
    }, ' ' // triggered whenever a '.' is being typed
    );
    const colonSeparator = vscode.languages.registerCompletionItemProvider('midiml', {
        provideCompletionItems(document, position) {
            // get all text until the `position` and check if it reads `console.`
            // and if so then complete if `log`, `warn`, and `error`
            const linePrefix = document.lineAt(position).text.slice(0, position.character);
            if (linePrefix.endsWith(':')) {
                return noteDuration.map(note => new vscode.CompletionItem(note, vscode.CompletionItemKind.Method));
            }
            return undefined;
        }
    }, ':' // triggered whenever a '.' is being typed
    );
    context.subscriptions.push(basicTemplateSnippet, characterCompletion, spaceSeparator, colonSeparator);
}
exports.activate = activate;
// This method is called when your extension is deactivated
function deactivate() { }
exports.deactivate = deactivate;
//# sourceMappingURL=extension.js.map