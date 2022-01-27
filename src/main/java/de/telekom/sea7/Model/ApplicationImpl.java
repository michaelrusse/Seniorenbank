package de.telekom.sea7.Model;

import de.telekom.sea7.*;
import de.telekom.sea7.Model.*;
import de.telekom.sea7.View.BookingsViewImpl;




public class ApplicationImpl implements Application {
	
	public ApplicationImpl () {};
	
	
	
	
	
	public void run(String[] args) {
		GenericList<Booking> bookingsimpl = new GenericListImpl<Booking>();
		BookingsView bookingsviewimpl = new BookingsViewImpl(bookingsimpl);
		
		
		bookingsviewimpl.menu();
				
	}

}
