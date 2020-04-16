package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persist.Person;
import ru.geekbrains.service.PersonService;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String allPersons(@RequestParam("minPrice") BigDecimal minPrice,
                             @RequestParam("maxPrice") BigDecimal maxPrice,
                             Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "persons";
    }

    @GetMapping("/form")
    public String formPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person_form";
    }

    @PostMapping("/form")
    public String newPerson(@Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "person_form";
        }

        personService.insert(person);
        return "redirect:/person";
    }
}
