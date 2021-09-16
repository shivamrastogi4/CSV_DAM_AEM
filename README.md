# CSV_DAM_AEM

go to mysbrcif and run command 
mvn clean install -PautoInstallPackage


Required steps in aem:

Step1: create a system user "mysbrservice", provide specific permission and add mapping for mysbrcif core in user service mapper.



Step2: create workflow model and add process step with GeeksWorkflowProcess Step and after syncing the WFModel, start the workflow with any page inside mysbrif sites.

