package view;

import controller.PostController;
import java.util.Scanner;

public class PostView {

    private final PostController postController = new PostController();
    private final Scanner scan = new Scanner(System.in);
    private final static String CASE_1 = "To create a new Post enter 1.";
    private final static String CASE_2 = "To see a Post enter 2.";
    private final static String CASE_3 = "To see all Posts enter 3.";
    private final static String CASE_4 = "To update Post enter 4.";
    private final static String CASE_5 = "To delete Post enter 5.";

    public void showPostView() {
        System.out.println(CASE_1);
        System.out.println(CASE_2);
        System.out.println(CASE_3);
        System.out.println(CASE_4);
        System.out.println(CASE_5);
        String choice = scan.nextLine();
        switch (choice) {
            case "1" -> createNewPost();
            case "2" -> getPost();
            case "3" -> getAllPosts();
            case "4" -> updatePost();
            case "5" -> deletePost();
        }
    }

    public void createNewPost() {
        System.out.println("Enter the text: ");
        String content = scan.nextLine();
        System.out.println("Enter id of the Writer: ");
        String writerId = scan.nextLine();
        postController.addNewPost(content, Long.parseLong(writerId));
        System.out.println();

    }

    public void getPost() {
        System.out.println("Enter id of the Post: ");
        String id = scan.nextLine();
        System.out.println(postController.getPost(Long.parseLong(id)));
        System.out.println();
    }

    public void getAllPosts() {
        System.out.println(postController.getAllPosts());
        System.out.println();
    }

    public void updatePost() {
        System.out.println("Enter id of the Post you want to edit: ");
        String id = scan.nextLine();
        System.out.println("Enter new content: ");
        String content = scan.nextLine();
        postController.updatePost(content, Long.parseLong(id));
    }

    public void deletePost() {
        System.out.println("Enter id of the Post you want to delete: ");
        String postId = scan.nextLine();
        postController.deletePost(Long.parseLong(postId));
    }
}
