/**
 CPP program to calculate the gaussian posteriorgrams.
Input to the program: a) path to MFCC file
		      b) path to GMM mean, var and weight files. 
              c) path to the output file
              d)number of gaussian components
		      e)feat dimension
		      f) number of frames
			
Output from the program: gaussian posteriorgram 

***/

#include<iostream>
#include<cstdlib>
#include<fstream>
#include<cmath>
#include<cstring>
using namespace std;

#define PI 3.1416
//double nor_arr[196][16];
//double clp_arr[196][16];


//// define the class for MFCC //////////////////////////////////////////////////////
class MFCC
{
	public:
	double **feat;
	int num_frames;
	int dimensions;
	
	
	MFCC(int frames, int dim)
	{
		num_frames = frames;
		dimensions = dim;
	}
	void read_MFCC(char *);
	void display();
};

void MFCC::read_MFCC(char *file_name)
{
	FILE *fid_mfcc;
	fid_mfcc = fopen(file_name,"r");
	
	//// allocate memory for mfcc
		feat = (double**)malloc(num_frames*sizeof(double*));
		for(int iter=0;iter<num_frames;iter++)
		{
			feat[iter] =(double*)malloc(dimensions*sizeof(double));
		}
	
	///// read the mfcc file and store the values in the matrix
	for(int iter=0; iter<num_frames; iter++)
	{
		for(int jter=0; jter<dimensions; jter++)
		   fscanf(fid_mfcc,"%lf",&feat[iter][jter]);
	}
	
	fclose(fid_mfcc);
}

void MFCC::display()
{
	for(int iter=0; iter<num_frames; iter++)
	{
		for(int jter=0; jter<dimensions; jter++)
		{
			cout<<feat[iter][jter]<<"   ";
		}
		cout<<"\n";
	}
}
//////// end of class MFCC /////////////////////////////////////////////////////////////

///// define the class for GMM ////////////////////////////////////////////////////////////////////////////////
class GMM
{
	public:
	double **means;
	double **var;
	double *wts;
	int mixtures;
	int dimensions;

	
	GMM(int mix, int dim)
	{
		mixtures = mix;
		dimensions = dim;
	}
	void read_GMM(char *, char *, char *);
};

void GMM::read_GMM(char *mean_file, char *var_file, char *wts_file)
	{
		
		FILE *fid_mean, *fid_var, *fid_wts;
		fid_mean = fopen(mean_file,"r");
		fid_var = fopen(var_file,"r");
		fid_wts = fopen(wts_file,"r");
		
		
		//// allocate memory for means
		means = (double**)malloc(mixtures*sizeof(double*));
		for(int iter=0;iter<mixtures;iter++)
		{
			means[iter] =(double*)malloc(dimensions*sizeof(double));
		}
		
		///// read the mean file and store the values in the matrix
		for(int iter=0; iter<mixtures; iter++)
		{
		for(int jter=0; jter<dimensions; jter++)
		   fscanf(fid_mean,"%lf",&means[iter][jter]);
		}
		
		//// allocate memory for variances
		var = (double**)malloc(mixtures*sizeof(double*));
		for(int iter=0;iter<mixtures;iter++)
		{
			var[iter] =(double*)malloc(dimensions*sizeof(double));
		}
		
		///// read the var file and store the values in the matrix
		for(int iter=0; iter<mixtures; iter++)
		{
		for(int jter=0; jter<dimensions; jter++)
		   fscanf(fid_var,"%lf",&var[iter][jter]);
		}
		
		//// allocate memory for weights
		wts = (double*)malloc(mixtures*sizeof(double));
		
		///// read the var file and store the values in the matrix
		for(int iter=0; iter<mixtures; iter++)
		{
		   fscanf(fid_wts,"%lf",&wts[iter]);
		}
		fclose(fid_mean);
		fclose(fid_var);
		fclose(fid_wts);
	}
/////////  end of class GMM /////////////////////////////////////////////////////////////////////////////////

////// define the class for Gaussian posteriorgrams
class Posteriorgrams
{
	double **post;
	
	public:
	Posteriorgrams(int frames, int mixes)
	{
		post = (double**)malloc(frames * sizeof(double*));
		
		for(int i=0;i<frames;i++)
		{
			post[i] = (double*)malloc(mixes * sizeof(double));
		}
		
	}
	double** calculate_post_probabilities(GMM, MFCC,char *);
	void gaussian_function(MFCC,double,GMM,int, double*);
	double** matrix_transpose(double **input, int row, int col);
	double** matrix_mult(double *, int , int , double **, int , int );
};

