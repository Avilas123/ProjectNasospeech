#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
#include "mfcc_defines.h"
#include "mfcc_globals.h"

//float   **memory_alloc_2D(int, int);
void     hamming(float input[], float output[]);
void     dft(float real_input[], float final_arg[]);
void     frequency_warping_spectrum(float Filter_resp[][DFT_POINT], float dft_value[], float spectrum_out[]);
void     Log_spectrum(float energy_output[], float log_out[]);
void     IDCT(float log_values[], float IDCT_out[]);
void     cms(float **mfccCoeffs, int totalFrames,int numCoeffs);
void     ZeroMeanUnitVariance(float **features, int totalFrames,int numCoeffs);
void     filter_bank(float sample_rate, float Filter_resp[][DFT_POINT]);
float    **ComputeDeltaCoefficients(float **inpFeatures, int numFeatures, int dimFeats, int K);
char     *mfcc_computation(char id[], int total_no_of_frames, char fullpath_input[], int nof_fsize, char DirNameforMfccFeatures[]);
float    **ComputeMFCC(char waveName[], int numSpeechFrames, short *speechNonSpeech, int nof_fsize, int totalFrames);
float    **ConcatenateFeatures(float **feature1, int rows1, int cols1, float **feature2, int rows2, int cols2);
float    **ConcatenateFeaturesWithOffset(float **feature1, int rows1, int cols1, float **feature2, int rows2, int cols2, int offset);


/*****    FUNCTION_DECLARATION_ENDS ***************************************************************/


// Assumed  dim(feature1) = dim(feature2) + 2 * K with K offsets from the beginning and End .... 
float **ConcatenateFeaturesWithOffset(float **feature1, int rows1, int cols1, float **feature2, int rows2, int cols2, int offset){
    float **concatfeat1feat2 = (float **) calloc(rows2, sizeof(float *));
    int iter, dimIter;
    for(iter = offset; iter <  rows1 - offset; iter++){
              concatfeat1feat2[iter - offset] = (float *) calloc(cols1 + cols2, sizeof(float));
              
             // copy the feature1 values for each of the dimension to the concatenated array  .... 
 
              for(dimIter = 0; dimIter < cols1; dimIter++)
                          concatfeat1feat2[iter - offset][dimIter] = feature1[iter][dimIter];

               
             // copy the feature2 values for each of the dimension to the concatenated array  .... 
 
              for(dimIter = 0; dimIter < cols2; dimIter++)
                          concatfeat1feat2[iter - offset][dimIter + cols1] = feature2[iter - offset][dimIter];
             

                                                   } // END_OF_FOR_LOOP_VARiable <<< iter >>>

     return concatfeat1feat2;
                                                                                                          
                                                                                                                        } // END_OF_FUNCTION_Concatenate_Features ....

// It is deemed necessary to have rows1 == rows2  ....
float **ConcatenateFeatures(float **feature1, int rows1, int cols1, float **feature2, int rows2, int cols2){
    // rows1 == rows2  .... is assumed ....   and in general cols1 != cols2 ..... 
    
  
    float **concatfeat1feat2 = (float **) calloc(rows2, sizeof(float *));
    int iter, dimIter;
    if (rows1 != rows2){
         printf("Error in Concatenating ");
         return concatfeat1feat2;
                       }
                  
    for(iter = 0; iter <  rows1 ; iter++){
              concatfeat1feat2[iter] = (float *) calloc(cols1 + cols2, sizeof(float));
              
             // copy the feature1 values for each of the dimension to the concatenated array  .... 
 
              for(dimIter = 0; dimIter < cols1; dimIter++)
                           concatfeat1feat2[iter][dimIter] = feature1[iter][dimIter];

               
             // copy the feature2 values for each of the dimension to the concatenated array  .... 
 
              for(dimIter = 0; dimIter < cols2; dimIter++)
                          concatfeat1feat2[iter][dimIter + cols1] = feature2[iter][dimIter];
             

                                                   } // END_OF_FOR_LOOP_VARiable <<< iter >>>

     return concatfeat1feat2;
                                                                                                          
                                                                                                                        } // END_OF_FUNCTION_Concatenate_Features ....





