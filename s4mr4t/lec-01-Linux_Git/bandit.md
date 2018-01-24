---
##### bandit00 -> bandit0<br/>
#####  login using `ssh`:<br/>
#####  ssh bandit0@bandit.labs.overthewire.org -p 2220<br/>
#####  `cat readme` reveals the password to bandit01
---
#####  bandit01 -> boJ9jbbUNNfktd78OOpsqOltutMc3MY1<br/>
#####   `cat ./-` reveals the password to bandit02
---
#####  bandit02 -> CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9<br/>
#####  `cat spaces\ in\ this\ filename`
---
##### bandit03 -> UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK<br/>
``` 
cd inhere
ls -a
cat .hidden
```
---
##### bandit04 -> pIwrPrtPN36QITSp3EQaw936yaFoFgAB
```
# cd inhere
# strings -- *
```
---
##### bandit05 -> koReBOKuIDDepwhWk7jZC0RTdopnAYKh
```
 cd inhere
 find . -size 1033c
 cat ./maybehere07/.file2
```
---
#####  bandit06 -> DXjZPULLxYr17uwoI01bNLQbtFemEgo7
```
 cd /
 find . -size 33c -group bandit6
 cat ./var/lib/dpkg/info/bandit7.password
```
---
##### bandit07 -> HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs
##### `grep -in millionth data.txt`
---
##### bandit08 -> cvX2JJa4CFALtqS87jk27qwqGhBM9plV
##### `sort data.txt | uniq -u`
---
##### bandit09 -> UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR
##### `strings data.txt`
---
##### bandit10 -> truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk
##### `base64 -d data.txt`
---
##### bandit11 -> IFukwKGsFW8MOq3IRFqrxE1hxTNEbUPR
##### `cat data.txt | tr '[A-Za-z]' '[N-ZA-Mn-za-m]'`
---
##### bandit12 -> 5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu
```
mkdir /tmp/s4mr4/
cp data.txt /tmp/s4mr4t/ && cd /tmp/s4mr4t/
xxd -r data.txt > c.gz
file c.gz
gzip -d c.gz
file c
mv c c.bz2
bzip2 -d c.bz2
file c
mv c c.gz
gzip -d c.gz
file c
tar -xf c
file data5.bin
tar -xf data5.bin
file data6.bin
mv data6.bin c.bz2
bzip2 -df c.bz2
file c
tar -xf c
file data8.bin
mv data8.bin c.gz
gzip -df c.gz
file c
cat c
```
---
##### bandit13 -> 8ZjyCRiBWFYkneahHwxCv3wb2a1ORpYL
---