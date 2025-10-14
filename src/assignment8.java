import javax.swing.*;
import java.awt.*;
import java.util.*;

public class assignment8 extends JFrame {
    private final DrawPanel drawPanel;
    private final JComboBox<String> algorithmBox;

    public assignment8() {
        setTitle("Polygon Fill Algorithms");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Algorithm:"));

        String[] algorithms = {"Scan Fill", "Flood Fill", "Seed Fill"};
        algorithmBox = new JComboBox<>(algorithms);
        controlPanel.add(algorithmBox);

        JButton drawButton = new JButton("Draw Polygon");
        JButton fillButton = new JButton("Fill Polygon");
        JButton clearButton = new JButton("Clear");

        controlPanel.add(drawButton);
        controlPanel.add(fillButton);
        controlPanel.add(clearButton);

        add(controlPanel, BorderLayout.NORTH);

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        drawButton.addActionListener(_ -> drawPanel.drawConcavePolygon());
        fillButton.addActionListener(_ -> {
            String selected = (String) algorithmBox.getSelectedItem();
            drawPanel.fillPolygon(selected);
        });
        clearButton.addActionListener(_ -> drawPanel.clear());

        setVisible(true);
    }

    static class DrawPanel extends JPanel {
        private int[] xPoints;
        private int[] yPoints;
        private int nPoints;
        private boolean[][] filled;

        public DrawPanel() {
            setBackground(Color.WHITE);
        }

        public void drawConcavePolygon() {
            xPoints = new int[]{100, 250, 250, 300, 250, 250, 100};
            yPoints = new int[]{150, 150, 100, 200, 300, 250, 250};
            nPoints = 7;
            filled = new boolean[800][600];
            repaint();
        }

        public void fillPolygon(String algorithm) {
            if (xPoints == null) {
                return;
            }

            switch (algorithm) {
                case "Scan Fill" -> scanFill();
                case "Flood Fill" -> floodFill();
                case "Seed Fill" -> seedFill();
            }
            repaint();
        }

        private void scanFill() {
            int minY = 600, maxY = 0;
            for (int y : yPoints) {
                if (y < minY) minY = y;
                if (y > maxY) maxY = y;
            }

            for (int y = minY; y <= maxY; y++) {
                ArrayList<Integer> intersections = new ArrayList<>();

                for (int i = 0; i < nPoints; i++) {
                    int j = (i + 1) % nPoints;
                    int y1 = yPoints[i];
                    int y2 = yPoints[j];
                    int x1 = xPoints[i];
                    int x2 = xPoints[j];

                    if ((y1 <= y && y < y2) || (y2 <= y && y < y1)) {
                        int x = x1 + (y - y1) * (x2 - x1) / (y2 - y1);
                        intersections.add(x);
                    }
                }

                Collections.sort(intersections);

                for (int i = 0; i < intersections.size() - 1; i += 2) {
                    for (int x = intersections.get(i); x <= intersections.get(i + 1); x++) {
                        if (x >= 0 && x < 800 && y >= 0 && y < 600) {
                            filled[x][y] = true;
                        }
                    }
                }
            }
        }

        private void floodFill() {
            if (filled[175][200]) {
                return;
            }

            Stack<int[]> stack = new Stack<>();
            stack.push(new int[]{175, 200});

            while (!stack.isEmpty()) {
                int[] p = stack.pop();
                int px = p[0];
                int py = p[1];

                if (px < 0 || px >= 800 || py < 0 || py >= 600 || filled[px][py]) {
                    continue;
                }

                if (isInsidePolygon(px, py)) {
                    filled[px][py] = true;
                    stack.push(new int[]{px + 1, py});
                    stack.push(new int[]{px - 1, py});
                    stack.push(new int[]{px, py + 1});
                    stack.push(new int[]{px, py - 1});
                }
            }
        }

        private void seedFill() {
            if (filled[175][200]) {
                return;
            }

            Stack<int[]> stack = new Stack<>();
            stack.push(new int[]{175, 200});

            while (!stack.isEmpty()) {
                int[] p = stack.pop();
                int px = p[0];
                int py = p[1];

                if (px < 0 || px >= 800 || py < 0 || py >= 600 || filled[px][py]) {
                    continue;
                }

                if (isInsidePolygon(px, py)) {
                    filled[px][py] = true;
                    stack.push(new int[]{px + 1, py});
                    stack.push(new int[]{px - 1, py});
                    stack.push(new int[]{px, py + 1});
                    stack.push(new int[]{px, py - 1});
                }
            }
        }

        private boolean isInsidePolygon(int x, int y) {
            int count = 0;
            for (int i = 0; i < nPoints; i++) {
                int j = (i + 1) % nPoints;
                int x1 = xPoints[i];
                int y1 = yPoints[i];
                int x2 = xPoints[j];
                int y2 = yPoints[j];

                if ((y1 <= y && y < y2) || (y2 <= y && y < y1)) {
                    int xIntersect = x1 + (y - y1) * (x2 - x1) / (y2 - y1);
                    if (x < xIntersect) {
                        count++;
                    }
                }
            }
            return (count % 2 == 1);
        }

        public void clear() {
            xPoints = null;
            yPoints = null;
            filled = new boolean[800][600];
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (filled != null) {
                g.setColor(Color.BLUE);
                for (int x = 0; x < 800; x++) {
                    for (int y = 0; y < 600; y++) {
                        if (filled[x][y]) {
                            g.fillRect(x, y, 1, 1);
                        }
                    }
                }
            }

            if (xPoints != null) {
                g.setColor(Color.BLACK);
                g.drawPolygon(xPoints, yPoints, nPoints);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(assignment8::new);
    }
}