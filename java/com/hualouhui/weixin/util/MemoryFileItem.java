package com.hualouhui.weixin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;

class MemoryFileItem implements FileItem {
	
    MemoryFileItem(String fieldName, String contentType, boolean isFormField, String fileName) {
        this.fieldName = fieldName;
        this.contentType = contentType;
        this.isFormField = isFormField;
        this.fileName = fileName;
    }

    @Override
    public FileItemHeaders getHeaders() {
        return fileItemHeaders;
    }

    @Override
    public void setHeaders(FileItemHeaders headers) {
        this.fileItemHeaders = headers;
    }

    @Override
    public void delete() {
        // do nothing
    }

    @Override
    public byte[] get() {
        return buffer.toByteArray();
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(buffer.toByteArray());
    }

    @Override
    public String getName() {
        return this.fileName;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return buffer;
    }

    @Override
    public long getSize() {
        return buffer.size();
    }

    @Override
    public String getString() {
        try {
            return new String(get(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @Override
    public String getString(String encoding)
            throws UnsupportedEncodingException {
        return new String(get(), encoding);
    }

    @Override
    public boolean isFormField() {
        return isFormField;
    }

    @Override
    public boolean isInMemory() {
        return true;
    }

    @Override
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public void setFormField(boolean isFormField) {
        this.isFormField = isFormField;
    }

    @Override
    public void write(File arg0) throws Exception {
        // do nothing
    }

    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private String fieldName, contentType, fileName;
    private FileItemHeaders fileItemHeaders;
    private boolean isFormField;

    private static final long serialVersionUID = -398063279023466010L;
}