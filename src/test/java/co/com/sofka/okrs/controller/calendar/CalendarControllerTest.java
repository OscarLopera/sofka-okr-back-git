package co.com.sofka.okrs.controller.calendar;

import co.com.sofka.okrs.service.calendar.CalendarService;
import com.google.api.services.calendar.model.Event;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.security.GeneralSecurityException;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalendarControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Mock
    CalendarService calendarService;

    @Test
    void loadCalendar() throws GeneralSecurityException, IOException {
        Mockito.when(calendarService.load()).thenReturn(Flux.just(new Event()));

        Flux<Event> calFlux = webTestClient.get().uri("/calendar/list").exchange()
                .expectStatus().isOk()
                .returnResult(Event.class)
                .getResponseBody();
    }


}