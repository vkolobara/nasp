package hr.vinko.nasp.lab1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		if (args.length != 1) {
			throw new IllegalArgumentException(
					"Only one argument required: path to file with numbers separated with spaces");
		}

		List<Integer> numbers = readFile(args[0]);
		AVLTree tree = new AVLTree();
		System.out.println(numbers);
		numbers.forEach(num -> tree.addElement(num));
	

		try (Scanner sc = new Scanner(System.in)) {
			while (true) {
				System.out.println();
				System.out.println(tree.prettyString());
				System.out.print("Commands: \nadd x (Adds element)\ndel x (Deletes element)\nexit\n>");
				String input = sc.nextLine();
				if (input.startsWith("add")) {
					tree.addElement(Integer.parseInt(input.split("\\s")[1]));
				} else if (input.startsWith("del")) {
					tree.deleteElement(Integer.parseInt(input.split("\\s")[1]));
				} else if (input.startsWith("exit")) {
					System.out.println("Goodbye!");
					break;
				}
			}
		}

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
