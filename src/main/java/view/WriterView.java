package view;

import controller.WriterController;
import java.util.Scanner;

public class WriterView {
    private final WriterController writerController = new WriterController();
    private final Scanner scan = new Scanner(System.in);
    private final static String CASE_1 = "To create a new Writer enter 1.";
    private final static String CASE_2 = "To see a Writer enter 2.";
    private final static String CASE_3 = "To see all Writer enter 3.";
    private final static String CASE_4 = "To update Writer enter 4.";
    private final static String CASE_5 = "To delete Writer enter 5.";

    public void showWriterView() {
        System.out.println(CASE_1);
        System.out.println(CASE_2);
        System.out.println(CASE_3);
        System.out.println(CASE_4);
        System.out.println(CASE_5);
        String choice = scan.nextLine();
        switch (choice) {
            case "1" -> createNewWriter();
            case "2" -> getWriter();
            case "3" -> getAllWriters();
            case "4" -> updateWriter();
            case "5" -> deleteWriter();
        }
    }

    public void createNewWriter() {
        System.out.println("Enter firstName: ");
        String firstName = scan.nextLine();
        System.out.println("Enter lastName: ");
        String lastName = scan.nextLine();
        writerController.addNewWriter(firstName, lastName);

    }

    public void getWriter() {
        System.out.println("Enter id of the Writer: ");
        String id = scan.nextLine();
        System.out.println(writerController.getWriter(Long.parseLong(id)));
    }

    public void getAllWriters() {
        System.out.println(writerController.getAllWriters());
    }

    public void updateWriter() {
        System.out.println("Enter id of the Writer");
        String writerId = scan.nextLine();
        System.out.println("Enter new first name: ");
        String firstName = scan.nextLine();
        System.out.println("Enter new last name: ");
        String lastName = scan.nextLine();
        writerController.updateWriter(firstName, lastName, Long.parseLong(writerId));
    }

    public void deleteWriter() {
        System.out.println("Enter id of Writer: ");
        String writerId = scan.nextLine();
        writerController.deleteWriter(Long.parseLong(writerId));
    }
}
