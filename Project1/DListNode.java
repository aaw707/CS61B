package Project1;
// I wrote this myself

public class DListNode {
    public pixel p;
    public int runLength;
    public DListNode prev;
    public DListNode next;

    public DListNode() {
        p = new pixel();
        runLength = 0;
        prev = null;
        next = null;
    }

    public DListNode(pixel p, int runLength) {
        this.p = p;
        this.runLength = runLength;
        prev = null;
        next = null;
    }

}
