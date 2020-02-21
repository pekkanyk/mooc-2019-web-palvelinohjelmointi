package todoapplication;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoApplicationController {
    @Autowired
    private TaskRepository taskRepository;
    
    //private Map <String, Task> tasks;
    
/*    public TodoApplicationController(){
        this.tasks = new TreeMap<>();
        Task task = new Task("imuroi");
        this.tasks.put(task.getId(), task);
    }
*/
    @GetMapping("/")
    public String home(Model model) {
  //      model.addAttribute("items", this.tasks.values());
        model.addAttribute("items", this.taskRepository.findAll());
        return "index";
    }
    @GetMapping("/{id}")
    public String task(Model model, @PathVariable Long id){
 //       model.addAttribute("item", this.tasks.get(id));
        model.addAttribute("item", this.taskRepository.getOne(id));
        Task task = this.taskRepository.getOne(id);
        int laskuri = task.getChecked();
        laskuri++;
        task.setChecked(laskuri);       
        this.taskRepository.save(task);
        return "todo";
    }
    
    @PostMapping("/")
    public String post(@RequestParam String name) {
 //       Task task = new Task(name);
 //       this.tasks.put(task.getId(), task);
        this.taskRepository.save(new Task(name, 0));
        return "redirect:/";
    }
}