package view;

import controller.LabelController;
import java.util.Scanner;

public class LabelView {
    private final static String CASE_1 = "To create a new Label enter 1.";
    private final static String CASE_2 = "To see Label by id enter 2.";
    private final static String CASE_3 = "To see all Labels enter 3.";
    private final static String CASE_4 = "To edit label enter 4.";
    private final static String CASE_5 = "To delete label enter 5";
    private final LabelController labelController = new LabelController();
    private final Scanner scan = new Scanner(System.in);

    public void showLabelView() {
        System.out.println(CASE_1);
        System.out.println(CASE_2);
        System.out.println(CASE_3);
        System.out.println(CASE_4);
        System.out.println(CASE_5);
        String choice = scan.nextLine();
        switch (choice) {
            case "1" -> addNewLabel();
            case "2" -> getLabel();
            case "3" -> getAllLabels();
            case "4" -> editLabel();
            case "5" -> deleteLabel();
        }
    }

    public void addNewLabel() {
        System.out.println("Enter the name of the Label: ");
        String name = scan.nextLine();
        System.out.println("Enter the id of the post: ");
        String postId = scan.nextLine();
        if(postId.isEmpty()) {
            throw new NullPointerException("Post id should`t be null");
        }
        labelController.addNewLabel(name, Long.parseLong(postId));
    }

    public void getLabel() {
        System.out.println("Enter id of the Label: ");
        String id = scan.nextLine();
        System.out.println(labelController.getLabel(Long.parseLong(id)));
    }

    public void getAllLabels() {
        System.out.println(labelController.getAllLabels());
    }

    public void editLabel() {
        System.out.println("Enter id of the Label you want to edit: ");
        String id = scan.nextLine();
        System.out.println("Enter new name: ");
        String name = scan.nextLine();
        labelController.update(name, Long.parseLong(id));
    }

    public void deleteLabel() {
        System.out.println("Enter id of the Label you want to delete: ");
        String id = scan.nextLine();
        labelController.delete(Long.parseLong(id));
        System.out.println("Label has been successfully deleted.");
    }

}
