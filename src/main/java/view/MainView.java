package view;

import java.util.Scanner;

public class MainView {
    private final static String CASE_1 = "To work with Writers enter 1: ";
    private final static String CASE_2 = "To work with Posts enter 2: ";
    private final static String CASE_3 = "To work with Labels enter 3: ";
    private final Scanner scan = new Scanner(System.in);
    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();


    public void show() {
        System.out.println(CASE_1);
        System.out.println(CASE_2);
        System.out.println(CASE_3);
        String choice = scan.nextLine();
        switch (choice) {
            case "1" -> writerView.showWriterView();
            case "2" -> postView.showPostView();
            case "3" -> labelView.showLabelView();
            default -> System.out.println("Invalid input");
        }
    }
}
