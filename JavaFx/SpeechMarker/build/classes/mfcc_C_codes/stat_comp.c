#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include<errno.h>
#include "mfcc_defines.h"
#include "mfcc_globals.h"
/**********************************************************   Dependency :::
                                                                             (i)   UBM Model Mean ... 
                                                                             (ii)  UBM Model Weight ...
                                                                             (iii) UBM Model Variance ... 

*****************************************************************************************************/  

void model(float **mfcc,float **mean_vec,float **vari_vec,float *wts,float **like, int);
void summation(float **like, float *csum_like, int no_of_speech_frames, char zeroOrderStats_fileName[]);
void means(char *id, float *csum_like, float **like, int no_of_speech_frames, float **adapt_mean, float **mfcc, float **mean_vect, char firstOrderStats_fileName[]);
void matrix_mult(float **diff_mfcc_mean, int rows_diff, float **var_vec, int cols_var, float **trans_diff, float result[1][1],int row_res, int col_res);
void matrix_transpose(float **diff_mfcc_mean, int row, int col, float **trans_diff);
float **memory_alloc_2D(int row_size, int col_size);
void StatisticsCompV(char *id, float **speech_mfcc, int no_of_speech_frames, int coeffDim, char zeroOrderStats_fileName[], char firstOrderStats_fileName[]);
/**
           Function Name: map_training
           Function Syntax: void map_training(char id[], short *speech_nonspeech, int no_of_speech_frames, int total_no_of_frames, char features_filename[])

           Inputs:
                  UBM_MEAN, UBM_VARIANCE and UBM_WEIGHTS defined in mfcc_defines.h
                  @param id                     - consists the id of the speaker undergoing training.
                  @param speech_nonspeech       - consists boolean array of size equal to total no. of frames, for a speeched frame value is 1 and for nonspeeched frame value is 0. 
                  @param no_of_speech_frames    - consists the number of speech_frames
                  @param total_no_of_frames     - consists the total number of frames
                  @param features_filename      - consists the fullpath of the filename where mfcc coefficients are saved.
           
           Outputs: 
                 adapted mean for the speaker model saved in file id_mean.txt (e.g 9003_mean.txt)

           Description:
                       map_training() is to compute the adapted means using the  coefficients obtained from feature extraction module and weights, variance and mean computed during GMM-UBM modeling.
*/
                  
