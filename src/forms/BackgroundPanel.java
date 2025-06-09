package forms; // Hoặc package components; nếu bạn đặt nó vào một package khác

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    private Image backgroundImage;

    /**
     * Khởi tạo BackgroundPanel với đường dẫn ảnh nền.
     * Ảnh sẽ được tải từ classpath.
     * @param imagePath Đường dẫn tới ảnh, ví dụ: "/images/your_background_image.jpg"
     */
    public BackgroundPanel(String imagePath) {
        // Tải ảnh từ đường dẫn. Đảm bảo đường dẫn đúng.
        // Dùng ClassLoader để tải ảnh từ classpath (thư mục resources/src)
        try {
            this.backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
            // Đặt panel này là trong suốt để các JInternalFrame bên trên có thể nhìn thấy nó.
            // Tuy nhiên, JDesktopPane sẽ được đặt Opaque(false) để đảm bảo độ trong suốt.
            // this.setOpaque(false); // Không cần thiết ở đây, JDesktopPane sẽ xử lý độ trong suốt
        } catch (Exception e) {
            System.err.println("Lỗi: Không thể tải ảnh nền từ đường dẫn: " + imagePath);
            e.printStackTrace();
            this.backgroundImage = null; // Đặt về null nếu lỗi, để tránh NullPointerException
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Đảm bảo các thành phần UI khác được vẽ đúng

        if (backgroundImage != null) {
            // Lấy kích thước hiện tại của panel
            int width = getWidth();
            int height = getHeight();

            // Vẽ ảnh nền và điều chỉnh kích thước để khớp với panel
            // Image.SCALE_SMOOTH để có chất lượng tốt hơn khi thay đổi kích thước
            g.drawImage(backgroundImage, 0, 0, width, height, this);
        }
    }
}