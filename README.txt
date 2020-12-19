Project introduction and explanation can be found in project reports. This README.txt file only explain the structure of the code files and how to run it and test it.


==========================General Intro=========================

* The codes are written on Eclipse, and is a Maven project, including dependencies to Stanford coreNLP. So I advice you to also run the program on eclipse.

* Import the entire Dolphin_Swarm_Clustering folder into you eclipse workspace.

* All codes are in src folder.

* Testing datasets are all inside the Dolphin_Swarm_Clustering folder. Details of datasets introduction can be found in report.
	- iris.data
	- breast_cancer.csv
	- cervical_cancer.csv
	- HTRU_2.csv
	- QCM3.csv
	- QCM6.csv
	- QCM7.csv
	- QCM10.csv
	- QCM12.csv
	- QCM.csv
	Above are real number datasets. Text datasets (document files) are inside folders
	- C1, C4, C7 
	- AaronPressman, AlanCrosby, AlexanderSmith, BenjaminKangLim, BernardHickey

* Similar to many other clustering algorithm, the clustering results may vary for every run. For the evaluation presented in the report, I have ran the algorithm multiple times, and have presented the best-performed results. Therefore if you find out the running results are different from what I present in the report, please do not feel weird. Just try to run more times.

* The algorithm prints out the clustering results. For above testing datasets, for convenience, I have saved their clustering results and the data points' final position in clustering space inside of the Dolphin_Swarm_Clustering folder, names as dolphin_test_<dataset name> and <dataset name>_performance_input.


==========================Parameters & Performance=========================

So before instructions of running the program, there is a few things to note here.

* Inside the program, there are three special parameters
	- e
	- speed
	- searchR
These three parameters can influence the cluster results and should be adjusted by programmer to find the best-performed value. For above testing datasets, I have found the best-performed parameter values and have kept them inside the main.java. 

* The algorithm supports two kinds of similarity measure: cosine similarity and Euclidean distance. 

For above testing datasets, their best-performed parameter values and most suitable similarity measurements are listed below:
- iris.data: cosineSim, k = 3, searchR = 0.8, speed = 0.1, e = 4
- C1, C4, C7 (HW1 and 2 Documents Files): cosineSim, k = 3, searchR = 0, speed = 0.02, e = 6
- cervical_cancer.csv: euclidean, k = 2, searchR = 100, speed = 0.1, e = 50
- HTRU_2.csv: euclidean, k = 2, searchR = 100, speed = 0.1, e = 50
- breast_cancer.csv: euclidean, k = 2, searchR = 700, speed = 0.3, e = 800
- QCM3.csv: euclidean, k = 5, searchR = 400, speed = 0.3, e = 1000
- QCM6.csv: euclidean, k = 5, searchR = 500, speed = 0.1, e = 100
- QCM7.csv: euclidean, k = 5, searchR = 300, speed = 0.3, e = 500
- QCM10.csv: euclidean, k = 5, searchR = 400, speed = 0.3, e = 1000
- QCM12.csv: euclidean, k = 5, searchR = 400, speed = 0.3, e = 1000
- QCM.csv: euclidean, k = 5, searchR = 400, speed = 0.3, e = 1000
- AaronPressman, AlanCrosby, AlexanderSmith, BenjaminKangLim, BernardHickey (author identification): cosineSim, k = 5, searchR = 0, speed = 0.01, e = 15



==========================How to Run=========================

The algorithm supports two kinds of similarity measure: cosine similarity and Euclidean distance. To switch the similarity measurements, please...
1. Go to src folder and find all codes file
2. Go to Dolphin.java
3. Find two functions: cosineSim, euclidean
4. Comment the one that you do NOT wnat to use, and keep the one that you want to use
5. Find the function: search
6. Follow the comments in that function
7. Go to cluster.java
8. ctrl+F (for mac) or shift+F (for windows), and search for 'fitness'. Follow the comments in codes nearby.
9. ctrl+F (for mac) or shift+F (for windows), and search for 'cosineSim' or 'euclidean'. 
	9a) Change that function name to the similarity measure that you want to use
	9b) Follow the nearby code comments to make certain small changes

* The algorithm mainly provides three kinds of testing
	- Test on real number datasets listed above
	- Test on text datasets listed above
	- Test on user-input datasets
The pre-processment of above three kinds of datasets may differ at some parts. Therefore, I write codes to test and print out results for above separately.

Specifically, I use different blocks of codes to test each dataset separately. This is because as said in previous bulletpoints, the value of those three parameters are different for different testing datasets. So inside the main.java, you can find blocks of codes for each datasets listed above. The values of the three parameters are set to be the best-performed values. And for some of the testing datasets listed above, they are arranged in a special way. So I write codes to specifically process some of them.

