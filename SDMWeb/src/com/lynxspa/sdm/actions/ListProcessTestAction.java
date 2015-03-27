package com.lynxspa.sdm.actions;


import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerNotification;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.lynxit.fpm.jmx.BusinessProcessWrapperMBean;
import com.lynxit.fpm.jmx.EventProducerWrapperMBean;
import com.lynxit.fpm.jmx.GenericMBean;
import com.lynxit.fpm.jmx.monitor.ConnectionEventsListener;
//import com.lynxit.fpm.jmx.monitor.ConnectionNotificationsHandler;
import com.lynxit.fpm.jmx.monitor.GenericMBeanProxyFactory;
import com.lynxit.fpm.jmx.monitor.GenericMBeanProxyHandler;
import com.lynxit.fpm.jmx.monitor.GenericMBeanProxyMap;
//import com.lynxit.fpm.jmx.monitor.LocalMBeanProxy;
import com.lynxit.fpm.jmx.monitor.RemoteApplicationMonitor;
import com.lynxit.webcomp.commands.form.ValidationException;
import com.lynxit.xweb.initialization.initializers.RemoteApplicationMonitorsInitializer;
import com.lynxit.xweb.users.LoggedUser;
import com.lynxspa.exception.FPMException;
import com.lynxspa.xweb.actions.EnhancedBasicAction;


public class ListProcessTestAction extends EnhancedBasicAction{

	
		
	@Override
	public void performAction(HttpServletRequest _request, Session _session,LoggedUser _user, Map<String, Object> _parameters) throws FPMException {

		
		
		
	       Map<String, RemoteApplicationMonitor> remoteMonitors=null;
	       RemoteApplicationMonitor remoteApplicationMonitor=null; 
	       Set<String> remoteMonitorNames=null;
	       Map<ObjectName,GenericMBean> managedBeans=null;
	       String bussinessProcessPath=null;
	       EventProducerWrapperMBean relatedEvent=null;	       
	       
	       /*
	        try{
		        remoteMonitors=RemoteApplicationMonitorsInitializer.getInstance().getRemoteMonitors();
		        remoteMonitorNames=remoteMonitors.keySet();
		        
		        for (String monitorName:remoteMonitorNames) {
		        remoteApplicationMonitor=remoteMonitors.get(monitorName);
		        
		        if(remoteApplicationMonitor.isConnected()){
		        
		        //Recuperaci�n de los processos de negocio implicados
		        managedBeans=remoteApplicationMonitor.getManagedBeans();
		        BusinessProcessWrapperMBean bussinessProcess=(BusinessProcessWrapperMBean)managedBeans.get("com.lynxit.fpm.FPM:FPM=application,BusinessProcesses=Business processes,BusinessProcess=UnblockedSecuritiesProcess");
		                //bussinessProcess.getProcessStartTime();
		        }
		        
		        }
		        }catch (Exception e){
		        e.printStackTrace();
		        }
		    */
		
		  
		  try {
		
	      JMXConnector jmxConnector;
	      Map unmodifiableMap;
	        
	  
	      MBeanServerConnection beanServerConnection;
	       
	       
	       JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:65000/jmxrmi");
	       Map credential = new HashMap();
	       credential.put("jmx.remote.credentials", new String[] {"fpm","fpm"});
	       jmxConnector = JMXConnectorFactory.connect(url, credential);
	       beanServerConnection= jmxConnector.getMBeanServerConnection();
	        
	      // ConnectionNotificationsHandler connectionNotificationsHandler = new ConnectionNotificationsHandler(null);
	       //jmxConnector.addConnectionNotificationListener(connectionNotificationsHandler, null, null);
	       ObjectName mBeanServerDelegate = new ObjectName("JMImplementation:type=MBeanServerDelegate");
	       //beanServerConnection.addNotificationListener(mBeanServerDelegate, connectionNotificationsHandler, null, this.jmxConnector_);
	      
	       ObjectName domainName = new ObjectName("com.lynxit.fpm.FPM:FPM=application,BusinessProcesses=Business processes,BusinessProcess=UnblockedSecuritiesProcess");
	       Set mBeans = beanServerConnection.queryMBeans(domainName, null);
	       
	       Iterator i = mBeans.iterator();
	       
	       while (i.hasNext())
	       
	       {
	    	   
	    	   ObjectInstance objectInstance = (ObjectInstance)i.next();
	    	   try
	    	    {
	    		   BusinessProcessWrapperMBean mbean = (BusinessProcessWrapperMBean)GenericMBeanProxyFactory.newProxyInstance(objectInstance, beanServerConnection);
	    		  
	    		   Date date = mbean.getProcessStartTime();
	    		   
	    		   System.out.println(date.toString());
	    		   
	    	    } catch (Exception e) { e.printStackTrace();  }
	    	   
	    	    }
	       
	       
		  } catch(Exception e) {}
		  
		  
		  /*
		  try {
		  
		  String name = "FPM";
		  String host = "localhost";
		  int port = 65000;
		  String userId = "fpm";
		  String password = "fpm";
		  String domain = "com.lynxit.fpm.FPM";
		  logger_.info("Initializing remote monitor for '" + name + "' domain.");
		  Map<ObjectName,GenericMBean> managedBeans;
		  RemoteApplicationMonitor applicationMonitor = new RemoteApplicationMonitor(host, port, userId, password,domain);
		  applicationMonitor.start();
		  
		  if(applicationMonitor.isConnected()){
		        
		        //Recuperaci�n de los processos de negocio implicados
		        managedBeans=applicationMonitor.getManagedBeans();
		        BusinessProcessWrapperMBean bussinessProcess=(BusinessProcessWrapperMBean)managedBeans.get(domain+":"+name+"=application,BusinessProcesses=Business processes,BusinessProcess=UnblockedSecuritiesProcess");
		                //bussinessProcess.getProcessStartTime();
		  }
		  
		  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  */
		  
	       
		
	}

	@Override
	public void validate(Map<String, Object> _parameters,
			List<ValidationException> _errors) throws FPMException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		
		 try {
			  
			  String name = "FPM";
			  String host = "localhost";
			  int port = 65000;
			  String userId = "fpm";
			  String password = "fpm";
			  String domain = "com.lynxit.fpm.FPM";
			 
			  RemoteApplicationMonitor applicationMonitor = new RemoteApplicationMonitor(host, port, userId, password, 
			          domain);
			  applicationMonitor.start();
			  
			  } catch (Exception e) {}
		
	}
	
	
}