float **ComputeMFCC(char waveName[], int numSpeechFrames, short *speechNonSpeech, int nof_fsize, int totalFrames){
short     *ptr;
int        i,j,k=0,m=0,shift=0;
int        frameNo = 0, speechFrame = 0;
FILE      *fp_input;
long       eoinput;
float      **mfccCoeffs;				

// printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n\n\n\n\n\n\n",totalFrames);

mfccCoeffs = (float **) calloc(totalFrames, sizeof(float *));  // Define the Mfcc Coefficients to have <<< NUMSPEECHFRAMES >>>  
if( ( fp_input=fopen(waveName,"rb") ) == NULL){
   printf("Cannot open the file");
   exit(0);
                                               }
else{    
     fread(&globDataSpeechRecgStruct.header,sizeof(waveStruct),1,fp_input);  //reading the header data of input(.wav) file
     }

       

filter_bank(globDataSpeechRecgStruct.header.Sample_Rate, globDataSpeechRecgStruct.Filter_resp); // Returns the Filter response which is a 2d matrix of 22*256 elements
ptr = (short*)malloc((FRAMESIZE * nof_fsize) * sizeof(short));    // For Storing the entire Data .... 
fread(ptr, sizeof(short), nof_fsize*FRAMESIZE, fp_input);         //reading complete data as single chunk
fclose(fp_input);


eoinput= nof_fsize * FRAMESIZE - FRAMESHIFT;       

          while((shift != eoinput-80)){
            //  printf("\n\n\n\n\n\nCCCHHHEEECCCCCKKKKKKK %d\n\n\n\n\n\n\n\n\n",shift);
           //   if (speechNonSpeech[frameNo] == 1){ // Check Whether the frame is Speech or not .... 
                          // Declare the array to contain sufficient number of MFCC Coefficients ..... 
                            
                           mfccCoeffs[speechFrame] = (float *) calloc(NUM_OF_COEFFICIENTS, sizeof(float )); 
                            
                           for(i=0; i<DFT_POINT; i++) {
					      if(i<FRAMESIZE){
							globDataSpeechRecgStruct.sample_value[i] = (float)(*ptr)/(float)pow(2,(globDataSpeechRecgStruct.header.Bits_Per_Sample)-1);  
                                                         ptr++;
						  }
				              else{
							globDataSpeechRecgStruct.sample_value[i] = 0;     //assigning zero from 161 to 512 samples
						  }
	
				         }

					hamming(globDataSpeechRecgStruct.sample_value, globDataSpeechRecgStruct.hamming_output1);  //calculates the hamming output
                                        
                                        dft(globDataSpeechRecgStruct.hamming_output1, globDataSpeechRecgStruct.final_arg1);        //computes the 512 point DFT
                                       
					frequency_warping_spectrum(globDataSpeechRecgStruct.Filter_resp, globDataSpeechRecgStruct.final_arg1, globDataSpeechRecgStruct.warping_spectrum1); //calculates the warping spectrum
                                        
					Log_spectrum(globDataSpeechRecgStruct.warping_spectrum1, globDataSpeechRecgStruct.log_magnitude1);    //calculates the log magnitude
                                        
					IDCT(globDataSpeechRecgStruct.log_magnitude1, globDataSpeechRecgStruct.IDCT_Output1);                 //computes the Inverse Discrete cosine transform                      


                                       // printf(" Printing the MFCC COeffs \n");
                                        for(i=1; i<=NUM_OF_COEFFICIENTS; i++){
                                               mfccCoeffs[speechFrame][i-1] =   globDataSpeechRecgStruct.IDCT_Output1[i]; // Finally Storing the MFCC_COEFS in
                                              // printf("  %f ",     mfccCoeffs[speechFrame][i-1]);                                                                     
						//	fprintf(fp_coeff, "%1.10f\n", globDataSpeechRecgStruct.IDCT_Output1[i]);             //printing the mfcc coefficients to coefficients.txt file 
						                              } 
                                        speechFrame++; // Increment the Counter of number Of Speech Frames ....  
                                         				 
                                                                  //    } // END_OF_IF_LOOP <<< if (speechNonSpeech[frameNo] == 1)>> I.E. SPEECH_FRAME 
                       frameNo++;
                       shift = shift + (FRAMESHIFT);                                // shift incremented by FRAMESHIFT
                       ptr = ptr - FRAMESHIFT;                                      // ptr decremented by frameshift
                       k=k+1;
                      
			

          }     // while LOOP ends


ptr = ptr - eoinput;
 //free(ptr);	// Free the allocated Pointer ....... 

//  Cepstral Mean Subtraction Logic is implemented only if CHANNELFLAG is 1. 

          // if(CHANNELFLAG == 1){
            //       cms(mfccCoeffs, totalFrames,NUM_OF_COEFFICIENTS);  // calling the cepstral mean subtraction function to subtract the mean from the coefficients and save the mean subtracted coefficients in file
              //                   }
                   

  return   mfccCoeffs;
                                           }   //END_OF_FUNCTION_ComputeMFCC() 











