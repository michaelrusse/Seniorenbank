package de.telekom.sea7;

import java.time.LocalDateTime;

public interface Booking {

	LocalDateTime getDatum();

	void setDatum(LocalDateTime datum);

	float getBetrag();

	void setBetrag(float betrag);

	String getEmpfaenger();

	void setEmpfaenger(String empfaenger);

	String getIban();

	void setIban(String iban);

	String getBic();

	void setBic(String bic);

	String getVerwendungszweck();

	void setVerwendungszweck(String verwendungszweck);

}