package com.example.samba.operation;

import java.io.File;
import java.io.FileInputStream;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class SambaCreateFileExample {
    public static void main(String[] args) {
        copyStringIntoNetworkFile("smb://username:password@server/share/newfile.txt", "This is the content of the new file.", false);
        copyStringIntoNetworkFile("smb://username:password@server/share/newfile.txt", "This is the content of the new file.", false);
        copyLocalFileIntoNetworkFile("smb://username:password@server/share/newfile.txt", "path/to/local/file.txt");
    }

	private static void copyStringIntoNetworkFile(String sambaPath, String content, boolean appendMode) {
		try {
            SmbFile sambaFile = new SmbFile(sambaPath);
            SmbFileOutputStream outputStream = new SmbFileOutputStream(sambaFile, appendMode);
            outputStream.write(content.getBytes());
            outputStream.close();
            System.out.println("File created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    public static void copyLocalFileIntoNetworkFile(String sambaPath, String localFilePath) {
        try {
            SmbFile sambaFile = new SmbFile(sambaPath);
            SmbFileOutputStream sambaFileOutputStream = new SmbFileOutputStream(sambaFile);
            
            // Create an InputStream to read from the local file
            FileInputStream localFileInputStream = new FileInputStream(new File(localFilePath));            
    		// Copy the content from the local file to the Samba share
    		byte[] buffer = new byte[8192];
    		int bytesRead;
    		while ((bytesRead = localFileInputStream.read(buffer)) != -1) {
    			sambaFileOutputStream.write(buffer, 0, bytesRead);
    		}
    		
    		// Close the streams
    		localFileInputStream.close();
    		sambaFileOutputStream.close();    		
    		System.out.println("File copied successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
