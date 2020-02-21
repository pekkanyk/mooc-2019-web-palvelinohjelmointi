package playlists;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class DefaultController {

    @GetMapping("*")
    public String home(Model model) {
        String title = "Playlist service";
        model.addAttribute("title", title);
        return "index";
    }
}
