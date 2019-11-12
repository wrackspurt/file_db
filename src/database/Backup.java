package database;

import java.io.*;

public class Backup {
    public void createBackup(String flname) throws IOException {
        File ffl = new File("C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/file_databases/" + flname + ".txt");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        int fileSize = 0, data = 0;
        fis = new FileInputStream(ffl);
        fileSize = fis.available();
        if (fileSize == 0)
        {
            System.out.println("Sorry, this file is empty.");
            fis.close();
            return;
        }
        fos = new FileOutputStream("C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/backup/" + flname + ".bak");
        while ( (data = fis.read()) != -1)
        {
            fos.write(data);
        }
        fos.flush();
        System.out.println("Backup file created successfully.");
        fos.close();
        fis.close();
    }

    public void createAnotherBackup(String flname) throws IOException {
        File ffl = new File("C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/file_databases/" + flname);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        int fileSize = 0, data = 0;
        fis = new FileInputStream(ffl);
        fileSize = fis.available();
        if (fileSize == 0)
        {
            System.out.println("Sorry, this file is empty.");
            fis.close();
            return;
        }
        String ff = flname.replaceAll("[.]\\w\\w\\w", "");
        fos = new FileOutputStream("C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/backup/" + ff + ".bak");
        while ( (data = fis.read()) != -1)
        {
            fos.write(data);
        }
        fos.flush();
        System.out.println("Backup file created successfully.");
        fos.close();
        fis.close();
    }

    public void restore(String flname) throws IOException {
        File ffl = new File("C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/backup/" + flname);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        int fileSize = 0, data = 0;
        fis = new FileInputStream(ffl);
        fileSize = fis.available();
        if (fileSize == 0)
        {
            System.out.println("Sorry, this file is empty.");
            fis.close();
            return;
        }

        String newfn = flname.replaceAll("[.]\\w\\w\\w", "");
        fos = new FileOutputStream("C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/file_databases/" + newfn + ".txt");
        while ( (data = fis.read()) != -1)
        {
            fos.write(data);
        }
        fos.flush();
        fos.close();
        fis.close();

    }

}
