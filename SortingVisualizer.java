import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SortingVisualizer extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private int[] array;
    private int currentIndex = -1;
    private int compareIndex = -1;

    public SortingVisualizer() {
        array = generatedRandomArray(50);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        new Timer(1000,e->{
            bubbleSort();
            repaint();
        }).start();

    }

    private int[] generatedRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(HEIGHT - 50) + 20;
        }

        return arr;
    }

    private void bubbleSort() {
        if (currentIndex > array.length - 1) {
            currentIndex = -1;

        }

        currentIndex++;
        compareIndex = currentIndex + 1;

        for (int i = 0; i < array.length - currentIndex - 1; i++) {
            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;

            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int barWidth = WIDTH / array.length;

        for (int i = 0; i < array.length; i++) {

            // Colores dinámico
            if (i == currentIndex || i == compareIndex) {
                g2d.setColor(Color.RED);
            } else if (i > array.length - currentIndex) {
                g2d.setColor(Color.GREEN);
            } else {
                float hue = (float) array[i] / HEIGHT;
                g2d.setColor(Color.getHSBColor(hue, 0.8f, 1.0f));
            }

            int barHeight = array[i];
            int x = i * barWidth;
            int y = HEIGHT - barHeight;

            g2d.fillRect(x, y, barWidth - 1, barHeight);
            g2d.setColor(Color.WHITE);
            g2d.drawRect(x, y, barWidth - 1, barHeight);

        }

        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Bubble Sort" + array.length, 20, 30);
        g2d.drawString("Comparando indices" + currentIndex + "y" + compareIndex, 20, 55);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BubbleSort");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new SortingVisualizer());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            JOptionPane.showMessageDialog(frame, "Observa el algorimto buuble sort en toda su potencia",
                    "Poyecto java para estudiantes",
                    JOptionPane.INFORMATION_MESSAGE);

        });

    }

}