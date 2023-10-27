package MVC;

import Errors.InvalidFeatureException;
import Input.Feature;
import Input.Features.Categories;
import Input.Features.Discover;
import Input.Features.Featured;
import Input.Features.Playlist;

public class PageView {


    private String[][] pages;

    public PageView() {
    }

    public void setPageCount(int totalPages, int itemsPerPage) {
        pages = new String[totalPages][itemsPerPage];
    }

    public void setPage(int pageNumber) {
        viewPage(pageNumber);
    }

    private void viewPage(int page) {
        for (String s : this.pages[page - 1]) {
            System.out.print(s);
        }

        System.out.println("---PAGE " + page + " OF " + (pages.length) + "---");
    }

    public <T> void addToPage(T t) {
        String inputValue = String.valueOf(t);

        for (int i = 0; i < pages.length; i++) {
            for (int k = 0; k < this.pages[i].length; k++) {
                if (this.pages[i][k] == null) {
                    this.pages[i][k] = inputValue;
                    return;
                }

            }
        }
    }

    public featureType getFeature(String input) throws InvalidFeatureException {
        switch (input.toLowerCase()) {
            case "new" -> {
                return featureType.DISCOVER;
            }
            case "featured" -> {
                return featureType.FEATURED;
            }
            case "categories" -> {
                return featureType.CATEGORIES;
            }
            case "playlists" -> {
                return featureType.PLAYLIST;
            }
            case "next" -> {
                return featureType.NEXT;
            }
            case "previous", "prev" -> {
                return featureType.PREVIOUS;
            }
            default -> throw new InvalidFeatureException();
        }
    }


    enum featureType {

        CATEGORIES(new Categories()),
        DISCOVER(new Discover()),
        FEATURED(new Featured()),
        PLAYLIST(new Playlist()),

        NEXT, PREVIOUS;

        public Feature feature;

        featureType(Feature feature) {
            this.feature = feature;
        }

        featureType() {
        }
    }

}
