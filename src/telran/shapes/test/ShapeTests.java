package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import telran.shapes.*;

public class ShapeTests {
	
	@Disabled
	@Test
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(10,5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
	}
	
	@Disabled
	@Test
	void squareTest() {
		Square square = new Square(5);
		Square square2 = new Square(22);
		displayStrings(square.presentation(20));
		square.setHeight(3);
		displayStrings(square.presentation(20));
		square.setWidth(11);
		displayStrings(square.presentation(20));
		System.out.println(square.getWidth());
		System.out.println(square2.getHeight());
	}
	
	@Disabled
	@Test
	void squareTriangleTest() {
		SquareLeftTriangle lSquare = new SquareLeftTriangle(5);
		SquareRightTriangle rSquare = new SquareRightTriangle(5);
		displayStrings(lSquare.presentation(10));
		displayStrings(rSquare.presentation(15));
		lSquare.setHeight(6);
		rSquare.setWidth(7);
		displayStrings(lSquare.presentation(20));
		displayStrings(rSquare.presentation(25));
		lSquare.setHeight(3);
		rSquare.setWidth(11);
		displayStrings(lSquare.presentation(30));
		displayStrings(rSquare.presentation(35));
	}
	
	@Disabled
	@Test
	void shapesTest1() {
		
		SquareLeftTriangle lSquare = new SquareLeftTriangle(6);
		SquareRightTriangle rSquare = new SquareRightTriangle(5);
		Square square = new Square(5);
		Rectangle rectangle = new Rectangle(15,3);
		Rectangle rectangle2 = new Rectangle(3,15);
		
		Shape[] shapes = {lSquare, rSquare, square, rectangle, rectangle2};
		Canvas canva = new Canvas(10, 10, shapes);
		canva.setDirection("column");
		//this.canva.setDirection("column");
		canva.setMargin(1);
		displayStrings(canva.presentation(5));
	}
	
	@Disabled
	@Test
	void shapesTest2() {
		
		SquareLeftTriangle lSquare = new SquareLeftTriangle(6);
		SquareRightTriangle rSquare = new SquareRightTriangle(5);
		Square square = new Square(5);
		Rectangle rectangle = new Rectangle(15,3);
		Rectangle rectangle2 = new Rectangle(3,15);
		
		Shape[] shapes = {lSquare, rSquare, square, rectangle, rectangle2};
		Canvas canva = new Canvas(10, 10, shapes);
		canva.setDirection("row");
		displayStrings(canva.presentation(5));
	}
	
	
	@Test
	void canvasTest() {
		SquareLeftTriangle lSquare = new SquareLeftTriangle(6);
		SquareRightTriangle rSquare = new SquareRightTriangle(5);
		Square square = new Square(5);
		Rectangle rect1 = new Rectangle(15,3);
		Rectangle rect2 = new Rectangle(3,15);
		
		Shape[] shapes1 = {rect1, square, rSquare, lSquare};
		Shape[] shapes2 = {lSquare, rect2, square};
		
		Canvas canvas1 = new Canvas(40, 10, shapes1);
		Canvas canvas2 = new Canvas(10, 10, shapes2);
		Canvas canvas3 = new Canvas(10, 10, new Shape[] {canvas1, canvas2});
		
		canvas1.setMargin(10);
		canvas1.setDirection("column");
		canvas2.setMargin(10);
		canvas2.setDirection("column");
		canvas3.setDirection("row");
		displayStrings(canvas3.presentation(5));
	}
	
	private void displayStrings(String strings[]) {
		for (String str: strings) {
			System.out.println(str);
		}
	}
}
