package com.hualouhui.weixin.util;

import java.util.HashMap;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;

public class MemoryFileItemFactory implements FileItemFactory {
    
    public FileItem createItem(String fieldName, String contentType, boolean isFormField, String fileName) {
        MemoryFileItem item = new MemoryFileItem(fieldName, contentType, isFormField, fileName);
        if (!isFormField) {
        	fieldName2Item.put(fieldName, item);// TODO
        }
        return item;
    }       
    
    public String getUploadedFileUrl(String fieldName) {
    	FileItem item = fieldName2Item.get(fieldName);
    	if (item != null) {
    		return "http://pic.nipic.com/2008-04-12/200841210122178_2.jpg";
    	}
    	return "http://www.hua1000.com/unknown.jpg";
    }
    
    public byte[] getUploadedFileData(String fieldName) {
        FileItem item = fieldName2Item.get(fieldName);
        return item.get();
    }
    
    private HashMap<String, FileItem> fieldName2Item = new HashMap<String, FileItem>();
}