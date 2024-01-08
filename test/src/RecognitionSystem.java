public class RecognitionSystem {           /*  车牌识别系统类  */
    private String type;                   /*  停车服务类型  */
    private double time;                   /*  停车时间  */
    private int days;                      /*  服务剩余天数  */

    public String getType() {
        return type;
    }  /*  获取停车服务类型  */

    public double getTime() {
        return time;
    }  /*  获取停车时间  */

    public int getDays() {
        return days;
    }     /*  获取服务剩余天数  */

    public double getAmount(String type, double time) {    /*  计算缴费金额（服务类型，停车时间）  */
        double price = 0;
        //根据服务类型，停车时长单价不同
        if (type.equals("包年")) price = 5.0;
        if (type.equals("包季")) price = 8.0;
        if (type.equals("包月")) price = 10.0;
        if (type.equals("临时停车")) price = 12.0;
        return price * time;
    }

    public void billRemind(int days, String type) {   /*  语音缴费提醒（服务剩余天数，服务类型）  */
        //若为临时停车则结果函数，若为长期停车服务，当剩余天数小于7，则推送语音提醒
        if (type.equals("临时停车")) return;
        else if (days < 7) System.out.println("发送语音提醒");
    }
}
