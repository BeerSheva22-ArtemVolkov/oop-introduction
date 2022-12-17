package telran.shapes;

import java.util.Arrays;

public class Canvas extends Shape {
	
	private Shape[] shapes;
	private String direction = "row";
	private int margin = 5;
	
	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setMargin(int margin) {
		this.margin = margin;
	}
	
	@Override
	public String[] presentation(int offset) {

		setPresSize();
		String[] res = new String[setResLength(offset)];
		
		setFullDirection();
		
		switch (direction){
			case "row":
				res = shapes[0].presentation(offset);
				for(int i = 1; i < shapes.length; i++) {
					res = mergeArraysInRow(res, shapes[i].presentation(offset));
				}
				break;
			case "column":
				Arrays.fill(res, " ".repeat(getWidth()));
				int length = mergeArraysInColumn(0, res, shapes[0].presentation(offset));
				for(int i = 1; i < shapes.length; i++) {
					length = mergeArraysInColumn(length + margin, res, shapes[i].presentation(offset));
				}
				break;
		}
		return res;
	}

	private int mergeArraysInColumn(int length, String[] res, String[] shapePresentation) {
		System.arraycopy(shapePresentation, 0, res, length, shapePresentation.length);
		return length + shapePresentation.length;
	}

	private static String[] mergeArraysInRow(String[] array1, String[] array2) {
		String result[] = new String[array1.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = array1[i] + array2[i];
		}
		return result;
	}
	
	private int setResLength(int offset) {
		int res = 0;
		switch (direction){
			case "row":
				res = shapes.length;
				break;
			case "column":
				for (Shape x : shapes) {
					res += x.getHeight();
				}
				res += margin * (shapes.length - 1);
				break;
		}
		return res;
	}
	
	private void setPresSize() {
		if (direction.equals("row")) {
			for (Shape x : shapes) {
				x.setHeight(getHeight());
			}
		}
		else if (direction.equals("column")){
			for (Shape x : shapes) {
				x.setWidth(getWidth());
			}
		}
	}
	
	private void setFullDirection() {
		for(int i = 0; i < shapes.length; i++) {
			if ( shapes[i] instanceof Canvas ) {
				((Canvas) shapes[i]).setDirection(direction);
			}
		}
	}
}
