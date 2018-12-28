# Fourier-Feature-Selection

### Fourier Feature Generation and Selection (FFGS) method. 

There are three major parts: 
1. Disjunctive Normal Form (DNF) formulas generation (rules generation)
2. Parity feature generation and selection 
3. Testing with multiple classifiers 


#### Phase I: DNF formulas generation
This phase takes a raw data file in .arff format and generate a set of rules in .csv format. Select a dataset in .arff format and run ‘DNF_Generation.java’.

A .csv file with DNF rules would be generated at the end. See ‘sample data/sample_raw_data_DNF.csv’ for example. 

#### Phase II: Parity feature generation and selection
This phase take DNF rules (.csv) and dataset with raw features (.csv) to generate and select best parity features. 

The code is written in python with Google Colab notebook. You can also run it locally by downloading the .ipynb file.

#### Phase III: Testing with classifiers 
In this phase, we compare the performance of raw features and the parity features using several classifiers. The code is written in Java, using the WEKA JAVA API. 

Run ‘wekaClassificationTest.java’




