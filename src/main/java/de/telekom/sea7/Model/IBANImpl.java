package de.telekom.sea7.Model;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.telekom.sea7.IBAN;

public class IBANImpl implements IBAN {
	private String iban;
	private int id;
//	private int BIC_ID;
	

	public IBANImpl(String iban) {
		this.iban = iban;
		
	}
	
	public IBANImpl( int id , String iban) {
		this.iban = iban;
		this.id = id;
		
	}

	
	@Override
	public int getID() {
		return id;
	}

	
	@Override
	public String getIBAN() {
		return iban;
	}

	@Override
	public void setIBAN(String iBAN) {
		iban = iBAN;
	}
	
	@Override
	public void setID(int id) {
		this.id = id;
	}
}
