package bean;

public class PhoneBean extends CommonBean{

    /**
     * result : {"shouji":"13456755448","province":"浙江","city":"杭州","company":"中国移动","cardtype":"GSM","areacode":"0571"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * shouji : 13456755448
         * province : 浙江
         * city : 杭州
         * company : 中国移动
         * cardtype : GSM
         * areacode : 0571
         */

        private String shouji;
        private String province;
        private String city;
        private String company;
        private String cardtype;
        private String areacode;

        public String getShouji() {
            return shouji;
        }

        public void setShouji(String shouji) {
            this.shouji = shouji;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }
    }
}
