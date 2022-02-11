package de.telekom.sea7.Model;

import java.sql.Connection;
import java.time.*;

import java.util.ArrayList;
import java.util.Iterator;

import de.telekom.sea7.Booking;
import de.telekom.sea7.Bookings;
import de.telekom.sea7.IBAN;
import de.telekom.sea7.IBANs;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IBANsImpl implements IBANs {

	private Connection con;
	IBAN iban;

	public IBANsImpl(Connection con) {
		this.con = con;

	}

	@Override
	public IBAN add(IBAN ibans) throws SQLException {

		System.out.println("Start Buchung");
		PreparedStatement ps = con.prepareStatement("insert into IBAN (IBAN) values (?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, ibans.getIBAN());

		ps.executeUpdate();
		ps.getGeneratedKeys();
		ResultSet result = ps.getGeneratedKeys();
		int rid = result.getInt(2);
		ibans.setID(rid);
		return ibans;
	}

	@Override
	public IBAN get(int id) throws SQLException {
		PreparedStatement ps = con.prepareStatement("select ID,IBAN from IBAN where ID = ?");
		ps.setInt(1, id);
		ResultSet result = ps.executeQuery();

		int rid = result.getInt(1);
		String riban = result.getString(2);

		return new IBANImpl(rid, riban);
	}

	@Override
	public IBAN get(String iban) throws SQLException {
		PreparedStatement ps = con.prepareStatement("select ID,IBAN from IBAN where IBAN = ?");

		ps.setString(1, iban);
		ResultSet result = ps.executeQuery();
		if (result.next()) {
			int rid = result.getInt(1);
			String riban;
			riban = result.getString(2);
			return new IBANImpl(rid, riban);
		} else

		{
			 throw new SQLException();
		}
	}
}
//public IBAN get(String iban) throws SQLException