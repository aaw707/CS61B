package Project1;

public class pixel {
    short r;
    short g;
    short b;

    pixel() {
        r = 0;
        g = 0;
        b = 0;
    }

    pixel(short r, short g, short b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    pixel(int r, int g, int b) {
        this.r = (short) r;
        this.g = (short) g;
        this.b = (short) b;
    }

    public short getR() {
        return r;
    }

    public short getG() {
        return g;
    }

    public short getB() {
        return b;
    }

    public boolean equals(pixel p) {
        if (p == null || this == null) {
            return false;
        }
        if (this.r == p.r && this.g == p.g && this.b == p.b) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "(" + r + "," + g + "," + b + ")";
    }
}
