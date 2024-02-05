package academy.atl.newsletters.controllers;

import academy.atl.newsletters.models.Lead;
import academy.atl.newsletters.repository.EmailRepository;
import academy.atl.newsletters.validators.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewslettersController {

    @Autowired
    private EmailRepository repositorioDeEmails;

    @PostMapping("/api/newsletter")
    public String registrar(@RequestBody Lead lead) {

        String email = lead.getEmail();

        EmailValidator emailValidator = new EmailValidator();
        if (emailValidator.esValido(email) == false) {
            return "El email no es valido";
        }

        repositorioDeEmails.guardarEmail(email);

        return "Email guardado: " + email;
    }

    @PostMapping("/api/newsletter/unsuscribe")
    public String eliminar(@RequestBody Lead lead) {
        String email = lead.getEmail();
        repositorioDeEmails.eliminarEmail(email);

        return "Email borrado: " + email;
    }

    @GetMapping("/api/newsletter/traer")
    public String traer(@RequestBody Lead lead) {
        String email = lead.getEmail();
        repositorioDeEmails.obtenerLeadPorEmail(email);

        return "Los usuarios son:  " + email;
    }

    @GetMapping("/api/newsletter/Traer")
    public String traer() {
        List<Lead> leads = repositorioDeEmails.obtenerTodosLosLeads();

        StringBuilder resultado = new StringBuilder("Los usuarios son: ");
        for (int i = 0; i < leads.size(); i++) {
            Lead lead = leads.get(i);
            resultado.append(i + 1).append(". ").append(lead.getEmail());

            // Agregar una coma y espacio si no es el último elemento
            if (i < leads.size() - 1) {
                resultado.append(", ");
            }
        }

        return resultado.toString();
    }



}
