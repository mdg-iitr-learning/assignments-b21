GETTING PW FOR GOING FROM

Level 0 to 1
how to -      in your terminal
					ssh -p 2220 bandit0@bandit.labs.overthewire.org 
					pwd -         bandit0
			  in server's terminal 
			  		cat readme	

Level 1 to 2
how to -      in your terminal
					ssh -p 2220 bandit1@bandit.labs.overthewire.org 
					pwd -         boJ9jbbUNNfktd78OOpsqOltutMc3MY1
			  in server's terminal 
			  		cat ./-
			  			
Level 2 to 3
how to -      in your terminal
					ssh -p 2220 bandit2@bandit.labs.overthewire.org 
					pwd -         CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9
			  in server's terminal 
			  		cat spaces\ \in\ \this\ \filename  

Level 3 to 4
how to -      in your terminal
					ssh -p 2220 bandit3@bandit.labs.overthewire.org 
					pwd -         UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK
			  in server's terminal 
					cd inhere
			  		ls -a
			  		cat ./hidden	  		

Level 4 to 5
how to -      in your terminal
					ssh -p 2220 bandit4@bandit.labs.overthewire.org 
					pwd -         pIwrPrtPN36QITSp3EQaw936yaFoFgAB
			  in server's terminal 
	  				cd inhere
			  		try for cat ./-file0i for all i
			  		cat ./-file07

Level 5 to 6
how to -      in your terminal
					ssh -p 2220 bandit5@bandit.labs.overthewire.org 
					pwd -         koReBOKuIDDepwhWk7jZC0RTdopnAYKh
			  in server's terminal 
			  		cd inhere
			  		find -size 1033c ! -executable
			  		cd maybehere07
			  		cat .file2			  		



Level 6 to 7
how to -      in your terminal
					ssh -p 2220 bandit6@bandit.labs.overthewire.org 
					pwd -         DXjZPULLxYr17uwoI01bNLQbtFemEgo7    
			   in server's terminal 
			  		find -user bandit7 -group bandit6 -size 33c
			  		cat ./var/lib/dpkg/info/bandit7.password

Level 7 to 8
how to -      in your terminal
					ssh -p 2220 bandit7@bandit.labs.overthewire.org 
					pwd -         HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs    
			  in server's terminal 
			  		grep millionth data.txt

Level 8 to 9
how to -      in your terminal
					ssh -p 2220 bandit8@bandit.labs.overthewire.org 
					pwd -         cvX2JJa4CFALtqS87jk27qwqGhBM9plV
			  in server's terminal 
			  		cat data.txt| tr '[:space:]' '[\n*]' | sort | uniq -c | sort -bnr
			  		the line which occurs ans is password to next levelcodekaust-lec-1-linuxgit

Level 9 to 10
how to -      in your terminal
					ssh -p 2220 bandit9@bandit.labs.overthewire.org 
					pwd -         UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR      
			  in server's terminal 
			  		strings data.txt
			  		the string just after ========== is password

Level 10 to 11
how to -      in your terminal
					ssh -p 2220 bandit10@bandit.labs.overthewire.org 
					pwd -         truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk
			  in server's terminal 
			  		cat data.txt
			  		base64 -d data.txt

Level 11 to 12
how to -      in your terminal
					ssh -p 2220 bandit11@bandit.labs.overthewire.org 
					pwd -         IFukwKGsFW8MOq3IRFqrxE1hxTNEbUPR
			  in server's terminal
			  		cat data.txt
			  		copy text and decode from rot13 decoder

Level 12 to 13
how to -      in your terminal
					ssh -p 2220 bandit12@bandit.labs.overthewire.org 
					pwd -         5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu
			  in server's terminal
			  		mkdir /tmp/anyrandomnamehahaha
			  		cp data.txt /tmp/anyrandomnamehahaha
			  		cd /tmp/anyrandomnamehahaha
			  		xxd -r data.txt > data
			  		file data
			  		mv data data.gz
			  		gzip -d data.gz
			  		file data 
			  		mv data data.bz2
			  		bzip2 -d data.bz2
			  		file data
			  		mv data data.gz
			  		gzip -d data.gz
			  		file data
			  		mv data data.tar
			  		tar -xvf data.tar
			  		file data5.bin
			  		mv data5.bin data5.tar
			  		tar -xvf data5.tar
			  		file data6.bin
			  		mv data6.bin data6.tar
			  		tar -xvf data6.tar
			  		file data8.bin
			  		mv data8.bin data8.gz
			  		gzip -d data8.gz
					file data8
					cat data8

Level 12 to 13
how to -      in your terminal
					ssh -p 2220 bandit13@bandit.labs.overthewire.org 
					pwd -         8ZjyCRiBWFYkneahHwxCv3wb2a1ORpYL
			  in server's terminal			  		