double** Posteriorgrams::calculate_post_probabilities(GMM gmm, MFCC mfcc, char *out_file)
{
	double constant,prod;
	double g_const;
	int mix = gmm.mixtures;
	int dim = mfcc.dimensions;
	int frames = mfcc.num_frames;
	double *out_post;
        double matrix [frames*2][mix*2];
        
        int static count=0;
	
	
	////precompute the model g-consts and normalize the weights
	constant = pow((2*PI),(double(dim)/2));	
	
	for(int iter=0; iter<mix; iter++)
	{
		prod = 1.0;
		for(int jter=0; jter<dim; jter++)
		{
			prod *= gmm.var[iter][jter];		//// compute the product of the variances and get the square root
		}
		prod = sqrt(prod);
		g_const = constant*prod;
		g_const = gmm.wts[iter]/g_const;
		
		///// call the gaussian function now to calculate the exponential part of the equation
		out_post = (double*)malloc(frames * sizeof(double));
		gaussian_function(mfcc,g_const,gmm,iter, out_post);
		
		for(int i =0;i<frames;i++)
		{
			post[i][iter] = out_post[i];
		}
	}
	
	
	///* write the output to a file
	ofstream out_postfile;
	out_postfile.open(out_file);
	for(int i=0;i<frames;i++)
	{
		double sum = 0.0;
		for(int j=0;j<mix;j++)
		{
			sum = sum + post[i][j];
		}
		for(int j=0;j<mix;j++)
		{
			//post[i][j] = post[i][j]/sum;
                        post[i][j] = post[i][j];
                        matrix[i][j] = post [i][j];
			out_postfile << post[i][j] <<"  ";
                        
		}
		out_postfile << endl;
                
	} 

                /*for(int i=0;i<frames;i++){
                for(int j=0;j<mix;j++){
                    cout << matrix[i][j]<< ' ';
                   // cout << i;
                    //cout << j  << endl;
                
                }
                cout << endl;
                }*/	
       
        //calculation of probability
       
        
        
       
        
        count = count+1;
	out_postfile.close();
        return post;
}

void Posteriorgrams::gaussian_function(MFCC mfcc,double c, GMM gmm, int iter, double* out_post)
{
	double *auxC,**aux,**aux_trans,**mult;
	int frames = mfcc.num_frames;
	int dim = mfcc.dimensions;
	

	auxC = (double*)malloc(dim*sizeof(double));
	aux =(double**)malloc(frames*sizeof(double*));
	
	for(int jter=0; jter<frames; jter++)
	{
		aux[jter] = (double*)malloc(dim*sizeof(double));
	}
	
	for(int jter=0; jter<dim; jter++)
	{
		auxC[jter] = -0.5/gmm.var[iter][jter];
	}
	
	for(int lter=0; lter<frames; lter++)
	{
		for(int jter=0; jter<dim; jter++)
		{
			aux[lter][jter] = mfcc.feat[lter][jter] - gmm.means[iter][jter];
			aux[lter][jter] = aux[lter][jter]*aux[lter][jter];
		}
	}
	
	//// transpose aux
	aux_trans = matrix_transpose(aux, frames, dim);
	
	//// multiply auxc and aux_trans
	mult = matrix_mult(auxC, 1, dim, aux_trans, dim, frames);
	
	
	
	for(int i=0;i<frames;i++)
	{
		mult[0][i] = exp(mult[0][i])*c;
		out_post[i] = mult[0][i];
		//cout<<mult[0][i]<<endl;
	}
	
	
}

double** Posteriorgrams::matrix_transpose(double **input, int row, int col)
{
	int iter, jter;
 
    double **output = (double**)malloc(col*sizeof(double*));
    for(iter=0;iter<col;iter++)
    {
		output[iter] = (double*)malloc(row*sizeof(double));
	}
	for(iter=0; iter<row; iter++)
	{
		for(jter=0; jter<col; jter++)
		{
			output[jter][iter] = input[iter][jter];
		}
	}
	
	return output;
}

double** Posteriorgrams::matrix_mult(double *A, int row_A, int col_A, double **B, int row_B, int col_B)
{
	if(col_A != row_B)
	{
	   cout<<"Incompatible matrices"<<endl;
	   exit(1);
	}  
	
	double **result = (double**)malloc(row_A * sizeof(double*));
	for(int i=0;i<row_A;i++)
	{
		result[i] = (double*)malloc(col_B * sizeof(double));
	}
	
	for(int i=0; i<row_A;i++)
	{
		for(int j=0; j<col_B; j++)
		{
			for(int k=0; k< row_B; k++)
				result[i][j] = result[i][j] + A[k]*B[k][j];
		}
	}
	
	return result;
        
}
/////////////// end of class Posteriorgrams ////////////////////////////////////////////////////////////////
	
