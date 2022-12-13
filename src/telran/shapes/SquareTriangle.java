package telran.shapes;

import java.util.Arrays;

public class SquareTriangle extends Square {

	protected boolean isLeftDiagonal;
	
	protected SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	public String getDiagonal() {
		return isLeftDiagonal ? "Left" : "Right";
	}
	
	public String[] myPresentation(int offset) {
		String res[] = new String[getWidth()];
		res[0] = isLeftDiagonal ? " ".repeat(offset) + getSymbol() : " ".repeat(offset + getWidth() - 1) + getSymbol();
		int lastLine = getHeight() - 1;
		res[lastLine] = getLine(offset);
		for (int i = 1; i < lastLine; i++) {
			res[i] = getMiddleLine(offset, i);
		}
		return res;
	}
	
	private String getMiddleLine(int offset, int space) {
		String res;
		if (isLeftDiagonal) {
			res = getOffset(offset) + getSymbol() +  getOffset(space - 1) + getSymbol();
		}
		else {
			res = getOffset(offset + getWidth() - space - 1) + getSymbol() + getOffset(space - 1) + getSymbol();
		}
		return res;
	}
	
	@Override
	public String[] presentation(int offset) {
		int height = getHeight();
		
		char[][] presentationBuffer = new char[height - 1][offset + height];
		fillBuffer(presentationBuffer, offset);
		return getLines(presentationBuffer, offset);	
	}
	
	private void fillBuffer(char[][] buffer, int offset) {
		int edgePos = isLeftDiagonal ? offset : buffer[0].length - 1;
		char symbol = getSymbol().charAt(0);
		for (int i = 0; i < buffer.length; i++) {
			Arrays.fill(buffer[i], ' ');
			int diagonalPos = isLeftDiagonal ? edgePos + i : edgePos - i;
			buffer[i][edgePos] = symbol;
			buffer[i][diagonalPos] = symbol;
		}
	}

	private String[] getLines(char[][] buffer, int offset) {
		String[] res = new String[getHeight()];
		for (int i = 0; i < buffer.length; i++) {
			res[i] = new String(buffer[i]);
		}
		res[res.length - 1] = getLine(offset);
		return res;
	}
	
	
	
	
}
