import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.models.GlobalPredictor;
import com.models.LocalPredictor;
import com.models.Selector;


public class mainClass {

	/**
	 * @param args
	 */
	//For local predictors
	static Map<String,LocalPredictor> myLocalMap = new HashMap<String,LocalPredictor>();
	
	//For global predictors
	static String nextIndexVal = null;
	static String startIndexVal = "000000";
	static Map<String,GlobalPredictor> myGlobalMap = new HashMap<String,GlobalPredictor>();
	
	//For selector	
	static Map<String,Selector> mySelectorMap = new HashMap<String,Selector>();
	
	public static String getLocalPrediction(String inputVal,String prediction){
		String local_predicion = "n";
		if(myLocalMap.containsKey(inputVal)) {
		   LocalPredictor lpObject = myLocalMap.get(inputVal);
           lpObject.setL_oldValue(lpObject.getL_newValue());
           if(lpObject.getL_oldValue() > 1){
        	   local_predicion = "t";
        	   lpObject.setL_localPrediction(local_predicion);
           }
           else{
        	   local_predicion = "n";
        	   lpObject.setL_localPrediction(local_predicion);        	   
           }
           if(prediction.equalsIgnoreCase("t")){
        	   if(lpObject.getL_newValue() < 3)
        		   lpObject.setL_newValue(lpObject.getL_newValue() + 1);
           }
           else{
        	   if(lpObject.getL_newValue() > 0)
        		   lpObject.setL_newValue(lpObject.getL_newValue() - 1);
           }
        } else {
        	int newValue = 0;
        	if(prediction.equalsIgnoreCase("t"))
        		newValue = newValue + 1;
        	myLocalMap.put(inputVal, new LocalPredictor(inputVal, 0, newValue,local_predicion));
        }
		return local_predicion;
	}
	public static void printMap(){
		// Get a set of the entries
	      Set set = myLocalMap.entrySet();    
	      // Get an iterator
	      Iterator i = set.iterator();
	      // Display elements
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         System.out.print(me.getKey() + ": ");
	         System.out.println(me.getValue());
	      }
	}
	public static String getSelectorPrediction(String inputVal,String finalPrediction,String localP,String globalP){
		String selectorPrediction = "l";
		
		if (mySelectorMap.containsKey(inputVal)) {
			Selector selectorObj = mySelectorMap.get(inputVal);
			selectorObj.setS_oldValue(selectorObj.getS_newValue());
			if (!localP.equalsIgnoreCase(globalP)) {
				if(localP.equalsIgnoreCase(finalPrediction)){
					if (selectorObj.getS_newValue() > 0)
						selectorObj.setS_newValue(selectorObj.getS_newValue() - 1);
				}
				else {
					if (selectorObj.getS_newValue() < 3)
						selectorObj.setS_newValue(selectorObj.getS_newValue() + 1);
				}
			}
			if (selectorObj.getS_oldValue() <= 1) {
				selectorPrediction="l";
				selectorObj.setS_localPrediction(selectorPrediction);
			} else {
				selectorPrediction="g";
				selectorObj.setS_localPrediction(selectorPrediction);
			}
		} else {
			mySelectorMap.put(inputVal, new Selector(inputVal, 0, 0, selectorPrediction));
		}
		
		return selectorPrediction;
	}
	public static String getGlobalPrediction(String inputVal,String prediction){
		String global_prediction = "n";
		if(prediction.equalsIgnoreCase("t")){
			nextIndexVal = inputVal.substring(1) + "1";
		}else{
			nextIndexVal = inputVal.substring(1) + "0";
		}
		if(myGlobalMap.containsKey(inputVal)) {
			GlobalPredictor gpObject = myGlobalMap.get(inputVal);
			gpObject.setG_oldValue(gpObject.getG_newValue());
	        if(gpObject.getG_oldValue() > 1){
	        	global_prediction = "t";
	        	gpObject.setG_localPrediction(global_prediction);
	        }
	        else{
	        	global_prediction = "n";
	        	gpObject.setG_localPrediction(global_prediction);        	   
	        }
	        if(prediction.equalsIgnoreCase("t")){
	           if(gpObject.getG_newValue() < 3)
	        	   gpObject.setG_newValue(gpObject.getG_newValue() + 1);
	        }
	        else{
	        	if(gpObject.getG_newValue() > 0)
	        		gpObject.setG_newValue(gpObject.getG_newValue() - 1);
	        }
        }
		else {
        	int newValue = 0;
	        if(prediction.equalsIgnoreCase("t"))
	        	newValue = newValue + 1;
	        myGlobalMap.put(inputVal, new GlobalPredictor(inputVal, 0, newValue,global_prediction));
	     }
		return global_prediction;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			String line=null;
			//int predictorCounter = 0;
			br = new BufferedReader(new FileReader(args[0]));
			File file = new File("outputFile.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			while ((line = br.readLine()) != null) {
				String node = Character.toString(line.charAt(0));
				String finalPrediction = Character.toString(line.charAt(1));
				String localP = getLocalPrediction(node,finalPrediction);
				String globalP = getGlobalPrediction(startIndexVal,finalPrediction);
				String selectorP = getSelectorPrediction(node,finalPrediction,localP,globalP);
				startIndexVal = nextIndexVal;
				String actual_prediction=null;
				if(selectorP.equalsIgnoreCase("l"))
					actual_prediction = localP;
				else
					actual_prediction = globalP;
				/*if(actual_prediction.equalsIgnoreCase(finalPrediction)){
					predictorCounter++;
				}*/
				
				line = node + localP + globalP + selectorP + actual_prediction  + finalPrediction;
				bw.write(line+"\n");
				System.out.println(line);
			}
			//System.out.println("predictor Correctness: "+predictorCounter);
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null){
					br.close();
					bw.close();
				}
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
	}
}