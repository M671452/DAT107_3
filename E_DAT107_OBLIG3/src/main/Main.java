package main;

import java.time.LocalDate;
import java.util.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AnsattDAO ansattDAO = new AnsattDAO();
		AvdelingDAO avdelingDAO = new AvdelingDAO();

		try {
			while (true) {
				System.out.println("\nMenyvalg:");
				System.out.println("1. Søke etter ansatt på ansatt-id");
				System.out.println("2. Søke etter ansatt på brukernavn");
				System.out.println("3. Utlisting av alle ansatte");
				System.out.println("4. Oppdatere en ansatt sin stilling og/eller lønn");
				System.out.println("5. Legge inn en ny ansatt");
				System.out.println("6. Avslutte");
				System.out.print("Velg et alternativ: ");

				int valg = Integer.parseInt(scanner.nextLine());

				switch (valg) {
				case 1: //// Implementasjon for søk etter ansatt på ansatt-id
					System.out.print("Skriv inn ansatt-id: ");
					long id = Long.parseLong(scanner.nextLine());
					Ansatt ansatt = ansattDAO.finnAnsattMedId(id);
					if (ansatt != null) {
						ansatt.skrivUt();
					} else {
						System.out.println("Ingen ansatt funnet.");
					}
					break;
				case 2: //// Implementasjon for søk etter ansatt på brukernavn
					System.out.print("Skriv inn brukernavn: ");
					String brukernavn = scanner.nextLine();
					ansatt = ansattDAO.finnAnsattMedBrukernavn(brukernavn);
					if (ansatt != null) {
						ansatt.skrivUt();
					} else {
						System.out.println("Ingen ansatt funnet med brukernavn " + brukernavn);
					}
					break;
				case 3: //// Implementasjon for utlisting av alle ansatte
					List<Ansatt> ansatte = ansattDAO.finnAlleAnsatte();
					if (ansatte.isEmpty()) {
						System.out.println("Ingen ansatte funnet.");
					} else {
						for (Ansatt a : ansatte) {
							a.skrivUt();
						}
					}
					break;
				case 4: //// Implementasjon for oppdatering av ansatt sin stilling og/eller lønn
					System.out.print("Skriv inn ansatt-id for oppdatering: ");
					id = Long.parseLong(scanner.nextLine());
					System.out.print("Skriv inn ny stilling: ");
					String nyStilling = scanner.nextLine();
					System.out.print("Skriv inn ny lønn: ");
					int nyLonn = Integer.parseInt(scanner.nextLine());
					ansattDAO.oppdaterAnsatt(id, nyStilling, nyLonn);
					System.out.println("Ansatt oppdatert.");
					break;
				case 5: // Implementasjon for å legge inn en ny ansatt
					System.out.print("Skriv inn fornavn: ");
					String fornavn = scanner.nextLine();
					System.out.print("Skriv inn etternavn: ");
					String etternavn = scanner.nextLine();
					System.out.print("Skriv inn stilling: ");
					String stilling = scanner.nextLine();
					System.out.print("Skriv inn lønn: ");
					int lonn = Integer.parseInt(scanner.nextLine());
					System.out.print("Skriv inn avdelings-ID: ");
					int avdelingId = Integer.parseInt(scanner.nextLine());
					Ansatt nyAnsatt = new Ansatt();
					nyAnsatt.setFornavn(fornavn);
					nyAnsatt.setEtternavn(etternavn);
					nyAnsatt.setStilling(stilling);
					nyAnsatt.setLonn(lonn);
					nyAnsatt.setAnsattDato(LocalDate.now()); // Setter ansattdato til dagens dato
					Avdeling avdeling = avdelingDAO.finnAvdelingMedId(avdelingId);
					if (avdeling != null) {
						nyAnsatt.setAvdeling(avdeling);
						ansattDAO.leggInnNyAnsatt(nyAnsatt);
						System.out.println("Ny ansatt lagt til.");
					} else {
						System.out.println(
								"Kunne ikke legge til ansatt. Avdeling med ID " + avdelingId + " eksisterer ikke.");
					}
					break;
				case 6: // Avslutte programmet
					System.out.println("Avslutter programmet...");
					scanner.close();
					System.exit(0);
					break;
				case 7:
					System.out.print("Skriv inn avdelingsnavn: ");
					String avdNavn = scanner.nextLine();
					System.out.print("Skriv inn IDen til sjefen: ");
					int sjefId = Integer.parseInt(scanner.nextLine());
					Avdeling nyAvdeling = new Avdeling();
					nyAvdeling.setAvdNavn(avdNavn);
					// Søk etter sjef med sjef-ID
					Ansatt sjef = ansattDAO.finnAnsattMedId(sjefId);
					if (sjef != null) {
						nyAvdeling.setSjef(sjef);
						avdelingDAO.leggInnNyAvdeling(nyAvdeling);
						System.out.println("Ny avdeling lagt til.");
					} else {
						System.out
								.println("Kunne ikke legge til avdeling. Sjef med ID " + sjefId + " eksisterer ikke.");
					}
					break;
				default:
					System.out.println("Ugyldig valg, prøv igjen.");
				}
			}
		} finally {
			scanner.close();
		}
	}
}
