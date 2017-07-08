#!/bin/bash
#---------------------------------------------------------
# TESTING
#---------------------------------------------------------

# Enter the HMM states 
exp_name=8_Keyword_Train;

# Feature extraction of testing files
#HCopy -T 1 -A -D -C ./analysis.conf -S ./ContConnect_test_feat.scp

# Decoding test data using viterbi decoder
HVite -T 1 -D -A -V -H ./ModelFiles-HCompv/macros -H ./ModelFiles-HCompv/hmmdefs -S ./ContConnect_test.scp -C ./analysis_train.conf -i ./recout_connected_continuous.mlf -w ./wdnet_cont.txt -p -10.0 -s 0 ./dict.txt ./hmmlist_KwrdGarb

# Eavaluate the test results 
#HResults -T 1 -I ./commodity_test.mlf ./hmmlist ./recout.mlf

