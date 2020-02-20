package google.hashcode.main;


import google.hashcode.data.Book;
import google.hashcode.data.Library;
import google.hashcode.data.Output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.*;


public class InputReader {
    public static Map<Character, Integer> terrainValueMap = new HashMap<>();

    public static void main(final String[] args) {
        final File f = new File("resources/a_example.txt");
        try {
            final FileInputStream inputStream = new FileInputStream(f);
            final Scanner sc = new Scanner(inputStream, "UTF-8");

            System.out.println("Start reading file......");


            final String firstLine = sc.nextLine();
            final String[] firstArr = firstLine.split("\\s");
            int books = Integer.parseInt(firstArr[0]);
            int libraries = Integer.parseInt(firstArr[1]);
            int days = Integer.parseInt(firstArr[2]);

            final String scores = sc.nextLine();
            final String[] arrScores = scores.split("\\s");

            int count = 0;
            Map<Integer, Integer> bookIndexScoreMap = new HashMap<>();
            while (books > 0) {
                bookIndexScoreMap.put(count,Integer.parseInt(arrScores[count]));
                books--;
                count++;
            }

            List<Library> libraryList = new ArrayList<>();
            while(libraries > 0){
                final String line1 = sc.nextLine();
                final String[] arrLine1 = line1.split("\\s");

                final String line2 = sc.nextLine();
                final String[] arrLine2 = line2.split("\\s");

                Library library = new Library();
                library.setSignUp(Integer.parseInt(arrLine1[1]));
                library.setShipPerDay(Integer.parseInt(arrLine1[2]));
                library.setBooks(createBooks(arrLine2, bookIndexScoreMap));

                libraryList.add(library);

                libraries--;
            }

            System.out.println("Finished reading file......");

            //			WriteObjectToFile(student);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Book> createBooks(String[] arrLine2, Map<Integer, Integer> bookIndexScoreMap) {
        List<Book> bookList = new ArrayList<>();
        int count = 0;
        while(count != arrLine2.length) {
            bookList.add(new Book(bookIndexScoreMap.get(Integer.parseInt(arrLine2[count]))));
            count++;
        }
        Collections.sort(bookList);
        return bookList;
    }

    public void WriteObjectToFile(final Output result) {
        try {
            // approach 1
            final FileOutputStream fileOut = new FileOutputStream("resources/result.txt");
            final ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(result);
            objectOut.close();
            fileOut.close();
            // approach 2
            final PrintWriter out = new PrintWriter(new FileWriter("resources/output.txt", true), true);
            out.write(result.getX() + " " + result.getY() + " " + result.getPath());
            out.close();

            System.out.println("The Object  was succesfully written to a file");

        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

}

