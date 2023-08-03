package desafio4.application;

import desafio4.entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo:");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Sale> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Sale(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2]
                        ,Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
                line = br.readLine();
            }
            System.out.println();
            Set<String> names = list.stream().map(s -> s.getSeller()).collect(Collectors.toSet());
            System.out.println("Total de vendas por vendedor:");
            names.forEach(seller -> {
                double sum = 0.0;
                sum = list.stream()
                        .filter(s -> s.getSeller().equalsIgnoreCase(seller))
                        .mapToDouble(s -> s.getTotal()).sum();
                System.out.printf("%s - %.2f\n",seller,sum);
            });

        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
