package reservations;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    AccountRepository accountRepository;

    
    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo,
            Authentication authentication) {
        Reservation varaus = new Reservation();
        varaus.setReservationFrom(reservationFrom);
        varaus.setReservationTo(reservationTo);
        varaus.setUser(accountRepository.findByUsername(authentication.getName()));
        if (reservationRepository.findAllByReservationFromLessThanEqualAndReservationToGreaterThanEqual(reservationFrom, reservationTo)==null){
            reservationRepository.save(varaus);
        }
        
        return "redirect:/reservations";
    }
    
    @GetMapping("/reservations")
    public String listReservations(Model model){
        model.addAttribute("reservations",reservationRepository.findAll());
        return "reservations";
    }
}
