import javax.swing.*;
import java.awt.*;

public class assignment7 extends JFrame {
    private final JComboBox<String> algoBox;
    private final JComboBox<String> styleBox;
    private final JTextField xcField;
    private final JTextField ycField;
    private final JTextField rField;
    private final DrawPanel drawPanel;

    public assignment7() {
        setTitle("Circle Drawing Algorithms");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] algos = {"DDA", "Bresenham", "Midpoint"};
        String[] styles = {"Solid", "Dotted", "Dashed"};

        algoBox = new JComboBox<>(algos);
        styleBox = new JComboBox<>(styles);
        xcField = new JTextField("350", 4);
        ycField = new JTextField("350", 4);
        rField = new JTextField("150", 4);
        JButton drawBtn = new JButton("Draw");

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Algorithm:"));
        controlPanel.add(algoBox);
        controlPanel.add(new JLabel("Style:"));
        controlPanel.add(styleBox);
        controlPanel.add(new JLabel("Center X:"));
        controlPanel.add(xcField);
        controlPanel.add(new JLabel("Center Y:"));
        controlPanel.add(ycField);
        controlPanel.add(new JLabel("Radius:"));
        controlPanel.add(rField);
        controlPanel.add(drawBtn);

        add(controlPanel, BorderLayout.NORTH);

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        drawBtn.addActionListener(_ -> {
            int xc = Integer.parseInt(xcField.getText());
            int yc = Integer.parseInt(ycField.getText());
            int r = Integer.parseInt(rField.getText());
            int algo = algoBox.getSelectedIndex();
            int style = styleBox.getSelectedIndex();

            drawPanel.setParameters(xc, yc, r, algo, style);
            drawPanel.repaint();
        });
    }

    static class DrawPanel extends JPanel {
        private int xc, yc, r, algo, style;

        public void setParameters(int xc, int yc, int r, int algo, int style) {
            this.xc = xc;
            this.yc = yc;
            this.r = r;
            this.algo = algo;
            this.style = style;
        }

        private void putPixel(Graphics g, int x, int y, int count) {
            boolean drawPixel = false;
            if (style == 0) drawPixel = true; // Solid
            else if (style == 1 && (count % 6 == 0)) drawPixel = true; // Dotted (every 6th pixel)
            else if (style == 2 && ((count / 6) % 2 == 0)) drawPixel = true; // Dashed (alternates 6 pixels on/off)
            if (drawPixel) {
                g.drawLine(xc + x, yc + y, xc + x, yc + y);
                g.drawLine(xc - x, yc + y, xc - x, yc + y);
                g.drawLine(xc + x, yc - y, xc + x, yc - y);
                g.drawLine(xc - x, yc - y, xc - x, yc - y);
                g.drawLine(xc + y, yc + x, xc + y, yc + x);
                g.drawLine(xc - y, yc + x, xc - y, yc + x);
                g.drawLine(xc + y, yc - x, xc + y, yc - x);
                g.drawLine(xc - y, yc - x, xc - y, yc - x);
            }
        }

        private void drawCircleDDA(Graphics g) {
            int count = 0;
            for (int angle = 0; angle < 360; angle++) {
                double rad = Math.toRadians(angle);
                int x = (int) (r * Math.cos(rad));
                int y = (int) (r * Math.sin(rad));
                putPixel(g, x, y, count++);
            }
        }

        private void drawCircleBresenham(Graphics g) {
            int x = 0, y = r, d = 3 - 2 * r;
            int count = 0;
            while (x <= y) {
                putPixel(g, x, y, count++);
                x++;
                if (d > 0) {
                    y--;
                    d += 4 * (x - y) + 10;
                } else {
                    d += 4 * x + 6;
                }
            }
        }

        private void drawCircleMidpoint(Graphics g) {
            int x = 0, y = r;
            int p = 1 - r;
            int count = 0;
            while (x <= y) {
                putPixel(g, x, y, count++);
                x++;
                if (p < 0) {
                    p += 2 * x + 1;
                } else {
                    y--;
                    p += 2 * (x - y) + 1;
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            if (r <= 0) return;

            switch (algo) {
                case 0:
                    drawCircleDDA(g);
                    break;
                case 1:
                    drawCircleBresenham(g);
                    break;
                case 2:
                    drawCircleMidpoint(g);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            assignment7 window = new assignment7();
            window.setVisible(true);
        });
    }
}
