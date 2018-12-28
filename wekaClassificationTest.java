package Fourier_tests;

import weka.core.Instances;

import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.Filter;
/**
 * @author Ziheng Zeng (zzeng13@illinois.edu)
 * 
 */


public class wekaClassificationTest {
	 //Testing Parameters
	static String data_filename = "data/kddcup99_200k_3pctPos_parity_k1.arff";
	static String model_name = "JRip"; //  DT RF SVM JRip Adaboost
	static int [] num_feat = new int [] {1,3,5,7,10,15,20,25,30} ;
	static int pos_classIdx = 1; //index of the positive class 
	
	static int k_fold = 3; //number of fold for cross validation # change this to 5 for smaller datasets
	
	public static void main(String[] args) throws Exception {
		System.out.println("-------------------START---------------------");
		System.out.println("Model Name: " + model_name);
		//Read in data
		DataSource source = new DataSource(data_filename);
		Instances data = source.getDataSet();
		//Train & Test a Model (with k-fold CV) 
		//======================================================================
		models clf = new models(model_name, pos_classIdx);  //model initialization n:index of the positive class 
		
		double [] accuracy = new double [num_feat.length];
		double [] precision = new double [num_feat.length];
		double [] recall = new double [num_feat.length];
		double [] f_score = new double [num_feat.length];
		double [] auc = new double [num_feat.length];
		
		String[] options = new String[2]; //for attribute remove filter 
		Remove remove = new Remove();  
		
		for(int i = 0; i < num_feat.length; i++) {
			
			//get subset of features 
			Instances curData;
			if(num_feat[i] != (data.numAttributes() - 1 )) {
				options[0] = "-R"; options[1] = String.valueOf(num_feat[i]+1) + "-" + String.valueOf(data.numAttributes() - 1);                      
				remove.setOptions(options); remove.setInputFormat(data);                          
				curData = Filter.useFilter(data, remove);
				curData.setClassIndex(curData.numAttributes() - 1);
			}else {
				System.out.println("Used all features in the file!");
				curData = data;
				curData.setClassIndex(curData.numAttributes() - 1);
			}
			
			//evaluate model with k-fold CV
			clf.testCV(curData, k_fold);
			//print out performance summary 
			System.out.println("------------------------------------------");
			System.out.println("Number of Features Used: " + String.valueOf(curData.numAttributes() - 1));
			System.out.println(clf.eval.toSummaryString());
			System.out.println("Recall: " + clf.get_recall());
			

			//save key performances 
			accuracy[i] = clf.get_accuracy();
			precision[i] = clf.get_precision();
			recall[i] = clf.get_recall();
			f_score[i] = clf.get_fscore();
			auc[i] = clf.get_AUC();
			
		}
		
		//Save Evaluation Results 
		//======================================================================
		String save_fname = "results/Test_KDDCUP99_200k_3pctPos_" + model_name + "_k1_results.csv";
		CsvFileWriter.writeCsvFile(save_fname, accuracy, precision, recall, f_score, auc, num_feat, model_name); 
		System.out.println("-----------------END OF PROGRAM-------------------------");

		
		
		
		
	}
 

}
