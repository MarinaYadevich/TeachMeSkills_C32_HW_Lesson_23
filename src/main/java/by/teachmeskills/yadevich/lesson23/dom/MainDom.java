package by.teachmeskills.yadevich.lesson23.dom;

import by.teachmeskills.yadevich.lesson23.model.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/** 3. Using any parser (DOM, SAX, StAX) parse the data in a Java application and output
 * to the console information about the book with the largest number of pages. */
public class MainDom {
    public static void main(String[] args) {
        String fileName = "books.xml";
        Book maxPagesBook = null;

        try {
            //Create a DOM document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fileName);

            document.getDocumentElement().normalize();

            NodeList bookNodes = document.getElementsByTagName("book");

            for (int i = 0; i < bookNodes.getLength(); i++) {
                Node bookNode = bookNodes.item(i);

                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) bookNode;

                    // Reading book data
                    String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                    String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                    int year = Integer.parseInt(bookElement.getElementsByTagName("year").item(0).getTextContent());
                    int pages = Integer.parseInt(bookElement.getElementsByTagName("pages").item(0).getTextContent());
                    String genre = bookElement.getElementsByTagName("genre").item(0).getTextContent();

                    // Create a Book object
                    Book currentBook = new Book(title, author, year, pages, genre);

                    // check the largest number of pages
                    if (maxPagesBook == null || currentBook.getPages() > maxPagesBook.getPages()) {
                        maxPagesBook = currentBook;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (maxPagesBook != null) {
            System.out.println("Book with the most pages:");
            System.out.println(maxPagesBook);
        } else {
            System.out.println("No books found.");
        }
    }
}

