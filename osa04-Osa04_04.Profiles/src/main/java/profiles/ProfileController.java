package profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {
    @Autowired
    private Environment env;


    @ResponseBody
    @GetMapping("/profile")
    public String getProfile() {
        //String profiili = env.getActiveProfiles()[0];
        return env.getActiveProfiles()[0];
    }
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("profiilit", env.getActiveProfiles());
        return "index";
    }

}
