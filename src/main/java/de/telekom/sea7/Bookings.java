package de.telekom.sea7;

import java.time.LocalDateTime;
import java.util.Iterator;

public interface Bookings extends Iterable {

	void add(float betrag, String empfaenger, String iban, String bic, String verwendungszweck, LocalDateTime datum);

	Iterator iterator();

	int getIndex(Booking horst);

	Booking getBooking(int index);

}