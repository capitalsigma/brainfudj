import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class BrainfuckInterpreter {
	public static void main(String[] args) throws Exception {

		for(String path : args){
			System.out.println("Generating parse tree for: " + path);

			ANTLRInputStream input = new ANTLRFileStream(path);

			BrainfuckLexer lexer = new BrainfuckLexer(input);

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			BrainfuckParser parser = new BrainfuckParser(tokens);

			ParseTree tree = parser.program(); // begin parsing at init rule
			System.out.println(tree.toStringTree(parser)); // print LISP-style tree

			System.out.println("Interpreted output: ");

			EvalVisitor eval = new EvalVisitor();

			eval.visit(tree);


			System.out.println("-------------------------------------");
		}
	}
}