void stat_comp(char id[], short *speech_nonspeech, int no_of_speech_frames, int total_no_of_frames, char features_filename[], char zeroOrderStats_fileName[], char firstOrderStats_fileName[]){
	int i,j,k=0;														
	float **mfcc=NULL, **speech_mfcc=NULL;									        /* To store all the mfcc coefficients in contiguous memory locations */ 
	float **mean_vect=NULL;											        /* To store all the mean vectors in contiguous memory locations */
	float *weight_vect=NULL;											/* To store all the weight vectors in contiguous memory locations */
	float **var_vect=NULL;											        /* To store all the variance vectors in contiguous memory locations*/
	float **like=NULL,*csum_like=NULL,**adapt_mean=NULL;								/* To store all the component densities */	
	FILE *fp_mfcc=NULL;
        FILE *fp_ubm_mean=NULL;
	FILE *fp_ubm_weight=NULL;
	FILE *fp_ubm_var=NULL;      																				
	printf("working\n");
       
        /*********reading coefficient values from file id_coeff.txt into memory***************/
      
	mfcc = memory_alloc_2D(total_no_of_frames, 3 * NUM_OF_COEFFICIENTS);              //calling the memory_alloc_2D() to allocate memory for mfcc coefficients of all frames
	
	speech_mfcc=memory_alloc_2D(no_of_speech_frames, 3 * NUM_OF_COEFFICIENTS);        //calling the memory_alloc_2D() to allocate memory for mfcc coefficients of speech frames
	
        fp_mfcc=fopen(features_filename,"r");
        printf("features filename is %s\n",features_filename);	
/**
 Logic: The following section of code stores the mfcc coeffients of speeched frames into 2d array speech_mfcc[][]. If value of speech_nonspeech[] is 1, the frame is speech and hence mfcc coefficient stored in mfcc[][] is taken
        into speech_mfcc[][]. Hence we get the coefficients of only speeched frames.  
*/
									       /* Opening the file containing the mfcc coefficients*/ 	
        if (fp_mfcc==NULL)
	{
		printf("mfcc coefficients file not opening");
	}
        else
        {
            for(i=0;i<total_no_of_frames;i++)
               {           
                   for(j=0;j< 3 * NUM_OF_COEFFICIENTS;j++)
                        {                
                         fscanf(fp_mfcc,"%f",&mfcc[i][j]);                             // reading the coefficients from the file in 2d array mfcc[][]

                        }
                   if(speech_nonspeech[i]==1)
                   {
                     for(j=0;j< 3 * NUM_OF_COEFFICIENTS;j++) 
                      {
                        speech_mfcc[k][j]=mfcc[i][j];                                 // storing the coefficients from  mfcc[][] into speech_mfcc[][] if the frame is speech
                        
                      }
                   k++;
            
                   }
               }
        }
	fclose(fp_mfcc);
printf("w2\n");

/*********Reading mean values of UBM into memory***************/
        

	fp_ubm_mean=fopen(UBM_MEAN,"r");                                                           //opening the UBM_mean file
	
	if (fp_ubm_mean==NULL)
	{
		printf("mean vector coefficients file not opening");
	}

	
	

         mean_vect = memory_alloc_2D(NUM_OF_GAUSSIANS,  3 * NUM_OF_COEFFICIENTS);            //calling the memory_alloc_2D() to allocate memory for mean_vector


	printf("\nreading means\n");
       for(i=0; i<NUM_OF_GAUSSIANS; i++)                                                //NUM_OF_GAUSSIANS  = number of gaussian mixtures in GMM.                                          
	{
           for(j=0; j< 3 * NUM_OF_COEFFICIENTS; j++)                                                  
        	{
                  fscanf(fp_ubm_mean, "%f", &mean_vect[i][j]);                             //reading the mean values from the file and storing in 2 dimensional array
                }	          
        }
	
	
	fclose(fp_ubm_mean);  
	      


	/*********Reading weight values of UBM into memory***************/


	printf("\nreading weights\n");
	fp_ubm_weight=fopen(UBM_WEIGHT,"r");                                       //opening the UBM_weights file
	
	if (fp_ubm_weight==NULL)
	{
		printf("weight coefficients file not opening");
	}

	
	weight_vect = (float *) malloc( (NUM_OF_GAUSSIANS) * sizeof(float) );
	
	for(i=0; i < NUM_OF_GAUSSIANS; i++)					   //NUM_OF_GAUSSIANS  = number of gaussian mixtures in GMM. 
	{
		fscanf(fp_ubm_weight, "%f", &weight_vect[i]);                      //Reading the weight values from the file and storing it in 1d array.		
	}

	fclose(fp_ubm_weight);

	/**********Storing variance values of UBM in memory*****************/



	printf("\nreading variance\n");
	fp_ubm_var=fopen(UBM_VARIANCE,"r");                                      //opening the UBM_variance file
	
	if (fp_ubm_var==NULL)
	{
		printf("variance coefficients file not opening");
	}	                                                                                      

        var_vect = memory_alloc_2D(NUM_OF_GAUSSIANS,  3 * NUM_OF_COEFFICIENTS);     //calling the memory_alloc_2D() to allocate memory for var_vector

        for(i=0; i<(NUM_OF_GAUSSIANS); i++)                                     //NUM_OF_GAUSSIANS  = number of gaussian mixtures in GMM.                                         
	{
           for(j=0; j < (3 * NUM_OF_COEFFICIENTS); j++)                                                  
        	{                  
                   fscanf(fp_ubm_var, "%f", &var_vect[i][j]);                    //reading the variance values from the file and storing  in 2 dimensional array
                }	          
        }

	fclose(fp_ubm_var);

	

	/******* Calculating likelihood values of mfcc coefficient w.r.t each   *******/        

	like = memory_alloc_2D(no_of_speech_frames, NUM_OF_GAUSSIANS); 															
	model(speech_mfcc, mean_vect, var_vect, weight_vect, like, no_of_speech_frames);
        

	
	/******* Calculating csum_like**************************************************************************/

	csum_like = (float *) malloc( (NUM_OF_GAUSSIANS ) * sizeof(float) );		
	summation(like, csum_like, no_of_speech_frames, zeroOrderStats_fileName);
	
	/****** Calculating adapted_mean (1 order moment)****************************************************************/

	adapt_mean = memory_alloc_2D(NUM_OF_GAUSSIANS, 3 * NUM_OF_COEFFICIENTS);
 
	means(id, csum_like, like, no_of_speech_frames, adapt_mean, speech_mfcc, mean_vect, firstOrderStats_fileName);
	
	free(mfcc);
        free(speech_mfcc);
	free(mean_vect);
	free(weight_vect);
	free(var_vect);


}                      // End of map training
	





