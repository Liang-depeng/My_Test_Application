package bean;

/**
 * @author mini
 * @date 2019/10/21
 */
public class TrainDetailBean {

    /**
     * trainno : G58
     * type : G
     * station : 杭州东
     * endstation : 北京南
     * departuretime : 08:50
     * arrivaltime : 14:43
     * sequenceno : 5
     * costtime : 5时53分
     * distance : 0
     * isend : 1
     * trainno12306 : 5e00000G5840
     * typename : 高铁
     * priceyd : null
     * priceed : null
     * pricesw : 1701
     * pricetd : null
     * pricegr1 : null
     * pricegr2 : null
     * pricerw1 : null
     * pricerw2 : null
     * priceyw1 : null
     * priceyw2 : null
     * priceyw3 : null
     */

    private String trainno;
    private String type;
    private String station;
    private String endstation;
    private String departuretime;
    private String arrivaltime;
    private int sequenceno;
    private String costtime;
    private int distance;
    private int isend;
    private String trainno12306;
    private String typename;
    private Object priceyd;
    private Object priceed;
    private Object pricesw;
    private Object pricetd;
    private Object pricegr1;
    private Object pricegr2;
    private Object pricerw1;
    private Object pricerw2;
    private Object priceyw1;
    private Object priceyw2;
    private Object priceyw3;

    public String getTrainno() {
        return trainno;
    }

    public void setTrainno(String trainno) {
        this.trainno = trainno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getEndstation() {
        return endstation;
    }

    public void setEndstation(String endstation) {
        this.endstation = endstation;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public int getSequenceno() {
        return sequenceno;
    }

    public void setSequenceno(int sequenceno) {
        this.sequenceno = sequenceno;
    }

    public String getCosttime() {
        return costtime;
    }

    public void setCosttime(String costtime) {
        this.costtime = costtime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getIsend() {
        return isend;
    }

    public void setIsend(int isend) {
        this.isend = isend;
    }

    public String getTrainno12306() {
        return trainno12306;
    }

    public void setTrainno12306(String trainno12306) {
        this.trainno12306 = trainno12306;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getPriceyd() {
        return ((String) priceyd);
    }

    public void setPriceyd(Object priceyd) {
        this.priceyd = priceyd;
    }

    public Object getPriceed() {
        return priceed;
    }

    public void setPriceed(Object priceed) {
        this.priceed = priceed;
    }

    public String getPricesw() {
        return String.valueOf(pricesw);
    }

    public void setPricesw(int pricesw) {
        this.pricesw = pricesw;
    }

    public Object getPricetd() {
        return pricetd;
    }

    public void setPricetd(Object pricetd) {
        this.pricetd = pricetd;
    }

    public Object getPricegr1() {
        return pricegr1;
    }

    public void setPricegr1(Object pricegr1) {
        this.pricegr1 = pricegr1;
    }

    public Object getPricegr2() {
        return pricegr2;
    }

    public void setPricegr2(Object pricegr2) {
        this.pricegr2 = pricegr2;
    }

    public Object getPricerw1() {
        return pricerw1;
    }

    public void setPricerw1(Object pricerw1) {
        this.pricerw1 = pricerw1;
    }

    public Object getPricerw2() {
        return pricerw2;
    }

    public void setPricerw2(Object pricerw2) {
        this.pricerw2 = pricerw2;
    }

    public Object getPriceyw1() {
        return priceyw1;
    }

    public void setPriceyw1(Object priceyw1) {
        this.priceyw1 = priceyw1;
    }

    public Object getPriceyw2() {
        return priceyw2;
    }

    public void setPriceyw2(Object priceyw2) {
        this.priceyw2 = priceyw2;
    }

    public Object getPriceyw3() {
        return priceyw3;
    }

    public void setPriceyw3(Object priceyw3) {
        this.priceyw3 = priceyw3;
    }

}
