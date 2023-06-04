package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Product;

public class Programa {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		List<Product> list = new ArrayList<>();
		String path = "/home/rennan/Documentos/ESTUDOS/curso/_2_curso de java/_5_exercicios/in.txt";
		try(BufferedReader bd = new BufferedReader(new FileReader(path))){
			String line = bd.readLine();
			while(line!=null) {
				String[] linesp = line.split(",");
				Product product = new Product(linesp[0],Double.parseDouble(linesp[1]));
				list.add(product);
				line = bd.readLine();
			}
			
			double avg = list.stream()
					.map(p -> p.getPrice()) // uso o map para criar stream p com os preços do produto
					.reduce(0.0,(x,y)-> x+y) /list.size();	
			System.out.println("Avarege Price: "+ String.format("%.2f", avg));
			
			Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2); // comparador de string		
			List<String> names = list.stream()
					.filter(p -> p.getPrice() < avg)
					.map(p -> p.getName())
					.sorted(comp.reversed())
					.collect(Collectors.toList());
			names.forEach(System.out::println);
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}
}
/*
 * Fazer um programa para ler um conjunto de produtos a partir de um
arquivo em formato .csv (suponha que exista pelo menos um produto).
Em seguida mostrar o preço médio dos produtos. Depois, mostrar os
nomes, em ordem decrescente, dos produtos que possuem preço
inferior ao preço médio.
Veja exemplo na próxima página. */