import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AdminClient {
    private static NewsService newsService;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            newsService = (NewsService) registry.lookup("NewsService");

            JFrame frame = new JFrame("Administrador de Noticias");
            frame.setSize(400, 300);
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

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 80, 80, 25);
        panel.add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(100, 80, 165, 25);
        panel.add(idText);

        JButton addButton = new JButton("Agregar Usuario");
        addButton.setBounds(10, 110, 150, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = userText.getText();
                    String password = new String(passwordText.getPassword());
                    int id = Integer.parseInt(idText.getText());
                    newsService.addUser(username, password, id);
                    JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al agregar usuario.");
                }
            }
        });

        JLabel deleteNewsLabel = new JLabel("ID de noticia a eliminar:");
        deleteNewsLabel.setBounds(10, 140, 150, 25);
        panel.add(deleteNewsLabel);

        JTextField newsIdText = new JTextField(20);
        newsIdText.setBounds(160, 140, 165, 25);
        panel.add(newsIdText);

        JButton deleteButton = new JButton("Eliminar Noticia");
        deleteButton.setBounds(10, 170, 150, 25);
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int newsId = Integer.parseInt(newsIdText.getText());
                    newsService.deleteNews(newsId);
                    JOptionPane.showMessageDialog(null, "Noticia eliminada exitosamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar noticia.");
                }
            }
        });
    }
}

