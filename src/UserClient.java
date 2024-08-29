import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class UserClient {
    private static NewsService newsService;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            newsService = (NewsService) registry.lookup("NewsService");

            JFrame frame = new JFrame("Cliente de Noticias");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            frame.add(panel);
            placeComponents(panel);

            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(10, 80, 150, 25);
        panel.add(loginButton);

        JTextArea newsArea = new JTextArea();
        newsArea.setBounds(10, 120, 560, 200);
        panel.add(newsArea);

        JButton refreshButton = new JButton("Refrescar Noticias");
        refreshButton.setBounds(10, 330, 150, 25);
        panel.add(refreshButton);

        JButton createNewsButton = new JButton("Crear Noticia");
        createNewsButton.setBounds(170, 330, 150, 25);
        panel.add(createNewsButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = userText.getText();
                    String password = new String(passwordText.getPassword());
                    if (newsService.login(username, password)) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                        refreshNews(newsArea);
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al iniciar sesión.");
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshNews(newsArea);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al refrescar noticias.");
                }
            }
        });

        createNewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = JOptionPane.showInputDialog("Nombre de la noticia:");
                    String title = JOptionPane.showInputDialog("Titular:");
                    String creationDate = JOptionPane.showInputDialog("Fecha de creación:");
                    String lastModification = JOptionPane.showInputDialog("Última modificación:");
                    String author = JOptionPane.showInputDialog("Autor:");
                    String content = JOptionPane.showInputDialog("Contenido:");

                    newsService.createNews(name, title, creationDate, lastModification, author, content);
                    JOptionPane.showMessageDialog(null, "Noticia creada exitosamente.");
                    refreshNews(newsArea);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al crear noticia.");
                }
            }
        });
    }

    private static void refreshNews(JTextArea newsArea) throws Exception {
        List<News> newsList = newsService.getAllNews();
        StringBuilder newsText = new StringBuilder();
        for (News news : newsList) {
            newsText.append("ID: ").append(news.getId()).append("\n");
            newsText.append("Nombre: ").append(news.getName()).append("\n");
            newsText.append("Titular: ").append(news.getTitle()).append("\n");
            newsText.append("Fecha de creación: ").append(news.getCreationDate()).append("\n");
            newsText.append("Última modificación: ").append(news.getLastModification()).append("\n");
            newsText.append("Autor: ").append(news.getAuthor()).append("\n");
            newsText.append("Contenido: ").append(news.getContent()).append("\n");
            newsText.append("\n");
        }
        newsArea.setText(newsText.toString());
    }
}
