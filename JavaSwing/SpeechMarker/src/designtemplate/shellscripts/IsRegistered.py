import os
import sys
import commands


############check valid input###########
if (len(sys.argv)!=2):
	print " usage : script.py	phoneNumber"
	exit()

phoneNumber=sys.argv[1]


cmd="cat /home/scripts/enrolledUserList.txt|grep "+phoneNumber+" |awk '{print $3}'"
no=commands.getoutput(cmd)

if (len(no)==10):
	print "found"
else:
	print "not found"
