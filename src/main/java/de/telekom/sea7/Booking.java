package de.telekom.sea7;

import java.time.LocalDateTime;

public interface Booking {

	LocalDateTime getDatum();

	void setDatum(LocalDateTime datum);

	float getBetrag();

	void setBetrag(float betrag);

	String getEmpfaenger();

	void setEmpfaenger(String empfaenger);

//	String getIban();
	
	IBAN getIban();

//	void setIban(String iban);
	
	void setIban(IBAN iban);

	String getBic();

	void setBic(String bic);

	String getVerwendungszweck();

	void setVerwendungszweck(String verwendungszweck);

//	int getIban_id();

}