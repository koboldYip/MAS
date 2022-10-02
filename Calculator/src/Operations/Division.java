package Operations;

public class Division implements Operation {
    @Override
    public int execute(int first, int second) {
        return Math.floorDiv(first, second);
    }
}
