package gifbin;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DefaultController {
    @Autowired
    public FileObjectRepository fileObjectRepository;
    

    @GetMapping("/")
    public String redirect() {
        return "redirect:/gifs";
    }
    @GetMapping("/gifs")
    public String gifut(){
        return "redirect:/gifs/1";
    }
    
    @GetMapping("/gifs/{id}")
    public String giffi(Model model, @PathVariable Long id) {
        Long count = fileObjectRepository.count();
        Long current = null;
        Long next = null;
        Long previous = null;
        if (fileObjectRepository.existsById(id)){
            current = id;
            if (count > current) next = current+1;
            if (current >1 ) previous = current-1;
        }
        
        model.addAttribute("count",count);
        model.addAttribute("current", current);
        model.addAttribute("next", next);
        model.addAttribute("previous",previous);    
        return "gifs";
    }
    
    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] getGif(@PathVariable Long id){
        return fileObjectRepository.findById(id).get().getContent();
    }
    
    @PostMapping("/gifs")
    public String tallenna(@RequestParam("file") MultipartFile file) throws IOException {
        FileObject fo = new FileObject();
        fo.setContent(file.getBytes());
        if (file.getContentType().equals("image/gif")){
        fileObjectRepository.save(fo);
        }
        return "redirect:/gifs";
    }
}
