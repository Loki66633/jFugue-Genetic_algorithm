public class Kromosom {
    private int size;
    private int ff;
    private char[] note;

    public Kromosom(char[] note, int size, int ff) {
        this.note=note;
        this.size = size;
        this.ff = ff;
    }





    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFf() {
        return ff;
    }

    public void setFf(int ff) {
        this.ff = ff;
    }

    public char[] getNote() {
        return note;
    }

    public void setNote(char[] note) {
        this.note = note;
    }
}
