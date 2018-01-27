0->1
bandit0: cat readme
password: boJ9jbbUNNfktd78OOpsqOltutMc3MY1

1->2
bandit1: cat ./-
password: CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9

2->3
bandit2: cat spaces\ in\ this\ filename
password: UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK

3->4
bandit3: cd inhere/
bandit3: cat .hidden
password: pIwrPrtPN36QITSp3EQaw936yaFoFgAB

4->5
bandit4: cd inhere/
bandit4: cat ./-file07
password: koReBOKuIDDepwhWk7jZC0RTdopnAYKh

5->6
bandit5: cd inhere/
bandit5: find .* -size 1033c ! -executable
bandit5: cat maybehere07/.file2
password: DXjZPULLxYr17uwoI01bNLQbtFemEgo7

6->7 
bandit6: cd /
bandit6: find 2>&1 -user bandit7 -group bandit6 -size 33c | grep -v 'Permission denied' >&2
bandit6: cat ./var/lib/dpkg/info/bandit7.password
password: HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs

7->8
bandit7: grep millionth data.txt 
password: cvX2JJa4CFALtqS87jk27qwqGhBM9plV

8->9
bandit8: cat data.txt |sort| uniq -c | sort |head -1
password: UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR

9->10
bandit9: strings data.txt | grep '^\='
password: truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk

10->11
bandit10: base64 --decode data.txt
password: IFukwKGsFW8MOq3IRFqrxE1hxTNEbUPR

11->12
bandit11: cat data.txt | tr 'A-Za-z' 'N-ZA-Mn-za-m'
password: 5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu







 


