package de.telekom.sea7;

import java.time.*;
import de.telekom.sea7.Model.ApplicationImpl;
import de.telekom.sea7.Model.*;
import de.telekom.sea7.View.*;
import de.telekom.sea7.View.BookingViewImpl;

public class Starter {

	public static void main(String[] args) {

		Application application = new ApplicationImpl();
		application.run(args);
		LocalDateTime datum = LocalDateTime.now();
		//BookingViewImpl bookingviewimpl = new BookingViewImpl(buchung);
		//bookingviewimpl.menu();
	}

}
