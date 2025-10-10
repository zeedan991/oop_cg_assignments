import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class assignment6 extends JPanel {
    public assignment6() {
    }

    private void drawLineDDA(Graphics var1, int var2, int var3, int var4, int var5, String var6) {
        int var7 = var4 - var2;
        int var8 = var5 - var3;
        int var9 = Math.max(Math.abs(var7), Math.abs(var8));
        float var10 = (float)var7 / (float)var9;
        float var11 = (float)var8 / (float)var9;
        float var12 = (float)var2;
        float var13 = (float)var3;

        for(int var14 = 0; var14 <= var9; ++var14) {
            switch (var6) {
                case "DOTTED":
                    if (var14 % 2 == 0) {
                        var1.fillRect(Math.round(var12), Math.round(var13), 1, 1);
                    }
                    break;
                case "THICK":
                    var1.fillRect(Math.round(var12), Math.round(var13), 2, 2);
                    break;
                default:
                    var1.fillRect(Math.round(var12), Math.round(var13), 1, 1);
            }

            var12 += var10;
            var13 += var11;
        }

    }

    private void drawLineBresenham(Graphics var1, int var2, int var3, int var4, int var5, String var6) {
        int var7 = Math.abs(var4 - var2);
        int var8 = Math.abs(var5 - var3);
        int var9 = var2 < var4 ? 1 : -1;
        int var10 = var3 < var5 ? 1 : -1;
        int var11 = var7 - var8;
        int var12 = 0;

        while(true) {
            if (var6.equals("DASHED")) {
                if (var12 / 5 % 2 == 0) {
                    var1.fillRect(var2, var3, 1, 1);
                }
            } else {
                var1.fillRect(var2, var3, 1, 1);
            }

            if (var2 == var4 && var3 == var5) {
                return;
            }

            int var13 = 2 * var11;
            if (var13 > -var8) {
                var11 -= var8;
                var2 += var9;
            }

            if (var13 < var7) {
                var11 += var7;
                var3 += var10;
            }

            ++var12;
        }
    }

    protected void paintComponent(Graphics var1) {
        super.paintComponent(var1);
        this.setBackground(Color.lightGray);
        var1.setColor(Color.BLACK);
        this.drawLineDDA(var1, 50, 50, 250, 50, "DOTTED");
        this.drawLineDDA(var1, 250, 50, 250, 200, "DOTTED");
        this.drawLineDDA(var1, 250, 200, 50, 200, "DOTTED");
        this.drawLineDDA(var1, 50, 200, 50, 50, "DOTTED");
        var1.setColor(Color.GREEN);
        this.drawLineDDA(var1, 85, 100, 215, 100, "THICK");
        this.drawLineDDA(var1, 215, 100, 215, 150, "THICK");
        this.drawLineDDA(var1, 215, 150, 85, 150, "THICK");
        this.drawLineDDA(var1, 85, 150, 85, 100, "THICK");
        short var2 = 150;
        byte var3 = 125;
        var1.setColor(Color.RED);
        this.drawLineBresenham(var1, var2, 50, 250, var3, "THICK");
        this.drawLineBresenham(var1, 250, var3, var2, 200, "THICK");
        this.drawLineBresenham(var1, var2, 200, 50, var3, "THICK");
        this.drawLineBresenham(var1, 50, var3, var2, 50, "THICK");
    }

    public static void main(String[] var0) {
        JFrame var1 = new JFrame("Pattern Drawing: DDA + Bresenham");
        var1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var1.setSize(500, 300);
        var1.add(new assignment6());
        var1.setVisible(true);
    }
}