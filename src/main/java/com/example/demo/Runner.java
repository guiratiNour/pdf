package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Hebergement;
import com.example.demo.entities.Reservation;
import com.example.demo.entities.User;
import com.example.demo.service.PdfFileService;

@Component
public class Runner implements ApplicationRunner {

    private PdfFileService pdfFileService;

    @Autowired
    public Runner(PdfFileService pdfFileService) {
        this.pdfFileService = pdfFileService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Application Started to Run");

        // Créer et initialiser un objet User
        User user = new User();
        user.setNom("Doe");
        user.setPrenom("John");
        user.setTlf("123456789");
        user.setEmail("john.doe@example.com");
        user.setPays("France");
        // Initialisez les autres champs nécessaires

        // Créer et initialiser un objet Hebergement
        Hebergement hebergement = new Hebergement();
        hebergement.setNom("Hotel de Paris");
        hebergement.setAdresse("123 Rue de Paris");
        hebergement.setVille("Paris");
        hebergement.setPays("France");
        hebergement.setPhone("987654321");
        hebergement.setEmail("contact@hotelparis.com");
        hebergement.setPolitiqueAnnulation("Annulation gratuite jusqu'à 24h avant l'arrivée");
        // Initialisez les autres champs nécessaires

        // Créer et initialiser un objet Reservation
        Reservation reservation = new Reservation();
        reservation.setId(1L); // exemple d'ID
        reservation.setUser(user); // Associer l'utilisateur à la réservation
        reservation.setHebergement(hebergement); // Associer l'hébergement à la réservation
        reservation.setDateCheckIn("2024-07-01");
        reservation.setDateCheckOut("2024-07-10");
        reservation.setNbRooms("2");
        reservation.setPrix("500.00");
        reservation.setEtat("Confirmed");

        pdfFileService.pdfCreation(reservation);
        System.out.println("Pdf File Got Created");
    }
}
