package de.telekom.sea7.View;

import de.telekom.sea7.Model.*;
import de.telekom.sea7.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BookingViewImpl implements BookingView {

	private Booking bookingimpl; // macht Impl -unsichtbar ???

	public BookingViewImpl(Booking bookingimpl) {
		this.bookingimpl = bookingimpl;
	}

	@Override
	public void menu() {
		String input = "";
		Scanner scannerOneTrans = new Scanner(System.in);
		while (!input.equals("back")) {
			System.out.println("Enter something: ");
			input = scannerOneTrans.next();

			switch (input) {
			case "Newbooking":
				newBooking();
				break;
				
			case "show":
				show();
				break;
			case "back": // scannerOneTrans.close();
				break;
			default:
				System.out.println(
						"Command unknown. Please enter an existing command. With command 'help' you can list all existing commands.");
				System.out.println();
				break;
			}
		}
	}

	@Override
	public void show() {
		System.out.println("Empfänger: " + bookingimpl.getEmpfaenger());
		System.out.println("IBAN: " + bookingimpl.getIban());
		System.out.println("BIC: " + bookingimpl.getBic());
		System.out.println("Verwendungszweck: " + bookingimpl.getVerwendungszweck());
		System.out.println("Betrag: " + String.format("%.2f", bookingimpl.getBetrag()) + " €");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		String formattedDateTime = bookingimpl.getDatum().format(formatter);
		System.out.println("Datum: " + formattedDateTime);
	}

	@Override
	public void newBooking() {
		Scanner transferscanner = new Scanner(System.in);
		System.out.println("Bitte geben Sie den Empfänger ein: ");
		String einEmpfaenger = transferscanner.nextLine();

		System.out.println("Bitte geben Sie die IBAN ein: ");
		String einIBAN = transferscanner.nextLine();

		System.out.println("Bitte geben Sie die Bic ein: ");
		String einBic = transferscanner.nextLine();

		System.out.println("Bitte geben Sie den Verwendungszweck ein: ");
		String einVerwendungszweck = transferscanner.nextLine();

		System.out.println("Bitte geben Sie den Betrag ein: ");
		float einBetrag = Float.parseFloat(transferscanner.nextLine());

		Booking buchung = new BookingImpl(einBetrag, einEmpfaenger, einIBAN, einBic, einVerwendungszweck,
				LocalDateTime.now());

	}
}
