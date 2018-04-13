#!/usr/bin/python

import os
import sys
import commands
###### check for proper inputs
if(len(sys.argv) != 2):

      print "usage : script.py   phoneNumber"
      exit(); 
phoneNumber=sys.argv[1]


#########################################################################
 ##this script will extract the features for TD and VP and the ivector
### for the TI part. The phoneNumber must be supplied as argument to 
### the python script
#######################################################################



####### initialize arrays to hold the wav and feature paths so that they can be used in the loop
wavPaths = ["/home/training_module/Voice-password/","/home/training_module/Text-dependent/","/home/training_module/Text-dependent/","/home/training_module/Text-dependent/"]
MfccPaths = ["/home/training_module/Mfcc/Voice-password/","/home/training_module/Mfcc/Text-dependent/","/home/training_module/Mfcc/Text-dependent/","/home/training_module/Mfcc/Text-dependent/"]
promptNames = ["_VP_","_TD1_","_TD2_","_TD3_"]
otherFilePath = "/home/training_module/Otherfiles/"
###########################################################################################################

cmd="mkdir -p /home/training_module/Mfcc/Text-independent/"+phoneNumber
os.system(cmd)

##### now write the main loop

for main_iter in range(1,5):

   for inside_iter in range(0,4):

        if main_iter <= 3:
     	    wavFilePath = wavPaths[inside_iter] + phoneNumber + "/" + phoneNumber + promptNames[inside_iter] + str(main_iter) +".wav"
            mfccFilePath = MfccPaths[inside_iter] + phoneNumber + "/" + phoneNumber + promptNames[inside_iter] + str(main_iter) +".mfcc"
            print wavFilePath

	    #### extract features for the wavFile
	    featExtractCmd = "/home/objectFile/mfccNStatComp_39 "   + wavFilePath + " " +  phoneNumber+  " " + otherFilePath+phoneNumber+"_strt"+" "+otherFilePath+phoneNumber+"_end"+" "+otherFilePath+phoneNumber+"_vunv"+" "+otherFilePath+phoneNumber+"_speech"+" "+otherFilePath+phoneNumber+"_avg"+" "+otherFilePath+phoneNumber+"_N"+" "+otherFilePath+phoneNumber+"_F"+" "+  mfccFilePath

	   # print featExtractCmd
	    os.system(featExtractCmd)


             ###  "src/designtemplate/object_file/mfccNStatComp_39 "+extn+" "+str+" "+otherFilePath+phoneNumber+"_strt"+" "+otherFilePath+phoneNumber+"_end"+" "+otherFilePath+phoneNumber+"_vunv"+" "+otherFilePath+phoneNumber+"_speech"+" "+otherFilePath+phoneNumber+"_avg"+" "+otherFilePath+phoneNumber+"_N"+" "+otherFilePath+phoneNumber+"_F"+" "+mfccPath+phoneNumber+"/"+phoneNumber+".mfcc"

   
   if main_iter == 4:
        cmd = 'ls /home/training_module/Text-independent/tmp/*.wav >/home/training_module/Text-independent/tmp/wavList'
	os.system(cmd)

	cmd='mkdir -p /home/training_module/Text-independent/'+phoneNumber
	os.system(cmd)

	### read the wavlist and store all filenames in an array
	wavFiles = [line.rstrip('\n') for line in open('/home/training_module/Text-independent/tmp/wavList')]
	total=len(wavFiles)

	cmd = 'ch_wave ' +wavFiles[0] + '  ' + wavFiles[1] + ' -o /home/training_module/Text-independent/tmp/tmp.wav'
	print cmd
	os.system(cmd)

	for i in range(2,total):

    		cmd = 'ch_wave /home/training_module/Text-independent/tmp/tmp.wav  '+ wavFiles[i] + '  -o /home/training_module/Text-independent/tmp/tmp.wav'
    		os.system(cmd)

	cmd = 'mv /home/training_module/Text-independent/tmp/tmp.wav '+   '  /home/training_module/Text-independent/'+phoneNumber+ '/' + phoneNumber+ '_TI.wav'
	os.system(cmd)

        ############## extract mfcc
	mfccFilePath = "/home/training_module/Mfcc/Text-independent/" +phoneNumber+ '/' + phoneNumber+ '_TI'
	featExtractCmd = "/home/objectFile/mfccNStatComp_39 "   + '/home/training_module/Text-independent/'+phoneNumber+ '/' + phoneNumber+ '_TI.wav'+ " " +  phoneNumber+  " " + otherFilePath+phoneNumber+"_strt"+" "+otherFilePath+phoneNumber+"_end"+" "+otherFilePath+phoneNumber+"_vunv"+" "+otherFilePath+phoneNumber+"_speech"+" "+otherFilePath+phoneNumber+"_avg"+" "+ mfccFilePath +"_N"+" "+ mfccFilePath +"_F"+" "+  mfccFilePath + ".mfcc"

	#print featExtractCmd
	os.system(featExtractCmd)

        #delete wav from tmp folder
        cmd="rm -rf /home/training_module/Text-independent/tmp/*.wav"
	os.system(cmd)

	##### join f and n
	outname = mfccFilePath + ".mat"
	cmd = "/home/objectFile/write_F_N    39   1024  " +  mfccFilePath +"_N" + " " +mfccFilePath +"_F" + " " + outname  
	#print cmd
        output = commands.getoutput(cmd)

	### extract i-vector
	cmd = "export LD_LIBRARY_PATH=/opt/intel/composerxe-2011/lib/ia32:/opt/intel/mkl/lib/ia32"
	commands.getoutput(cmd)
        
        os.environ["LD_LIBRARY_PATH"]="/opt/intel/composerxe-2011/lib/ia32:/opt/intel/mkl/lib/ia32"
        
	cmd = "echo " + outname + "> /home/objectFile/matFileList"
	commands.getoutput(cmd)

	cmd= "/home/objectFile/tmatTrain    /home/objectFile/matFileList    /home/UBM/ubm_mean_sv      /home/UBM/ubm_variance_sv   400 0  /home/training_module/Mfcc/Text-independent/     /home/Tmatrix_LDA_WCCN/tmat_10.txt"

	out = commands.getoutput(cmd)
 	
        









