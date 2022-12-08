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
	
	private void displayStrings(String strings[]) {
		for (String str: strings) {
			System.out.println(str);
		}
	}
}
