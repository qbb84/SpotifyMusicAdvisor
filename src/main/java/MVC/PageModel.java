package MVC;

public class PageModel {

    int page = 1;

    public int getPage() {
        return this.page;
    }

    public void decrement() {
        this.page--;
    }

    public void increment() {
        this.page++;
    }

}
