#!/usr/bin/python

import os
import sys
import commands
###### check for proper inputs
if(len(sys.argv) != 2):

      print "usage : script.py   fileName"
      exit(); 

fileName=sys.argv[1]

#retrieve speaker ID
speakerID=fileName.split("_")[0]
#print speakerID
#check module from file
currentFileName=fileName.split(".")[0]
#print currentFileName
moduleName=currentFileName.split("_")[-1]
#print moduleName

otherFilePath="/home/training_module/Otherfiles/"


if (moduleName=="VP"):
	wavFilePath="/home/testing_module/Voice-password/"+speakerID+"/"+fileName
elif (moduleName=="TD1"or moduleName=="TD2" or moduleName=="TD3"):
	wavFilePath="/home/testing_module/Text-dependent/"+speakerID+"/"+fileName
else:
	wavFilePath="/home/testing_module/Text-independent/"+speakerID+"/"+fileName


#check number of frames

cmd="/home/objectFile/check_speech_frames "+ wavFilePath + " "+ otherFilePath+speakerID+"_strt"+" "+otherFilePath+speakerID+"_end"+" "+otherFilePath+speakerID+"_vunv"+" "+otherFilePath+speakerID+"_speech"+" "+otherFilePath+speakerID+"_avg"
#print cmd
no_of_frames=commands.getoutput(cmd)

print no_of_frames
if (moduleName=="VP"):
	if (int(no_of_frames)>230):
		print "pass"
	else:
		print "fail"
elif (moduleName=="TD1"or moduleName=="TD2" or moduleName=="TD3"):
	if (int(no_of_frames)>120):
		print "pass"
	else:
		print "fail"
	
else:
	if (int(no_of_frames)>350):
		print "pass"
	else:
		print "fail"