float **ComputeDeltaCoefficients(float **inpFeatures, int numFeatures, int dimFeats, int K){
    //  The inpFeatures is of dimension (numFeatures * dimFeats) and can be referred as inpFeatures[2][0] etc ...
    //  Start the frames from Feature No. = skpF by skipping the first the features from [0  ......  K - 1] feature Vector ...
    //  And  End the processing after processing [numFeatures - K] feature Vector i.e. produce upto numFeatures - 2 * K feature 
    //  Vectors
    
     float **deltaFeatVect;
     int   iter, dimIter, kIter;
     float prevVal, nextVal;
     deltaFeatVect = (float **) calloc(numFeatures, sizeof(float *));
     printf(" In Delta Now ....  %d Limit and NNUM_FEATURES = %d", numFeatures, numFeatures -  2 * K);
     float normVals = 0;
     
// computing the Normalizing Coefficients .... now 

     for(iter = 1; iter <= K; iter++)
              normVals += (iter * iter);


     normVals = 2 * normVals;
// Implemented the formula >>>   2 * \sum_{i=1}^{i=K} i^{2}

     for(iter = 0; iter < numFeatures; iter++){ // The Iterator is for the Input_Feature_Vector .. THE OP_FEAT_VECT should starts from [iter - K] locs
           
           deltaFeatVect[iter] =  (float *) calloc(dimFeats, sizeof(float));
           //printf(" \n Printing the Values for iteration No = %d \n", iter - K );  
           for(dimIter = 0; dimIter < dimFeats; dimIter++){  //  Make it feel like a Vector ....   It does exactly that  ....
                   prevVal   = 0;   // For Each dimension Accumulate the MFCC values span-wise
                   nextVal   = 0;
                   
                   
                   for(kIter = 1; kIter <= K; kIter++) { //  Accumulater for the span_Variable .....  spans exactly [2 * K + 1] locations with Varying weights  
                   // Check whether the next and previous locations exists  .....
                          // check the boundary condition ....
                          if(iter + kIter < numFeatures)
                                nextVal  +=  (kIter    * inpFeatures[iter + kIter][dimIter]);
                          // check the initial part of the mfcc features .... 
                          if(iter - kIter >= 0)
                                prevVal  +=  ((-kIter) * inpFeatures[iter - kIter][dimIter]); 
                         // normVals += kIter * kIter;
                                                        }
                  
                               
                   //printf(" %f ",  deltaFeatVect[iter - K][dimIter]);
                   deltaFeatVect[iter][dimIter] = ((nextVal + prevVal)/normVals);                               
                   //printf(" %f ",  deltaFeatVect[iter - K][dimIter]);                                        
                                                            } // END_OF_FOR_LOOP of Variable <<< dimIter >>> 

                                                }// END_OF_FOR_LOOP of Variable <<< iter >>>  

    // printf(" NUMBER OF ITERATION = %d\n", iteration);
     return deltaFeatVect;
    
                                                                                     }



/**
   Function Name: mfcc_computation
   Function Syntax: char * mfcc_computation(char id[], int total_no_of_frames, char fullpath_input[], int nof_fsize)
   
   Inputs:
   @param id                      - consists the id of the speaker    
   @param total_no_of_frames      - consists the total number of frames
   @param fullpath_input          - consists the fullpath of the input file name
   @param nof_fsize               - consists the number of frames obtained without overlapping, gets value only after vad module is executed

   Outputs:
   @return features_filename      - consists the Features file name where mfcc coefficients are saved. If CHANNELFLAG is 1, mfcc coefficients are saved after implementing cepstral mean subtraction
                                    

   Description : Feature extraction module calculates the Mel Frequency Cepstral Coefficients(mfcc22) for all the frames.
                 waveStruct and globDataSpeechRecgStruct have been defined in mfcc_globals.h.
*/

