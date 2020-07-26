package cc.sfclub.mars.database.result;

public class Result implements IResult {
    private final int effectedLines;
    private final boolean isSucceed;

    public Result(int effectedLines, boolean isSucceed) {
        this.effectedLines = effectedLines;
        this.isSucceed = isSucceed;
    }

    @Override
    public int effectedLines() {
        return effectedLines;
    }

    @Override
    public boolean isSucceed() {
        return isSucceed;
    }
}
