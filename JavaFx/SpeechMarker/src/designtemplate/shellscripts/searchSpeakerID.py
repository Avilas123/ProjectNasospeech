import os
import sys
import commands

#############################Get phone number as input parameter################

if (len(sys.argv)!=2):
	print "usage: script.py phoneNumber"
	exit()
phoneNumber=sys.argv[1]


##############search the phoneNumber the database################

cmd="cat /home/scripts/enrolledUserList.txt|grep "+phoneNumber+"|awk '{print $NF}'"
os.system(cmd)

