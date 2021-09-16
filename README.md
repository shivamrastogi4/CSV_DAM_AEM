# CSV_DAM_AEM

go to mysbrcif and run command 
mvn clean install -PautoInstallPackage


Required steps in aem:


Step1: create a system user "mysbrservice", provide specific permission and add mapping for mysbrcif core in user service mapper.
Step2: create /content/dam/migratiob/ folder and add Book1.csv file with some data to it.
For reference the data used is:
![image](https://user-images.githubusercontent.com/19723661/133573897-e2540901-bf49-4f4a-a3e8-09f42911b623.png)



Step2: create workflow model and add process step with GeeksWorkflowProcess Step and after syncing the WFModel, start the workflow with any page inside mysbrif sites.

