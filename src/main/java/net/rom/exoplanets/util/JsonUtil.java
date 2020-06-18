package net.rom.exoplanets.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.block.Block;

public class JsonUtil {
	private String path;
	private String id;
	private static String TAB = "    ";// 4 space

	
	public JsonUtil(String modid, Block block) {
		this.id = modid;
		addBlockJsonFiles(block);
	}
	
	public JsonUtil(String modid, Block block, String path) {
		this.id = modid;
		this.path = path;
		addBlockJsonFiles(block, this.path);

	}
	
	public void addBlockJsonFiles(Block block){
		try{
			String loc = "C:/Users/AJ/git/Interstellar-Exoplanets/src/main/resources/assets/exoplanets";
			String first = block.getUnlocalizedName().toLowerCase().substring(5);
			String last = first.replace(this.id + ".", "");
			File blockStates = new File(loc + "/blockstates/", last + ".json");
			File modelBlock = new File(loc + "/models/block/", last + ".json");
			File modelItemBlock = new File(loc + "/models/item/", last + ".json");
			
			//TODO NEED ADD UPDATE JSON
			if (!blockStates.exists())
				if (blockStates.createNewFile()) {
					blockstateJson(last, blockStates);
				} 
			
			if (!modelBlock.exists())
				if (modelBlock.createNewFile()) {
					modelBlockJson(last, modelBlock);
				}
			
			if (!modelItemBlock.exists())
				if (modelItemBlock.createNewFile()) {
					modelItemBlockJson(last, modelItemBlock);
				}
			
		} catch(IOException ex){
			new Throwable(ex.getCause().toString());
		}
	}
	
	public void addBlockJsonFiles(Block block, String addPath){
		try{
			String loc = "C:/Users/AJ/git/Interstellar-Exoplanets/src/main/resources/assets/exoplanets";
			String first = block.getUnlocalizedName().toLowerCase().substring(5);
			String last = first.replace(this.id + ".", "");
			File blockStates = new File(loc + "/blockstates/", last + ".json");
			File modelBlock = new File(loc + "/models/block/" + addPath + "/", last + ".json");
			File modelItemBlock = new File(loc + "/models/item/" + addPath + "/", last + ".json");
			
			//TODO NEED ADD UPDATE JSON
			if (!blockStates.exists())
				if (blockStates.createNewFile()) {
					blockstateJson(last, blockStates, addPath);
				} 
			
			if (!modelBlock.exists() && modelBlock.mkdir())
				if (modelBlock.createNewFile()) {
					modelBlockJson(last, modelBlock, addPath);
				}
			
			if (!modelItemBlock.exists() && modelBlock.mkdir())
				if (modelItemBlock.createNewFile()) {
					modelItemBlockJson(last, modelItemBlock, addPath);
				}
			
		} catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	private void blockstateJson(String block, File file){
		try{

			FileWriter writer = new FileWriter(file);
			
			writer.write("{" + "\n");
			writer.write(TAB + "\"variants\": {" + "\n");
			writer.write(TAB+TAB+"\"normal\": {" + "\n");
			writer.write(TAB+TAB+TAB+"\"model\": " + "\"" + this.id + ":" +  block + "\"" + "\n");
			writer.write(TAB+TAB + "}" + "\n");
			writer.write(TAB+ "}" + "\n");
			writer.write("}");
			
			writer.close();
		} catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	private void modelBlockJson(String block, File file){
		try{
			FileWriter writer = new FileWriter(file);
			writer.write("{" + "\n");
			writer.write(TAB + "\"parent\": \"block/cube_all\"," + "\n");
			writer.write(TAB+ "\"textures\": {" + "\n");
			writer.write(TAB+TAB+TAB+"\"all\": " + "\"" + this.id + ":blocks/" + block + "\"" + "\n");
			writer.write(TAB+ "}" + "\n");
			writer.write("}");			
		
			writer.close();
		} catch(IOException ex){
			System.out.println(ex);
		}
		
	}
	
	private void modelItemBlockJson(String block, File file) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write("{" + "\n");
			writer.write(TAB+ "\"parent\": \"" + this.id + ":block/" + block + "\"" + "\n");
			writer.write("}");			
			writer.close();
		}catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	private void blockstateJson(String block, File file, String path){
		try{

			FileWriter writer = new FileWriter(file);
			
			writer.write("{" + "\n");
			writer.write(TAB + "\"variants\": {" + "\n");
			writer.write(TAB+TAB+"\"normal\": {" + "\n");
			writer.write(TAB+TAB+TAB+"\"model\": " + "\"" + this.id + ":" + path + "/" +  block + "\"" + "\n");
			writer.write(TAB+TAB + "}" + "\n");
			writer.write(TAB+ "}" + "\n");
			writer.write("}");
			
			writer.close();
		} catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	private void modelBlockJson(String block, File file, String addPath){
		try{
			FileWriter writer = new FileWriter(file);
			writer.write("{" + "\n");
			writer.write(TAB + "\"parent\": \"block/cube_all\"," + "\n");
			writer.write(TAB+ "\"textures\": {" + "\n");
			writer.write(TAB+TAB+TAB+"\"all\": " + "\"" + this.id + ":blocks/" + addPath + "/" + block + "\"" + "\n");
			writer.write(TAB+ "}" + "\n");
			writer.write("}");			
		
			writer.close();
		} catch(IOException ex){
			System.out.println(ex);
		}
		
	}
	
	private void modelItemBlockJson(String block, File file, String addPath) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write("{" + "\n");
			writer.write(TAB+ "\"parent\": \"" + this.id + ":block/"  + addPath + "/" + block + "\"" + "\n");
			writer.write("}");			
			writer.close();
		}catch (IOException ex) {
			System.out.println(ex);
		}
	}
}
