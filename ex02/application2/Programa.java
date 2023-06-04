package application2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Functionary;

public class Programa {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		List<Functionary> list = new ArrayList<>();
		String path = "/home/rennan/Documentos/ESTUDOS/curso/_2_curso de java/_5_exercicios/in2.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while(line!=null) {
				String[] linesp = line.split(",");
				list.add(new Functionary(linesp[0],linesp[1],Double.parseDouble(linesp[2])));
				line = br.readLine();
			}
			System.out.println("Enter Salary: "); Double salary = sc.nextDouble();
			System.out.println("Email of people whose salary is more than "+ String.format("%.2f", salary)+ ":");
			List<String> emails = list.stream()
					.filter(s -> s.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
			emails.forEach(System.out::println);
			Double sum = list.stream()
					.filter(s -> s.getName().toUpperCase().charAt(0) == 'M')
					.map(s -> s.getSalary())
					.reduce(0.0, (x,y)-> x+y);
			System.out.println("Sum of salary of people whose name starts with 'M':"+ sum);
		sc.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
