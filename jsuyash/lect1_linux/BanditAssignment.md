ssh bandit0@bandit.labs.overthewire.org -p 2220
pw - bandit0

cat readme   #password for bandit1
exit

ssh bandit1@bandit.labs.overthewire.org -p 2220
pw - boJ9jbbUNNfktd78OOpsqOltutMc3MY1
cat ./-        #password for bandit2
exit

ssh bandit2@bandit.labs.overthewire.org -p 2220
pw -  CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9
cat spaces\ in\ this\ filename      #password for bandit3
exit

ssh bandit3@bandit.labs.overthewire.org -p 2220
pw - UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK
cd inhere
ls -a
cat .hidden      #password for bandit4
exit

ssh bandit4@bandit.labs.overthewire.org -p 2220
pw - pIwrPrtPN36QITSp3EQaw936yaFoFgAB
cd inhere
strings -- *      #password for bandit5
exit

ssh bandit5@bandit.labs.overthewire.org -p 2220
pw - koReBOKuIDDepwhWk7jZC0RTdopnAYKh
find . ! -executable -size 1033c
cat ./maybehere07/.file2     #password for bandit6
exit

ssh bandit6@bandit.labs.overthewire.org -p 2220
pw - DXjZPULLxYr17uwoI01bNLQbtFemEgo7
cd /
find . -user bandit7 -group bandit6 -size 33c      #only one file accessible
cat ./var/lib/dpkg/info/bandit7.password        #password for bandit7
exit

ssh bandit7@bandit.labs.overthewire.org -p 2220
pw - HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs
cat data.txt | grep millionth      #password for bandit8
exit

ssh bandit8@bandit.labs.overthewire.org -p 2220
pw - cvX2JJa4CFALtqS87jk27qwqGhBM9plV
sort > data.txt     #arranged the lines of data.txt in alphabetical order
                               # Identified the line occuring only once -- password for bandit9
exit

ssh bandit9@bandit.labs.overthewire.org -p 2220
pw - UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR
strings data.txt | grep =         #password for bandit10
exit

ssh bandit10@bandit.labs.overthewire.org -p 2220
pw - truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk
cat data.txt         #copied the base-64 encoded pw
echo <base-64 encoded pw> | base-64 --decode        #password for bandit11
exit

ssh bandit11@bandit.labs.overthewire.org -p 2220
pw - IFukwKGsFW8MOq3IRFqrxE1hxTNEbUPR
cat data.txt        #copied the whole rot-13 text
echo "<rot-13>" | tr 'A-Za-z' 'N-ZA-Mn-za-m'           #password for bandit12
exit

ssh bandit12@bandit.labs.overthewire.org -p 2220
pw - 5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu          #Entered bandit12
