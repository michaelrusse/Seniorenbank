package de.telekom.sea7.Model;

import java.time.LocalDateTime;

import de.telekom.sea7.Booking;

public class BookingImpl implements Booking {
	
	private float betrag;
	private String empfaenger;
	private String iban;
	private String bic;
	private String verwendungszweck;
	private LocalDateTime datum;
	
	public BookingImpl( float betrag, String empfaenger, String iban, String bic, String verwendungszweck, LocalDateTime datum)  {
		this.betrag = betrag;
		this.empfaenger = empfaenger;
		this.iban = iban;
		this.bic = bic;
		this.verwendungszweck = verwendungszweck;
		this.datum = datum;
	}

	@Override
	public LocalDateTime getDatum() {
		return datum;
	}

	@Override
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

	@Override
	public float getBetrag() {
		return betrag;
	}

	@Override
	public void setBetrag(float betrag) {
		this.betrag = betrag;
	}

	@Override
	public String getEmpfaenger() {
		return empfaenger;
	}

	@Override
	public void setEmpfaenger(String empfaenger) {
		this.empfaenger = empfaenger;
	}

	@Override
	public String getIban() {
		return iban;
	}

	@Override
	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public String getBic() {
		return bic;
	}

	@Override
	public void setBic(String bic) {
		this.bic = bic;
	}

	@Override
	public String getVerwendungszweck() {
		return verwendungszweck;
	}

	@Override
	public void setVerwendungszweck(String verwendungszweck) {
		this.verwendungszweck = verwendungszweck;
	}

}


