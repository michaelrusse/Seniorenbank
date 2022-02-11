package de.telekom.sea7;

import java.sql.SQLException;

public interface IBANs {

	IBAN add(IBAN ibans) throws SQLException;

	IBAN get(int id) throws SQLException;

	IBAN get(String iban) throws SQLException;

}