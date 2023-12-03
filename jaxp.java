package org.example;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class jaxp {
    public static void main(String[] args) {
        try {
            // Kreiranje parsera za XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Učitavanje XML datoteke
            File xmlFile = new File("catalog.xml");
            Document document = builder.parse(xmlFile);

            // Dobivanje svih "book" elemenata
            NodeList bookList = document.getElementsByTagName("book");

            // Postavljanje formata za datume
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date cutoffDate = dateFormat.parse("2005-01-01");

            // Iteriranje kroz "book" elemente
            for (int i = 0; i < bookList.getLength(); i++) {
                org.w3c.dom.Element book = (org.w3c.dom.Element) bookList.item(i);
                double price = Double.parseDouble(book.getElementsByTagName("price").item(0).getTextContent());
                Date publishDate = dateFormat.parse(book.getElementsByTagName("publish_date").item(0).getTextContent());

                // Provjera uslova (cena > 10 i izdanje nakon 2005. godine)
                if (price > 10.0 && publishDate.after(cutoffDate)) {
                    String title = book.getElementsByTagName("title").item(0).getTextContent();
                    String author = book.getElementsByTagName("author").item(0).getTextContent();
                    System.out.println("Naslov: " + title);
                    System.out.println("Autor: " + author);
                    System.out.println("Cena: " + price);
                    System.out.println("Datum izdanja: " + dateFormat.format(publishDate));
                    System.out.println("=============================");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

