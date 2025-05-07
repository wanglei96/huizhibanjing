package strosoft.sys.model;

import strosoft.sys.service.ExportHandlerFactory;

public class ExportManager {

	private static ExportManager _instance;

	public static ExportManager getInstance() {
		if (_instance == null) {
			_instance = new ExportManager();
		}
		return _instance;
	}

	private ExportManager() {
	}

	/**
	 * 导出
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public void export(ExportContext context) throws Exception {
		ExportHandler theExportHandler = ExportHandlerFactory.GetHandler(context.getExportName());
		theExportHandler.process(context);
	}
}
