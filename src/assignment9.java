public class assignment9 {
    // Region codes
    static final int INSIDE = 0; // 0000
    static final int LEFT   = 1; // 0001
    static final int RIGHT  = 2; // 0010
    static final int BOTTOM = 4; // 0100
    static final int TOP    = 8; // 1000

    // Clipping window
    static final int x_min = 4;
    static final int y_min = 4;
    static final int x_max = 10;
    static final int y_max = 8;

    // Compute region code for a point (x, y)
    static int computeCode(double x, double y) {
        int code = INSIDE;
        if (x < x_min)
            code |= LEFT;
        else if (x > x_max)
            code |= RIGHT;
        if (y < y_min)
            code |= BOTTOM;
        else if (y > y_max)
            code |= TOP;
        return code;
    }

    // Cohen-Sutherland line clipping routine
    static void lineClip(double x1, double y1, double x2, double y2) {
        int code1 = computeCode(x1, y1);
        int code2 = computeCode(x2, y2);
        boolean accept = false;

        while (true) {
            if ((code1 == 0) && (code2 == 0)) {
                // Both endpoints inside: trivially accept
                accept = true;
                break;
            } else if ((code1 & code2) != 0) {
                // Both endpoints share an outside zone: trivially reject
                break;
            } else {
                // Some segment is inside; find intersection
                double x = 0, y = 0;
                int codeOut = (code1 != 0) ? code1 : code2;

                if ((codeOut & TOP) != 0) {
                    x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
                    y = y_max;
                } else if ((codeOut & BOTTOM) != 0) {
                    x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
                    y = y_min;
                } else if ((codeOut & RIGHT) != 0) {
                    y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
                    x = x_max;
                } else if ((codeOut & LEFT) != 0) {
                    y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
                    x = x_min;
                }

                // Replace outside endpoint by intersection point
                if (codeOut == code1) {
                    x1 = x;
                    y1 = y;
                    code1 = computeCode(x1, y1);
                } else {
                    x2 = x;
                    y2 = y;
                    code2 = computeCode(x2, y2);
                }
            }
        }
        if (accept) {
            System.out.printf("Accepted Line: (%.2f, %.2f) to (%.2f, %.2f)\n", x1, y1, x2, y2);
        } else {
            System.out.println("Line rejected.");
        }
    }

    public static void main(String[] args) {
        // Example: Try clipping 3 sample lines
        lineClip(5, 5, 7, 7);      // Fully inside: accepted
        lineClip(7, 9, 11, 4);     // Partially inside: clipped and accepted
        lineClip(1, 5, 4, 1);      // Fully outside: rejected
    }
}
