package de.telekom.sea7;

import java.sql.SQLException;

public interface IBANs {

	void add(IBAN ibans) throws SQLException;

	IBAN get(int id) throws SQLException;

	IBAN get(String iban) throws SQLException;

}