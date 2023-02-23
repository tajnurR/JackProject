package com.jack.task;

import java.io.File;

import org.testng.annotations.Test;

public class FileRename {
	
	
	@Test
	void rename () {
		File f = new File("C:\\Users\\tajnu\\Desktop\\img\\Adrian-King-Jr.jpg");
		File r = new File("C:\\Users\\tajnu\\Desktop\\img\\x\\xx.jpg");
		
		if (f.exists()) {
			System.out.println(f.renameTo(r));
		}else {
			System.out.println("Faild");
		}
	}

}
