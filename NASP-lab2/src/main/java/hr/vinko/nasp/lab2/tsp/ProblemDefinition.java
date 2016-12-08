package hr.vinko.nasp.lab2.tsp;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProblemDefinition {
	
	public double[][] distances;
	public int size;
	
	public ProblemDefinition(String path) throws IOException {
		parseFile(path);
	}
	
	private void parseFile(String path) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(path));
		Iterator<String> it = lines.iterator();
		
		String row = "";
		
		while(!(row = it.next()).startsWith("NODE_COORD"));
		
		List<Point2D> points = new ArrayList<>();
		
		while(it.hasNext()) {
			row = it.next();
			if (row.equals("EOF")) break;
			row.trim();
			String[] split = row.split("\\s+");
			
			Point2D point = new Point2D.Double(Double.parseDouble(split[1]), Double.parseDouble(split[2]));
			points.add(point);
		}
		size = points.size();
		distances = new double[size][size];
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				distances[i][j] = distances[j][i] = distance(points.get(i), points.get(j));
			}
		}	
	}
	
	private double distance(Point2D point1, Point2D point2) {
		return Math.hypot(point1.getX()-point2.getX(), point1.getY()-point2.getY());
	}

}
