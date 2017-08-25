close all;
clear all;
[y,Fs]=audioread('Tarun_s4.wav');
Fs=8000;
Frame_size=50 ;
Frame_shift=20;
l=length(y);
te=(1:Frame_shift:1-Frame_size)/Fs;
plot(y)
plot(te)