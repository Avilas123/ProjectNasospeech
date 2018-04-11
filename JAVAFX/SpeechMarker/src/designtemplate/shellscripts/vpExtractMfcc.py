#!usr/bin/python

import sys
import os
import commands

###### check for proper inputs
if(len(sys.argv) != 3):

      print "usage : script.py   speakerID   filePath"
      exit(); 

SpkrId=sys.argv[1]
filePath=sys.argv[2]

#cmd="mkdir -p /home/testing_module/Voice-password/"+SpkrId
#os.system(cmd)

wavFilePath="/home/testing_module/Voice-password/"+SpkrId+"/"+filePath
########split the wav file and taken the first part
fileName=wavFilePath.split(".")[0]

#print fileName
###############mfcc path defined here##################
mfccFilePath=fileName+".mfcc"
otherFilePath="/home/training_module/Otherfiles/"
trnMfccDir="/home/training_module/Mfcc/Voice-password/"
##################Extract mfcc#######################
featExtractCmd = "src/designtemplate/object_file/mfccNStatComp_39 "   + wavFilePath + " " +  SpkrId+  " " + otherFilePath+SpkrId+"_strt"+" "+otherFilePath+SpkrId+"_end"+" "+otherFilePath+SpkrId+"_vunv"+" "+otherFilePath+SpkrId+"_speech"+" "+otherFilePath+SpkrId+"_avg"+" "+otherFilePath+SpkrId+"_N"+" "+otherFilePath+SpkrId+"_F"+" "+  mfccFilePath
#print featExtractCmd
commands.getoutput(featExtractCmd)
##################no lines in test file##############
testLineNo=commands.getoutput("wc  -l  " + mfccFilePath +  "|awk '{print $1}'")

###############retrieve phone no from text file##########
enrollList="src/designtemplate/shellscripts/enrolledUserList.txt"
phoneNumber=commands.getoutput("cat "+enrollList+" |grep -w "+SpkrId+" |awk '{print $3}'")
#print phoneNumber
#################declare list#############
outfile=['1','2','3']

###########initialize variable###############
dtwscore=0.0
############DTW##################
for itr in range(1,4):
	trnMfccPath=trnMfccDir+phoneNumber+"/"+phoneNumber+"_VP_"+outfile[itr-1]+".mfcc"
	trainLineNo=commands.getoutput("wc -l "+ trnMfccPath+" |awk '{print $1}'")
	cmd="src/designtemplate/object_file/dtw_39 "+mfccFilePath+" "+trnMfccPath+" "+"src/designtemplate/shellscripts/outfile"+outfile[itr-1]+" "+testLineNo+" "+trainLineNo+" "+" 39 debug |tail -1|awk '{print $NF}'" 
	#print cmd
	score=commands.getoutput(cmd)
	#print score
	normalScore=float(score)/float(testLineNo)
	#print normalScore
	dtwscore=dtwscore+float(normalScore)
	#print dtwscore
avgScore=dtwscore/3
#print avgScore
############check whether claim speaker pass or fail##############

cmd="cat src/designtemplate/shellscripts/VP_speaker_specific_thresholdNov12|grep -w "+SpkrId+"|awk '{print $2}'"
threshold1=commands.getoutput(cmd)
#print threshold1
cmd="cat src/designtemplate/shellscripts/VP_speaker_specific_thresholdNov12|grep -w "+SpkrId+"|awk '{print $NF}'"
threshold2=commands.getoutput(cmd)
#print threshold2

if avgScore<float(threshold2):
	print "pass"
else:
	print "fail"








