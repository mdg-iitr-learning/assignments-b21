1. ssh bandit0@bandit.labs.overthewire.org -p 2220
****bandit0
cat readme ====> password

2. ssh bandit1@bandit.labs.overthewire.org -p 2220
****boJ9jbbUNNfktd78OOpsqOltutMc3MY1
cat ./-  ====>password

3. ssh bandit2@bandit.labs.overthewire.org -p 2220
****CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9
cat spaces\ in\ this\ filename ====> password

4. ssh bandit3@bandit.labs.overthewire.org -p 2220
****UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK
ls -a;
cat .hidden ====>password

5.ssh bandit4@bandit.labs.overthewire.org -p 2220
****pIwrPrtPN36QITSp3EQaw936yaFoFgAB
cd inhere;
cat ./-file07 ====>password

6. ssh bandit5@bandit.labs.overthewire.org -p 2220
****koReBOKuIDDepwhWk7jZC0RTdopnAYKh
find -size 1033c ! -executable====>password

7. ssh bandit6@bandit.labs.overthewire.org -p 2220
****DxjZPULLxYr17uwoI01bNLQbtFemEgo7
cd .. ;
cd .. ;
find -size 33c -user bandit7 -group bandit6;
cat bandit7.password====> password

8. ssh bandit7@bandit.labs.overthewire.org -p 2220
****HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs
grep millionth data.txt ====> password
 
9. ssh bandit8@bandit.labs.overthewire.org -p 2220
cvX2JJa4CFALtqS87jk27qwqGhBM9plV
sort data.txt | uniq -u ====> password

10. ssh bandit9@bandit.labs.overthewire.org -p 2220
****UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR
strings data.txt | grep = | cat ====> password

11. ssh bandit10@bandit.labs.overthewire.org -p 2220
****truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk
cat data.txt | base64 –decode ====> password

12.ssh bandit11@bandit.labs.overthewire.org -p 2220
****IfukwKGsFW8MOq3IRFqrxE1hxTNEbUPR
cat data.txt | tr '[A-Za-z]' '[N-ZA-Mn-za-m]' ====> password 

13. ssh bandit12@bandit.labs.overthewire.org -p 2220
****5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu
mkdir /tmp/dewat1/;
cp data.txt /tmp/dewat1/;
cd /tmp/dewat1/;
xxd -r data.txt abc.txt;
file abc.txt;
mv abc.txt abc.gz;
file abc.gz;
gunzip -k abc.gz;
file abc;
mv abc abc.bz2;
bzip2 -d abc.bz2;
file abc;
mv abc abc.gz;
gunzip -k abc.gz;
file abc;
tar -xvf abc;
file data5.bin ;
tar -xvf data5.bin;
file data6.bin ;
mv data6.bin data6.bz2 ;
bzip2 -d data6.bz2 ;
file data6 ;
tar -xvf data6 ;
file data8.bin ;
mv data8.bin data8.gz ;
gunzip -k data8.gz ;
file data8;
cat data8 ====>password ;

14.ssh bandit13@bandit.labs.overthewire.org -p 2220
****8ZjyCRiBWFYkneahHwxCv3wb2a1ORpYL