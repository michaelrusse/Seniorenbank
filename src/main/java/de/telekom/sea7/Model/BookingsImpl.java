package de.telekom.sea7.Model;
import java.time.*;

import java.util.ArrayList;
import java.util.Iterator;

import de.telekom.sea7.Booking;
import de.telekom.sea7.Bookings;


public class BookingsImpl implements Iterable, Bookings {

		private ArrayList bookings;
		
		public BookingsImpl() {
			bookings = new ArrayList();
		}
		
		@Override
		public void add(float betrag, String empfaenger, String iban, String bic, String verwendungszweck, LocalDateTime datum) {
			Booking bookingimpl = new BookingImpl(betrag, empfaenger, iban, bic, verwendungszweck, datum);
			bookings.add(bookingimpl);
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
		public Booking getBooking (int index ) {
			return (Booking) bookings.get(index);
		}
}