* To test the above listed datasets, please...
1. Go to main.java. Browse the code, you can easily find out that the codes are arranged in blocks. There are very clear comment that specifies which block of code is used to test each listed testing datasets
2. Comment out the block of codes that test the datasets you want to test, and make sure you comment all other codes in the main function. (This is important. Otherwise, if there are other blocks of codes not commented, there may be error message!)
3. Go to cluster.java, and go the bottom. There you may find two blocks of codes for returning, and two very clear comments that specify the function of each block of the returning codes
	3a) If you are testing a real number datasets (all .data and .csv files listed above), use the block of code under the comment of "RETURN CLUSTER RESULT FOR REAL NUMBER DATASETS", and go to the line 64 and change the return type to ArrayList<Prey[]>
	3b) If you are testing a text document datasets (all datasets in form of folders), use the block of code under the comment of "RETURN CLUSTER RESULT FOR TEXT DATASETS", and go to the line 64 and change the return type to HashMap<String, String>. 
4. After above, the last thing to do is that in order to have a clear clustering results for text datasets, an extra attribute 'name' is added to Dolphin object and Prey object while testing text datasets. (This attribute has no influence on the performance. It is only added so that it is easier to observe the clustering results). So please
	4a) If you are testing a real number datasets, go to Dolphin.java. Find the constructor function, and follow the comments in codes to delete name attribute. Go to Prey.java, and do the same thing to the constructor. 
	4b) If you are testing a text datasets, go to Dolphin.java. Find the constructor function, and follow the comments in codes to add name attribute. Go to Prey.java, and do the same thing to the constructor. 
	4c) Go to cluster.java, and find the constructor function. Follow the comment code to add / delete the 'docs' attribute
	4d) Now if you are using eclipse or similar coding tools, you may notice there are errors inside Main.java. Do not panic and go to the line of code that have errors. Follow the clear comments nearby to add / remove 'name' attribute to / from Dolphin and Prey constructor. (If you are using sublime and do not see errors, use ctrl+F or shift+F to search for 'new Dolphin' and 'new Prey'. Just make sure all constructor functions called in cluster.java are updated with the adding / removing 'name' attribute)

* To test user-input datasets, please...
1. Go to Main.java again. Browse the code, and near the bottom part you can find the block of code (with very clear comments that specify) for user-input datasets test. 
2. There are two blocks: one for text documents and one for real number documents. Find the one you want and comment out that block. Again, make sure the other blocks of codes are all commented, or there may be error.
3. Follow above instructions to adjust some small parts in codes for real number / text documents
4. Choose a similarity measure you want, and follow above instructions to adjust some small parts in codes to switch between these two similarity measure
5. Run the code, and enter the information the code asks you to answer. 
5. Clustering result and the final position for each data point will be printed in console.
Here several points need to be noticed:
- The program so far assumes for real number datasets, there would be a column representing category / label / class, and would therefore not be considered for cluster but would be considered for evaluation. This column can be String. But all the other columns must be real numbers. (Except for the first row. The first row can be all strings, specifying the attribute names. The program will ask the user whether you want to skip the first row or not).
- If you want to test a text document, make sure when you enter the names and directories, they are in the same order. That means, if you enter file names red,blue,yellow, then the directories must be something like ../test/red.txt,../test/blue.txt,../test/yellow.txt. Otherwise, the program will not work fluently

For clarification, starting line for each block of codes in Main.java are listed below.
iris.data-------------------------------------------------------------------line 43
C1, C4, C7------------------------------------------------------------------line 91
cervical_cancer-------------------------------------------------------------line 131
HTRU------------------------------------------------------------------------line 186
breast_cancer---------------------------------------------------------------line 244
QCM3, 6, 7, 10, 12, all-----------------------------------------------------line 293
author identification-------------------------------------------------------line 365
user-input real number------------------------------------------------------line 471
user-input text-------------------------------------------------------------line 543

In cluster.java, some useful line numbers are
cluster method starts-------------------------------------------------------line 65
return codes for real number------------------------------------------------line 314
return codes for text-------------------------------------------------------line 328

In Dolphin.java, we have
cosineSim-------------------------------------------------------------------line 57
euclidean-------------------------------------------------------------------line 87
search----------------------------------------------------------------------line 119



==========================Performance=========================

One last thing to note here is that for performance measure, the Cluster.java does not calculate performance automatically. The program uses another class of codes, which is Performance.java, to calculate and print the confusion matrix and precision, recall, and accuracy. The above listed testing datasets' results are stored, and I arrange them in a way that is very friendly to be processed, which are stored in <dataset name>_performance_input. Therefore, please do not feel panic when you run Main.java but do not see performance printed out. 

Inside the Performance.java, again, since each listed testing datasets have different number of attributes and so on, I use different blocks of codes to beautifully print out their confusion matrix and precision things. For checking, just...
1. Go to Performance.java
2. Find the block of codes that is used to process your wanted datasets. Comment out that blocks, and make sure all the other codes in main function is commented.
3. Run and look at the console!

There are very clear comments in Performance.java to specify each blocks of codes. But I also list them here
iris----------------------------------------line 41
breast cancer-------------------------------line 133
cervical cancer-----------------------------line 199
QCM3,...all---------------------------------line 265
HTRU----------------------------------------line 403
author--------------------------------------line 471 < text > 
C1,4,7--------------------------------------line 606 < text > 