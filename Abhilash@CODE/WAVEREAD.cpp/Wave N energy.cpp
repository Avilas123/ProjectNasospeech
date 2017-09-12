#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include<math.h>
#define DFT_POINT 512
#define PI 3.1415926536
using namespace std;    


typedef struct header_file
{
    char chunk_id[4];
    int chunk_size;
    char format[4];
    char subchunk1_id[4];
    int subchunk1_size;
    short int audio_format;
    short int num_channels;
    int sample_rate;			 
    int byte_rate;
    short int block_align;
    short int bits_per_sample;
    char subchunk2_id[4];
    int subchunk2_size;			
    float sample_value[DFT_POINT];
} header;

typedef struct header_file* header_p;

int sampling_frequency = 8000;
int framesize = 20*sampling_frequency/1000;
int frameshift = 10*sampling_frequency/1000;
int Energy[10];






int main()

{
	FILE * infile = fopen("/Users/DoD/Desktop/Speech_files/Aishwarya_s1.wav","rb");		
	FILE * outfile = fopen("/Users/DoD/Desktop/Output.wav","wb");		      

	int BUFSIZE = 256;					
	int count = 0;						
	short int buff16[BUFSIZE];				
	header_p meta = (header_p)malloc(sizeof(header));	
	int nb;	
        short *ptr;                                             
        float *Energy_frames;
        int nof_fshift=419;
        int nof_fsize=210;
        int shift=0, incr = 0;
        
        long eoinput;
        float w;
        
        float output[100000];
        float final_arg[1000000];
        
        double complex_input[DFT_POINT], arg_output[DFT_POINT];   
	int i,j,k,l,m,n,o,p,num1,num2,half_dft;
	double c,s,e,a, temp_var, butterfly_real, butterfly_complex;        
        
        float energy, avgEnergy;
        int sampling_frequency = 8000;
        int framesize = 20*sampling_frequency/1000;
        int frameshift = 10*sampling_frequency/1000;
       
        
        
	if (infile)
	{
		fread(meta, 1, sizeof(header), infile);
		fwrite(meta,1, sizeof(*meta), outfile);
		cout << " Size of Header file is "<<sizeof(*meta)<<" bytes" << endl;
		cout << " Sampling rate of the input wave file is "<< meta->sample_rate <<" Hz" << endl;
		cout << " Number of samples in wave file are " << meta->subchunk2_size << " samples" << endl;
                cout << " first chunk is :" << sizeof(meta->chunk_id) << " bytes in size" << endl;
                cout << " The file is a :" << meta->chunk_id << " format" << endl;
                cout << " BITS PER SAMPLE " << meta->bits_per_sample << endl;
               
                
                cout << " Size of data in the audio is: " << sizeof(meta->subchunk2_size)<< " bytes" << endl;
                cout << " The number of channels of the file is "<< meta->num_channels << " channels" << endl;
                cout << " The audio format is PCM:"<< meta->audio_format << endl;
                cout<< " Framesize is " << framesize <<endl;
                cout<< " Frameshift is " << frameshift <<endl;
                
                cout << " Sample_values " << * meta->sample_value << endl;
                
                cout << " nof_fsize " << nof_fsize << endl;
                
                
                
                Energy_frames = (float *)malloc( nof_fshift * sizeof(float) );                
                
                ptr = (short*)malloc((framesize * (nof_fsize)) * sizeof(short));          
                
                cout<< "PTR is " << * ptr << endl;
                
               
                
                fread(ptr, sizeof(short), (nof_fsize)*framesize, infile);
                //cout << "PTR IS " << *ptr << endl;
                
                
                
                
                
                
   eoinput= ( (nof_fsize) * framesize ) - frameshift;
    cout<<  "EOI " << eoinput << endl;
   {
        
               
                
         {
             
              
                           for(p=0;p<DFT_POINT; p++) {
					      if(p<framesize){
							meta->sample_value[p] = (float)(*ptr)/(float)pow(2,(meta->bits_per_sample)-1);
                                                       // cout << "sample_value[p] \t " << meta->sample_value[p] << endl;
                                                        //cout << "P is"  << p<<   "framesize is  "<< framesize  << endl;
                                                         ptr++;
                                                         
						  }
				              else{
							meta->sample_value[p] = 0;     //assigning zero from 161 to 512 samples
                                                       // cout << "sample_value[q] \t " << meta->sample_value[p] << endl;
						  }
	
				         }
                       
                       shift = shift + (frameshift);                                // shift incremented by FRAMESHIFT
                       ptr = ptr - frameshift;                                      // ptr decremented by frameshift
                       k=k+1;
                      

          }ptr = ptr - eoinput;
           //free(ptr);
   }
           /////////////HAMMING//////////////     
                
	for(j=0; j<DFT_POINT; j++)
		{
            
			w = (float)(0.54 - (0.46 * (float)cos((2*PI*j)/(framesize-1))));  // applying hamming equation
                        //cout << " ramdom W " << w << endl;
                        //cout << "sample_value[j] \t " << meta->sample_value[j] << endl;
			output[j] = (w * meta->sample_value[j]); 
                        cout << "Hamming window is " <<output[j] << endl;
        }
                
                
                
        /////////////// DFT///////////                
                
        
	                      // c-cosine, s-sine, e-exponential, a-angle


	n = DFT_POINT;
	m = (int)(log(n)/log(2));
	l = 0; 
	half_dft = n/2;
        
        cout << "DFT_points " << n << endl;
        //cout << "HALF dft " << half_dft << endl;
        {
	for(k=0; k<DFT_POINT; k++)
            int count=0;
		{
            
			complex_input[k] = 0;   //Intializing the array to zero.
                        cout << "complex_input " << complex_input[k] << endl;
                        count++;
		}
             cout << " COUNT " << count << endl;
        }
               
	

          for (k=1; k<(n-1); k++)              // loop is for readjusting the values of hamming output to implement FFT
		{
			num1 = half_dft;
	               
			while (l >= num1)
				{
					l = l - num1;
					num1 = num1/2;
                                     // cout << " num1 " << num1 << endl;
				}
	
			l = l + num1;
	
			if (k < l)                                 // this block is used to swap the values to implement FFT
				{
                          
					temp_var = output[k];
					output[k] = output[l];
                                        output[l] = (float)temp_var;
                                        //cout << " o/p " << output[k] << endl;
					/*
                                          temp_var = complex_input[i];
					  complex_input[i] = complex_input[j];        // written the code just to demonstrate the complex part of the input should be swapped to implement FFT
					  complex_input[j] = temp_var;                // as complex part is 0 here we are not performing this operation.
                                        */
				}
		}

	num1 = 0; 
	num2 = 1;
        
        
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
							butterfly_real = c*output[k+num1] - s*complex_input[k+num1];
							butterfly_complex = s*output[k+num1] + c*complex_input[k+num1];
							output[k+num1] = output[k] - (float)butterfly_real;
							complex_input[k+num1] = complex_input[k] - (float)butterfly_complex;
							output[k] = output[k] + (float)butterfly_real;
							complex_input[k] = complex_input[k] + butterfly_complex;
						}
				}
		}


	for(i=0;i<n;i++)                 // calculating the magnitude of complex FFT
		{
			arg_output[i] = sqrt(pow(output[i], 2) + pow(complex_input[i], 2));
		}

		
	for(i=0; i<(DFT_POINT/2); i++)  // storing the values for first 256 points
		{
			final_arg[i] = (float)arg_output[i];
                        cout << "DFT points are " << final_arg[i] << endl;

		}

}	
        // dft() ends here








		while (!feof(infile))
		{
			nb = fread(buff16,1,BUFSIZE,infile);		
			//cout << nb <<endl;
			count++;					

			

			fwrite(buff16,1,nb,outfile);			
		}
                while(shift != eoinput-frameshift)
		{
			        for(i=0; i<framesize; i++)                   
				  {
					
                                    //cout << " TESTTTTTTT " << endl;
						meta->sample_value[i] = (float)(*ptr)/(float)pow(2,(meta->bits_per_sample-1));  
                                                //cout << "FLOAT POINTER " << *ptr <<endl;
                                                //cout << " sample value IS " <<meta->sample_value[i]  << endl ;
                                                ptr++;
								
				   }
                        

					energy = 0;
                                        int static count=0;
					for(i=0; i<framesize; i++)           // Calculating energy of the frame
						{
							energy = (float)energy + (float)pow(meta->sample_value[i], 2);
                                                       
                                                        
                                                        //cout<< "ENERGY IS "<<Energy_frames[incr] << endl;
                                                       // cout << " NO OF VALUES" << count <<endl; 
                                                        
                                                        //count++;
						}
					                                    
				// cout << "energy IS " << energy << endl ;	
Energy_frames[incr] = energy;
incr++;
//cout << "IncREMENT " << incr << endl;
                        
                        ptr = ptr - frameshift;
			shift = shift + frameshift;
                        
                     //cout<< "ENERGY IS "<<Energy_frames[incr] << endl;
                                        								

		}                                     
                
                for(i=0; i<incr; i++)
                {
                    //cout << "ENERGY_FRAMES "<< Energy_frames[i]<< endl;
                }
                
                


	//cout << " Number of frames in the input wave file are " <<count << endl;

       return 0;
}
 
 
 
 

