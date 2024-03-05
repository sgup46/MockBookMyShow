package sapient.service;

import com.sapient.dao.TheaterServiceDAO;
import com.sapient.model.*;
import com.sapient.service.TheaterService;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Data
public class TheaterServiceTest {

    @InjectMocks
    TheaterService theaterService;

    @Mock
    TheaterServiceDAO theaterServiceDAO;

    @Test
    public void testGetTheatresByMovieAndShowTimings() {

        List<Object[]> objectList = new ArrayList<Object[]>();
        Object[] object = new Object[3];
        object[0] = BigInteger.ONE;
        object[1] = "Test";
        object[2] = "Test";
        objectList.add(object);

        Mockito.when(theaterServiceDAO.getTheatresByMovieAndShowTimings(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(objectList);
        theaterService.setTheaterServiceDAO(theaterServiceDAO);
        List<Theater> theatres =  theaterService.getTheatresByMovieAndShowTimings(6L, "Name", "showTime", "2022-07-31");
        Assertions.assertNotNull(theatres);
        Assertions.assertNotNull(theatres.get(0));
        Assertions.assertNotNull(theatres.get(0).getTheater_id());

    }
}