void StatisticsCompV(char *id, float **speech_mfcc, int no_of_speech_frames, int coeffDim, char zeroOrderStats_fileName[], char firstOrderStats_fileName[]){
	int i,j,k=0;														
	//float **mfcc=NULL, **speech_mfcc=NULL;									        /* To store all the mfcc coefficients in contiguous memory locations */ 
	float **mean_vect=NULL;											        /* To store all the mean vectors in contiguous memory locations */
	float *weight_vect=NULL;											/* To store all the weight vectors in contiguous memory locations */
	float **var_vect=NULL;											        /* To store all the variance vectors in contiguous memory locations*/
	float **like=NULL,*csum_like=NULL,**adapt_mean=NULL;								/* To store all the component densities */	
        FILE *fp_ubm_mean=NULL;
	FILE *fp_ubm_weight=NULL;
	FILE *fp_ubm_var=NULL;      																				
	printf("working\n");
       
        /*********reading coefficient values from file id_coeff.txt into memory***************/
      
	
/*********Reading mean values of UBM into memory***************/
        

	fp_ubm_mean=fopen(UBM_MEAN,"r");                                                           //opening the UBM_mean file
	
	if (fp_ubm_mean==NULL)
	{
		printf("mean vector coefficients file not opening");
	}

	
	

       mean_vect = memory_alloc_2D(NUM_OF_GAUSSIANS, coeffDim);            //calling the memory_alloc_2D() to allocate memory for mean_vector


       printf("\nreading means\n");
       for(i=0; i < NUM_OF_GAUSSIANS; i++)                                                //NUM_OF_GAUSSIANS  = number of gaussian mixtures in GMM.                                          
	{
           for(j=0; j < coeffDim; j++)                                                  
        	{
                  fscanf(fp_ubm_mean, "%f", &mean_vect[i][j]);                             //reading the mean values from the file and storing in 2 dimensional array
                }	          
        }
	
	
	fclose(fp_ubm_mean);  
	      


	/*********Reading weight values of UBM into memory***************/


	printf("\nreading weights\n");
	fp_ubm_weight=fopen(UBM_WEIGHT,"r");                                       //opening the UBM_weights file
	
	if (fp_ubm_weight==NULL)
	{
		printf("weight coefficients file not opening");
	}

	
	weight_vect = (float *) malloc( (NUM_OF_GAUSSIANS) * sizeof(float) );
	
	for(i=0; i < NUM_OF_GAUSSIANS; i++)					   //NUM_OF_GAUSSIANS  = number of gaussian mixtures in GMM. 
	{
		fscanf(fp_ubm_weight, "%f", &weight_vect[i]);                      //Reading the weight values from the file and storing it in 1d array.		
	}

	fclose(fp_ubm_weight);

	/**********Storing variance values of UBM in memory*****************/



	printf("\nreading variance\n");
	fp_ubm_var=fopen(UBM_VARIANCE,"r");                                      //opening the UBM_variance file
	
	if (fp_ubm_var==NULL)
	{
		printf("variance coefficients file not opening");
	}	                                                                                      

        var_vect = memory_alloc_2D(NUM_OF_GAUSSIANS, coeffDim);     //calling the memory_alloc_2D() to allocate memory for var_vector

        for(i=0; i<(NUM_OF_GAUSSIANS); i++)                                     //NUM_OF_GAUSSIANS  = number of gaussian mixtures in GMM.                                         
	{
           for(j=0; j < coeffDim; j++)                                                  
        	{                  
                   fscanf(fp_ubm_var, "%f", &var_vect[i][j]);                    //reading the variance values from the file and storing  in 2 dimensional array
                }	          
        }

	fclose(fp_ubm_var);

	

	/******* Calculating likelihood values of mfcc coefficient w.r.t each   *******/        

	like = memory_alloc_2D(no_of_speech_frames, NUM_OF_GAUSSIANS); 															
	model(speech_mfcc, mean_vect, var_vect, weight_vect, like, no_of_speech_frames);
        

	
	/******* Calculating csum_like**************************************************************************/

	csum_like = (float *) malloc( (NUM_OF_GAUSSIANS ) * sizeof(float) );		
	summation(like, csum_like, no_of_speech_frames, zeroOrderStats_fileName);
	
	/****** Calculating adapted_mean (1 order moment)****************************************************************/

	adapt_mean = memory_alloc_2D(NUM_OF_GAUSSIANS, coeffDim);
 
	means(id, csum_like, like, no_of_speech_frames, adapt_mean, speech_mfcc, mean_vect, firstOrderStats_fileName);
	
	//free(mfcc);
        free(speech_mfcc);
	free(mean_vect);
	free(weight_vect);
	free(var_vect);


}                      // End of map training
	





