package Fourier_tests;

import weka.core.Instances;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.Filter;
/**
 * @author Ziheng Zeng (zzeng13@illinois.edu)
 * 
 */

public class DNF_Generation{
	 //Testing Parameters
	static String data_filename = "data/sample_raw_data.arff";
	static String model_name = "JRip"; //  RIPPER
	static int pos_classIdx = 1; //index of the positive class 
	
	public static void main(String[] args) throws Exception {
		System.out.println("-------------------START---------------------");
		System.out.println("Model Name: " + model_name);
		//Read in data
		DataSource source = new DataSource(data_filename);
		Instances data = source.getDataSet();
		data.setClassIndex(data.numAttributes() - 1);
		//Generate Rules using RIPPER
		//======================================================================
		models clf = new models(model_name, pos_classIdx);  //model initialization n:index of the positive class 
		//train model
	    clf.train(data);
	    //extract rules 
	    String rules[] = clf.clf.toString().split("\\r?\\n");
	    rules = Arrays.copyOfRange(rules, 3, rules.length - 2);
	    //format the rules 
	    Set<String> rule_set = new HashSet<>(); // a set that only keeps distinctive rules 
	    for( String line: rules) {
	    	String cur_rules[] = line.substring(0, line.indexOf("=>")).trim().split(" and ");
	    	for(String r: cur_rules) {
	    		if(r.length() != 0 && r.charAt(0) == '(') {
	    			rule_set.add(r.substring(1, r.length()-1).replace(' ', ','));
	    		}	
	    	}	
	    }
		System.out.println("=== ALL RULES: ===");
	    rule_set.forEach(System.out::println);
	    List<String> rule_list = new ArrayList<String>(rule_set);
	    

	    // Save rules to a .csv File
	    String fname = data_filename.substring(0, data_filename.length()-5) + "_DNF.csv";
	    CsvFileWriter.writeRules2CsvFile(fname, rule_list);
		System.out.println("-----------------END OF PROGRAM-------------------------");
	}
		
		
		
		
	
 

}
