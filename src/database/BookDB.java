package database;

import java.io.*;

public class BookDB {
    public int book_id;
    public String appellation;
    public String author;
    public int year_of_publication;
    public double price;

    public BookDB(int b_id, String b_apl, String b_auth, int yop, double pr)
    {
        book_id = b_id;
        appellation = b_apl;
        author = b_auth;
        year_of_publication = yop;
        price = pr;
    }


    public void addNote(String filename) throws IOException {
        File file = new File("C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/file_databases/" + filename + ".txt");
        PrintWriter pw =new PrintWriter(new FileOutputStream(file, true));
        pw.append("\n" + book_id + " " + appellation + " " + author + " " + year_of_publication + " " + price + " ");
        pw.close();
    }

    public void addAnotherNote(String filename) throws FileNotFoundException {
        File file = new File("C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/file_databases/" + filename);
        PrintWriter pw =new PrintWriter(new FileOutputStream(file, true));
        pw.append("\n" + book_id + " " + appellation + " " + author + " " + year_of_publication + " " + price + " ");
        pw.close();
    }
}
