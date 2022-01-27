package de.telekom.sea7.View;

import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Iterable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import de.telekom.sea7.Booking;
import de.telekom.sea7.BookingView;
import de.telekom.sea7.Bookings;
import de.telekom.sea7.BookingsView;
import de.telekom.sea7.GenericList;
import de.telekom.sea7.Model.BookingImpl;

public class BookingsViewImpl implements BookingsView {

	private  GenericList<Booking> bookingsimpl;

	public BookingsViewImpl(GenericList<Booking> bookingsimpl) {
		this.bookingsimpl = bookingsimpl;
	}

	@Override
	public void menu() {
		String input = "";
		Scanner scanner = new Scanner(System.in);
		while (!input.equals("exit")) {
			System.out.println(
					"Enter something (e.g add - create new transaction,showAll - lists all transactions, showOne - List one transaction, Download): ");
			input = scanner.next();

			switch (input) {
			case "add":
				add();
				break;
			case "showOne":
				showOne();
				break;
			case "showAll":
				showAll();
				break;
			case "Download":
				download();
				break;
			case "exit":
				scanner.close();
				System.out.println();
				System.out.println("Programm will be closed...");
				System.out.println();
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

	private void add() {
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
		System.out.println("BIC: ");
		String bic = scannerAdd.nextLine();
		System.out.println("Verwendungszweck: ");
		String verwendungszweck = scannerAdd.nextLine();
		
		Booking bookingimpl = new BookingImpl(betrag, empfaenger, iban, bic, verwendungszweck, datum);
		bookingsimpl.add(bookingimpl);
		// scannerAdd.close();
	}

	private void showAll() {

		for (Object o : bookingsimpl) {
			Booking tempTrans = (Booking) o;
			System.out.println(bookingsimpl.getIndex(tempTrans) + "-" + tempTrans.getEmpfaenger() + " - "
					+ tempTrans.getVerwendungszweck() + " - " + String.format("%.2f", tempTrans.getBetrag()) + "€");
		}

	}

	private void showOne() {
		Scanner scannershowOne = new Scanner(System.in);
		System.out.println("Wähle den gewünschten Datensatz aus: ");
		int index = scannershowOne.nextInt();
		scannershowOne.nextLine();
		Booking temp = bookingsimpl.getOneObject(index);

		BookingView bookingviewimpl = new BookingViewImpl(temp);
		bookingviewimpl.show();

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
}
