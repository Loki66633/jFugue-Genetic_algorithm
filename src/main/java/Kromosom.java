import java.util.Objects;

public class Kromosom {
    private int size;
    private int ff;
    private String note;
    public int age;

    public Kromosom(String note, int size, int ff) {
        this.note=note;
        this.size = size;
        this.ff = ff;
        this.age = 0;
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
        return note.toCharArray();
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kromosom kromosom = (Kromosom) o;
        return Objects.equals(note, kromosom.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note);
    }
}