/*char *mfcc_computation(char id[], int total_no_of_frames, char fullpath_input[], int nof_fsize, char DirNameforMfccFeatures[]){
short *ptr;
int i,j,k=0,m=0,shift=0;
int id_len, suffix_len, features_filename_len;
char *features_filename;
FILE *fp_coeff, *fp_input;
long eoinput;
char suffix[11] = "_coeff.txt"; 
char *features_filepath;
features_filepath = (char *) calloc(SIZE_FULLPATH, sizeof(char));
features_filepath =    DirNameforMfccFeatures;   
 // Used to attach the full path to the file of coefficients without mean subtraction. PATH_TEXT - contains full path of text files directory
printf(" \n%s filepath put here \n",features_filepath);					
	
if( ( fp_input=fopen(fullpath_input,"rb") ) == NULL)  // opening input(.wav) file
  {
   printf("Cannot open the file");
   exit(0);
  }
else
  {    
     fread(&globDataSpeechRecgStruct.header,sizeof(waveStruct),1,fp_input);  //reading the header data of input(.wav) file
    
  }

       

filter_bank(globDataSpeechRecgStruct.header.Sample_Rate, globDataSpeechRecgStruct.Filter_resp); // Returns the Filter response which is a 2d matrix of 22*256 elements


ptr = (short*)malloc((FRAMESIZE * nof_fsize) * sizeof(short));    

fread(ptr, sizeof(short), nof_fsize*FRAMESIZE, fp_input);         //reading complete data as single chunk

fclose(fp_input);


/
The following piece of code is concatenating id with constant _coeff.txt to generate id_coeff.txt where the coefficients are getting saved. Then the fullpath is being attached to the filename to give the complete
path of filename.
/
id_len = strlen(id);
suffix_len = strlen(suffix);
features_filename_len = id_len + suffix_len + strlen(PATH_TEXT) + 1;
features_filename = (char*)malloc(features_filename_len * sizeof(char));

strcpy(features_filename, features_filepath);
strcat(features_filename,id);
strcat(features_filename,suffix);

printf("\nfeatures=%s\n",features_filename);

if((fp_coeff = fopen(features_filename, "w")) == NULL)         // opening id_coeff.txt file (eg: 9001_coeff.txt, 9002_coeff.txt)
		{
			printf("Unable to open the %s file\n",features_filename);
			exit(0);
		}

eoinput= nof_fsize*FRAMESIZE - FRAMESHIFT;
/
   Calculating the mel cepstral coefficients obtained after implementing 5 functions as mentioned below. 
   While Loop Logic: One temporary variable shift is used to traverse the entire file and take one frame of FRAMESIZE with shift of FRAMESHIFT. Sample value is calculated for the every frame and following 5 functions are called.
                     hamming(), DFT(), frequency_warping_spectrum(), Log_spectrum(), IDCT() through which we finally get mfcc coefficients. 
                     

          while((shift != eoinput))   
             {
                                       for(i=0; i<DFT_POINT; i++)  //calculating channel data for one block of 20 milli seconds
				        {
					      if(i<FRAMESIZE)
						  {
							globDataSpeechRecgStruct.sample_value[i] = (float)(*ptr)/(float)pow(2,(globDataSpeechRecgStruct.header.Bits_Per_Sample)-1);  
                                                         ptr++;
						  }
				              else
						  {
							globDataSpeechRecgStruct.sample_value[i] = 0;                              //assigning zero from 161 to 512 samples
						  }
	
				         }

					hamming(globDataSpeechRecgStruct.sample_value, globDataSpeechRecgStruct.hamming_output1);  //calculates the hamming output
                                        
                                        dft(globDataSpeechRecgStruct.hamming_output1, globDataSpeechRecgStruct.final_arg1);        //computes the 512 point DFT
                                        
					//frequency_warping_spectrum(globDataSpeechRecgStruct.Filter_resp, globDataSpeechRecgStruct.final_arg1, globDataSpeechRecgStruct.warping_spectrum1); //calculates the warping spectrum
                                        
					//Log_spectrum(globDataSpeechRecgStruct.warping_spectrum1, globDataSpeechRecgStruct.log_magnitude1);    //calculates the log magnitude
                                        
					//IDCT(globDataSpeechRecgStruct.warping_spectrum1, globDataSpeechRecgStruct.IDCT_Output1);                 //computes the Inverse Discrete cosine transform                      



                                        for(i=1; i<=NUM_OF_COEFFICIENTS; i++)
						{
							fprintf(fp_coeff, "%1.10f\n", globDataSpeechRecgStruct.final_arg1[i]);             //printing the mfcc coefficients to coefficients.txt file 
						} 				 

                       shift = shift + (FRAMESHIFT);                                // shift incremented by FRAMESHIFT
                       ptr = ptr - FRAMESHIFT;                                      // ptr decremented by frameshift
                       k=k+1;
                      
			

          }     // while LOOP ends

fclose(fp_coeff);

ptr = ptr - eoinput;
free(ptr);	

/**
    Cepstral Mean Subtraction Logic is implemented only if CHANNELFLAG is 1. 
*/

           /* if(CHANNELFLAG == 1)

           {
            
	     cms(total_no_of_frames,features_filename);  // calling the cepstral mean subtraction function to subtract the mean from the coefficients and save the mean subtracted coefficients in file
             
                        
           }
            
           
            return features_filename;
            

           

}   //mfcc_computation() ends here*/




