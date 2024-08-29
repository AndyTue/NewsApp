import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsServiceImpl extends UnicastRemoteObject implements NewsService {
    private static final long serialVersionUID = 1L;
    private Map<String, String> users = new HashMap<>();
    private List<News> newsList = new ArrayList<>();
    private int newsIdCounter = 0;

    protected NewsServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean login(String username, String password) throws RemoteException {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    @Override
    public void addUser(String username, String password, int id) throws RemoteException {
        users.put(username, password);
    }

    @Override
    public void deleteNews(int newsId) throws RemoteException {
        newsList.removeIf(news -> news.getId() == newsId);
    }

    @Override
    public void createNews(String name, String title, String creationDate, String lastModification, String author, String content) throws RemoteException {
        News news = new News(newsIdCounter++, name, title, creationDate, lastModification, author, content);
        newsList.add(news);
    }

    @Override
    public List<News> getAllNews() throws RemoteException {
        return new ArrayList<>(newsList);
    }
}

