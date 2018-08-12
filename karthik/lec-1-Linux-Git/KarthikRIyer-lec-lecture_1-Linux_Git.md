bandit0->
ls
cat readme
boJ9jbbUNNfktd7800psg0ltutMc3MY1


bandit1->
ls
cat ./-
CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9


bandit2->
ls
cat space\ in\ this\ filename
UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK


bandit3->
ls -a
cat .hidden
pIwrPrtPN36QITSp3EQaw936yaFoFgAB


bandit4->
cd inhere/
ls
cat ./-file07
koReBOKuIDDepwhWk7jZC0RTdopnAYKh


bandit5->
find -size 1033c
cat ./maybehere07/.file2
DXjZPULLxYr17uwoI01bNLQbtFemEgo7


bandit6->
cd ..
cd ..
find -size 33c
 cat ./var/lib/dpkg/info/bandit7.password
HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs


bandit7->
ls
grep millionth data.txt
cvX2JJa4CFALtqS87jk27qwqGhBM9plV


bandit8->
sort data.txt | uniq -u
UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR


bandit9->
strings data.txt	
truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk


bandit10->
base64 --decode data.txt
The password is IFukwKGsFW8MOq3IRFqrxE1hxTNEbUPR


bandit11->
cat data.txt | tr 'A-Za-z' 'N-ZA-Mn-za-m'
The password is 5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu


bandit12->
ls
data.txt
mkdir /tmp/kart
cp data.txt /tmp/kart
cd /tmp/kart
ls
data.txt
xxd -r data.txt > data.gz
ls
data.gz  data.txt
gzip -d data.gz
ls
data  data.txt
file data
data: bzip2 compressed data, block size = 900k
mv data data.bz2
ls
data.bz2  data.txt
bzip2 -d data.bz2
ls
data  data.txt
file data
data: gzip compressed data, was "data4.bin", last modified: Thu Dec 28 13:34:36 2017, max compression, from Unix
mv data data.gz
gzip -d data.gz
ls
data  data.txt
file data
data: POSIX tar archive (GNU)
tar -xf data
ls
data  data.txt  data5.bin
file data5.bin
data5.bin: POSIX tar archive (GNU)
tar -xf data5.bin
ls
data  data.txt  data5.bin  data6.bin
file data6.bin
data6.bin: bzip2 compressed data, block size = 900k
mv data6.bin data6.bz2
bzip2 -d data6.bz2 
ls
data  data.txt  data5.bin  data6
file data6
data6: POSIX tar archive (GNU)
tar -xf data6
ls
data  data.txt  data5.bin  data6  data8.bin
file data8.bin
data8.bin: gzip compressed data, was "data9.bin", last modified: Thu Dec 28 13:34:36 2017, max compression, from Unix
mv data8.bin data8.gz
gzip -d data8.gz
ls
data  data.txt  data5.bin  data6  data8
file data8
data8: ASCII text
cat data8
The password is 8ZjyCRiBWFYkneahHwxCv3wb2a1ORpYL


