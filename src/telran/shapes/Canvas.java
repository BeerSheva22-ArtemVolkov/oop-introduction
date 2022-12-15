package telran.shapes;

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
		switch (direction){
			case "row":
				//shapes[0].setHeight(getHeight());
				res = shapes[0].presentation(offset);
				for(int i = 1; i < shapes.length; i++) {
					res = joinArraysInRow(res, shapes[i].presentation(offset));
				}
				break;
			case "column":
				int j = 0;
				for (Shape x : shapes) {
					for (int i = 0; i < x.presentation(offset).length; i++) {
						res[j] = x.presentation(offset)[i];
						j++;
					}
					if (!x.equals(shapes[shapes.length - 1])) {
						for (int i = 0; i < margin; i++) {
							res[j] = "";
							j++;
						}
					}
				}
				break;
		}
		return res;
	}

	private int setResLength(int offset) {
		int res = 0;
		switch (direction){
			case "row":
				res = shapes.length;
				break;
			case "column":
				for (Shape x : shapes) {
					res = res + x.presentation(offset).length;
				}
				res = res + margin * (shapes.length - 1);
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

	private static String[] joinArraysInRow(String[] array1, String[] array2) {
		String result[] = new String[array1.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = array1[i] + array2[i];
		}
		return result;
	}
	
}
