import org.junit.Test;

import static org.junit.Assert.*;

public class RecognitionSystemTest {
    RecognitionSystem recognition = new RecognitionSystem();

    @Test
    public void getAmount() {                       /*  计算缴费金额（服务类型，停车时间）*/
        //输入停车类型和停车时间,计算缴费金额
        double amount = new RecognitionSystem().getAmount("包月", 0);      /*  停车时间为0  */
        System.out.println(amount);
    }

    @Test
    public void billRemind() {
        new RecognitionSystem().billRemind(7, "包月");     /*  边界情况  */
    }
}