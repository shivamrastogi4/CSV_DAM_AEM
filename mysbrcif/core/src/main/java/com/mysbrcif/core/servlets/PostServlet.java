/*
package com.mysbrcif.core.servlets;

import com.adobe.xfa.ut.StringUtils;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.commons.jcr.JcrUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

@Component(immediate = true, service = PostServlet.class)
public class PostServlet {
    private final Logger log = LoggerFactory.getLogger(PostServlet.class);
    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Activate
    public void Activate(ComponentContext ctx) {
        PostServlet ps = new PostServlet();
        String vOutput = ps.ReadCellData(0, 0);
        log.info(vOutput);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ResourceResolverFactory.SUBSERVICE, "mysbrservice");
        ResourceResolver resourceResolver = null;
        try {
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
            log.info(resourceResolver.getUserID());
            Resource res = resourceResolver.getResource("/content/mysbrcif/us/en/testing/jcr:content/root/container/container/text");
            ValueMap readMap = res.getValueMap();
            log.info(readMap.get("jcr:primaryType", ""));
            ModifiableValueMap modMap = res.adaptTo(ModifiableValueMap.class);
            if (modMap != null) {
                modMap.put("text", vOutput);
                resourceResolver.commit();
                log.info("Successfully saved");
            }
            } catch(LoginException e){
                log.error("LoginException", e);
            } catch(PersistenceException e){
                e.printStackTrace();
            } finally{
                if (resourceResolver != null && resourceResolver.isLive()) {
                    resourceResolver.close();
                }
            }
        }

    public String ReadCellData(int vRow, int vColumn)
    {
        String value=null;          //variable for storing the cell value
        Workbook wb=null;           //initialize Workbook null
        try
        {
//reading data from a file in the form of bytes
            FileInputStream fis=new FileInputStream("C:\\demo\\EmployeeData.xlsx");
//constructs an XSSFWorkbook object, by buffering the whole stream into the memory
            wb=new XSSFWorkbook(fis);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
        Sheet sheet=wb.getSheetAt(0);   //getting the XSSFSheet object at given index
        Row row=sheet.getRow(vRow); //returns the logical row
        Cell cell=row.getCell(vColumn); //getting the cell representing the given column
        value=cell.getStringCellValue();    //getting cell value
        return value;               //returns the cell value
    }
}
*/
