package telran.shapes;

public class SquareTriangle extends Square {

	protected boolean isLeftDiagonal;
	
	protected SquareTriangle(int size, boolean isLeftDiagonal) {
		super(size);
		this.isLeftDiagonal = isLeftDiagonal;
	}

	public String getDiagonal() {
		return isLeftDiagonal ? "Left" : "Right";
	}
	
	public String[] presentation(int offset) {
		String res[] = new String[super.getWidth()];
		res[0] = isLeftDiagonal ? " ".repeat(offset) + super.getSymbol() : " ".repeat(offset + super.getWidth() - 1) + super.getSymbol();
		int lastLine = super.getHeight() - 1;
		res[lastLine] = super.getLine(offset);
		for (int i = 1; i < lastLine; i++) {
			res[i] = getMiddleLine(offset, i);
		}
		return res;
	}
	
	private String getMiddleLine(int offset, int space) {
		String res;
		if (isLeftDiagonal) {
			res = super.getOffset(offset) + super.getSymbol() +  super.getOffset(space - 1) + super.getSymbol();
		}
		else {
			res = super.getOffset(offset + super.getWidth() - space - 1) + super.getSymbol() +  super.getOffset(space - 1) + super.getSymbol();
		}
		return res;
	}
	
}
