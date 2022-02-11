package de.telekom.sea7;

import java.sql.*;
import java.util.Scanner;

public class DadabaseConnection {

	public static void main(String[] args) {

		// Datenbankadresse und Anmeldedaten
		String url = "jdbc:mariadb://localhost:3306/myfirstdb";
		String user = "admin";
		String pass = "sea5";

		try (Connection con = DriverManager.getConnection(url, user, pass); Statement stm = con.createStatement();) {
			// Verbindung aufbauen

			System.out.println("Verbindung erfolgreich hergestellt\n");

			ResultSet rs = stm.executeQuery("select * from Mafia");

			System.out.println(
					"+------------------------------+------------+------------+----------------------------------------+----------------------------------+");
			System.out.print(String.format("|%-30s|", "Empfaenger"));
			System.out.print(String.format("%-12s|", "IBAN"));
			System.out.print(String.format("%-12s|", "BIC"));
			System.out.print(String.format("%-40s|", "Verwendungszweck"));
			System.out.print(String.format("%-12s|", "Betrag"));
			System.out.println(String.format("%-21s|", "Datum"));
			System.out.println(
					"+------------------------------+------------+------------+----------------------------------------+----------------------------------+");

			while (rs.next()) {
				System.out.print(String.format("|%-30s|", rs.getString(1)));
				System.out.print(String.format("%-12s|", rs.getString(2)));
				System.out.print(String.format("%-12s|", rs.getString(3)));
				System.out.print(String.format("%-40s|", rs.getString(4)));
				System.out.print(String.format("%11.2f€|", rs.getFloat(5)));
				System.out.println(String.format("%-20s|", rs.getTimestamp(6)));

			}
			System.out.println(
					"+------------------------------+------------+------------+----------------------------------------+----------------------------------+");

			PreparedStatement ps = con.prepareStatement(
					"select Empfaenger, IBAN, BIC, Verwendungszweck, Betrag, Datum from Mafia where Empfaenger = ?");
			System.out.println("\nWen willst Du sehen ?");
			Scanner scanner = new Scanner(System.in);
			ps.setString(1, scanner.nextLine());

			ResultSet rs1 = ps.executeQuery();
			scanner.close();

			System.out.println(
					"+------------------------------+------------+------------+----------------------------------------+----------------------------------+");
			System.out.print(String.format("|%-30s|", "Empfaenger"));
			System.out.print(String.format("%-12s|", "IBAN"));
			System.out.print(String.format("%-12s|", "BIC"));
			System.out.print(String.format("%-40s|", "Verwendungszweck"));
			System.out.print(String.format("%-12s|", "Betrag"));
			System.out.println(String.format("%-21s|", "Datum"));
			System.out.println(
					"+------------------------------+------------+------------+----------------------------------------+----------------------------------+");

			while (rs1.next()) {
				System.out.print(String.format("|%-30s|", rs1.getString(1)));
				System.out.print(String.format("%-12s|", rs1.getString(2)));
				System.out.print(String.format("%-12s|", rs1.getString(3)));
				System.out.print(String.format("%-40s|", rs1.getString(4)));
				System.out.print(String.format("%11.2f€|", rs1.getFloat(5)));
				System.out.println(String.format("%-20s|", rs1.getTimestamp(6)));

			}
			
			System.out.println(
					"+------------------------------+------------+------------+----------------------------------------+----------------------------------+");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
