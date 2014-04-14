import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Scanner;
import java.lang.Byte;
import java.lang.ArrayIndexOutOfBoundsException;
import org.antlr.v4.runtime.misc.NotNull;

public class EvalVisitor extends BrainfuckBaseVisitor<Void> {
	List<Byte> tape = new ArrayList<Byte>(asList(new Byte((byte)0)));
	Integer currentPoint = 0;
	Scanner scanner = new Scanner(System.in);

	@Override public Void visitMoveExp(@NotNull BrainfuckParser.MoveExpContext ctx) {
		switch (ctx.op.getType()) {
		case BrainfuckParser.MV_LEFT:
			if (currentPoint > 0) {
				currentPoint--;
			} else {
				throw (new ArrayIndexOutOfBoundsException());
			}
			break;
		case BrainfuckParser.MV_RT:
			if (++currentPoint == tape.size()) {
				tape.add((new Byte((byte)0)));
			}
			break;
		}
		return null;
	}
	@Override public Void visitIoExp(@NotNull BrainfuckParser.IoExpContext ctx) {
		switch (ctx.op.getType()) {
		case BrainfuckParser.OUTPUT:
			Byte toPrint = tape.get(currentPoint);
			System.out.printf("%c", toPrint);
			break;
		case BrainfuckParser.INPUT:
			tape.set(currentPoint, scanner.next(".").getBytes()[0]);
			break;
		}

		return null;
	}

	@Override public Void visitArithExp(@NotNull BrainfuckParser.ArithExpContext ctx) {
		Byte currentVal = tape.get(currentPoint);
		switch(ctx.op.getType()) {
		case BrainfuckParser.INC:
			currentVal++;
			break;
		case BrainfuckParser.DEC:
			currentVal--;
			break;
		}

		tape.set(currentPoint, currentVal );
		return null;
	}

	@Override public Void visitWhileExp(@NotNull BrainfuckParser.WhileExpContext ctx) {
		while (tape.get(currentPoint) != 0) {
			visitChildren(ctx);
		}
		return null;
	}

}
