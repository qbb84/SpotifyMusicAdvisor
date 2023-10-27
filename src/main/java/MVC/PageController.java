package MVC;

import API.APIAuthorization;
import Errors.InvalidFeatureException;
import Input.FeatureWrapper;
import Input.InputAnnotations.AnnotationProcessor;
import Input.InputAnnotations.InputPrefix;
import Input.InputCommand;

import java.util.LinkedList;
import java.util.Scanner;

public class PageController {

    public static APIAuthorization apiAuth;


    private final PageModel model;
    private final PageView view;


    public PageController(PageModel model, PageView view) {
        this.model = model;
        this.view = view;

        apiAuth = new APIAuthorization();
        apiAuth.setAuthUrl(apiAuth.getSpotifyAuthUrl());
    }


    public void processUserInput(int itemsPerPage) {
        Scanner scanner = new Scanner(System.in);
        InputCommand.getInstance().setInputCommand(scanner.nextLine());

        if (!processAuthorization(scanner)) return;

        String inputCommand = InputCommand.getInstance().getInputCommand();

        try {
            PageView.featureType featureType = view.getFeature(inputCommand.split(" ")[0]);

            LinkedList<String> outputStringList = null;
            int outputSize = 0;

            if (!(featureType == PageView.featureType.NEXT || featureType == PageView.featureType.PREVIOUS)) {
                if (featureType == PageView.featureType.PLAYLIST) {
                    getPlaylist(inputCommand, featureType);
                }

                outputStringList = featureType.feature.getSetOutput();
                setListCache(outputStringList);
                outputSize = outputStringList.size();
                view.setPageCount(outputSize / itemsPerPage, itemsPerPage);
            }


            switch (featureType) {
                case NEXT -> {
                    if (checkPageBounds(model, getListCache().size(), PageOperation.NEXT)) return;
                    model.increment();
                    break;
                }
                case PREVIOUS -> {
                    if (checkPageBounds(model, getListCache().size(), PageOperation.PREVIOUS)) return;
                    model.decrement();
                    break;
                }

                case FEATURED, DISCOVER, CATEGORIES -> {
                    model.page = 1;
                    outputStringList.forEach(s -> view.addToPage(s));
                    break;
                }
                case PLAYLIST -> {
                    model.page = 1;
                    outputStringList.forEach(s -> view.addToPage(s));
                    break;
                }
            }

            view.setPage(model.getPage());

        } catch (InvalidFeatureException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean processAuthorization(Scanner scanner) {
        String inputCommand = InputCommand.getInstance().getInputCommand();

        if (inputCommand.equalsIgnoreCase("exit")) FeatureWrapper.exit();

        if (!apiAuth.invoke().isAuthSuccess() && !inputCommand.equalsIgnoreCase(new AnnotationProcessor(APIAuthorization.class).getValues(InputPrefix.class)[0].toString())) {

            System.out.println("Please, provide access for application.");
            return false;
        } else if (inputCommand.equalsIgnoreCase("auth") && !apiAuth.invoke().isAuthSuccess()) {
            System.out.println("use this link to request the access code:");
            System.out.println(apiAuth.getSpotifyAuthUrl());
            return false;
        }
        return true;
    }

    private boolean checkPageBounds(PageModel model, int totalPages, PageOperation operation) {

        switch (operation) {
            case NEXT -> {
                if ((model.getPage() + 1) > totalPages) {
                    System.out.println("No more pages.");
                    return true;
                }
            }
            case PREVIOUS -> {
                if ((model.getPage() - 1) < 1) {
                    System.out.println("No more pages.");
                    return true;
                }
            }
        }
        return false;

    }

    private void getPlaylist(String inputCommand, PageView.featureType featureType) {
        String playlistsSuffix = "";
        for (String s : inputCommand.split(" ")) {
            if (s.equals(inputCommand.split(" ")[0])) continue;

            if (inputCommand.split(" ")[inputCommand.split(" ").length - 1] != s) {
                playlistsSuffix += s + " ";
            }
        }
        featureType.feature.setInput(playlistsSuffix);
    }

    private LinkedList<String> listCache;

    private void setListCache(LinkedList<String> list) {
        listCache = new LinkedList<>(list);
    }

    public LinkedList<String> getListCache() {
        return listCache;
    }


}
