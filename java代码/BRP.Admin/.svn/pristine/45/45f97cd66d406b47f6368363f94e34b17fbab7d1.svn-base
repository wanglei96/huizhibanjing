package strosoft.app.util;

public class ExceptionHelper {

    /**
     *  恩成字符串
     * @param ex
     * @return
     */
    public static String toString(Exception ex){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ex.getStackTrace().length; i++) {
            if (i > 0) {
                sb.append("\r\n");
            }
            StackTraceElement curStackTraceElement = ex.getStackTrace()[i];
            sb.append(curStackTraceElement.toString());
        }
        String strStackTrace = sb.toString();
        return strStackTrace;
    }

    /**
     * 跟踪
     * @param ex
     */
    public static void trace(Exception ex){
        ex.printStackTrace();
        String strStackTrace = ExceptionHelper.toString(ex);
        Tracer.writeLine(strStackTrace);
    }
    /**
     * 跟踪
     * @param ex
     */
    public static void log(Exception ex){
        String strStackTrace = ExceptionHelper.toString(ex);
        Logger.writeLine(strStackTrace);
    }
}
