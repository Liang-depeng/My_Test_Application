package bean;

import java.util.ArrayList;
import java.util.List;

public class HistoryTodayBean extends CommonBean {

    private ArrayList<ContentBean> result;

    public ArrayList<ContentBean> getResult() {
        return result;
    }

    public void setResult(ArrayList<ContentBean> result) {
        this.result = result;
    }

    public static class ContentBean {

        private String title;
        private String year;
        private String month;
        private String day;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
