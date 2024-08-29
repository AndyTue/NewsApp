import java.io.Serializable;

public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String title;
    private String creationDate;
    private String lastModification;
    private String author;
    private String content;

    public News(int id, String name, String title, String creationDate, String lastModification, String author, String content) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.creationDate = creationDate;
        this.lastModification = lastModification;
        this.author = author;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getLastModification() {
        return lastModification;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
