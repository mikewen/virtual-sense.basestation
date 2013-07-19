package it.uniurb.disbef.virtualsense.basestation.textparser;

import java.util.StringTokenizer;

import com.sun.istack.internal.logging.Logger;

import it.uniurb.disbef.virtualsense.basestation.BaseStationLogger;
import it.uniurb.disbef.virtualsense.basestation.Packet;

public class TextParser {
	static boolean intPacket = false;
	static long time;
	static short lastRouter;
	static short sender;
	static short counter;
	static short route;
	static short noise;
	static short co2;
	/*static short pressure;
	static short temperature;
    static short luminosity;*/
	public static void parseText(String text){
		System.out.println("parsing: "+text);
		if(text.indexOf("<packet>") != -1){
			intPacket = true;
			return;
		}
		if(text.indexOf("</packet>") != -1){
			intPacket = false;
			// finalize the packet
			Packet p = new Packet(time,lastRouter,sender,counter,route,noise,co2 /*,pressure,temperature,luminosity*/);
			BaseStationLogger.newPacket(p);
			return;
		}
		
		if(intPacket && (text.indexOf(">") != -1)){
			StringTokenizer tokenizer = new StringTokenizer(text, ": ");
			String tag = tokenizer.nextToken();				
			System.out.println("working on "+tag);
			if(tag.equals(">time")){				
				time = System.currentTimeMillis();
			}
			if(tag.equals(">router")){	
				short value = Short.parseShort(tokenizer.nextToken());	
				lastRouter = value;
			}
			if(tag.equals(">sender")){	
				short value = Short.parseShort(tokenizer.nextToken());	
				sender = value;
			}
			if(tag.equals(">counter")){	
				short value = Short.parseShort(tokenizer.nextToken());	
				counter = value;
			}			
			if(tag.equals(">route")){
				short value = Short.parseShort(tokenizer.nextToken());	
				route = value;
			}
			if(tag.equals(">noise")){	
				short value = Short.parseShort(tokenizer.nextToken());	
				noise = value;
			}
			if(tag.equals(">co2")){		
				short value = Short.parseShort(tokenizer.nextToken());	
				co2 = value;
			}
			/*if(tag.equals(">pressure")){				
				pressure = value;
			}
			if(tag.equals(">temperature")){				
				temperature = value;
			}
			if(tag.equals(">luminosity")){				
				luminosity = value;
			}*/
		}
	}

}
