@echo off
set CLASSPATH=
for %%f in (lib\*.jar) do ( 
	call cpappend %%f
)
:end
java com.lynxit.xweb.resourcesdeployer.DeploySystem