/** 

Function Name: model
Function Syntax: void model(float **mfcc,float **mean_vec,float **vari_vec,float *wts,float **like,int no_of_speech_frames)

Description: model() calculates the likelihood values for the feature set with the model

   Inputs:    
   @param mfcc                   - consists the mfcc coefficents obtainained from mfcc_computation module.
   @param mean_vec               - consists the mean vectors obtained while GMM_UBM modeling       
   @param vari_vec               - consists the variance vectors obtained while GMM_UBM modeling
   @param wts                    - consists the weights obtained while GMM_UBM modeling
   @param no_of_speech_frames    - consists the no of speech frames 
    
   Outputs:
   @param like                   - returns the 2d array of likelihood values 
   
   Logic : The model () computes the probabilistic alignment of feature vectors for each speech frame w.r.t each model and then using the probabilistic alignment it computes the likelihood values for each feature vector with respect to 
           each model. 
           1. The alignment  is computed by implementing the Gaussian kernel equation. 
           2. First the difference vector is computed between the mfcc vectors and mean vectors. 
           3. The matrix_transpose() is called which computes the transpose of difference vector.
           4. Once the transpose is computed, matrix mult() is called which computes the multiplication between difference vector, the variance vector and the transpose of difference vector. 
           5. The scalar value obtained through matrix_mult() is used in the Gaussian kernel equation through which the probablistic alignment is computed. The alignment values are stored in array like[].
           6. The probablistic alignment stored in the array like[] is then used to compute the likelihood value for each mfcc vector w.r.t each model by multiplying the weights with the values of probalistic alignment and then  
              normalizing with respect to norm. Norm is the weighted addition of values of probablistic alignment stored in a single dimensional array. 
           7. Hence we get a two dimensional matrix of likelihood values which is a 2D array of size equal to no_of_speech_frames * NO_OF_GAUSSIANS.

*/

