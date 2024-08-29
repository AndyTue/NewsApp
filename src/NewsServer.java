import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NewsServer {
    public static void main(String[] args) {
        try {
            NewsService newsService = new NewsServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("NewsService", newsService);
            System.out.println("News Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
