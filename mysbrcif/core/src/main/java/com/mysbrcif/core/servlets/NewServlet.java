/*

package com.mysbrcif.core.servlets;

import com.adobe.cq.wcm.core.components.models.Page;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import com.mysbrcif.core.util.ExcelReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component(service = NewServlet.class)
public class NewServlet {
    private final Logger log = LoggerFactory.getLogger(NewServlet.class);
    @Reference
    private ResourceResolverFactory resourceResolverFactory;
    @Inject
    Page currentPage;


    public void Activate() throws Exception {
        log.info("Came from Workflow Process Step");
        String filePath= "C:\\Users\\shirasto\\OneDrive - Publicis Groupe\\Desktop\\Book1.xlsx";

        String sheetName = "Sheet1";
        NewServlet ps = new NewServlet();
        int rowCount = ExcelReader.getRowCount(filePath,sheetName);
        log.info("row count is"+rowCount);
        int colCount = ExcelReader.getColumnCount(filePath,sheetName);
        log.info("column count is"+colCount);
        int flag = 1;
        while (flag < rowCount) {
            String vOutput = ps.ReadCellData(flag, 1);
            String nodeType = ps.ReadCellData(flag, 0);
            String propType = ps.ReadCellData(flag,2);
            String resourceType = ps.ReadCellData(flag, 3);
            log.info("Data at"+flag +" is " + vOutput);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put(ResourceResolverFactory.SUBSERVICE, "mysbrservice");
            ResourceResolver resourceResolver = null;
            try {
                resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
                log.info(resourceResolver.getUserID());
                Resource res= resourceResolver.getResource("/content/mysbrcif/us/en/testing/jcr:content/root/container/container/");
                Node node = res.adaptTo(Node.class);
                log.info("Res user id:_" + res.getPath());
                String resType = res.getResourceType();
                Node save = node.addNode(nodeType+"_"+Math.random()*100, JcrConstants.NT_UNSTRUCTURED);
                log.info("node is added" + node);
                if (save != null) {
                    save.setProperty(propType, vOutput);
                    save.setProperty("sling:resourceType", resourceType);
                    log.info("save node is added");
                    resourceResolver.commit();
                } else {
                    log.info("Node is null");
                    resourceResolver.commit();
                }

            } catch (LoginException e) {
                log.error("LoginException", e);
            } catch (PathNotFoundException e) {
                e.printStackTrace();
            } catch (RepositoryException e) {
                e.printStackTrace();
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
            flag+=1;
        }
    }

    public String ReadCellData(int vRow, int vColumn)
    {
        String value=null;          //variable for storing the cell value
        Workbook wb=null;           //initialize Workbook null
        try
        {
//reading data from a file in the form of bytes
            FileInputStream fis=new FileInputStream("C:\\Users\\shirasto\\OneDrive - Publicis Groupe\\Desktop\\Book1.xlsx");
//constructs an XSSFWorkbook object, by buffering the whole stream into the memory
            wb=new XSSFWorkbook(fis);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        log.info("wbinside");
        Sheet sheet=wb.getSheetAt(0);   //getting the XSSFSheet object at given index
        Row row=sheet.getRow(vRow); //returns the logical row
        Cell cell=row.getCell(vColumn); //getting the cell representing the given column
        value=cell.getStringCellValue();
        log.info("value is "+value);//getting cell value
        return value;               //returns the cell value
    }
}
*/
