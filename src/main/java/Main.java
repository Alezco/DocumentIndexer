
import service.Crawler;
import service.URLRepo;

/**
 * Created by Guillaume on 06/12/2017.
 */
public class Main {
    public static void main(String[] args) {

        URLRepo repo = new URLRepo();

        //Indexer indexer = new Indexer(repo);
        Crawler crawler = new Crawler(repo);
    }
}
