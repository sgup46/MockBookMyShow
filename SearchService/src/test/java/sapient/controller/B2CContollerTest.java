package sapient.controller;

import com.sapient.controller.B2CContoller;

import com.sapient.model.*;
import com.sapient.service.*;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@Data
public class B2CContollerTest {

    @InjectMocks
    B2CContoller b2CContoller;

    @Mock
    CityService theCityService;

    @Mock
    MovieService theMovieService;

    @Mock
    TheaterService theTheaterService;

    @Mock
    ShowService theShowService;


    @Test
    public void testGetTheatresByMovieAndShowTimings() throws Exception {
        Theater theater = new Theater();
        theater.setTheater_id(1L);
        theater.setTheater_name("testTheatrer");
        List<Theater> employees = Arrays.asList(theater);
        b2CContoller.setTheTheaterService(theTheaterService);

        Mockito.when(theTheaterService.getTheatresByMovieAndShowTimings(1L, "WAR 03", "6:00 PM",
                "2022-07-31")).thenReturn(employees);

        List<Theater> theatres =  b2CContoller.getTheatresByMovieAndShowTimings(1L, "WAR 03", "6:00 PM", "2022-07-31");
        assertEquals(1, theatres.size());
    }
}
