package org.bladerunnerjs.plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.bladerunnerjs.model.App;
import org.bladerunnerjs.model.BRJS;
import org.bladerunnerjs.model.engine.Node;
import org.bladerunnerjs.model.events.NodeCreatedEvent;
import org.bladerunnerjs.model.events.NodeDiscoveredEvent;
import org.bladerunnerjs.plugin.base.AbstractModelObserverPlugin;

public class ReadMeAppPlugin extends AbstractModelObserverPlugin implements EventObserver {
    private BRJS brjs;

   
    public void setBRJS(BRJS brjs) {
        this.brjs = brjs;
    //subscribe to the events
        this.brjs.addObserver(NodeCreatedEvent.class, this);
        this.brjs.addObserver(NodeDiscoveredEvent.class, this);
    }

    public void onEventEmitted(Event event, Node node) {
    	if (event instanceof NodeCreatedEvent &&  node instanceof App) {
    		createReadMe(node);
    		 
    	} else if (event instanceof NodeDiscoveredEvent &&  node instanceof App && node.dirExists()) {
    		createReadMe(node);
    		 
    	}
    	
    }

	private void createReadMe(Node node) {
		App app = (App) node;
		File appRead = app.file("README.md");
		String name = app.getName();
		Date date = new Date();
		String user = System.getProperty("user.name");
		List<String> text = Arrays.asList("# " + name, "Default readme for " + name, "App created on " + date, "App created by " + user);
		
   
		try {
			FileUtils.writeLines(appRead, text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		
	}
	
}
    








