package sapient.service;

import com.sapient.dao.CityServiceDAO;
import com.sapient.model.*;
import com.sapient.service.CityService;
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
public class CityServiceTest {

    @InjectMocks
    CityService cityService;

    @Mock
    CityServiceDAO cityServiceDAO;

    @Test
    public void testGetTheatresByMovieAndShowTimings() {


        City t = new City();
        t.setCity_id(6L);

        Mockito.when(cityServiceDAO.findOne(Mockito.anyLong())).thenReturn(t);
        cityService.setCityServiceDAO(cityServiceDAO);
        City city =  cityService.findOne(6);
        Assertions.assertNotNull(city);
        Assertions.assertNotNull(city.getCity_id());

    }
}
