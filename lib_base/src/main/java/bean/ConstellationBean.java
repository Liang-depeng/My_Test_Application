package bean;

import java.util.ArrayList;


public class ConstellationBean extends CommonBean {

    private ArrayList<ResultBean> result;

    public ArrayList<ResultBean> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * astroid : 1
         * astroname : 白羊座
         * date : 3-21~4-19
         * pic : http://api.jisuapi.com/astro/static/images/baiyang.png
         */

        private int astroid;
        private String astroname;
        private String date;
        private String pic;

        public int getAstroid() {
            return astroid;
        }

        public void setAstroid(int astroid) {
            this.astroid = astroid;
        }

        public String getAstroname() {
            return astroname;
        }

        public void setAstroname(String astroname) {
            this.astroname = astroname;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