void model(float **mfcc,float **mean_vec,float **vari_vec,float *wts,float **like, int no_of_speech_frames)
{         
          int i,j,k,l;
          float **diff_mfcc_mean,temp_final_mult;
          float **trans_diff;
          float var_mul, *norm;
          float final_mult[1][1];
          
          int coeffDim =  3 * NUM_OF_COEFFICIENTS;

          norm = (float *) malloc(no_of_speech_frames * sizeof(float));               // Allocating contiguous memory of NO of Frames          
          diff_mfcc_mean = memory_alloc_2D(1, coeffDim);                   // Allocating 2 dimensional contiguous memory  to store difference between mfcc vector and mean vector
          trans_diff = memory_alloc_2D(coeffDim, 1);                       // Allocating 2 dimensional contiguous memory  to store transpose of diff


         for(j=0; j < no_of_speech_frames; j++)                                       // Initializing norm array to zero
         {
           norm[j]=0;
         }


/************************* Below loop is used to implement the Gaussian kernel equation 
  First the differnce vector is calculated by subtacting mean vector from the feature vector. The matrix_transpose() is called to compute the transpose of difference vector. Once the transpose is computed, the matrix_mult() is computed 
  to compute the multiplication of difference vector, inverse of variance vector and transpose of differnce matrix. The result of multiplication is returned which is then used in the gaussian kernel equation to calculate the  
  probabilistic alignment( Gaussian kernels) of mfcc vector w.r.t each model stored in like[].
*/
 
       for(i=0; i < no_of_speech_frames; i++)
       {
           for(j=0; j < NUM_OF_GAUSSIANS; j++)
             {
               for(k=0; k < coeffDim; k++)         
                  {    
                     diff_mfcc_mean[0][k]=mfcc[i][k]-mean_vec[j][k];                  // Calculating the diff by subtracting mean from the mfcc coefficients
                  }
    
                matrix_transpose(diff_mfcc_mean, 1, coeffDim, trans_diff);   // Performing the transpose of a matrix
 
                matrix_mult(diff_mfcc_mean, 1, &vari_vec[j], coeffDim, trans_diff, final_mult, 1, 1);      // Calculating the kernel

   
                temp_final_mult=final_mult[0][0];
   
                var_mul = 0;
             
               for(l=0; l < coeffDim;l++)
                 {
                  // var_mul=var_mul*vari_vec[j][l];       // calculating the determinant of covariance matrix ... changed to logScale ... 
                  var_mul += log(vari_vec[j][l]);
                     
                 }


              //like[i][j]=(exp(-0.5 * temp_final_mult)/(pow((2*PI), (coeffDim/2.0))*sqrt(var_mul))) + 2.2204e-016;  /**********computing and storing the gaussian kernel values(Probabilistic alignment)****************/  
                // Take Log Score to avoid Zeros ..... 
                like[i][j] = (-1.0)/2.0 * temp_final_mult - coeffDim/2.0 * log(2 * PI) - 1.0/2.0 * var_mul;
                like[i][j] = exp(like[i][j]);

             }
       }

free(diff_mfcc_mean);
free(trans_diff);


for(i=0;i<no_of_speech_frames;i++)
 {
  for(j=0;j<NUM_OF_GAUSSIANS;j++)
  {
   norm[i]= norm[i] + wts[j] * like[i][j] ;      /**********computing the normalization factor by weighted addition of gaussian kernels*********************/                  
  }
 }
	



	for(i=0; i<no_of_speech_frames; i++)
	{
		for(j=0; j<NUM_OF_GAUSSIANS ; j++)
		{
			like[i][j] = ((wts[j] * like[i][j])/norm[i]);	// calculating the likelihood values.	
                        //printf("%f\n",like[i][j]);											
		}
	}

	
	free(norm);

	
}



