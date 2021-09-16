package com.mysbrcif.core.servlets;

import com.adobe.granite.asset.api.AssetManager;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.Node;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component(service = CSVServlet.class)
public class CSVServlet {
    private final Logger log = LoggerFactory.getLogger(CSVServlet.class);
    @Reference
    ResourceResolverFactory resourceResolverFactory;

    public void Activate() throws Exception {
        XSSFWorkbook workbook = null;
        XSSFSheet sheet = null;
        ResourceResolver resourceResolver= null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ResourceResolverFactory.SUBSERVICE, "mysbrservice");
        resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
        Resource res = resourceResolver.getResource("/content/dam/migratiob/Book1.csv");
        log.info("Resource type of container is " + res.getResourceType());
        String filePath = res.getPath();
        AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);
        Asset asset = res.adaptTo(Asset.class);;
        log.info("got the asset "+asset.getName());
        Resource original = asset.getOriginal();
        log.info("original name is"+original.getName());
        InputStream content = original.adaptTo(InputStream.class);
        StringBuilder sb = new StringBuilder();
        String line="";
        String splitBy = ",";
        InputStreamReader ir = new InputStreamReader(content,StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(ir);
        log.info("below BR read line");
        br.readLine();
        Resource nodeRes= resourceResolver.getResource("/content/mysbrcif/us/en/test/jcr:content/root/container/container");
        while ((line = br.readLine()) != null) {
            br.skip(0);
            String[] arr = line.split(splitBy);
            log.info("array length is "+arr.length);
            Node node = nodeRes.adaptTo(Node.class);
            Node save = node.addNode(arr[0]+"_"+Math.random()*100, JcrConstants.NT_UNSTRUCTURED);
            if (save != null) {
                save.setProperty(arr[2], arr[1]);
                save.setProperty("sling:resourceType", arr[3]);
                log.info("save node is added");
                resourceResolver.commit();
            } else {
                log.info("Node is null");
                resourceResolver.commit();
            }

        }

    }
    /*public Page create(String parentPath, String pageName, String template, String title,
                       boolean autoSave) throws WCMException {
        if (parentPath == null) throw new IllegalArgumentException("Parent path can't be null.");
        if (pageName == null && title == null)
            throw new IllegalArgumentException("Page and title name can't be both null.");
        if (template != null && !template.isEmpty())
            throw new UnsupportedOperationException("Templates are not supported.");

        try {
            Node parent = JcrUtils.getOrCreateByPath(parentPath, JcrConstants.NT_UNSTRUCTURED, session);

            if (pageName == null || pageName.isEmpty())
                pageName = JcrUtil.createValidName(title, JcrUtil.HYPHEN_LABEL_CHAR_MAPPING);
            if (!JcrUtil.isValidName(pageName)) throw new IllegalArgumentException("Illegal page name: " + pageName);

            Node pageNode = parent.addNode(pageName, JcrConstants.CQ_PAGE);
            Node contentNode = pageNode.addNode("jcr:content", JcrConstants.CQ_PAGE_CONTENT);

            if (title != null && !title.isEmpty()) contentNode.setProperty("jcr:title", title);
            if (autoSave) {
                session.save();
            }

            return getPage(pageNode.getPath());
        }
        catch (RepositoryException e) {
            throw new WCMException("Unable to create page", e);
        }
    }*/
}
