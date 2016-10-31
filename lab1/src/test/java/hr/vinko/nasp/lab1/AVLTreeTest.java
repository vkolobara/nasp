package hr.vinko.nasp.lab1;

import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AVLTreeTest {
	
	@Test
	public void insertion1a() {
		List<Integer> numbers = readFile(getClass().getClassLoader().getResource("test/insertion1a.txt").getPath());
		AVLTree tree = new AVLTree();
		numbers.forEach(num -> tree.addElement(num));
		numbers = readFile(getClass().getClassLoader().getResource("test/solution/insertion1a.txt").getPath());
		AVLTree sol = new AVLTree();
		numbers.forEach(num -> sol.addElement(num));

		assertEquals(tree, sol);
	}
	
	@Test
	public void insertion1b() {
		List<Integer> numbers = readFile(getClass().getClassLoader().getResource("test/insertion1b.txt").getPath());
		AVLTree tree = new AVLTree();
		numbers.forEach(num -> tree.addElement(num));
		numbers = readFile(getClass().getClassLoader().getResource("test/solution/insertion1b.txt").getPath());
		AVLTree sol = new AVLTree();
		numbers.forEach(num -> sol.addElement(num));

		assertEquals(tree, sol);
	}
	
	@Test
	public void insertion2a() {
		List<Integer> numbers = readFile(getClass().getClassLoader().getResource("test/insertion2a.txt").getPath());
		AVLTree tree = new AVLTree();
		numbers.forEach(num -> tree.addElement(num));
		numbers = readFile(getClass().getClassLoader().getResource("test/solution/insertion2a.txt").getPath());
		AVLTree sol = new AVLTree();
		numbers.forEach(num -> sol.addElement(num));

		assertEquals(tree, sol);
	}
	
	@Test
	public void insertion2b() {
		List<Integer> numbers = readFile(getClass().getClassLoader().getResource("test/insertion2b.txt").getPath());
		AVLTree tree = new AVLTree();
		numbers.forEach(num -> tree.addElement(num));
		numbers = readFile(getClass().getClassLoader().getResource("test/solution/insertion2b.txt").getPath());
		AVLTree sol = new AVLTree();
		numbers.forEach(num -> sol.addElement(num));

		assertEquals(tree, sol);
	}
	
	@Test
	public void insertion3a() {
		List<Integer> numbers = readFile(getClass().getClassLoader().getResource("test/insertion3a.txt").getPath());
		AVLTree tree = new AVLTree();
		numbers.forEach(num -> tree.addElement(num));
		numbers = readFile(getClass().getClassLoader().getResource("test/solution/insertion3a.txt").getPath());
		AVLTree sol = new AVLTree();
		numbers.forEach(num -> sol.addElement(num));

		assertEquals(tree, sol);
	}
	
	@Test
	public void insertion3b() {
		List<Integer> numbers = readFile(getClass().getClassLoader().getResource("test/insertion3b.txt").getPath());
		AVLTree tree = new AVLTree();
		numbers.forEach(num -> tree.addElement(num));
		numbers = readFile(getClass().getClassLoader().getResource("test/solution/insertion3b.txt").getPath());
		AVLTree sol = new AVLTree();
		numbers.forEach(num -> sol.addElement(num));

		assertEquals(tree, sol);
	}
	
	@Test
	public void deletion1() {
		List<Integer> numbers = readFile(getClass().getClassLoader().getResource("test/deletion1.txt").getPath());
		AVLTree tree = new AVLTree();
		numbers.forEach(num -> tree.addElement(num));
		numbers = readFile(getClass().getClassLoader().getResource("test/solution/deletion1.txt").getPath());
		AVLTree sol = new AVLTree();
		numbers.forEach(num -> sol.addElement(num));

		tree.deleteElement(1);
		
		assertEquals(tree, sol);
	}
	
	@Test
	public void deletion2() {
		List<Integer> numbers = readFile(getClass().getClassLoader().getResource("test/deletion2.txt").getPath());
		AVLTree tree = new AVLTree();
		numbers.forEach(num -> tree.addElement(num));
		numbers = readFile(getClass().getClassLoader().getResource("test/solution/deletion2.txt").getPath());
		AVLTree sol = new AVLTree();
		numbers.forEach(num -> sol.addElement(num));

		tree.deleteElement(1);
		
		assertEquals(tree, sol);
	}
	
	@Test
	public void deletion3() {
		List<Integer> numbers = readFile(getClass().getClassLoader().getResource("test/deletion3.txt").getPath());
		AVLTree tree = new AVLTree();
		numbers.forEach(num -> tree.addElement(num));
		numbers = readFile(getClass().getClassLoader().getResource("test/solution/deletion3.txt").getPath());
		AVLTree sol = new AVLTree();
		numbers.forEach(num -> sol.addElement(num));

		tree.deleteElement(1);

		assertEquals(tree, sol);
	}
	
		
	
	
	
	
	
	private static List<Integer> readFile(String path) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(path));
			List<Integer> numbers = new ArrayList<Integer>();
			for (String line : lines) {
				List<String> lineSplit = Arrays.asList(line.split("\\s+"));
				lineSplit.forEach(num -> numbers.add(Integer.parseInt(num)));
			}
			return numbers;
		} catch (Exception e) {
			throw new IllegalArgumentException("Couldn't read from file!");
		}
	}

}
