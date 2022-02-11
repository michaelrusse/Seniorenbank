package de.telekom.sea7.Model;

import java.sql.Connection;
import java.time.*;

import java.util.ArrayList;
import java.util.Iterator;

import de.telekom.sea7.Booking;
import de.telekom.sea7.Bookings;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookingsImpl implements Iterable, Bookings {
	private Connection con;

	public BookingsImpl(Connection con) {
		this.con = con;
	}

	@Override
	public void add(Booking bookingimpl) throws SQLException {

		System.out.println("Start Buchung");
		PreparedStatement ps = con.prepareStatement(
				"insert into Zahlungen (Empfaenger,Verwendungszweck, Betrag,IBAN_ID) values (?,?,?,?)");
		
		ps.setString(1, bookingimpl.getEmpfaenger());
		ps.setInt(4, bookingimpl.getIban().getID());
		//ps.setString(3, bookingimpl.getBic());
		ps.setString(2, bookingimpl.getVerwendungszweck());
		ps.setFloat(3, bookingimpl.getBetrag());
		

		ps.executeUpdate();

	}

	public void show () throws SQLException {
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("select * from showall");
		
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

	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return bookings.iterator();
	}

	@Override
	public int getIndex(Booking horst) {
		return this.bookings.indexOf(horst);

	}

	@Override
	public Booking getBooking(int index) {
		return (Booking) bookings.get(index);
	}
}
