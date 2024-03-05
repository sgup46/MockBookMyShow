package sapient.service;

import com.sapient.dao.MovieServiceDAO;
import com.sapient.dao.ShowServiceDAO;
import com.sapient.dao.TheaterServiceDAO;
import com.sapient.model.*;
import com.sapient.service.ShowService;
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
public class ShowServiceTest {

    @InjectMocks
    ShowService showService;
    @Mock
    ShowServiceDAO showServiceDAO;
    @Mock
    TheaterServiceDAO theaterServiceDAO;
    @Mock
    MovieServiceDAO movieServiceDAO;

    @Test
    public void testGetTheatresByMovieAndShowTimings() {

        Theater t = new Theater();
        t.setTheater_id(6L);

        Movie m = new Movie();
        m.setMovie_id(5L);

        Show s = new Show();
        s.setTheMovie(m);
        s.setTheTheater(t);
        s.setShow_Id(1L);

        Mockito.when(theaterServiceDAO.findOne(Mockito.anyLong())).thenReturn(t);
        Mockito.when(movieServiceDAO.findOne(Mockito.anyLong())).thenReturn(m);
        Mockito.when(showServiceDAO.save(Mockito.any(Show.class))).thenReturn(s);
        showService.setTheaterServiceDAO(theaterServiceDAO);
        showService.setShowServiceDAO(showServiceDAO);
        showService.setMovieServiceDAO(movieServiceDAO);
        Show show =  showService.createShow(6L, 5L, "showTime");
        Assertions.assertNotNull(show);
        Assertions.assertNotNull(show.getShow_Id());
        Assertions.assertNotNull(show.getTheMovie());
        Assertions.assertNotNull(show.getTheTheater());

    }
}