/** 
Function Name: summation()
Function Syntax: void summation(float **like, float *csum_like, int no_of_speech_frames )
Description: summation() calculates the cumulative sum of likelihood

     Inputs:
      @param like                   - consists the likelihhod vectors
      @param no_of_speech_frames    - consists the no of speech frames

     Outputs:      
      @param csum_like              - consists the single dimensional array of cumulative sum of likelihood
      
     Logic: The summation() computes the csum_like which is the cumulative sum of likelihood values. Hence we get a single dimensional array of size equal to number of gaussians containing the 
            summed up likelihood values along the rows.
  
*/
void summation(float **like, float *csum_like, int no_of_speech_frames, char firstOrderStats_fileName[])
{
	FILE *fp_nc=NULL;
	
	int i,j;

	if((fp_nc=fopen(firstOrderStats_fileName,"w"))==NULL)
	  {
		printf("\nunable to open file nc.txt\n");
	  }	
	
	for(i=0; i<NUM_OF_GAUSSIANS; i++)
		{
			csum_like[i] = 0;
		}
	
	printf("\ncalculating zero order stats\n");


	for(i=0; i<NUM_OF_GAUSSIANS; i++)
	{	
		for(j=0; j<no_of_speech_frames; j++)
		
		{
			csum_like[i] = csum_like[i] + like[j][i];
		}
	}

	for(i=0; i<NUM_OF_GAUSSIANS; i++)
		{
			fprintf(fp_nc,"%f\n",csum_like[i]);			/////writing zeroth order statistics to a file.
		}

	printf("\nzero order stats calculated\n");
	fclose(fp_nc);
}


/** 
Function Name: means()
Function Syntax: void means(char id[], float *csum_like, float **like, int no_of_speech_frames, float **adapt_mean, float **mfcc, float **mean_vect)

Description: Creating the model of trained speaker by calculating adapted means 

          Inputs:
           PATH_TEXT are defined in mfcc_defines.h
           @param id                     - consists the id of speaker undergoing training.
           @param csum_like              - consists the cumulative summation of likelihood values along the row.
           @param like                   - consists the likelihood values
           @param no_of_speech_frames    - consists the no of speech frames
           @param mfcc                   - consists the mfcc coefficients of speeched frames.
           @param mean_vect              - consists the mean vectors obtained from GMM-UBM modeling
          
          Outputs:      
           @param adapt_mean             - returns the adapted mean.

          Logic: means () computes the adapted means from the mfcc coefficients, mean vectors, likelihood array and the csum_like while using some standard equations. 
                 1. Firstly the file id_mean.txt is generated where the adapted means are going to be saved. The file generated is then concatenated with the fullpath to generate the complete file. The complete file is
                    /home/ast4/textfiles/id_mean.txt .
                 2. Then the computation of adapted means begins. First the adapted mean array is initialzed to 0. 
                 3. The array is then adapted by summed multiplication of likelihood array and the mfcc vector and then normalizing the sum w.r.t csum_like. A constant 'alpha' is defined which is equal to csum_like/(csum_like+16). 
                 4. Using alpha the adapted mean array is again updated using the standard equation:
                    adapted mean = alpha* adapted mean + (1-alpha)*mean vector.
                 5. The final array of adapted means is written to the file id_mean.txt ( e.g 9003_means.txt).

*/
void means(char id[], float *csum_like, float **like, int no_of_speech_frames, float **adapt_mean, float **mfcc, float **mean_vect, char firstOrderStats_fileName[]){
	int i,j,k;       
	float inv_csum_like, alpha, denominator_alpha;
	float **adap_mean=NULL;


	FILE *fp_fc=NULL;
	
        /**
         The following piece of code is written to generate the file id_mean.txt which is further concatenated to fullpath to get the complete path of filename.
        */		
	//strcat(id, suffix);	                                                  // generating the filename id_coeff.txt
	//strcat(fullpath, id);                                                     // generating the fullpath of the filename where adapted means will be saved.
	int coeffDim =  3 * NUM_OF_COEFFICIENTS; 
	adap_mean = memory_alloc_2D(NUM_OF_GAUSSIANS, coeffDim);     // Allocating 2 dimensional contiguous memory to store adapted means


	for(j=0;j<NUM_OF_GAUSSIANS ; j++)
	{	
		for(i=0; i< coeffDim; i++)
		{
			adapt_mean[j][i] = 0;
		}
	}


	if((fp_fc=fopen(firstOrderStats_fileName,"w"))==NULL)
	  {
		printf("\nunable to open file fc.txt\n");
	  }
    
	/*adap_ubm_mean=fopen(fullpath,"w");                                      // Opening the file to store the ubm adapted means
	
		if (adap_ubm_mean==NULL)
		{
			printf("adapted means file not opening");
			
		}*/
	
	printf("\ncalculating first order stats\n");


	for(i=0; i < NUM_OF_GAUSSIANS; i++)	                                //Calculating the first moment from likelihood array and mfcc coefficients
		
	{
		for(j=0; j < no_of_speech_frames; j++)
		{
			for(k=0; k < coeffDim ; k++)
			
			{
				adapt_mean[i][k] = adapt_mean[i][k] + like[j][i]*mfcc[j][k];             // computing the first_moment by summed multiplication of feature vector and likelihood values
			}
			
		}
		
		/*inv_csum_like = 1/csum_like[i];             //computing the inverse of cumulative sum of likelihood values
			
		for(k=0;k<NUM_OF_COEFFICIENTS;k++)         //Normalizing the first moment w.r.t csum_like
			
			{
			   adapt_mean[i][k] = adapt_mean[i][k]*inv_csum_like;
                           printf("%f\n",adapt_mean[i][k]);	
			}
	
		denominator_alpha = csum_like[i] + rel_fact;               
		alpha = csum_like[i]/denominator_alpha;	
		
		for(k=0;k<NUM_OF_COEFFICIENTS;k++)      // calculating adapted means
		
		{
			adap_mean[i][k] = alpha*adapt_mean[i][k] + (1-alpha)*mean_vect[i][k];
	
		}*/
		
				
			
	}

	printf("\nfirst order stats calculated\n");

	for(i=0; i<NUM_OF_GAUSSIANS; i++)	
	  {

		for(k=0; k < coeffDim; k++)   
		
		  {			
			fprintf(fp_fc, "%f\n", adapt_mean[i][k]);  //Storing first order stats in a file
		  }

	  }
	//fclose(adap_ubm_mean);
	free(adap_mean);	
	fclose(fp_fc);
}


