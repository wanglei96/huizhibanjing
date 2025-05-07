package strosoft.app.util;

/*
 * 分页帮助类
 */
public class PagerHelper {

	/*
	 * 取得页数
	 */
	public static int getPageCount(int recordCount, int pageSize) {
		int pageCount = recordCount / pageSize
				+ (recordCount % pageSize == 0 ? 0 : 1);
		return pageCount;
	}
}
