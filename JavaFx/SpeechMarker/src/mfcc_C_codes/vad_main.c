
#include<stdio.h>
#include<stdlib.h>
main(int argc,int *argv[])
{

	if(argc != 7 )
	{
		printf("Invalid inputs\n usage: ./a.out  wavname startfilename endfilename vunvfilename, spfrfilename avgengfilename\n");
		exit(1);
	}

	
	
	/////// declaring the necessary variables
	char *wav_filename, *start_file, *end_file, *vunv_file,*spfr_file, *avg_eng_file;
	int *total_frames, *num_speech_frames,*nof_fsize;
	short *speech_non_speech;
	
	/// allocate memory for the char arrays

	wav_filename = (char*)malloc(100*sizeof(char));
	start_file = (char*)malloc(100*sizeof(char));
	end_file = (char*)malloc(100*sizeof(char));
	vunv_file = (char*)malloc(100*sizeof(char));
	spfr_file = (char*)malloc(100*sizeof(char));
	avg_eng_file = (char*)malloc(100*sizeof(char));

	/////// copy the filenames from argv to the char arrays
	strcpy(wav_filename,argv[1]);
	strcpy(start_file,argv[2]);
	strcpy(end_file,argv[3]);
	strcpy(vunv_file,argv[4]);
	strcpy(spfr_file,argv[5]);
	strcpy(avg_eng_file,argv[6]);
	
	//// call the VAD function
	speech_non_speech = vad_enrthr(wav_filename, &total_frames, &num_speech_frames, &nof_fsize, start_file, end_file, vunv_file, spfr_file,avg_eng_file);
}
