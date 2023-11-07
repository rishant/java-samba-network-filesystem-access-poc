package com.example.samba.operation;

import jcifs.smb.SmbFile;

public class SambaDeleteFileExample {
    public static void main(String[] args) {
    	deleteFileFromNetworkFileSystem("smb://server/share/folderToDelete");
        deleteFileFromNetworkFileSystem("smb://username:password@server/share/existingfile.txt");
    }

	private static void deleteFileFromNetworkFileSystem(String sambaPath) {
		try {
            SmbFile sambaFile = new SmbFile(sambaPath);

            if (sambaFile.exists()) {
                sambaFile.delete();
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("File does not exist, so it cannot be deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    public static void deleteFolderFromNetworkFileSystem(String sambaFolderPath) {
    	try {
    		// Create an SmbFile object for the folder on the Samba share
    		SmbFile sambaFolder = new SmbFile(sambaFolderPath);
    		
    		if (sambaFolder.exists() && sambaFolder.isDirectory()) {
    			// List the contents of the folder (optional)
    			SmbFile[] files = sambaFolder.listFiles();
    			for (SmbFile file : files) {
    				System.out.println("Deleting: " + file.getName());
    				file.delete();
    			}
    			
    			// Delete the folder itself
    			sambaFolder.delete();
    			System.out.println("Folder deleted successfully.");
    		} else {
    			System.out.println("Folder does not exist or is not a directory.");
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}