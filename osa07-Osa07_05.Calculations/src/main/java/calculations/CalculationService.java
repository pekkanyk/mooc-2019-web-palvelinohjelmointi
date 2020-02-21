package calculations;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;

    
    @Async
public Calculation process(Calculation calc) {
        calc.setStatus("PROCESSING");
        calc.setContent(calc.getContent());
        calculationRepository.save(calc);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CalculationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //calc =calculationRepository.
        calc.setStatus("PROCESSED");
        calc.setCalculationResult(calc.getContent() + ";" + UUID.randomUUID().toString());
return calculationRepository.save(calc);
    }

}