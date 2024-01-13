// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import * as vscode from 'vscode';

const notes = ['DO', 'DO_D', 'RE', 'RE_D', 'MI', 'FA', 'FA_D', 'SOL', 'SOL_D', 'LA', 'LA_D', 'SI'];
const noteDuration = ['D_C', 'C', 'C_P', 'P', 'N', 'N_P', 'BL', 'BL_P', 'R'];
const instrumentOptions = ['PIANO', 'BATTERIE', 'XYLOPHONE', 'ACCORDEON', 'HARMONICA', 'GUITARE', 'CONTREBASSE', 'VIOLON', 'TROMPETTE', 'TROMBONE', 'ALTO', 'CLARINETTE', 'FLUTE', 'WHISTLE', 'OCARINA', 'BANJO'];
const rythmeOptions = ['3/4', '4/4', '6/8', '7/8'];
const tempoOptions = ['80 bpm', '100 bpm', '120 bpm'];

// This method is called when your extension is activated
// Your extension is activated the very first time the command is executed
export function activate(context: vscode.ExtensionContext) {

	// Use the console to output diagnostic information (console.log) and errors (console.error)
	// This line of code will only be executed once when your extension is activated
	console.log('Congratulations, your extension "identifier-test" is now active!');

    const basicTemplateSnippet = vscode.languages.registerCompletionItemProvider(
        'midiml',
        {

		provideCompletionItems(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken, context: vscode.CompletionContext) {
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

    const characterCompletion = vscode.languages.registerCompletionItemProvider(
        'midiml',
        {
            provideCompletionItems(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken, context: vscode.CompletionContext) {
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
	    }
    );

    const spaceSeparator = vscode.languages.registerCompletionItemProvider(
		'midiml',
		{
			provideCompletionItems(document: vscode.TextDocument, position: vscode.Position) {

				// get all text until the `position` and check if it reads `console.`
				// and if so then complete if `log`, `warn`, and `error`
				const linePrefix = document.lineAt(position).text.slice(0, position.character);
				if (linePrefix.endsWith('instrument ')) {
                    return instrumentOptions.map(instrument => new vscode.CompletionItem(instrument, vscode.CompletionItemKind.Method));
				} else if (linePrefix.endsWith('rythme ')) {
                    return rythmeOptions.map(rythme => new vscode.CompletionItem(rythme, vscode.CompletionItemKind.Method));
                } else if (linePrefix.endsWith('tempo ')) {
                    return tempoOptions.map(tempo => new vscode.CompletionItem(tempo, vscode.CompletionItemKind.Method));
                } else if (linePrefix.endsWith('| ') || linePrefix.match(/.*(DO|DO_D|RE|RE_D|MI|FA|FA_D|SOL|SOL_D|LA|LA_D|SI|SILENCE)(-2|-1|0|1|2|3|4|5|6|7)?[ ]/)) {
                    const notesCompletion = notes.map(note => new vscode.CompletionItem(note, vscode.CompletionItemKind.Method));
                    notesCompletion.push(new vscode.CompletionItem('SILENCE', vscode.CompletionItemKind.Method));
                    return notesCompletion;
                } else if (linePrefix.match(/(SILENCE|D_C|C|C_P|P|N|N_P|BL|BL_P|R)[ ]/)) {
                    const notesCompletion = notes.map(note => new vscode.CompletionItem(note, vscode.CompletionItemKind.Method));
                    notesCompletion.push(new vscode.CompletionItem('SILENCE', vscode.CompletionItemKind.Method));
                    notesCompletion.push(new vscode.CompletionItem('|', vscode.CompletionItemKind.Method));
                    return notesCompletion;
                }

				return undefined;
			}
		},
		' ' // triggered whenever a '.' is being typed
	);

    const colonSeparator = vscode.languages.registerCompletionItemProvider(
		'midiml',
		{
			provideCompletionItems(document: vscode.TextDocument, position: vscode.Position) {

				// get all text until the `position` and check if it reads `console.`
				// and if so then complete if `log`, `warn`, and `error`
				const linePrefix = document.lineAt(position).text.slice(0, position.character);
				if (linePrefix.endsWith(':')) {
					return noteDuration.map(note => new vscode.CompletionItem(note, vscode.CompletionItemKind.Method));
				}

				return undefined;
			}
		},
		':' // triggered whenever a '.' is being typed
	);

	context.subscriptions.push(basicTemplateSnippet, characterCompletion, spaceSeparator, colonSeparator);
}

// This method is called when your extension is deactivated
export function deactivate() {}
