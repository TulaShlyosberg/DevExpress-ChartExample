package GLCalendarGetter;

import java.util.Date;

public class DataItem {
    public String Id;
    public String Summary;
    public long StartTime;
    public long EndTime;

    public DataItem(String Id, String Summary, long StartTime, long EndTime) {
        this.Id = Id;
        this.Summary = Summary;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }

}