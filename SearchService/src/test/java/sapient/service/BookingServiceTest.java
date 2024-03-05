package sapient.service;

import com.sapient.dao.BookingsServiceDAO;
import com.sapient.model.*;
import com.sapient.service.BookingsService;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Data
public class BookingServiceTest {

    @InjectMocks
    BookingsService bookingsService;

    @Mock
    BookingsServiceDAO bookingsServiceDAO;

    @Test
    public void testGetTheatresByMovieAndShowTimings() {


        Booking t = new Booking();
        t.setBooking_id(6L);

        Mockito.when(bookingsServiceDAO.getAvailableSeat(Mockito.anyLong())).thenReturn(t);
        bookingsService.setBookingsServiceDAO(bookingsServiceDAO);
        Booking booking =  bookingsService.getAvailableSeat(6);
        Assertions.assertNotNull(booking);
        Assertions.assertNotNull(booking.getBooking_id());

    }
}
