package by.teachmeskills.yadevich.lesson23;

import by.teachmeskills.yadevich.lesson23.model.Books;
import by.teachmeskills.yadevich.lesson23.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/** 1. Using Jackson library, write a Java program that parses a JSON file (books.json)
 and saves the data to a Java collection.
 2. Convert the data from this collection to XML format using JAXB library.
 Save the resulting XML result to a file.**/

public class Main {
    public static void main(String[] args) {
        // Create an ObjectMapper object from the Jackson library
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // formatting

        try {
            //read from json file using Jackson and put into collection
            List<Book> bookList = objectMapper.readValue(new File("books.json"), new TypeReference<List<Book>>() {});


            //Create a Books object that will contain a collection of books bookList
            Books books = new Books();
            books.setBooks(bookList);

            //JAXBContext is used to manage the process of converting Java objects to XML
            JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File xmlFile = new File("books.xml");

            //Books object in XML
            marshaller.marshal(books, xmlFile);

            System.out.println("XML file created successfully: " + xmlFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.printf("Error while reading books.json file: %s\n", e.getMessage());
        } catch (PropertyException e) {
            System.out.println(e.getMessage());
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }
}
