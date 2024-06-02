package com.example.demo.service;

import java.io.FileNotFoundException;
import com.example.demo.entities.Reservation;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

@Service
public class PdfFileService {

    public void pdfCreation(Reservation reservation) {
        if (reservation == null || reservation.getUser() == null || reservation.getHebergement() == null) {
            throw new IllegalArgumentException("Reservation, User, or Hebergement cannot be null");
        }

        String filepath = "C:\\Users\\pc\\Desktop\\PdfFiles\\reservation_" + reservation.getId() + ".pdf";

        try {
            PdfWriter writer = new PdfWriter(filepath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.addNewPage();
            Document document = new Document(pdfDoc);

            // Titre
            document.add(new Paragraph("Confirmation de Réservation").setBold().setFontSize(20));
            document.add(new Paragraph(" "));

            // Informations sur le client
            document.add(new Paragraph("Informations sur le client :").setBold());
            document.add(new Paragraph("Nom : " + reservation.getUser().getNom()));
            document.add(new Paragraph("Prénom : " + reservation.getUser().getPrenom()));
            document.add(new Paragraph("Téléphone : " + reservation.getUser().getTlf()));
            document.add(new Paragraph("Email : " + reservation.getUser().getEmail()));
            document.add(new Paragraph(" "));

            // Détails de l'hébergement
            document.add(new Paragraph("Détails de l'hébergement :").setBold());
            document.add(new Paragraph("Nom : " + reservation.getHebergement().getNom()));
            document.add(new Paragraph("Adresse : " + reservation.getHebergement().getAdresse()));
            document.add(new Paragraph("Ville : " + reservation.getHebergement().getVille()));
            document.add(new Paragraph("Pays : " + reservation.getHebergement().getPays()));
            document.add(new Paragraph(" "));

            // Détails de la réservation
            document.add(new Paragraph("Détails de la réservation :").setBold());
            document.add(new Paragraph("Numéro de réservation : " + reservation.getId()));
            document.add(new Paragraph("Date de Check-in : " + reservation.getDateCheckIn()));
            document.add(new Paragraph("Date de Check-out : " + reservation.getDateCheckOut()));
            document.add(new Paragraph("Nombre de chambres : " + reservation.getNbRooms()));
            document.add(new Paragraph("Prix total : " + reservation.getPrix()));
            document.add(new Paragraph("État de la réservation : " + reservation.getEtat()));
            document.add(new Paragraph(" "));

            // Politique d'annulation
            document.add(new Paragraph("Politique d'annulation :").setBold());
            document.add(new Paragraph(reservation.getHebergement().getPolitiqueAnnulation()));
            document.add(new Paragraph(" "));

            // Informations de contact
            document.add(new Paragraph("Informations de contact :").setBold());
            document.add(new Paragraph("Téléphone : " + reservation.getHebergement().getPhone()));
            document.add(new Paragraph("Email : " + reservation.getHebergement().getEmail()));
            document.add(new Paragraph(" "));

            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
