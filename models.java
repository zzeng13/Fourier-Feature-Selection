package Fourier_tests;
import weka.classifiers.Classifier;
import weka.core.Instances;

import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.rules.JRip;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;

import weka.classifiers.Evaluation;
import java.util.Random;

/**
 * @author Ziheng Zeng (zzeng13@illinois.edu)
 * 
 */

public class models {
	
	Classifier clf;
	Evaluation eval;
	int classIdx;
	
	//class constructor - initialize model 
	public models(String model_name, int classPos) {
		switch(model_name){
		case "Adaboost":
			clf =  new AdaBoostM1();
			System.out.println("Build Model: " + model_name);
			break;
		case "JRip":
			clf =  new JRip();
			System.out.println("Build Model: " + model_name);
			break;
			
		case "SVM":
			clf =  new SMO();
			System.out.println("Build Model: " + model_name);
			break;
			
		case "DT":
			clf =  new J48();
			System.out.println("Build Model: " + model_name);
			break;
			
		case "RF": 
			clf = new RandomForest();
			System.out.println("Build Model: " + model_name);
			break;
			
		}
		classIdx = classPos;
		
	}
	
	//Train a classifier 
	public void train(Instances TrainingSet) throws Exception {
		clf.buildClassifier(TrainingSet);
		return;
	}
	
	//Train and Test with Cross-Validation 
	// Note: this function takes care of training. Do Not use with train()
	public void testCV(Instances data, int k_fold) throws Exception {
		
		eval = new Evaluation(data);
		eval.crossValidateModel(clf, data, k_fold, new Random(1));
		return;
			
	}
	
	//Functions to get evaluation results.
	public double get_accuracy() {
		return eval.pctCorrect();
	}
	
	public double get_precision() {
		return eval.precision(classIdx);
	}
	
	public double get_recall() {
		return eval.recall(classIdx);
	}
	
	public double get_fscore() {
		return eval.fMeasure(classIdx);
	}
	
	public double get_AUC(){
		return eval.areaUnderROC(classIdx);
	}
	
	
	
	
	
	
}