/** 
Function Name: matrix_mult()
Funtion Syntax: void matrix_mult(float **diff_mfcc_mean, int rows_diff, int cols_diff, float **var_vec, int rows_var, int cols_var, float **trans_diff, int rows_transDiff, int cols_transDiff, float result[1][1], int row_res, int col_res)

Description: Function to compute matrix multiplication 

        Inputs:
           @param diff_mfcc_mean         - consists the difference array which is the difference between between the feature set and model.
           @param rows_diff              - consists the no of rows of difference vector
           @param var_vec                - consists a single row of variance vector obtained from GMM-UBM modeling.
           @param cols_var               - consists the no of columns of variance vector 
           @param trans_diff             - consists the transpose of differnce matrix
           @param row_res                - consists the no of rows of result
           @param col_res                - consists the no of columns of result
           
        Outputs
           @param result                 - returns the final result of the multiplication which will be used in Gaussian kernel equation.
 
        Logic: matrix_mult () computes the multiplication of 3 matrices which are the difference vector, the inverse of variance vector and transpose of difference vector and finally returns the result which is stored in an 
               array of size 1*1. 

*/
        
        
  
void matrix_mult(float **diff_mfcc_mean, int rows_diff, float **var_vec, int cols_var, float **trans_diff, float result[1][1],int row_res, int col_res)
{
float temp_mat[1][39];
int i,j,k;

 for(i=0; i<row_res; i++)
	{
		for(j=0; j<col_res; j++)
		{
			result[i][j] = 0;
		}
	}


 
 for(i=0;i<rows_diff;i++)
       {
          for(j=0;j<cols_var;j++)
           {
             temp_mat[i][j]=0;
           }
       }

for(i=0;i<rows_diff;i++)
      {
        for(j=0;j<cols_var;j++)
         {
          temp_mat[i][j]=temp_mat[i][j]+diff_mfcc_mean[i][j]/var_vec[i][j];
         }
      }

for(i=0;i<row_res;i++)
    {
      for(j=0;j<col_res;j++)
       {
        for(k=0;k<cols_var;k++)
         {
           result[i][j]=result[i][j]+temp_mat[i][k]*trans_diff[k][j];
         }
       } 
    }
}



