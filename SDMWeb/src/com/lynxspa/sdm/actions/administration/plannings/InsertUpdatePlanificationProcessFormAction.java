package com.lynxspa.sdm.actions.administration.plannings;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.sdm.entities.plannings.SPFTPGetProcess;
import com.lynxspa.sdm.entities.plannings.SPFTPPutProcess;
import com.lynxspa.sdm.entities.plannings.SPFileProcess;
import com.lynxspa.sdm.entities.plannings.SPShellProcess;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.plannings.SPProcess;
import com.lynxspa.exception.FPMException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.sdm.actions.CoacEnhancedBasicAction;
import com.lynxspa.validation.utils.ValidationUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class InsertUpdatePlanificationProcessFormAction extends CoacEnhancedBasicAction {
	private static final String EXECUTE_SHELL_PROCESS = "EXEC";
	private static final String GENERATE_FILE_PROCESS = "FILETOPATH";
	private static final String FTP_PUT_PROCESS = "FTPEXPORT";
	private static final String FTP_GET_PROCESS = "FTPIMPORT";
	private static final String ACTIVE = "active";
	private static final String BINARY = "binary";
	private static final String PASSIVE = "passive";
	

	@Override
	public void validate(Map<String, Object> _parameters,List<ValidationException> _errors) throws FPMException {
	
		

		if(EXECUTE_SHELL_PROCESS.equals(_parameters.get("processType"))){
			
			ValidationUtils.validateField("description", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			ValidationUtils.validateField("shellPath", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			ValidationUtils.validateField("shellName", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			ValidationUtils.validateField("shellExtension", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			
		}else{
			ValidationUtils.validateField("description", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			ValidationUtils.validateField("filePath", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			ValidationUtils.validateField("fileName", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			ValidationUtils.validateField("fileExtension", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			
			if(!GENERATE_FILE_PROCESS.equals(_parameters.get("processType"))){

				ValidationUtils.validateField("ftpServer", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
				ValidationUtils.validateField("ftpUser", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
				ValidationUtils.validateField("ftpPassword", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
				ValidationUtils.validateField("ftpPath", _parameters, _errors, ValidationsDict.ISNOTEMPTY,ValidationsDict.ISSTRING);
			}
		}
		
		
	}
	
	@Override
	public void performAction(HttpServletRequest _request, Session _session,
			LoggedUser _user, Map<String, Object> _parameters)
			throws FPMException {

		String description=null;
		String operationType=null;
		String type = null;
		String filePath=null;
		String fileName=null;
		String fileExtension=null;
		String fileOverwrite=null;
		String ftpServer = null;
		String ftpUser = null;
		String ftpPassword = null;
		String ftpPath = null;
		SPProcess process=null;
		String id=null;
		String shellPath=null;
		String shellName=null;
		String shellExtension=null;
		String ftpProxy = null;
		String ftpPort = null;
		String ftpFile = null;
		String ftpBinary = null;
		String ftpPassive = null;
		String ftpProxyUser = null;
		String ftpProxyPassword = null;
		
		try {
			//Recovering parameters
			operationType=(String)_parameters.get("operationType");
			description = (String)_parameters.get("description");
			type = (String)_parameters.get("processType");
			
			if (EXECUTE_SHELL_PROCESS.equals(type)){
				shellPath = _parameters.get("shellPath")!=null?(String)_parameters.get("shellPath"):"";
				shellName = _parameters.get("shellName")!=null?(String)_parameters.get("shellName"):"";
				shellExtension = _parameters.get("shellExtension")!=null?(String)_parameters.get("shellExtension"):"";
				
				if (!shellExtension.startsWith(".")){
					shellExtension = ".".concat(shellExtension);
				}
				
				if (!shellPath.endsWith("/")){
					shellPath = shellPath.concat("/");
				}
				
			}else{
				filePath = _parameters.get("filePath")!=null?(String)_parameters.get("filePath"):"";
				fileName = _parameters.get("fileName")!=null?(String)_parameters.get("fileName"):"";
				fileExtension = _parameters.get("fileExtension")!=null?(String)_parameters.get("fileExtension"):"";
				fileOverwrite = (String)_parameters.get("overwrite");
				
				
				ftpServer = _parameters.get("ftpServer")!=null?(String)_parameters.get("ftpServer"):"";
				ftpUser = _parameters.get("ftpUser")!=null?(String)_parameters.get("ftpUser"):"";
				ftpPassword = _parameters.get("ftpPassword")!=null?(String)_parameters.get("ftpPassword"):"";
				ftpPath = _parameters.get("ftpPath")!=null?(String)_parameters.get("ftpPath"):"";
				ftpProxy = _parameters.get("ftpProxy")!=null?(String)_parameters.get("ftpProxy"):"";
				ftpPort = _parameters.get("ftpPort")!=null?(String)_parameters.get("ftpPort"):"";
				ftpFile = _parameters.get("ftpFile")!=null?(String)_parameters.get("ftpFile"):"";
				ftpBinary = _parameters.get(BINARY)!=null?(String)_parameters.get(BINARY):"";
				ftpPassive = _parameters.get(PASSIVE)!=null?(String)_parameters.get(PASSIVE):"";
				ftpProxyUser = _parameters.get("ftpProxyUser")!=null?(String)_parameters.get("ftpProxyUser"):"";
				ftpProxyPassword = _parameters.get("ftpProxyPassword")!=null?(String)_parameters.get("ftpProxyPassword"):"";
				
				if (!fileExtension.startsWith(".")){
					fileExtension = ".".concat(fileExtension);
				}
				if (!filePath.endsWith("/")){
					filePath = filePath.concat("/");
				}
				if (!"".equals(ftpPath) && !ftpPath.endsWith("/")){
					ftpPath = ftpPath.concat("/");
				}
				
			}
			
			if(operationType.equalsIgnoreCase("UPDATE")){
				id=(String)_parameters.get("id");
				if (GENERATE_FILE_PROCESS.equals(type)){
					SPFileProcess fileProcess = (SPFileProcess) _session.load(SPFileProcess.class, Long.parseLong(id));
					fileProcess.setDescription(description);
					fileProcess.setPath(filePath);
					fileProcess.setExtension(fileExtension);
					fileProcess.setOverwrite(ACTIVE.equals(fileOverwrite));
					fileProcess.setFileName(fileName);
	
				}else if (FTP_PUT_PROCESS.equals(type)){
					SPFTPPutProcess ftpPutProcess= (SPFTPPutProcess) _session.load(SPFTPPutProcess.class, Long.parseLong(id));
					ftpPutProcess.setFileName(fileName);
					ftpPutProcess.setDescription(description);
					ftpPutProcess.setPath(filePath);
					ftpPutProcess.setExtension(fileExtension);
					ftpPutProcess.setOverwrite(ACTIVE.equals(fileOverwrite));
					ftpPutProcess.setFtpServer(ftpServer);
					ftpPutProcess.setFtpUser(ftpUser);
					ftpPutProcess.setFtpPassword(ftpPassword);
					ftpPutProcess.setFtpPath(ftpPath);
					ftpPutProcess.setFtpProxy(ftpProxy);
					ftpPutProcess.setFtpPort(ftpPort);
					ftpPutProcess.setFtpFile(ftpFile);
					ftpPutProcess.setFtpBinary(BINARY.equals(ftpBinary));
					ftpPutProcess.setFtpPassiveMode(PASSIVE.equals(ftpPassive));
					ftpPutProcess.setFtpProxyUser(ftpProxyUser);
					ftpPutProcess.setFtpProxyPassword(ftpProxyPassword);
	
				}else if (EXECUTE_SHELL_PROCESS.equals(type)){
					SPShellProcess shellProcess= (SPShellProcess) _session.load(SPShellProcess.class, Long.parseLong(id));
					shellProcess.setPath(shellPath);
					shellProcess.setFileName(shellName);
					shellProcess.setExtension(shellExtension);
					shellProcess.setDescription(description);
					shellProcess.setOverwrite(false);
	
				}else {
					SPFTPGetProcess ftpGetProcess= (SPFTPGetProcess) _session.load(SPFTPGetProcess.class, Long.parseLong(id));
					ftpGetProcess.setDescription(description);
					ftpGetProcess.setPath(filePath);
					ftpGetProcess.setExtension(fileExtension);
					ftpGetProcess.setOverwrite(ACTIVE.equals(fileOverwrite));
					ftpGetProcess.setFtpServer(ftpServer);
					ftpGetProcess.setFtpUser(ftpUser);
					ftpGetProcess.setFtpPassword(ftpPassword);
					ftpGetProcess.setFtpPath(ftpPath);
					ftpGetProcess.setFileName(fileName);
					ftpGetProcess.setFtpProxy(ftpProxy);
					ftpGetProcess.setFtpPort(ftpPort);
					ftpGetProcess.setFtpFile(ftpFile);
					ftpGetProcess.setFtpBinary(BINARY.equals(ftpBinary));
					ftpGetProcess.setFtpPassiveMode(PASSIVE.equals(ftpPassive));
					ftpGetProcess.setFtpProxyUser(ftpProxyUser);
					ftpGetProcess.setFtpProxyPassword(ftpProxyPassword);
				}
			}else{
				
				if (GENERATE_FILE_PROCESS.equals(type)){
					process = new SPFileProcess(_user.getId(),filePath,
							fileName,fileExtension,description,ACTIVE.equals(fileOverwrite));
				}else if (FTP_PUT_PROCESS.equals(type)){
					
					process = new SPFTPPutProcess(_user.getId(),filePath,
							fileName,fileExtension, description,
							ftpUser,ftpPassword,
							ftpServer,ftpPath,
							ACTIVE.equals(fileOverwrite),
							ftpFile,ftpProxy,ftpPort,ftpProxyUser, ftpProxyPassword,
							(BINARY.equals(ftpBinary)),(PASSIVE.equals(ftpPassive)));
					
				}else if (FTP_GET_PROCESS.equals(type)){
					
					process = new SPFTPGetProcess(_user.getId(),filePath,
							fileName,fileExtension,description,
							ftpUser,ftpPassword,ftpServer,ftpPath, 
							ACTIVE.equals(fileOverwrite),
							ftpFile,ftpProxy,ftpPort,ftpProxyUser, ftpProxyPassword,
							(BINARY.equals(ftpBinary)),(PASSIVE.equals(ftpPassive)));
					
				}else if (EXECUTE_SHELL_PROCESS.equals(type)){
					process = new SPShellProcess(_user.getId(),shellPath,
							shellName,shellExtension,description,false,"");
				}
				
				if (process!=null)
					HibernateUtils.customSave(_session, process, _user.getId());
			}
		}catch(FPMException e){
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),e,true);
			throw e;
		}catch (Exception e) {
			FPMException newException=new FPMException(e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw new FPMException(e);
		}catch(Throwable e) {
			FPMException newException=new FPMException(e);
			LogUtils.createLog(_session,_user.getId(),SDMConfigManager.BUNDLENAME,_user.getLocale(),SDMConfigManager.getInstance().getApplication(_session),newException,true);
			throw newException;
		}

	}

}
