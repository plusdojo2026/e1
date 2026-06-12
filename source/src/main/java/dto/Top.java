package dto;

public class Top {

    // ランキング表示用
    private String itemName;
    private int count;

    // 年間忘れ物数
    private int yearlyCount;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getYearlyCount() {
        return yearlyCount;
    }

    public void setYearlyCount(int yearlyCount) {
        this.yearlyCount = yearlyCount;
    }
}