/// main function
int main(int argc, char* argv[])
{
	/// check for proper number of inputs
	if(argc!= 13)
	{
		cout<<"improper arguments: use the following to run the program correctly"<<endl;
		cout<<"./a.out  mfcc_filePath  GMM_meanFile_normPath   GMM_varFile_normPath  GMM_wtsFile_normPath GMM_meanFile_clpPath   GMM_varFile_clpPath  GMM_wtsFile_clpPath   Posteriorgrams_outputFilePath_normal Posteriorgrams_outputFilePath_clp numMixtures  FeatDimensions  NumOfFrames "<<endl;
		exit(1);
	}
	
	/// declare the necessary file variables
	char *mfcc_file,*mean_file_norm,  *var_file_norm, *wts_file_norm, *mean_file_clp,  *var_file_clp, *wts_file_clp, *post_out_file_norm, *post_out_file_clp;
	int mixtures, feat_dim, frames;
        
        
         double W1 = 0.35;
         double W2 = 0.65;
         double sum =0.0;
         
         
	
	/// allocate memory for the file paths to be stored
	mfcc_file = (char*)malloc(1000*sizeof(char));
	mean_file_norm = (char*)malloc(1000*sizeof(char));
	var_file_norm = (char*)malloc(1000*sizeof(char));
	wts_file_norm = (char*)malloc(1000*sizeof(char));
        mean_file_clp = (char*)malloc(1000*sizeof(char));
	var_file_clp = (char*)malloc(1000*sizeof(char));
	wts_file_clp = (char*)malloc(1000*sizeof(char));
	post_out_file_norm = (char*)malloc(1000*sizeof(char));
        post_out_file_clp = (char*)malloc(1000*sizeof(char));
	
	/// copy the input arguments to the file path variables
	strcpy(mfcc_file,argv[1]);
	strcpy(mean_file_norm,argv[2]);
        strcpy(var_file_norm,argv[3]);
	strcpy(wts_file_norm,argv[4]);
        strcpy(mean_file_clp,argv[5]);
        strcpy(var_file_clp,argv[6]);
	strcpy(wts_file_clp,argv[7]);
	strcpy(post_out_file_norm,argv[8]);
	strcpy(post_out_file_clp,argv[9]);	
	mixtures = atoi(argv[10]);
	feat_dim = atoi(argv[11]);
	frames = atoi(argv[12]);
        cout<<"frames " << frames;
        
         long double nor_sum[frames];
         long double clp_sum[frames];
	
	MFCC m(frames,feat_dim);
	m.read_MFCC(mfcc_file);  /// read the mfcc file
	//cout << "mfcc read calculated";
	/// declare the GMM object
	GMM g(mixtures,feat_dim);
	g.read_GMM(mean_file_norm,var_file_norm,wts_file_norm);  /// read the mean, var and wts files
	
	/// declare the posteriorgrams object
	Posteriorgrams p(frames,mixtures);
	 double** norm_post = p.calculate_post_probabilities(g,m,post_out_file_norm);     /// calculate posteriors and write to file
        
        
        GMM g2(mixtures,feat_dim);
	g2.read_GMM(mean_file_clp,var_file_clp,wts_file_clp);  /// read the mean, var and wts files
        Posteriorgrams p2(frames,mixtures);
	double** clp_post = p2.calculate_post_probabilities(g2,m,post_out_file_clp); 
        //cout << "posteriorgrams calculated";
        
       
        
        for(int i=0;i<frames;i++){
            nor_sum[i]=0;
            clp_sum[i]=0;
        }
        
       
        
       // for (int i=0;i<1;i++){
         //   for (int j=0;j<1;j++){
         //       cout << nor_arr[i][j];
          //  }
           // cout << endl;
       // }
      
        for (int i=0;i<frames;i++){
            for (int j=0;j<mixtures;j++){
                
                nor_sum[i] = nor_sum[i] + norm_post[i][j];
                clp_sum[i] = clp_sum[i] + clp_post[i][j];
               // cout << clp_sum[i] << ' ' ;            
            }
           // cout << endl; 
        }
      
        
       // cout << clp_arr[10][14];
        
        
         
        for(int i=0; i<frames;i++)
        {
            //cout << ((clp_sum[i]*W2)/((clp_sum[i]*W2)+ (nor_sum[i]*W1))) << ' ';
            sum = sum + ((clp_sum[i]*W2)/((clp_sum[i]*W2)+ (nor_sum[i]*W1)));
        }
        sum= (sum/frames);
        
        cout << sum;
      
       
        
    
    return(0);
}
