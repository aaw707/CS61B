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

    public short getR() {
        return r;
    }

    public short getG() {
        return g;
    }

    public short getB() {
        return b;
    }
}
