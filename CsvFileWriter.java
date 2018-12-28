package Fourier_tests;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ziheng Zeng (zzeng13@illinois.edu)
 * 
 */
public class CsvFileWriter {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "ModelName,NumFeatures,Accuracy,Precision,Recall,FMeasure,AUC";

	public static void writeCsvFile(String fileName, double[] acc, double [] pre, double[] rec, double [] fm, double[] auc, int[] num_feat, String modelName) {
		
		FileWriter fileWriter = null;
				
		try {
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
			for (int i = 0; i < acc.length; i++) {
				fileWriter.append(modelName);
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(String.valueOf(num_feat[i]));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(String.valueOf(acc[i]));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(String.valueOf(pre[i]));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(String.valueOf(rec[i]));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(String.valueOf(fm[i]));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(String.valueOf(auc[i]));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
	
	
	public static void writeRules2CsvFile(String fileName, List<String>  rules) {
		
		FileWriter fileWriter = null;
				
		try {
			fileWriter = new FileWriter(fileName);

			for (int i = 0; i < rules.size(); i++) {
				fileWriter.append(rules.get(i));				
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}

}