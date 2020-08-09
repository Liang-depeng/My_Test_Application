package bean;

/**
 * @author mini
 * @date 2019/10/21
 */
public class CommonBean {

    /**
     * status : 0
     * msg : ok
     * result : {"start":"杭州","end":"北京","ishigh":"0","date":"2019-10-21","list":[{"trainno":"G58","type":"G","station":"杭州东","endstation":"北京南","departuretime":"08:50","arrivaltime":"14:43","sequenceno":5,"costtime":"5时53分","distance":0,"isend":1,"trainno12306":"5e00000G5840","typename":"高铁","priceyd":null,"priceed":null},{"trainno":"G46","type":"G","station":"杭州东","endstation":"北京南","departuretime":"09:50","arrivaltime":"15:43","sequenceno":7,"costtime":"5时53分","distance":1279,"isend":1,"trainno12306":"5600000G4650","pricesw":1701,"pricetd":null,"pricegr1":null,"pricegr2":null,"pricerw1":null,"pricerw2":null,"priceyw1":null,"priceyw2":null,"priceyw3":null,"typename":"高铁","priceyd":"907.0","priceed":"538.5"},{"trainno":"G168","type":"G","station":"杭州东","endstation":"北京南","departuretime":"11:39","arrivaltime":"18:13","sequenceno":9,"costtime":"6时34分","distance":0,"isend":1,"trainno12306":"5j0000G16822","pricesw":1701,"pricetd":null,"pricegr1":null,"pricegr2":null,"pricerw1":null,"pricerw2":null,"priceyw1":null,"priceyw2":null,"priceyw3":null,"typename":"高铁","priceyd":"907.0","priceed":"538.5"},{"trainno":"G56","type":"G","station":"杭州东","endstation":"北京南","departuretime":"12:47","arrivaltime":"18:58","sequenceno":10,"costtime":"1天6时11分","distance":0,"isend":1,"trainno12306":"5800000G560J","pricesw":1701,"pricetd":null,"pricegr1":null,"pricegr2":null,"pricerw1":null,"pricerw2":null,"priceyw1":null,"priceyw2":null,"priceyw3":null,"typename":"高铁","priceyd":"907.0","priceed":"538.5"},{"trainno":"G164","type":"G","station":"杭州东","endstation":"北京南","departuretime":"13:41","arrivaltime":"19:53","sequenceno":13,"costtime":"6时12分","distance":0,"isend":1,"trainno12306":"5j0000G16422","pricesw":1701,"pricetd":null,"pricegr1":null,"pricegr2":null,"pricerw1":null,"pricerw2":null,"priceyw1":null,"priceyw2":null,"priceyw3":null,"typename":"高铁","priceyd":"907.0","priceed":"538.5"},{"trainno":"G166","type":"G","station":"杭州东","endstation":"北京南","departuretime":"15:12","arrivaltime":"20:58","sequenceno":2,"costtime":"5时46分","distance":1236,"isend":1,"trainno12306":"5t0000G16600","pricesw":1701,"pricetd":null,"pricegr1":null,"pricegr2":null,"pricerw1":null,"pricerw2":null,"priceyw1":null,"priceyw2":null,"priceyw3":null,"typename":"高铁","priceyd":"907.0","priceed":"538.5"},{"trainno":"G44","type":"G","station":"杭州东","endstation":"北京南","departuretime":"16:15","arrivaltime":"23:08","sequenceno":1,"costtime":"6时53分","distance":1477,"isend":1,"trainno12306":"5600000G4400","pricesw":1966,"pricetd":null,"pricegr1":null,"pricegr2":null,"pricerw1":null,"pricerw2":null,"priceyw1":null,"priceyw2":null,"priceyw3":null,"typename":"高铁","priceyd":"1048.5","priceed":"626.0"},{"trainno":"D718","type":"D","station":"杭州","endstation":"北京","departuretime":"17:32","arrivaltime":"07:07","sequenceno":1,"costtime":"13时35分","distance":0,"isend":1,"trainno12306":"560000D71800","pricesw":null,"pricetd":null,"pricegr1":null,"pricegr2":null,"pricerw1":957,"pricerw2":null,"priceyw1":756,"priceyw2":null,"priceyw3":null,"typename":"动车","priceyd":null,"priceed":"504.0"},{"trainno":"D712","type":"D","station":"杭州","endstation":"北京","departuretime":"20:05","arrivaltime":"09:36","sequenceno":1,"costtime":"13时31分","distance":0,"isend":1,"trainno12306":"560000D71201","pricesw":null,"pricetd":null,"pricegr1":null,"pricegr2":null,"pricerw1":957,"pricerw2":null,"priceyw1":756,"priceyw2":null,"priceyw3":null,"typename":"动车","priceyd":null,"priceed":"504.0"}]}
     */

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
