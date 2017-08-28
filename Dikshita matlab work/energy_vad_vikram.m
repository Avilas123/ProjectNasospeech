close all
clear all
[s,fs]=audioread('Tarun_s4.wav');
s=resample(s,8000,fs);
fs=8000;
framesize=20*fs/1000;
frameshift=10*fs/1000; 
E=zeros(1,length(1:frameshift:length(s)-framesize));
arr=1:frameshift:length(s)-framesize;
for i=1:length(arr);
    s1=s(arr(i):(arr(i))+framesize-1);
    s1=s1.*hanning(length(s1));
    E(i)=sum(s1.^2);
end
time=[0:length(s)-1]/fs;
tf=[1:frameshift:length(s)-framesize]/fs;
 
VAD=zeros(1,length(E));
VAD(E>0.66*mean(E))=1;
figure(1);
ax(1)=subplot(2,1,1);plot(time,s);hold on;plot(tf,VAD,'r');
ax(2)=subplot(2,1,2);stem(tf,E);
linkaxes(ax,'x');

%%%%%%% %%%%%%%%%%%%%%%%%%

dlmwrite('energy_matlab.txt',E');
E1=dlmread('energy_matlab.txt');%%%%% Put the path of c output
%%%
%% figure(2);
%plot(matlab);hold on;plot(C,'r');