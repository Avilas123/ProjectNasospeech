#!/usr/bin/python
import os
import sys
import commands
###### check for proper inputs
if(len(sys.argv)!=3):
	print "usage : script.py   SpeakerID   fileName"
	exit()

SpkrId=sys.argv[1]
fileName=sys.argv[2]



##########
wavFilePath="/home/testing_module/Text-independent/"+SpkrId+"/"
wavFile="/home/testing_module/Text-independent/"+SpkrId+"/"+fileName
########split the wav file and taken the first part
filePath=wavFile.split(".")[0]

fName=fileName.split(".")[0]+"_F"
nName=fileName.split(".")[0]+"_N"


mfccFilePath=filePath+".mfcc"
otherFilePath="/home/training_module/Otherfiles/"
trnMfccDir="/home/training_module/Mfcc/Text-independent/"


##################Extract mfcc#######################
featExtractCmd = "/home/objectFile/mfccNStatComp_39 "   + wavFile + " " +  SpkrId+  " " + otherFilePath+SpkrId+"_strt"+" "+otherFilePath+SpkrId+"_end"+" "+otherFilePath+SpkrId+"_vunv"+" "+otherFilePath+SpkrId+"_speech"+" "+otherFilePath+SpkrId+"_avg"+" "+wavFilePath+nName+" "+wavFilePath+fName+" "+  mfccFilePath
#print featExtractCmd
commands.getoutput(featExtractCmd)

###############retrieve phone no from text file##########
enrollList="/home/scripts/enrolledUserList.txt"
phoneNumber=commands.getoutput("cat "+enrollList+" |grep -w "+SpkrId+" |awk '{print $3}'")
#print phoneNumber


outname = filePath + ".mat"
cmd = "/home/objectFile/write_F_N    39   1024  " +  filePath +"_N" + " " +filePath +"_F" + " " + outname  
#print cmd
output = commands.getoutput(cmd)

	### extract i-vector
cmd = "export LD_LIBRARY_PATH=/opt/intel/composerxe-2011/lib/ia32:/opt/intel/mkl/lib/ia32"#for 32 bit
commands.getoutput(cmd)

os.environ["LD_LIBRARY_PATH"]="/opt/intel/composerxe-2011/lib/ia32:/opt/intel/mkl/lib/ia32"

cmd = "echo " + outname + " >/home/scripts/matFileList"
#print cmd
commands.getoutput(cmd)

cmd= "/home/objectFile/tmatTrain    /home/scripts/matFileList    /home/UBM/ubm_mean_sv      /home/UBM/ubm_variance_sv   400 0  /home/training_module/Mfcc/Text-independent/     /home/Tmatrix_LDA_WCCN/tmat_10.txt"
#print cmd
commands.getoutput(cmd)


################i-vector testing

trnIvectFile=trnMfccDir+phoneNumber+"/"+phoneNumber+"_TI.mx"

cmd="echo "+trnIvectFile +" >/home/scripts/cohortList"

commands.getoutput(cmd)

tstIvectFile=filePath+".mx"

cmd="/home/objectFile/ivect_testing "+tstIvectFile+" /home/scripts/cohortList  /home/scripts/score.txt  1 /home/Tmatrix_LDA_WCCN/LDA  /home/Tmatrix_LDA_WCCN/WCCN"
#print cmd
commands.getoutput(cmd)

cmd="cat /home/scripts/score.txt"
result=commands.getoutput(cmd)

if float(result)>=0.2:
	print "pass"
else:
	print "fail"

