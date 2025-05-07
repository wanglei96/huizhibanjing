package strosoft.cg.model;

import strosoft.cg.data.CgProjectInfo;
import strosoft.cg.data.CgProjectGenerateParameter;

public class CreateCodeThread implements Runnable{
	private CgProjectInfo project = null;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		CgProjectGenerateParameter projectGenerateParameter = new CgProjectGenerateParameter();
		projectGenerateParameter.setThreadId(Thread.currentThread().getId());
		projectGenerateParameter.setProjectName(project.getProjectName());
		CodeGenerateProject codeGenerateServiceHandler = new CodeGenerateProject();
		CreateCodeManager.getInstance().setCgProjectGenerateParameter(projectGenerateParameter);
		codeGenerateServiceHandler.setProject(getProject());
		try{
			codeGenerateServiceHandler.buildProject();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("生成代码失败");
		}finally{
			CreateCodeManager.getInstance().setCgProjectGenerateParameter(null);
		}
	}

	public CgProjectInfo getProject() {
		return project;
	}

	public void setProject(CgProjectInfo project) {
		this.project = project;
	}

	

}

