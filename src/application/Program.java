package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import entities.Candidate;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter file full path: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			Map<Candidate, Integer> candidates = new LinkedHashMap<>();
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				Integer votes = Integer.parseInt(fields[1]);
				Candidate candidate = new Candidate(name);
				// Check if the candidate is already in the map
                if (candidates.containsKey(candidate)) {
                    // If the candidate is already in the map, add the new votes to the existing count
                    int existingVotes = candidates.get(candidate);
                    candidates.put(candidate, existingVotes + votes);
                } else {
                    // If the candidate is not in the map, add them with the new votes
                    candidates.put(candidate, votes);
                }
				line = br.readLine();
			}
			
			for (Candidate key: candidates.keySet()) {
				System.out.println(key.getName() + ": " + candidates.get(key));
			}
			
		} catch (IOException e) {
			System.out.println("Error: " +  e.getMessage());
		}
		
		
		
		sc.close();
	}

}