/** 

   Function Name: filter_bank
   Function Syntax: void filter_bank(float sample_rate, int filter_nos, int half_dft, float Filter_resp[][DFT_POINT])
   
   Description: filter_bank calculates the frequency response of the mel filter bank
   
   Inputs:     
   @param sample rate            - consists the sampling rate of the input file
   @global NUM_OF_FILTER         - consists the number of filters constituting the filter bank. Defined in mfcc_define.h file.        
   @global DFT_POINT             - defined in mfcc_defines.h file

   Outputs:
   @param Filter_resp            - returns the Filter Coefficeints as a 2D matrix
   
   Logic : The filter bank () computes the filter frequency response on mel scale. 
           (i)   Mel frequency is calculated using the standard formula used in the filter_bank (). The standard formula is 
                 mel_frequency = 2595 * log10((1+(sample_rate/1400)))
           (ii)  The maximum frequency limit on linear scale is 4000 Hz which is equivalent to sample_rate/2 . 
           (iii) This frequency on linear scale is converted to mel scale in order to capture the vocal tract spectral information in a better way. 
           (iv)  From the mel-frequency,filter spacing is calculated for each of the 22 filters that constitute the filter bank which is then used to calculate the center frequencies.
           (v)   Now on frequency scale of 0-4000 Hz, we compute the modified bin[] which is then compared to modified_bin[bin_center] where each bin center is the center of each traingular filter. The comparision is done to find        
                 whether value of modified bin [] lies on positive slope or the negative slope of the triangular filter so that accordingly frequency response is calculated by equation y=mx where m is the slope of the triangular filter
                 and x is the value of modified_bin[].
*/


void filter_bank(float sample_rate, float Filter_resp[][DFT_POINT])      
{

	float filter_spacing, mel_freq, center_freq[NUM_OF_FILTER+1], modified_bin[DFT_POINT];
	float scale_point_freq;
	int i, j;
	int bin_center[NUM_OF_FILTER+1];
        int half_dft=DFT_POINT/2;        

	mel_freq = 2595 * log10((1+(sample_rate/1400)));                                           // Calculating Mel Frequency
  
	filter_spacing = (mel_freq/(NUM_OF_FILTER+1));                                             // Calculating Filter Spacing using mel frequency

	for(i=0; i<=NUM_OF_FILTER; i++)
		{
			center_freq[i] = 700 * (pow(10,((filter_spacing * (i+1))/2595)) - 1);      // Calculating Center Frequency using filter spacing
		}

	scale_point_freq = ((float)sample_rate/2)/half_dft;                                               // Calculating slot in frequency domain

/******************************** below three FOR loops used to calculate the Filter bank Coefficents *****************************************/
	for(i=0; i<=half_dft+1; i++)
		{
			modified_bin[i] = (scale_point_freq * i);                                        // Calculating modified bin using slot             

		}

	for(i=0; i<=NUM_OF_FILTER; i++)
		{
			bin_center[i] = ((center_freq[i] * half_dft)/(sample_rate/2)) + 0.99;    // Calculating bin center using center frequency
		}
	
	bin_center[-1] = 0;
        

/**
   Calculating Filter Frequency Response
   Logic of the following loop: The following loop calculates the filter frequency response. The response is calculated by first finding whether the value of modified_bin lies on the positive slope or on the negative slope. 
   If neither of the two happens the value is assigned to 0. The frequency response is calculated individually for each of the 22 filters that constitute the filter bank.

*/
	for(i=0; i<NUM_OF_FILTER; i++)
		{
			for(j=0; j<half_dft; j++)
				{
					if((modified_bin[j] < modified_bin[bin_center[i]]) && (modified_bin[j] > modified_bin[bin_center[i-1]]))
						{
							Filter_resp[i][j] = (modified_bin[j]-modified_bin[bin_center[i-1]])/(modified_bin[bin_center[i]]-modified_bin[bin_center[i-1]]);
						}
				    	else
						{
							if((modified_bin[j] >= modified_bin[bin_center[i]]) && (modified_bin[j] < modified_bin[bin_center[i+1]]))
							{
								Filter_resp[i][j] = (modified_bin[bin_center[i+1]]-modified_bin[j])/(modified_bin[bin_center[i+1]]-modified_bin[bin_center[i]]);
							}			
							else
							{
								Filter_resp[i][j] = 0;
							}
						}

						
				}


		}

}	// filter_bank() ends here





/**
Function Name: hamming()
Function syntax: void hamming(float input[], float output[])

Description: Applying hamming window to speech signal

Inputs:
   @param input          - contains the channel data for one frame.

Outputs:
   @param output         - returns the windowed data.
            
   Logic: Hamming() calculates the hamming output of the channel data. It is computed by multiplying the hamming equation with the channel data for the entire length of channel data.
*/

void hamming(float input[], float output[])

{

     
	int i;
	float w;

	for(i=0; i<DFT_POINT; i++)
		{
			w = (float)(0.54 - (0.46 * (float)cos((2*PI*i)/(FRAMESIZE-1))));  // applying hamming equation
			output[i] = (w * input[i]);                                             
		}
}



