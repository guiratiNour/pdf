package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import com.example.demo.entities.Reservation;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import org.springframework.stereotype.Service;

@Service
public class PdfFileService {

    public void pdfCreation(Reservation reservation) {
        if (reservation == null || reservation.getUser() == null || reservation.getHebergement() == null) {
            throw new IllegalArgumentException("Reservation, User, or Hebergement cannot be null");
        }

        String filepath = "C:\\Users\\pc\\Desktop\\PdfFiles\\reservation5" + reservation.getId() + ".pdf";
        File file = new File(filepath);

        if (file.exists()) {
            file.delete();
        }

        try {
            PdfWriter writer = new PdfWriter(filepath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.addNewPage();
            Document document = new Document(pdfDoc);

            try {
            	// Adding Logo Image at the top left
            	String logoImagePath = "C:\\Users\\pc\\Desktop\\pfe\\logo.png"; 
            	ImageData logoImageData = ImageDataFactory.create(logoImagePath);
            	Image logoImage = new Image(logoImageData)
            	        .setFixedPosition(50, 750)  // Position the logo image at the top left, adjust '50' as needed for exact positioning
            	        .scaleToFit(100, 100);  // Adjust the scale to make the image fit
            	document.add(logoImage);

            } catch (Exception e) {
                System.out.println("Erreur lors du chargement de l'image du logo : " + e.getMessage());
            }

            try {
                // Adding "Paid" Image at the top right
                String paidImagePath = "C:\\Users\\pc\\Desktop\\pfe\\paid.png";
                ImageData paidImageData = ImageDataFactory.create(paidImagePath);
                Image paidImage = new Image(paidImageData)
                        .setFixedPosition(500, 750)  // Position the "paid" image at the top right
                        .scaleToFit(50, 50);  // Adjust the scale
                document.add(paidImage);
            } catch (Exception e) {
                System.out.println("Erreur lors du chargement de l'image 'Paid' : " + e.getMessage());
            }

            // Title directly below the logo image
            Paragraph title = new Paragraph("Reservation Confirmation")
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(50);  // Adjust the top margin to ensure it is right below the logo image
            document.add(title);

            // Client Information
            Paragraph clientInfoTitle = new Paragraph("Client Information:")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(10)
                    .setFontColor(ColorConstants.BLUE);
            document.add(clientInfoTitle);

            Table clientInfoTable = new Table(UnitValue.createPercentArray(new float[]{1, 3}))
                    .useAllAvailableWidth()
                    .setMarginBottom(20);
            clientInfoTable.addCell(new Paragraph("Last Name:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            clientInfoTable.addCell(reservation.getUser().getNom());
            clientInfoTable.addCell(new Paragraph("First Name:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            clientInfoTable.addCell(reservation.getUser().getPrenom());
            clientInfoTable.addCell(new Paragraph("Phone:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            clientInfoTable.addCell(reservation.getUser().getTlf());
            clientInfoTable.addCell(new Paragraph("Email:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            clientInfoTable.addCell(reservation.getUser().getEmail());

            document.add(clientInfoTable);

            // Accommodation Details
            Paragraph hebergementInfoTitle = new Paragraph("Accommodation Details:")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(10)
                    .setFontColor(ColorConstants.BLUE);
            document.add(hebergementInfoTitle);

            Table hebergementInfoTable = new Table(UnitValue.createPercentArray(new float[]{1, 3}))
                    .useAllAvailableWidth()
                    .setMarginBottom(20);
            hebergementInfoTable.addCell(new Paragraph("Name:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            hebergementInfoTable.addCell(reservation.getHebergement().getNom());
            hebergementInfoTable.addCell(new Paragraph("Address:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            hebergementInfoTable.addCell(reservation.getHebergement().getAdresse());
            hebergementInfoTable.addCell(new Paragraph("City:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            hebergementInfoTable.addCell(reservation.getHebergement().getVille());
            hebergementInfoTable.addCell(new Paragraph("Country:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            hebergementInfoTable.addCell(reservation.getHebergement().getPays());

            document.add(hebergementInfoTable);

            // Reservation Details
            Paragraph reservationInfoTitle = new Paragraph("Reservation Details:")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(10)
                    .setFontColor(ColorConstants.BLUE);
            document.add(reservationInfoTitle);

            Table reservationInfoTable = new Table(UnitValue.createPercentArray(new float[]{1, 3}))
                    .useAllAvailableWidth()
                    .setMarginBottom(20);
            reservationInfoTable.addCell(new Paragraph("Reservation Number:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            reservationInfoTable.addCell(String.valueOf(reservation.getId()));
            reservationInfoTable.addCell(new Paragraph("Check-in Date:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            reservationInfoTable.addCell(reservation.getDateCheckIn().toString());
            reservationInfoTable.addCell(new Paragraph("Check-out Date:").setBold().setBackgroundColor (ColorConstants.LIGHT_GRAY));
            reservationInfoTable.addCell(reservation.getDateCheckOut().toString());
            reservationInfoTable.addCell(new Paragraph("Number of Rooms:").setBold().setBackgroundColor (ColorConstants.LIGHT_GRAY));
            reservationInfoTable.addCell(String.valueOf(reservation.getNbRooms()));
            reservationInfoTable.addCell(new Paragraph("Total Price:").setBold().setBackgroundColor (ColorConstants.LIGHT_GRAY));
            reservationInfoTable.addCell(String.valueOf(reservation.getPrix()));
            reservationInfoTable.addCell(new Paragraph("Reservation Status:").setBold().setBackgroundColor (ColorConstants.LIGHT_GRAY));
            reservationInfoTable.addCell(reservation.getEtat());

            document.add(reservationInfoTable);

            // Cancellation Policy
            Paragraph annulationTitle = new Paragraph("Cancellation Policy:")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(10)
                    .setFontColor(ColorConstants.BLUE);
            document.add(annulationTitle);

            Paragraph annulationPolicy = new Paragraph(reservation.getHebergement().getPolitiqueAnnulation())
                    .setMarginBottom(20);
            document.add(annulationPolicy);

            // Contact Information
            Paragraph contactInfoTitle = new Paragraph("Contact Information:")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(10)
                    .setFontColor (ColorConstants.BLUE);
            document.add(contactInfoTitle);

            Table contactInfoTable = new Table(UnitValue.createPercentArray(new float[]{1, 3}))
                    .useAllAvailableWidth()
                    .setMarginBottom(20);
            contactInfoTable.addCell(new Paragraph("Phone:").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
            contactInfoTable.addCell(reservation.getHebergement().getPhone());
            contactInfoTable.addCell(new Paragraph("Email:").setBold().setBackgroundColor (ColorConstants.LIGHT_GRAY));
            contactInfoTable.addCell(reservation.getHebergement().getEmail());

            document.add(contactInfoTable);

            // Footer
            Paragraph footer = new Paragraph("Thank you for choosing our booking service.")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20);
            document.add(footer);

            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
