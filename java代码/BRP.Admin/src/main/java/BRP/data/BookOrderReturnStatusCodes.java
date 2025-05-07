package BRP.data;

public class BookOrderReturnStatusCodes {
    /**
     * 待提交
     */
    public static String Submitting = "Submitting";
    /**
     * 待客服处理
     */
    public static String Approving = "Approving";
    /**
     * 待还入云库
     */
    public static String CloudStockIn  = "CloudStockIn";
    /**
     * 已赔偿
     */
    public static String Compensated = "Compensated";
    /**
     * 待赔偿
     */
    public static String Compensating = "Compensating";
    /**
     * 破损反馈核实中
     */
    public static String DamageChecking = "DamageChecking";
    /**
     * 已入库
     */
    public static String Finished = "Finished";
    /**
     * 线下已还，等待入库
     */
    public static String OfflineReturned = "OfflineReturned";
    /**
     * 取书异常已处理
     */
    public static String RetrieveErrorProcessed = "RetrieveErrorProcessed";
    /**
     * 取书异常处理中
     */
    public static String RetrieveErrorProcessing = "RetrieveErrorProcessing";
    /**
     * 待取书
     */
    public static String Retrieving = "Retrieving";
    /**
     * 待消毒
     */
    public static String Sterilizing = "Sterilizing";
    /**
     * 已取消
     */
    public static String Cancelled = "Cancelled";
    /**
     * 审核未通过
     */
    public static String Rejected = "Rejected";
}