/**     
Function Name: dft()
Function Syntax: void dft(float real_input[], float final_arg[])

Description: Calculating dft of windowed speech signal

Inputs:
    @param input          - input contains the hamming output of the channel data.

Outputs:
    @param final_arg      - returns the 256 point dft of the windowed data. 
            
    Logic : dft() computes the 512 point dft of the hammimg windowed output. 
            (i)   The function returns the first 256 points only taking into consideration the symmetricity property of the Discrete Fourier Transform(dft).
            (ii)  dft is computed in 2 steps. In 1st step, the values of hamming windowed output are readjusted by swapping the values. The readjustment is done to implement the bit reversal concept required in Decimation in Time 
                  FFT implementation.
            (iii) The swapped values are then used for dft calculation. Here dft is calculated by implementing the algorithm of Decimation in time(DIT) FFT i.e 512 point is computed by computing first 1-point, 2-point,4-point, 8-        
                  point ..... and finally 256-point.
            (iv)  After getting all the values the magnitude at each point is calculated by taking the sqaure root of sum of square of real part and imaginary part. Finally the 1st 256 points are returned in the array final_array[].
*/

void dft(float real_input[], float final_arg[])
{
       
	int n,m;
	double complex_input[DFT_POINT], arg_output[DFT_POINT];   
	int i,j,k,num1,num2,half_dft;
	double c,s,e,a,temp_var,butterfly_real,butterfly_complex;                              // c-cosine, s-sine, e-exponential, a-angle


	n = DFT_POINT;
	m = (int)(log(n)/log(2));
	j = 0; 
	half_dft = n/2;

	for(i=0; i<DFT_POINT; i++)
		{
			complex_input[i] = 0;   //Intializing the array to zero.
		}

	
/**
    The following loop is implementing the concept of bit-reversal of each element's index so that the new sets of inputs can be used in the Decimation in Time concept of FFT. Bit reversal of each element's index is found
    and swapping is done for the value of original index and the value of bit reversed index. Eg: a[x] is the element, x is bit reversed which gives us y so a[x] swapped with a[y]
*/
          for (i=1; i<(n-1); i++)              // loop is for readjusting the values of hamming output to implement FFT
		{
			num1 = half_dft;
	
			while (j >= num1)
				{
					j = j - num1;
					num1 = num1/2;
				}
	
			j = j + num1;
	
			if (i < j)                                 // this block is used to swap the values to implement FFT
				{
					temp_var = real_input[i];
					real_input[i] = real_input[j];
					real_input[j] = (float)temp_var;
					/*
                                          temp_var = complex_input[i];
					  complex_input[i] = complex_input[j];        // written the code just to demonstrate the complex part of the input should be swapped to implement FFT
					  complex_input[j] = temp_var;                // as complex part is 0 here we are not performing this operation.
                                        */
				}
		}

	num1 = 0; 
	num2 = 1;

/**

*/
	for (i=0; i < m; i++)                         // To calculate FFT's from the swapped inputs
		{
			num1 = num2;
			num2 = num2 + num2;
			e = -6.283185307179586/num2;
			a = 0.0;

			for (j=0; j < num1; j++)        
				{
					c = cos(a);
					s = sin(a);
					a = a + e;

					for (k=j; k < n; k=k+num2)
						{
							butterfly_real = c*real_input[k+num1] - s*complex_input[k+num1];
							butterfly_complex = s*real_input[k+num1] + c*complex_input[k+num1];
							real_input[k+num1] = real_input[k] - (float)butterfly_real;
							complex_input[k+num1] = complex_input[k] - (float)butterfly_complex;
							real_input[k] = real_input[k] + (float)butterfly_real;
							complex_input[k] = complex_input[k] + butterfly_complex;
						}
				}
		}


	for(i=0;i<n;i++)                 // calculating the magnitude of complex FFT
		{
			arg_output[i] = sqrt(pow(real_input[i], 2) + pow(complex_input[i], 2));
		}

		
	for(i=0; i<(DFT_POINT/2); i++)  // storing the values for first 256 points
		{
			final_arg[i] = (float)arg_output[i];

		}

}	// dft() ends here




/**
Function Name: frequency_warping_spectrum()
Function Syntax: void frequency_warping_spectrum(float Filter_resp[][DFT_POINT], float dft_value[], float spectrum_out[])

Description: Computing the frequency warped spectrum by passing the DFT output through the filter bank.

Inputs:
@param Filter_resp   - consists the 2D matrix of filter response.
@param dft_value     - consists the 256-point DFT of the windowed data

Outputs:
@param spectrum_out  - returns the filter warped spectrum of the input data.
            
            Logic : frequency_warping_spectrum() calculates the frequency warped spectrum to map the Discrete fourier Transform of the windowed data onto mel scale.
                    (i)  It is computed by multiplying the filter response of the triangular filter which is a 2D matrix of 22*256 elements with the 256 point DFT of the windowed data.
                    (ii) The result of multiplication is strored in an array spectrum_out[] which is returned as the final output. The values of array spectrum_out[] are mapped spectrum onto mel scale.
*/

