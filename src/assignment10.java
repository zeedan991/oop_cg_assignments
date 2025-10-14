import java.util.Scanner;

public class assignment10 {
    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }

    // Apply Translation
    static Point translate(Point p, double tx, double ty) {
        double xNew = p.x + tx;
        double yNew = p.y + ty;
        return new Point(xNew, yNew);
    }

    // Apply Rotation (about origin)
    static Point rotate(Point p, double angleDeg, char axis) {
        double angleRad = Math.toRadians(angleDeg);
        double xNew = p.x, yNew = p.y;
        if (axis == 'x' || axis == 'X') { // Rotation about X (i.e., Y axis only moves)
            xNew = p.x;
            yNew = p.y * Math.cos(angleRad) - 0; // No Z axis in pure 2D, so only rotate y
        } else if (axis == 'y' || axis == 'Y') {
            xNew = p.x * Math.cos(angleRad) - 0; // No Z axis, only x moves
            yNew = p.y;
        } else { // Rotation in 2D about origin
            xNew = p.x * Math.cos(angleRad) - p.y * Math.sin(angleRad);
            yNew = p.x * Math.sin(angleRad) + p.y * Math.cos(angleRad);
        }
        return new Point(xNew, yNew);
    }

    // Apply Scaling
    static Point scale(Point p, double sx, double sy) {
        double xNew = p.x * sx;
        double yNew = p.y * sy;
        return new Point(xNew, yNew);
    }

    // Apply Shear
    static Point shear(Point p, double shX, double shY) {
        double xNew = p.x + shX * p.y;
        double yNew = p.y + shY * p.x;
        return new Point(xNew, yNew);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many points in object? ");
        int n = sc.nextInt();
        Point[] obj = new Point[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter x y for point " + (i+1) + ": ");
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            obj[i] = new Point(x, y);
        }
        while (true) {
            System.out.println("\nTransformation Menu:");
            System.out.println("1: Translation");
            System.out.println("2: Rotation");
            System.out.println("3: Scaling");
            System.out.println("4: Shear");
            System.out.println("5: Exit");
            System.out.print("Select option: ");
            int choice = sc.nextInt();
            if (choice == 5) break;
            for (int i = 0; i < n; i++) {
                switch (choice) {
                    case 1:
                        System.out.print("Enter tx, ty: ");
                        double tx = sc.nextDouble(), ty = sc.nextDouble();
                        obj[i] = translate(obj[i], tx, ty);
                        break;
                    case 2:
                        System.out.print("Enter rotation angle in degrees: ");
                        double angle = sc.nextDouble();
                        System.out.print("Rotate about axis (X/Y/origin): ");
                        char axis = sc.next().charAt(0);
                        obj[i] = rotate(obj[i], angle, axis);
                        break;
                    case 3:
                        System.out.print("Enter sx, sy: ");
                        double sx = sc.nextDouble(), sy = sc.nextDouble();
                        obj[i] = scale(obj[i], sx, sy);
                        break;
                    case 4:
                        System.out.print("Enter shear factors shX, shY: ");
                        double shX = sc.nextDouble(), shY = sc.nextDouble();
                        obj[i] = shear(obj[i], shX, shY);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }
            System.out.println("\nTransformed object:");
            for (int i = 0; i < n; i++) {
                System.out.printf("Point %d: (%.2f, %.2f)\n", i+1, obj[i].x, obj[i].y);
            }
        }
        sc.close();
    }
}
