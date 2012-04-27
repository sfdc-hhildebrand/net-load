package com.salesforce.systesting.netload.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/simple-diff")
@Produces(MediaType.TEXT_PLAIN)
public class SimpleDiffResource {
	private static final Logger log = LoggerFactory.getLogger(SimpleLoadResource.class);

	public SimpleDiffResource() {
	}
	@GET
	@Path("view")
	public String view(@Context HttpServletRequest hsr) throws Exception {
		log.info(String.format("request from %s", hsr.getRemoteAddr()));
		return generateDiff(getScriptFromFile("file1", "txt"),getScriptFromFile("file2","txt"));
	}	

	@POST
	@Path("view")
	public String view(@FormParam("file1") String file1,
			@FormParam("file2") String file2,
			@Context HttpServletRequest hsr) throws Exception {
		log.info(String.format("request from %s", hsr.getRemoteAddr()));
		return generateDiff(file1,file2);
	}
	
    private void createFile(String filename, String diffs) throws IOException {
        File temp = File.createTempFile("temp", "txt");
        temp.deleteOnExit();

        FileWriter fw = new FileWriter(temp);

        fw.append(String.format("%s", diffs));
        fw.flush();
        fw.close();

        FileOutputStream fos = new FileOutputStream(filename + ".txt");
        FileInputStream fis = new FileInputStream(temp);
        byte [] buff = new byte[4096];
        for (int read = fis.read(buff); read != -1; read = fis.read(buff)){
            fos.write(buff, 0, read);
        }
        fos.close();
        fis.close();
    	
    }

    private String getScriptFromFile (String filename, String extension) throws FileNotFoundException, IOException {
    	return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(filename + "." + extension));
        
    }
    
	private String generateDiff(String fileContent1, String fileContent2) throws IOException {
		createFile("a", fileContent1);
		createFile("b", fileContent2);
		Runtime.getRuntime().exec(new String[] {"bash", "-c", "diff -b -U 50 -N a.txt b.txt | /usr/local/bin/diffh > diff.html"});
		return getScriptFromFile("diff", "html");
	}
	
	
}