void frequency_warping_spectrum(float Filter_resp[][DFT_POINT], float dft_value[], float spectrum_out[])

{
     
	
	int i, j;

	for(i=0; i<NUM_OF_FILTER; i++)					
		{
			spectrum_out[i] = 0;

			for(j=0; j<(DFT_POINT/2); j++)			
				{
					spectrum_out[i] = spectrum_out[i] + Filter_resp[i][j] * dft_value[j] ;   // DFT values being warped with filter response
					
				}
		}    	
	
}	//frequency_warping_spectrum() ends here



/** 
Function Name: Log_spectrum
Function Syntax: void Log_spectrum(float spectrum_output[], float log_out[])

Description: Applying Log function on frequency warped spectrum which is the output of frequency_warping_spectrum ()

Inputs:
@param spectrum_output  - consists the frequency warped spectrum on mel scale.

Outputs:
@param log_out          - returns the log values of the frequency warped spectrum.


               Logic : Log_spectrum () computes the logarithm of the frequency warped spectrum on mel scale and returns the values in an array log_out[].

*/
void Log_spectrum(float spectrum_output[], float log_out[])

{

	int i;

	for(i=0; i<NUM_OF_FILTER; i++)
		{
			log_out[i] = (float)log(spectrum_output[i])/(float)log(10);  // taking the logarithm of frequency warped spectrum 
			//log_out[i] = (spectrum_output[i])/10.0;
			//printf("\nlog=%f\n",log_out[i]);
		}
}	//Log_spectrum() ends here



 
/**              
Function Name: IDCT()
Function Syntax: void IDCT(float log_values[], float IDCT_out[])

Description: Calculating Inverse discrete cosine transform of the log values of frequency warped spectrum.

Inputs:
   @param log_values     - consists the log values of the frequency warped spectrum which is the output of Log_spectrum () .
   @global NUM_OF_FILTER - consists the number of filters constituting the filter bank. Defined in mfcc_defines.h file.

Outputs:
   @param IDCT_out    - returns the Inverse Discrete Transform as an array.

        Logic : IDCT() computes the inverse discrete transform of the log values of frequency warped spectrum. 
                (i)  IDCT is calculated by multiplying the cosine function with the log values of frequency warped spectrum.
                (ii) For each speech frame, we get an array of 13 mfcc coefficients which is returned as IDCT_out []. 
 
*/

void IDCT(float log_values[], float IDCT_out[])

{


	int i, j;

	for(i=0; i<=NUM_OF_COEFFICIENTS; i++) 
		{
			IDCT_out[i] = 0;

			for(j=0; j<NUM_OF_FILTER; j++)
				{
					IDCT_out[i] = IDCT_out[i] + (log_values[j] * (float)cos((PI * (j+0.5) * (i+1))/NUM_OF_FILTER));	// computing the inverse discrete transform of the log values of frequency warped spectrum
         																
		                }

                 }
}//IDCT() ends here




/** 
Function Name: cms()
Function Syntax: void  cms(int total_no_of_frames,  char *features_filename)

Description: Calculating the cepstral mean subtracted features and saving the coefficients in a file id_coeff.txt.

Inputs:
   @param total_no_of_frames   - consists the total number of speech frames.
   @param features_filename    - consists the filename where the mfcc coefficients are saved. cms is implmented on this file and the mean subtracted coefficients are saved on this file which is returned as an argument.
   



     Logic : cms() function generates the cepstral mean subtracted coefficients.
             
             (i)   coefficients are read from the file id_coeff.txt into a 2d array, then cumulative sum is calculated along the rows of mfcc coefficients and stored in 1d array csum_coeff[].
             (ii)  Mean is calculated from the cumulative sum and stored in 1d array mean_coeff[].
             (iii) Mean is subtracted from the coefficients to generate the cepstral mean subtracted coefficients
             (iv)  Opening the same id_coeff.txt (eg: 9003_coeff.txt) file in write mode and the final coefficients are being saved.



*/
void  cms(float **mfccCoeffs, int totalFrames,int numCoeffs){

       float *meanVects = (float *) calloc(numCoeffs, sizeof(float));
       int iter, dimIter;
       // Compute the mean for each of the dimension and then subtract it from the feature vectors .... 
       // \bm{c}^{'} = \sigma_{0}^{numFrames-1} (\bm{c_i} - \bm{c_mean}) // This equation is implemented in the next few lines ... 
       for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
            meanVects[dimIter]  = 0;          
            for(iter = 0 ; iter < totalFrames; iter++)
                    meanVects[dimIter] += mfccCoeffs[iter][dimIter];
             meanVects[dimIter] =  meanVects[dimIter]/totalFrames;
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

                  
     // Do the Subtraction Now ..... 
          for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
                   
            for(iter = 0 ; iter < numCoeffs; iter++)
                   mfccCoeffs[iter][dimIter] = mfccCoeffs[iter][dimIter] - meanVects[dimIter] ;
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

                  
	free(meanVects);

                                                          } // END_OF_THE_FUNCTION_CMS 
	







