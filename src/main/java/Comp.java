public class Comp implements java.util.Comparator<Kromosom> {
    @Override
    public int compare(Kromosom o1, Kromosom o2) {
        if (o1.getFf() > o2.getFf()) {
            return -1;
        } else if (o1.getFf() < o2.getFf()) {
            return 1;
        }
        return 0;

    }
}
