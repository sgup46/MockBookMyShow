package sapient.service;

import com.sapient.dao.MovieServiceDAO;
import com.sapient.model.*;
import com.sapient.service.MovieService;
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
public class MovieServiceTest {

    @InjectMocks
    MovieService movieService;

    @Mock
    MovieServiceDAO movieServiceDAO;

    @Test
    public void testGetMovieByTheaterId() {

        List<Object[]> objectList = new ArrayList<Object[]>();
        Object[] object = new Object[3];
        object[0] = BigInteger.ONE;
        object[1] = "Test";
        object[2] = "Test";
        objectList.add(object);

        Mockito.when(movieServiceDAO.getMovieByTheaterId(Mockito.anyLong())).thenReturn(objectList);
        movieService.setMovieServiceDAO(movieServiceDAO);
        List<Movie> movies =  movieService.getMovieByTheaterId(6L);
        Assertions.assertNotNull(movies);
        Assertions.assertNotNull(movies.get(0));
        Assertions.assertNotNull(movies.get(0).getMovie_id());

    }
}