void  ZeroMeanUnitVariance(float **mfccCoeffs, int totalFrames,int numCoeffs){

       float *meanVects = (float *) calloc(numCoeffs, sizeof(float)); // store the mean of each of the dimension
       float *variance  = (float *) calloc(numCoeffs, sizeof(float)); // store the variance of each of the dimension 
       
       int iter, dimIter;
       float tmpxSquared = 0;
       // Compute the mean for each of the dimension and then subtract it from the feature vectors .... 
       // \bm{c}^{'} = \sigma_{0}^{numFrames-1} (\bm{c_i} - \bm{c_mean}) // This equation is implemented in the next few lines ...
       //  
       for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
            meanVects[dimIter]  = 0;
            tmpxSquared = 0;          
            for(iter = 0 ; iter < totalFrames; iter++){
                    meanVects[dimIter] += mfccCoeffs[iter][dimIter];
                    //tmpxSquared += (mfccCoeffs[iter][dimIter] * mfccCoeffs[iter][dimIter]);
                                                       }
             
            printf(" Before Mean = %f\n ", meanVects[dimIter]);
            meanVects[dimIter] =  meanVects[dimIter]/totalFrames;
           // variance[dimIter]  =  sqrt((tmpxSquared - (meanVects[dimIter] * meanVects[dimIter])) * 1.0/totalFrames);
            //printf("\n Variance = %f ",   variance[dimIter]);     
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

       //  Do the Variance Computation Now .... 
         for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
            
            tmpxSquared = 0;          
            for(iter = 0 ; iter < totalFrames; iter++){
                   // meanVects[dimIter] += mfccCoeffs[iter][dimIter];
                    tmpxSquared += (mfccCoeffs[iter][dimIter] - meanVects[dimIter] ) * (mfccCoeffs[iter][dimIter] - meanVects[dimIter] );
                                                       }
             
             variance[dimIter] = sqrt ((tmpxSquared  * 1.0)/totalFrames);
            
           // variance[dimIter]  =  sqrt((tmpxSquared - (meanVects[dimIter] * meanVects[dimIter])) * 1.0/totalFrames);
             printf("\n Variance = %f Mean = %f  Number of Frames = %d ",   variance[dimIter], meanVects[dimIter], totalFrames);     
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

                  
     // Do the Subtraction Now ..... 
           for(dimIter = 0; dimIter < numCoeffs; dimIter++) {
                   
            for(iter = 0 ; iter < numCoeffs; iter++)
                   mfccCoeffs[iter][dimIter] = (mfccCoeffs[iter][dimIter] - meanVects[dimIter])/variance[dimIter];
            
                                                        } // END_OF_THE_FOR_LOOP_OF Variable <<<< dimIter>>>>> 

                  
	free(meanVects);
        free(variance);

                                                          } // END_OF_THE_FUNCTION_CMS 


/**
              
           JOB:  Implementation of Mel Frequency Cepstral Coefficients(MFCC) computation

            a) The module is computing the mel frequency cepstral coefficients(mfcc) which are getting saved in a file id_coeff.txt (eg: 9003_coeff.txt)
            b) The module is making the use of 7 functions which are mentioned as below
               1. filter_bank()  2. hamming()  3. DFT() 4. frequency_warping_spectrum() 5. Log_spectrum() 6. IDCT() 7. cms()
               The logic of each of the above mentioned 7 functions have been explained just before the definition of each function.
            c) The first 6 functions are involved in computing the mfcc coefficients and getting it stored in array of size total_no_of_frames * NUM_OF_COEFFICIENTS. 
            d) cms() is implemented only if CHANNELFLAG is 1. If it is 0 mfcc coefficients are saved in file id_coeff.txt without implementing cms().
            d) cms() function is used to remove the channel effects from the coefficients so that coefficients will represent only the vocal tract information.
               cms() subtacts the mean of the coefficients from each of the coefficients and hence finally we get the cepstral mean subtracted coefficients which are written to file id_coeff.txt (e.g 9003_coeff.txt).
               
            
             mfcc_computation() Module
                               
                                  Inputs  - input file name i.e. the file name of the current speaker undergoing training.                                          
                                          - no of speech frames
                                          - fullpath of the input file name.
                         

                                  Outputs - fullpath of the filename where the mfcc coefficients are saved.

 Authors: 
 Creation Date : 
 Copyright:       
            
***************************************************************************
 Log of Changes
   
            Last Updated: 
            Update Notes: 

********************************************************************************/
