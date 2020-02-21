package reloadheroes;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {
        ReloadStatus rs = new ReloadStatus();
        if (session.getAttribute("name")==null){
            session.setAttribute("name", UUID.randomUUID());
            rs.setName(session.getAttribute("name").toString());
            rs.setReloads(0);
        }
        else {
            rs = reloadStatusRepository.findByName(session.getAttribute("name").toString());
        }
                
        int count = rs.getReloads();
        count++;
        rs.setReloads(count);
        reloadStatusRepository.save(rs);
        
        model.addAttribute("status", rs);
        
        //if (session.getAttribute("name")==null) session.setAttribute("name", UUID.randomUUID());
        
        model.addAttribute("scores", reloadStatusRepository.findAll());
        return "index";
    }
}
