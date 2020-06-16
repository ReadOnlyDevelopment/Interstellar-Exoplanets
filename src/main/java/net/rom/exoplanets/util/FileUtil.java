package net.rom.exoplanets.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import net.minecraft.client.Minecraft;

public class FileUtil {
	
	private static final String CONFIG_DIRECTORY = Minecraft.getMinecraft().mcDataDir.toString();

    public static boolean find(File fileName, String entry) {
        boolean result = false;
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(CONFIG_DIRECTORY + "/config/" + fileName));
            while(in.hasNextLine() && !result) {
                result = in.nextLine().indexOf(entry) >= 0;
            }
        }
        catch(IOException e) {
            e.printStackTrace();      
        }
        finally {
            try { in.close() ; } catch(Exception e) { /* ignore */ }  
        }
        return result;
    }
    


}