/** 
Function Name: matrix_transpose()
Funtion Syntax: void matrix_transpose(float **diff_mfcc_mean, int row, int col, float **trans_diff)

Description: Function to compute transpose of a matrix 

           Inputs:
           @param diff_mfcc_mean         - consists the difference array which is the difference between between the feature set and model.
           @param row                    - consists the no of rows of difference vector
           @param col                    - consists the no of columns of difference vector
    
           Outputs:
           @param var_vec                   - returns the transpose of differnce matrix

           Logic: matrix_transpose () computes the transpose of difference matrix and returns the result as a 2D array
*/

void matrix_transpose(float **diff_mfcc_mean, int row, int col, float **trans_diff)
{
	int i, j;

	for(i=0; i<row; i++)
	{
		for(j=0; j<col; j++)
		{
			trans_diff[j][i] = diff_mfcc_mean[i][j];
		}
	}
}


/**

Function Name: memory_alloc_2D()
Function Syntax: float **memory_alloc_2D(int row_size, int col_size)

Description: memory_alloc_2D() allocates dimensional contiguous memory 

Inputs:
    @param row_size - no. of rows
    @param col_size - no. of columns

Outputs:
    @return pt      - float float pointer to which 2 dimensional contiguous memory has been allocated.
*/

float **memory_alloc_2D(int row_size, int col_size)
{
	int i;
	float **ptr;

	ptr = (float **)malloc(row_size * sizeof(float*));

	for(i=0; i<row_size; i++)
	{
		ptr[i] = (float *)malloc(col_size * sizeof(float));
	}

	return(ptr);
}

/**

            JOB:  Implementation of map_training()

                   (a) We have obtained the coefficients from mfcc computation module. This module calculates the adapted means using the coefficients of speeched frames and weights, variance and mean computed during GMM-UBM modeling.
                   (b) ubm_training_new() assumes that the files UBM_MEAN, UBM_WEIGHT, UBM_VARIANCE are available in standard locations.
                   (c) The above mentioned files are read to fetch the values of mean, weights and variance obtained during GMM-UBM modeling and store the values in 2D array .
                   (d) model() is called to compute the likelihood of mfcc vector w.r.t each Gaussian mixture and store it in 2D array. Within model(), two more functions namely matrix_transpose() and matrix_mult()
                       are called to compute the transpose of a matrix and perform matrix multiplication between difference vector, the inverse of variance vector and transpose of difference vector respectively. The final multiplication 
                       result is returned to the model() and then used in the standard equation of Gaussian kernel used to calculate the probabilistic alignment of feature on each Gaussian mixture
                   (e) Once the like[] is obtained which contains the likelihood values, summation() computes the csum_like which is the cumulative sum of likelihood values .
                   (f) means() is then called using mean_vector, mfcc vector, likelihood array and the csum_like to compute the adapted means. means() implements some standard equations to calculate the adapted_means.
                       adapted_means after being computed are written to the file id_means.txt (e.g. 9003_means.txt).
                   
            
            Inputs  -  File_Name              - consists the input file name
                    -  speech_nonspeech       - consists boolean array of size equal to total no. of frames, for a speeched frame value is 1 and for nonspeeched frame value is 0. 
                    -  no_of_speech_frames    - consists the number of speech_frames
                    -  total_no_of_frames     - consists the total number of frames
                    -  features_filename      - consists the fullpath of the filename where mfcc coefficients are saved.
                         

            Outputs -  adapted mean for the speaker model saved in file id_mean.txt (e.g 9003_mean.txt)

 Author: 
 Creation Date : 
 Copyright:       
            
***************************************************************************
 Log of Changes
   
            Last Updated: 
            Update Notes: 

********************************************************************************/



