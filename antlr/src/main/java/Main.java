import fr.teama.App;
import fr.teama.ModelBuilder;
import fr.teama.generator.ToWiring;
import fr.teama.generator.Visitor;
import fr.teama.StopErrorListener;
import fr.teama.grammar.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main (String[] args) throws Exception {
        System.out.println("\n\nRunning the ANTLR compiler for MidiML");

        CharStream stream = getCharStream(args);
        App theApp = buildModel(stream);
        exportToCode(theApp);
    }

    private static CharStream getCharStream(String[] args) throws IOException {
        if (args.length < 1)
            throw new RuntimeException("no input file");
        String cleanPath = args[0].replaceAll("&quot;", "");
        Path input = Paths.get(new File(cleanPath).toURI());
        System.out.println("Using input file: " + input);
        return CharStreams.fromPath(input);
    }

    private static App buildModel(CharStream stream) {
        MidimlLexer    lexer   = new MidimlLexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new StopErrorListener());

        MidimlParser   parser  = new MidimlParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new StopErrorListener());

        ParseTreeWalker   walker  = new ParseTreeWalker();
        ModelBuilder builder = new ModelBuilder();

        walker.walk(builder, parser.root()); // parser.root() is the entry point of the grammar

        return builder.retrieve();
    }

    private static void exportToCode(App theApp) {
        Visitor codeGenerator = new ToWiring();
        System.out.println("Generating music...");
        theApp.accept(codeGenerator);
        //System.out.println(codeGenerator.getResult());
    }

}
