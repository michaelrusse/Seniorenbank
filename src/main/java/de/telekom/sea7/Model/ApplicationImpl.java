package de.telekom.sea7.Model;

import de.telekom.sea7.*;
import de.telekom.sea7.Model.*;
import de.telekom.sea7.View.BookingsViewImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ApplicationImpl implements Application {
	public Connection con;
	public IBANs ibans;

	public ApplicationImpl() {
	};

	public void run(String[] args) {

//		GenericList<Booking> bookingsimpl = new GenericListImpl<Booking>();

		try {
			con = getConnection();
			ibans = initIBAN ();
			Bookings bookingsimpl = new BookingsImpl(con);
			BookingsView bookingsviewimpl = new BookingsViewImpl(bookingsimpl,con, ibans);
			bookingsviewimpl.menu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Connection getConnection() throws SQLException {

		// Datenbankadresse und Anmeldedaten
		String url = "jdbc:mariadb://localhost:3306/myfirstdb";
		String user = "admin";
		String pass = "sea5";

		// Verbindung aufbauen
		Connection con = DriverManager.getConnection(url, user, pass);
		System.out.println("Verbindung erfolgreich hergestellt");
		return con;

	}
	
	public IBANs initIBAN ()throws SQLException {
		
		return new IBANsImpl(con);
	}
 
}
