package google.hashcode.main;


import google.hashcode.data.Book;
import google.hashcode.data.Library;
import google.hashcode.data.LibraryInfo;
import google.hashcode.data.Result;

import java.io.*;
import java.util.*;


public class InputReader {

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
                bookIndexScoreMap.put(count, Integer.parseInt(arrScores[count]));
                books--;
                count++;
            }

            int index = 0;
            int signUpDays = days;
            List<Library> libraryList = new ArrayList<>();
            while (libraries > 0) {
                final String line1 = sc.nextLine();
                final String[] arrLine1 = line1.split("\\s");

                final String line2 = sc.nextLine();
                final String[] arrLine2 = line2.split("\\s");

                Library library = new Library();
                library.setIndex(index);
                library.setSignUp(Integer.parseInt(arrLine1[1]));
                library.setShipPerDay(Integer.parseInt(arrLine1[2]));
                library.setBooks(createBooks(arrLine2, bookIndexScoreMap));

                signUpDays -= library.getSignUp();
                library.setDaysForShipping(signUpDays);

                libraryList.add(library);

                index++;
                libraries--;
            }

            System.out.println("Finished reading file......");

            //logic without prioritizing libraries

            Result result = new Result();
            List<LibraryInfo> libraryInfoList = new ArrayList<>();

            for (Library lib : libraryList) {
                LibraryInfo libraryInfo = new LibraryInfo();
                libraryInfo.setIndex(lib.getIndex());

                int totalNumberOfBooksCanBeShipped = lib.getDaysForShipping() * lib.getShipPerDay();
                int numberOfFiles = Math.min(totalNumberOfBooksCanBeShipped, lib.getBooks().size());

                libraryInfo.setNumberOfFiles(numberOfFiles);
                Book[] selectedBooks = new Book[numberOfFiles];
                for (int i = 0; i < numberOfFiles; i++) {
                    selectedBooks[i] = lib.getBooks().get(i);
                }
                libraryInfo.setBooks(selectedBooks);

                libraryInfoList.add(libraryInfo);
            }
            result.setLibraryInfoList(libraryInfoList);

            System.out.println("Writing output file......");
            writeOutput(result);

        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Book> createBooks(String[] arrLine2, Map<Integer, Integer> bookIndexScoreMap) {
        List<Book> bookList = new ArrayList<>();
        int count = 0;
        while (count != arrLine2.length) {
            bookList.add(new Book(Integer.parseInt(arrLine2[count]), bookIndexScoreMap.get(Integer.parseInt(arrLine2[count]))));
            count++;
        }
        Collections.sort(bookList);
        return bookList;
    }

    public static void writeOutput(final Result result) {
        String fileName = "resources/resultnew.txt";

        try {
            FileWriter fileWriter =
                    new FileWriter(fileName);

            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            bufferedWriter.write(result.getLibraryInfoList().size()+"");
            bufferedWriter.newLine();

            for (LibraryInfo libraryInfo : result.getLibraryInfoList()) {
                bufferedWriter.write(libraryInfo.getIndex() + " " + libraryInfo.getNumberOfFiles());
                bufferedWriter.newLine();
                Book[] books = libraryInfo.getBooks();
                for (int i = 0; i < books.length; i++) {
                    if(i < books.length-1)
                        bufferedWriter.write(books[i].getIndex() + " ");
                    else
                        bufferedWriter.write(books[i].getIndex()+"");
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
        }
    }
}

