package academy.atl.newsletters.repository;

import academy.atl.newsletters.models.Lead;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmailRepository {

    @PersistenceContext
    private EntityManager baseDeDatos;

    @Transactional
    public void guardarEmail(String emailDelUsuario) {
        String consultaSql = "INSERT INTO lead (id, email)" +
                " VALUES (NULL, '"+emailDelUsuario+"')";
        baseDeDatos.createNativeQuery(consultaSql).executeUpdate();

    }

    @Transactional
    public void eliminarEmail(String emailDelUsuario) {
        String consultaSql = "DELETE FROM Lead WHERE email = :paramEmail";
        baseDeDatos.createQuery(consultaSql)
                .setParameter("paramEmail", emailDelUsuario)
                .executeUpdate();
    }

    @Transactional
    public void obtenerLeadPorEmail(String emailDelUsuario) {
        String consultaSql = "SELECT l FROM Lead l ";
         baseDeDatos.createQuery(consultaSql)
                .getResultList();

    }

    @Transactional
    public List<Lead> obtenerTodosLosLeads() {
        String consultaSql = "SELECT l FROM Lead l";
        List<Lead> resultados = baseDeDatos.createQuery(consultaSql, Lead.class)
                .getResultList();
        return resultados;
    }


}
//INSERT INTO `lead` (`id`, `email`) VALUES (NULL, 'jordansafren@gmail.com');
/*
String consultaSql = "INSERT INTO lead (email)  VALUES (:paramEmail)";
        baseDeDatos.createQuery(consultaSql)
                .setParameter("paramEmail", emailDelUsuario)
                .executeUpdate();

 */