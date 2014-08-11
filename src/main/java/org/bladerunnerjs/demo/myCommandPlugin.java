package org.bladerunnerjs.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import org.bladerunnerjs.logging.Logger;
import org.bladerunnerjs.model.App;
import org.bladerunnerjs.model.BRJS;
import org.bladerunnerjs.model.exception.command.CommandArgumentsException;
import org.bladerunnerjs.model.exception.command.CommandOperationException;
import org.bladerunnerjs.plugin.utility.command.ArgsParsingCommandPlugin;










import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

public class myCommandPlugin extends ArgsParsingCommandPlugin {

	private BRJS brjs;
	private Logger logger;
	

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "list-apps";
	}

	@Override
	public String getCommandDescription() {
		// TODO Auto-generated method stub
		return "List the applications associated with a BladeRunnerJS install";
	}

	@Override
	public void setBRJS(BRJS brjs) {
		// TODO Auto-generated method stub
		this.brjs = brjs;
		this.logger = brjs.logger(myCommandPlugin.class);
		
		
	}

	@Override
	protected void configureArgsParser(JSAP argsParser) throws JSAPException {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected int doCommand(JSAPResult parsedArgs) throws CommandArgumentsException, CommandOperationException {
			listApps("Apps:", brjs.apps());
			
			// TODO Auto-generated catch bloc	
			return 0;
		}
		
		


	private void createReadMe(String fileName) {
		File readMeFile = new File(fileName);
		boolean fileCreated = false;
		
		try{
			fileCreated = readMeFile.createNewFile();
		} catch (IOException ioe){
			logger.console("Error while creating empty file" + ioe);
		}
		if (fileCreated){
			logger.console("Created Empty File: " + readMeFile.getPath());
		} else {
			logger.console("Failed to create empty file" + readMeFile.getPath());
		}
		
		
		
		Writer writer=null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(fileName),"utf-8"));
			writer.write("Readme Line 1");
			logger.console("test Confirm");
		} catch (IOException ex){
			logger.console("error found");
			
		} finally {
			try{ writer.close();} catch (Exception ex) {}
		}
		
		
		
	
		// TODO Auto-generated method stub
		
	}

	private void listApps(String title , List<App> apps) {
		logger.console(title + "\n");
		for(App app : apps){
			logger.console("* %s", app.getName());
		}
	}
	
	
	
	
	
	}
	


