package pl.krzysztofwywial;


import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pl.krzysztofwywial.exception.RecordNotFoundException;
import pl.krzysztofwywial.repository.CarRepository;
import pl.krzysztofwywial.service.CarService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CarRentalKwApplicationTests {

    @InjectMocks
    private CarService service;

    @Mock
    private CarRepository carRepository;

    @Test
    void carServiceGetCarByIdAssertThrowsRecordNotFoundException() {

        final ThrowableAssert.ThrowingCallable callable = () -> service.getCarById(1L);

        assertThatThrownBy(callable)
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("No car record was found for given ID");
    }

    @Test
    public void carServiceDeleteCarByIdAssertThrowsRecordNotFoundException() {

        final ThrowableAssert.ThrowingCallable callable = () -> service.deleteCarById(1L);

        assertThatThrownBy(callable)
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("No car record was found for given ID");
    }
}

