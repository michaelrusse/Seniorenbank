package de.telekom.sea7.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Iterable;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import de.telekom.sea7.Booking;
import de.telekom.sea7.BookingView;
import de.telekom.sea7.Bookings;
import de.telekom.sea7.BookingsView;
import de.telekom.sea7.GenericList;
import de.telekom.sea7.IBAN;
import de.telekom.sea7.IBANs;
import de.telekom.sea7.Model.BookingImpl;
import de.telekom.sea7.Model.GenericListImpl;
import de.telekom.sea7.Model.IBANImpl;
import de.telekom.sea7.Model.IBANsImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingsViewImpl implements BookingsView {

	private Bookings bookingsimpl;
	private Connection con;
	private IBANs ibans;

	public BookingsViewImpl(Bookings bookingsimpl, Connection con , IBANs ibans) {
		this.bookingsimpl = bookingsimpl;
		this.con = con;
		this.ibans = ibans;
	}

	@Override
	public void menu() {
		String input = "";
		Scanner scanner = new Scanner(System.in);
		while (!input.equals("exit")) {
			System.out.println(
					"\nTransaktionsmenu:\n \n 1 - create new transaction \n 2 - lists all transactions \n 3 - List one transaction  \n 4 - Download \n 5 - Import \n exit - Programm beenden");
			input = scanner.next();

			switch (input) {
			case "1":
				try {
					add();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "2":
				try {
					showAll();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "3":
				try {
					showOne();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case "4":
				download();
				break;
/*			case "5":
				csv1reader();
				break;*/
			case "exit":
				scanner.close();
				System.out.println();
				System.out.println("Programm will be closed...");
				System.out.println();
				break;
			default:
				System.out.println(
						"Command unknown. Please enter an existing command. With command 'help' you can list all existing commands.");
				System.out.println();
				break;

			}
		}
	}

	/*
	 * private void showOne() { Scanner scannerAdd = new Scanner(System.in);
	 * System.out.println("Wähle eine Transaktion aus: "); int position =
	 * scannerAdd.nextInt(); scannerAdd.nextLine(); ArrayList allTrans =
	 * bookingsimpl.get(); TransactionView transabookingsimplctionView = new
	 * TransactionView(this, (Transaction) allTrans.get(position));
	 * transactionView.menu(); }
	 */

	private void add() throws SQLException {
		LocalDateTime datum = LocalDateTime.now();
		Scanner scannerAdd = new Scanner(System.in);
		System.out.println("Wie viel soll überwiesen werden: ");

		while (!scannerAdd.hasNextFloat()) {
			System.out.println("Es wurde kein gültiger Betrag eingegeben. Bitte um neue Eingabe: ");
			scannerAdd.next();
		}
		float betrag = scannerAdd.nextFloat();

		scannerAdd.nextLine();
		System.out.println("Empfänger: ");
		String empfaenger = scannerAdd.nextLine();
		System.out.println("IBAN: ");
		String iban = scannerAdd.nextLine();
		IBAN ibanobject1;
		try {
		ibanobject1 = ibans.get(iban);
		System.out.println(ibanobject1.getID());}
		catch (SQLException e) {
			IBANImpl riban = new IBANImpl(0, iban);
			ibanobject1 = ibans.add(riban);
			
		}

		
		System.out.println("BIC: ");
		String bic = scannerAdd.nextLine();
		System.out.println("Verwendungszweck: ");
		String verwendungszweck = scannerAdd.nextLine();

		Booking bookingimpl = new BookingImpl(betrag, empfaenger, ibanobject1, bic, verwendungszweck, datum);
		bookingsimpl.add(bookingimpl);
		// scannerAdd.close();
	}

	private void showAll() throws SQLException {

		bookingsimpl.show();
		

		/*
		 * for (Object o : bookingsimpl) { Booking tempTrans = (Booking) o;
		 * System.out.println(bookingsimpl.getIndex(tempTrans) + "-" +
		 * tempTrans.getEmpfaenger() + " - " + tempTrans.getVerwendungszweck() + " - " +
		 * String.format("%.2f", tempTrans.getBetrag()) + "€"); }
		 */

	}

	private void showOne()throws SQLException {

		PreparedStatement ps = con.prepareStatement(
				"select ID,Empfaenger,IBAN,BIC,Verwendungszweck, Betrag, Datum from showall where ID = ?");
		System.out.println("Wähle den gewünschten Datensatz aus:  ");
		Scanner scannerselect = new Scanner(System.in);

		ps.setString(1, scannerselect.nextLine());
		ResultSet rs = ps.executeQuery();

		System.out.println(
				"+----------+------------------------------+------------+------------+----------------------------------------+----------------------------------+");
		System.out.print(String.format("|%-10s|", "ID"));
		System.out.print(String.format("%-30s|", "Empfaenger"));
		System.out.print(String.format("%-12s|", "IBAN"));
		System.out.print(String.format("%-12s|", "BIC"));
		System.out.print(String.format("%-40s|", "Verwendungszweck"));
		System.out.print(String.format("%-12s|", "Betrag"));
		System.out.println(String.format("%-21s|", "Datum"));
		System.out.println(
				"+----------+------------------------------+------------+------------+----------------------------------------+----------------------------------+");

		while (rs.next()) {

			System.out.print(String.format("|%-10s|", rs.getInt(1)));
			System.out.print(String.format("%-30s|", rs.getString(2)));
			System.out.print(String.format("%-12s|", rs.getString(3)));
			System.out.print(String.format("%-12s|", rs.getString(4)));
			System.out.print(String.format("%-40s|", rs.getString(5)));
			System.out.print(String.format("%11.2f€|", rs.getFloat(6)));
			System.out.println(String.format("%-20s|", rs.getTimestamp(7)));

		}
		System.out.println(
				"+----------+------------------------------+------------+------------+----------------------------------------+----------------------------------+");

		/*
		 * Scanner scannershowOne = new Scanner(System.in);
		 * System.out.println("Wähle den gewünschten Datensatz aus: "); int index =
		 * scannershowOne.nextInt(); scannershowOne.nextLine(); Booking temp =
		 * bookingsimpl.getOneObject(index);
		 
		BookingView bookingviewimpl = new BookingViewImpl(temp);
		bookingviewimpl.menu();*/
		

	}

	private void download() {
		try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {

			StringBuilder sb = new StringBuilder();

			sb.append("Betrag");
			sb.append(',');
			sb.append("Empfaenger");
			sb.append(',');
			sb.append("IBAN");
			sb.append(',');
			sb.append("BIC");
			sb.append(',');
			sb.append("Verwendungszeck");
			sb.append(',');
			sb.append("Datum");
			sb.append('\n');
// 			writer.write(sb.toString()); - alte Variante

			for (Object o : bookingsimpl) {
				Booking tempTrans = (Booking) o;

				sb.append(tempTrans.getBetrag());
				sb.append(',');
				sb.append(tempTrans.getEmpfaenger());
				sb.append(',');
				sb.append(tempTrans.getIban());
				sb.append(',');
				sb.append(tempTrans.getBic());
				sb.append(',');
				sb.append(tempTrans.getVerwendungszweck());
				sb.append(',');
				sb.append(tempTrans.getDatum());
				sb.append('\n');

//				writer.write(sb.toString()); - alte Variante

				System.out.println("done!");

			}
			writer.write(sb.toString());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

/*	private void csv1reader() {
// Einlesen des Files und splitten

		FileReader myFile = null;
		BufferedReader buff = null;
		final List<String> lines = new ArrayList<String>();
		try {
			myFile = new FileReader("test.csv");
			buff = new BufferedReader(myFile);
			String line = "";
			String trenner = ",";
			buff.readLine();
			while ((line = buff.readLine()) != null) {
				// System.out.println(line); // kontrolle was eingelesen
				String[] ausgelesenerWert = line.split(trenner);
				float betrag = Float.parseFloat(ausgelesenerWert[0]);
				String empfaenger = ausgelesenerWert[1];
				String iban = ausgelesenerWert[2];
				String bic = ausgelesenerWert[3];
				String verwendungszweck = ausgelesenerWert[4];
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime datum = LocalDateTime.parse(ausgelesenerWert[5]);
				Booking bookingimpl = new BookingImpl(betrag, empfaenger, iban, bic, verwendungszweck, datum);
				bookingsimpl.add(bookingimpl);
			}
		} catch (IOException e) {
			System.err.println("Error2 :" + e);
		} finally {
			try {
				buff.close();
				myFile.close();
			} catch (IOException e) {
				System.err.println("Error2 :" + e);
			}
		}

	}*/
}

/*
 * private void csv1reader() {
 * 
 * // Einlesen des Files und splitten. Erstellung von 2 neuen Objektinstanzen //
 * der Klasse Filereader und BufferedReader. Setzen der Instanzen auf NULL.
 * //Erstelung einer finalen Liste als Arraylist. Frage: Warum final ?
 * 
 * FileReader myFile = null; BufferedReader buff = null; final List<String>
 * lines = new ArrayList<String>();
 * 
 * // im try-catch: Zuweisung test.csv, Anlegen einer neuen String-Instanz mit
 * dem Namen "line". //Anlegen der Variablen buff -> Erhält zeilenweise den
 * Inhalt von myFile über den BufferedReader. //Anlegen der Stringvar line
 * //buff.readline bewirkt einen Skip der ersten Zeile der CSV try { myFile =
 * new FileReader("test.csv"); buff = new BufferedReader(myFile); String line;
 * buff.readLine(); //Schleife wird so lange durchlaufen bis der eigelesene Wert
 * null ist. //Danach wird die jeweilige Zeile in das Array eingelesen
 * 
 * 
 * while ((line = buff.readLine()) != null) { // System.out.println(line); //
 * kontrolle was eingelesen
 * 
 * lines.add(line);
 * 
 * //Exception-catch IO Exception mit Ausgabe Fehlermeldung // finally - Bereich
 * mit Schliessung der "Arbeitsdateien im //try-catch mit Fehlermeldung } }
 * catch (IOException e) { System.err.println("Error2 :" + e); } finally { try {
 * buff.close(); myFile.close(); } catch (IOException e) {
 * System.err.println("Error2 :" + e); } }
 * 
 * 
 * final String[][] valuesArray = new String[lines.size()][]; int cnt = 0; for
 * (final String line : lines) { valuesArray[cnt++] = line.split(","); }
 * 
 * 
 * 
 * // Ausgabe des Array for (String[] arr : valuesArray) {
 * 
 * float betrag = Float.parseFloat(arr[0]) ; String empfaenger = arr [1]; String
 * iban = arr [2]; String bic = arr [3]; String verwendungszweck = arr [4];
 * DateTimeFormatter formatter =
 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); LocalDateTime datum =
 * LocalDateTime.parse(arr[5]);
 * 
 * Booking bookingimpl = new BookingImpl(betrag, empfaenger, iban, bic,
 * verwendungszweck, datum); bookingsimpl.add(bookingimpl); } }
 */
