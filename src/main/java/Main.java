import API.APIAuthorization;
import MVC.PageController;
import MVC.PageModel;
import MVC.PageView;
import http.HandleHtppServer;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        String clientID = APIAuthorization.CLIENT_ID;
        String clientSecret = APIAuthorization.CLIENT_SECRET;

        System.out.print("Enter your Spotify Client ID: ");
        APIAuthorization.CLIENT_ID = scanner.nextLine();

        System.out.print("Enter your Spotify Client Secret: ");
        APIAuthorization.CLIENT_SECRET = scanner.nextLine();

        if (APIAuthorization.CLIENT_ID.isEmpty() || APIAuthorization.CLIENT_SECRET.isEmpty()) {
            System.out.println("Both Client ID and Client Secret are required.");
            return;
        }


        HandleHtppServer spotify = new HandleHtppServer();
        spotify.setSocketAddress(8080).createServerContext(args);
        spotify.start();

        PageModel model = new PageModel();
        PageView view = new PageView();
        PageController controller = new PageController(model, view);

        while (true) {
            controller.processUserInput(5);
        }


    }
}
