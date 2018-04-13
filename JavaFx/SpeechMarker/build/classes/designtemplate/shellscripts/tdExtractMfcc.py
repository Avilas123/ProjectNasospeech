#!/usr/bin/python
import os
import sys
import random
import commands

############check valid input

if (len(sys.argv)!=3):
	print "usage: script.py   SpeakerID	   fileName"
	exit()

SpkrId=sys.argv[1]
filePath=sys.argv[2]

#cmd="mkdir -p /home/testing_module/Voice-password/"+SpkrId
#os.system(cmd)

wavFilePath="/home/testing_module/Text-dependent/"+SpkrId+"/"+filePath
########split the wav file and taken the first part
fileName=wavFilePath.split(".")[0]
promptNo=fileName.split("_")[-1]

#print fileName
###############mfcc path defined here##################
mfccFilePath=fileName+".mfcc"
otherFilePath="/home/training_module/Otherfiles/"
trnMfccDir="/home/training_module/Mfcc/Text-dependent/"

##################Extract mfcc#######################
featExtractCmd = "/home/objectFile/mfccNStatComp_39 "   + wavFilePath + " " +  SpkrId+  " " + otherFilePath+SpkrId+"_strt"+" "+otherFilePath+SpkrId+"_end"+" "+otherFilePath+SpkrId+"_vunv"+" "+otherFilePath+SpkrId+"_speech"+" "+otherFilePath+SpkrId+"_avg"+" "+otherFilePath+SpkrId+"_N"+" "+otherFilePath+SpkrId+"_F"+" "+  mfccFilePath
#print featExtractCmd
commands.getoutput(featExtractCmd)


##################no lines in test file##############
testLineNo=commands.getoutput("wc  -l  " + mfccFilePath +  "|awk '{print $1}'")
#print testLineNo



###############retrieve phone no from text file##########
enrollList="/home/scripts/enrolledUserList.txt"
phoneNumber=commands.getoutput("cat "+enrollList+" |grep -w "+SpkrId+" |awk '{print $3}'")
#print "cat "+enrollList+" |grep -w "+SpkrId+" |awk '{print $3}'"



#################declare list#############
outfile=['1','2','3']

###########initialize variable###############
dtwscore=0.0
allScores = []


############DTW##################
for itr in range(1,4):
	trnMfccPath=trnMfccDir+phoneNumber+"/"+phoneNumber+"_"+promptNo+"_"+outfile[itr-1]+".mfcc"
	trainLineNo=commands.getoutput("wc -l "+ trnMfccPath+" |awk '{print $1}'")
	cmd="/home/objectFile/dtw_39 "+mfccFilePath+" "+trnMfccPath+" "+"/home/scripts/outfile"+outfile[itr-1]+" "+testLineNo+" "+trainLineNo+" "+" 39 debug |tail -1|awk '{print $NF}'" 
	#print cmd
	score=commands.getoutput(cmd)
#	print score
	normalScore=float(score)/float(testLineNo)
#	print normalScore
	roundnormScores = round(normalScore)
	#dtwscore=dtwscore+float(normalScore)
	#print dtwscore
	allScores.append(roundnormScores)
#print allScores
#avgScore=dtwscore/3



cmd="cat /home/scripts/enrolledUserList.txt | grep -v "+SpkrId+" |awk '{print $3}'"
impPhoneNo=commands.getoutput(cmd)
impPhoneNo=impPhoneNo.split("\n")
#print impPhoneNo

for cohorts in xrange(0,3):
	phoneNumber=impPhoneNo[cohorts]

	for itr in xrange(1,4):
		
		trnMfccPath=trnMfccDir+phoneNumber+"/"+phoneNumber+"_"+promptNo+"_"+outfile[itr-1]+".mfcc"
		#print "ieterator value"+outfile[itr-1]
		trainLineNo=commands.getoutput("wc -l "+ trnMfccPath+" |awk '{print $1}'")
		cmd="/home/objectFile/dtw_39 "+mfccFilePath+" "+trnMfccPath+" "+"outfile"+outfile[itr-1]+" "+testLineNo+" "+trainLineNo+" "+" 39 debug |tail -1|awk '{print $NF}'" 
		score=commands.getoutput(cmd)
		#print cmd
		normalScore=float(score)/float(testLineNo)
		#print normalScore
		roundnormScores = round(normalScore)
	#dtwscore=dtwscore+float(normalScore)
	#print dtwscore
		allScores.append(roundnormScores)

#print allScores

tmpScores=list(allScores)
#print tmpScores
allScores.sort()
#print allScores

confidence=0

for index in range(0,3):
	if(tmpScores[index]==allScores[0] or tmpScores[index]==allScores[1] or tmpScores[index]==allScores[2]):
		confidence=confidence+1

print confidence

if(confidence>=1):
	print "pass"
else:
	print "fail"

