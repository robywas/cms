package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.bottega.cms.application.CommandGateway;
import pl.bottega.cms.application.ReservationDto;
import pl.bottega.cms.application.ReservationFinder;
import pl.bottega.cms.model.ReservationQuery;
import pl.bottega.cms.model.ReservationStatus;
import pl.bottega.cms.model.commands.CalculatePricesCommand;
import pl.bottega.cms.model.commands.CreateReservationCommand;

import java.util.List;

@RestController
public class ReservationController {

    private CommandGateway commandGateway;
    private ReservationFinder reservationFinder;

    public ReservationController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/reservations")
    public ReservationNumber create(@RequestBody CreateReservationCommand cmd) {
        ReservationNumber reservationNumber = new ReservationNumber(commandGateway.execute(cmd));
        return reservationNumber;
    }

    @PostMapping("/price_calculator")
    public void calculatePrice(@RequestBody CalculatePricesCommand cmd) {
        commandGateway.execute(cmd);
    }

    @GetMapping("/reservations")
    public List<ReservationDto> search(@RequestParam("query") String query,
                                       @RequestParam("status") ReservationStatus status){

        ReservationQuery reservationQuery = new ReservationQuery(query, status);
        return reservationFinder.search(reservationQuery);
    }


}
