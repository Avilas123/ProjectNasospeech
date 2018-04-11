#!/usr/bin/python
import os
import sys
import random
import commands


############check valid input###########
if (len(sys.argv)!=2):
	print " usage : script.py	SpkrID"
	exit()

SpkrID=sys.argv[1]


cmd="cat src/designtemplate/shellscripts/enrolledUserList.txt|grep -w "+SpkrID+" |awk '{print $NF}'"
no=commands.getoutput(cmd)

if (len(no)==4):
	print "found"
else:
	print "not found"
