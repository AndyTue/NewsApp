import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NewsService extends Remote {
    boolean login(String username, String password) throws RemoteException;
    void addUser(String username, String password, int id) throws RemoteException;
    void deleteNews(int newsId) throws RemoteException;
    void createNews(String name, String title, String creationDate, String lastModification, String author, String content) throws RemoteException;
    List<News> getAllNews() throws RemoteException;
}
