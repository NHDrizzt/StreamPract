import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entities.Product;

public class MainProgram {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Product> list = new ArrayList<Product>();
		System.out.println("Print the path: ");
		String path = "C:\\Repositorios\\Source.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			
			while(line!=null) {
				String[] str = line.split(",");
				list.add(new Product(str[0],Double.parseDouble(str[1]),Integer.parseInt(str[2])));	
				line = br.readLine();

			}
			double avg = list.stream().map(p -> p.getValor()).reduce(0.0, (x,y) -> x+y) / list.size(); 
			System.out.println("Avarege price: " + avg);
			
			Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> cons = list.stream()
					.filter(p -> p.getValor() < avg)
					.map(p -> p.getNome()).sorted(comp.reversed())
					.collect(Collectors.toList());
			cons.forEach(p -> System.out.println(p));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}
