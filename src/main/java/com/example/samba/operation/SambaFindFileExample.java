package com.example.samba.operation;
import jcifs.smb.SmbFile;


public class SambaFindFileExample {
	public static void main(String[] args) {
		findFilesIntoDirectory("smb://username:password@server/share");
		findFile("smb://username:password@server/share/existingfile.txt");
	}
	
	public static void findFilesIntoDirectory(String sambaPath) {
        try {
            SmbFile sambaFile = new SmbFile(sambaPath);
            if (sambaFile.exists() && sambaFile.isDirectory()) {
                System.out.println("Listing files in the Samba share:");

                SmbFile[] files = sambaFile.listFiles();
                for (SmbFile file : files) {
                    System.out.println(file.getName());
                }
            } else {
                System.out.println("Samba share not found or is not a directory.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public static void findFile(String sambaPath) {
    	SmbFile sambaFile = null;
        try {
            sambaFile = new SmbFile(sambaPath);
            if (sambaFile.exists()) {
                System.out.println("File exists.");
            } else {
                System.out.println("File does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(sambaFile != null) {
        		sambaFile.close();        		
        	}
		}
    }
}
