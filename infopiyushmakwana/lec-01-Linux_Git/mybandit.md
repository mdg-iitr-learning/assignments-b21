
### Piyush Makwana
# Bandit-Writeup

### 27th January 2018

### LEVEL-0
Log into server using -
ssh bandit0@bandit.labs.overthewire.org -p 2220
Password:bandit0


### LEVEL-0 > 1
Ls
Cat readme
(Password found) boJ9jbbUNNfktd78OOpsqOltutMc3MY1


### LEVEL-1 > 2
Login using - ssh bandit1@bandit.labs.overthewire.org -p 2220
Password:boJ9jbbUNNfktd78OOpsqOltutMc3MY1
Ls
Cat ./-
(Password found)CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9


### LEVEL-2 > 3
Login using - ssh bandit2@bandit.labs.overthewire.org -p 2220
Password:CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9
Ls
 cat "spaces in this filename" or spaces\ in\ this\ filename
{Password found) UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK


### LEVEL-3 > 4
Login using - ssh bandit3@bandit.labs.overthewire.org -p 2220
Password:UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK
Ls
Cd inhere
Ls
Ls -a
Cat .hidden
{Password found) pIwrPrtPN36QITSp3EQaw936yaFoFgAB


### LEVEL-4 > 5
Login using - ssh bandit4@bandit.labs.overthewire.org -p 2220
Password:pIwrPrtPN36QITSp3EQaw936yaFoFgAB
Ls
Cd inhere
Ls
File ./*
Output -
./-file00: data
./-file01: data
./-file02: data
./-file03: data
./-file04: data
./-file05: data
./-file06: data
./-file07: ASCII text
./-file08: data
./-file09: data
Cat ./-file07
{Password found) koReBOKuIDDepwhWk7jZC0RTdopnAYKh








### LEVEL-5 > 6
Login using - ssh bandit5@bandit.labs.overthewire.org -p 2220
Password:koReBOKuIDDepwhWk7jZC0RTdopnAYKh
Cd inhere
Ls
Find . -size 1033c
Output : ./maybehere07/.file2
Cd maybehere07
Cat .file2
(Password found)DXjZPULLxYr17uwoI01bNLQbtFemEgo7


### LEVEL-6 > 7
Login using - ssh bandit6@bandit.labs.overthewire.org -p 2220
Password:DXjZPULLxYr17uwoI01bNLQbtFemEgo7
Cd ..
Cd ..
Find , -size 33c -a -user bandit7 -a -group bandit6
Only one result which dose’nt shows Permission denied or No such file ……
Cd /var/lib/dpkg/info
Cat bandit7.password
(Password found)HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs



### LEVEL-7 > 8
Login using - ssh bandit7@bandit.labs.overthewire.org -p 2220
Password:HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs
Ls
Grep ‘millionth’ data.txt
(Password found)cvX2JJa4CFALtqS87jk27qwqGhBM9plV
LEVEL-8 > 9
Login using - ssh bandit8@bandit.labs.overthewire.org -p 2220
Password:cvX2JJa4CFALtqS87jk27qwqGhBM9plV
Sort data.txt | uniq -u      or       sort data.txt | uniq -c (and see which line has count = 1)
(Password found)UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR


### LEVEL-9 > 10
Login using - ssh bandit9@bandit.labs.overthewire.org -p 2220
Password:UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR
Strings data.txt
(Password found)truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk


### LEVEL-10 > 11
Login using - ssh bandit10@bandit.labs.overthewire.org -p 2220
Password:truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk
Cat data.txt | base64 --decode
(Password found)IFukwKGsFW8MOq3IRFqrxE1hxTNEbUPR


### LEVEL-11 > 12
Login using - ssh bandit11@bandit.labs.overthewire.org -p 2220
Password:IFukwKGsFW8MOq3IRFqrxE1hxTNEbUPR
Cat data.txt |  tr '[A-Za-z]' '[N-ZA-Mn-za-m]'    or  download a rot13 decoder (tr - translate command)
(Password found)5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu



### LEVEL-12 > 13
Login using - ssh bandit12@bandit.labs.overthewire.org -p 2220
Password:5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu

```
bandit12@bandit:~$ ls
data.txt
bandit12@bandit:~$ mkdir /tmp/Piyush999
mkdir: cannot create directory '/tmp/Piyush999': File exists
bandit12@bandit:~$ mkdir /tmp/piyush
bandit12@bandit:~$ cp data.txt /tmp/piysuh/data1.txt
cp: cannot create regular file '/tmp/piysuh/data1.txt': No such file or directory
bandit12@bandit:~$ cd ..
bandit12@bandit:/home$ cd ..
bandit12@bandit:/$ cd temp
-bash: cd: temp: No such file or directory
bandit12@bandit:/$ cd tmp
bandit12@bandit:/tmp$ cd piyush
bandit12@bandit:/tmp/piyush$ touch data1.txt
bandit12@bandit:/tmp/piyush$ cp /home/bandit12/data.txt data1.txt
bandit12@bandit:/tmp/piyush$ cat data1.txt
00000000: 1f8b 0808 ecf2 445a 0203 6461 7461 322e  ......DZ..data2.
00000010: 6269 6e00 0149 02b6 fd42 5a68 3931 4159  bin..I...BZh91AY
00000020: 2653 5930 3e1b 4000 0014 ffff dde3 2b6d  &SY0>.@.......+m
00000030: afff dd1e dfd7 ffbf bdfb 3f67 bfff ffff  ..........?g....
00000040: bde5 bfff aff7 bfdb e5ff ffef b001 39b0  ..............9.
00000050: 480d 3400 0068 0068 1a00 0000 01a3 4000  H.4..h.h......@.
00000060: 0001 a643 4d34 0000 d00d 0698 800d 1934  ...CM4.........4
00000070: d0c4 d034 1a36 a343 646a 1c9a 3206 9a00  ...4.6.Cdj..2...
00000080: 3406 8000 068d 064f 51a3 4000 000f 5000  4......OQ.@...P.
00000090: 6868 0034 d308 0da4 6990 1a03 4000 6869  hh.4....i...@.hi
000000a0: a0d0 00d3 2341 94d0 0006 8006 8034 1a34  ....#A.......4.4
000000b0: 00d0 d000 0310 d068 3400 001e 900d 1a19  .......h4.......
000000c0: 0062 68d3 4680 640f 48d0 d320 0068 621a  .bh.F.d.H.. .hb.
000000d0: 0543 0116 180c 6232 a7d7 82c8 7bd4 2374  .C....b2....{.#t
000000e0: 1de5 e375 b7b9 0b78 2d37 bd61 5cdf 40da  ...u...x-7.a\.@.
000000f0: b8e5 3258 213d e4bb ecb2 8d51 84f9 3bd0  ..2X!=.....Q..;.
00000100: b1c9 ef2a bcff 45cc 1f1c 0028 1cfe 8784  ...*..E....(....
00000110: 78a9 7611 0a81 c4d5 cb26 4b80 7888 c9bc  x.v......&K.x...
00000120: 2b3e a351 59ae c1fd 36c8 286e d6c3 bb2b  +>.QY...6.(n...+
00000130: b280 d19b 70b3 190a 0204 4603 9f79 e2b8  ....p.....F..y..
00000140: cf1b 8330 fcad 3780 86c2 5c3d 5bc9 4631  ...0..7...\=[.F1
00000150: 3718 5e2e a88c 34e6 8461 35ad c14f 6fd4  7.^...4..a5..Oo.
00000160: 31dd a5cc 5223 545e e01d ff23 cde3 22cc  1...R#T^...#..".
00000170: 22fa a62b e27a dfa5 d4f0 c326 28ef a4b3  "..+.z.....&(...
00000180: adc5 149c 1c27 dbc4 97b9 6342 487e bfe3  .....'....cBH~..
00000190: 02ee d63e 3379 8ebc d559 c670 7987 da1d  ...>3y...Y.py...
000001a0: 4c4b 5ec4 9965 075b 9d0b 08ee df17 d07c  LK^..e.[.......|
000001b0: ea9a 5fbf 43e7 d405 5239 1437 0c8a 34cd  .._.C...R9.7..4.
000001c0: be6f a949 b061 68e8 6ba5 c9ba 4112 0819  .o.I.ah.k...A...
000001d0: 7cb9 a3c8 bff1 0895 1819 8f80 407e dc32  |...........@~.2
000001e0: 9269 ca68 3f58 bb30 cd9b fcd6 0006 1224  .i.h?X.0.......$
000001f0: 177b fe66 c676 01f0 a5bc 9131 6746 cc85  .{.f.v.....1gF..
00000200: 1a39 e46f 6b9a 7bd4 694b e999 c300 b57e  .9.ok.{.iK.....~
00000210: 9b0a 1229 fac1 cc0c 24fb a905 a06a b8cf  ...)....$....j..
00000220: cb56 2a73 6016 6950 8208 5785 af54 0d42  .V*s`.iP..W..T.B
00000230: 754e 5a48 8835 2b47 aa9b c45e 4ca8 a7a0  uNZH.5+G...^L...
00000240: 61dd e070 7717 9346 5f14 d808 8263 7746  a..pw..F_....cwF
00000250: 5100 3af8 fa20 ff8b b922 9c28 4818 1f0d  Q.:.. ...".(H...
00000260: a000 e793 1e61 4902 0000                 .....aI...
bandit12@bandit:/tmp/piyush$ file data1.txt
data1.txt: ASCII text
bandit12@bandit:/tmp/piyush$ xxd -r  data1.txt  > fileone
bandit12@bandit:/tmp/piyush$ file fileone
fileone: gzip compressed data, was "data2.bin", last modified: Thu Dec 28 13:34:36 2017, max compression, from Unix
bandit12@bandit:/tmp/piyush$ zcat fileone
bh�F�dH�� hbC44���h4��g�����������������9�H
                   b2�ׂ�{�#t��u��
                                 x-7�a\�@ڸ�2X!=��첍Q��;б��*��E�(���x�v
����&K�x�ɼ+>�QY���6�(n�û+��ћp�
F�y����0��7���\=[�F17^.��4��a5��Oo�1ݥ�R#T^��#��"�"��+�zߥ���&(盧���'�ė�cBH~����>3y���Y�py��LK^ęe[�
                                                                                                          ���|��_�C��R97
                                                                                                                          �4;o�I�ah�k�ɺA|��ȿ���@~�2�i�h?X�0͛��${�f�v𥼑1gF̅9�ok�{�iK����~�
)���
�bandit12@bandit:/tmp/piyush$ FQ:�� ���"�(H
bandit12@bandit:/tmp/piyush$ ls
data1.txt  fileone
bandit12@bandit:/tmp/piyush$ zcat fileone > filetwo
bandit12@bandit:/tmp/piyush$ file filetwo
filetwo: bzip2 compressed data, block size = 900k
bandit12@bandit:/tmp/piyush$ bzip2 -dk filetow
bzip2: Can't open input file filetow: No such file or directory.
bandit12@bandit:/tmp/piyush$ ls
data1.txt  fileone  filetwo
bandit12@bandit:/tmp/piyush$ bzip2 -dk filetwo
bzip2: Can't guess original name for filetwo -- using filetwo.out
bandit12@bandit:/tmp/piyush$ ls
data1.txt  fileone  filetwo  filetwo.out
bandit12@bandit:/tmp/piyush$ file filetwo.out
filetwo.out: gzip compressed data, was "data4.bin", last modified: Thu Dec 28 13:34:36 2017, max compression, from Unix
bandit12@bandit:/tmp/piyush$ zcat filetwo.out > filethree
bandit12@bandit:/tmp/piyush$ ls
data1.txt  fileone  filethree  filetwo  filetwo.out
bandit12@bandit:/tmp/piyush$ file filethree
filethree: POSIX tar archive (GNU)
bandit12@bandit:/tmp/piyush$ tar -xvf filethree
data5.bin
bandit12@bandit:/tmp/piyush$ ls
data1.txt  data5.bin  fileone  filethree  filetwo  filetwo.out
bandit12@bandit:/tmp/piyush$ file data5.bin
data5.bin: POSIX tar archive (GNU)
bandit12@bandit:/tmp/piyush$ tar -xvf data5.bin
data6.bin
bandit12@bandit:/tmp/piyush$ file data6.bin
data6.bin: bzip2 compressed data, block size = 900k
bandit12@bandit:/tmp/piyush$ bzip2 -dk data6.bin
bzip2: Can't guess original name for data6.bin -- using data6.bin.out
bandit12@bandit:/tmp/piyush$ ls
data1.txt  data5.bin  data6.bin  data6.bin.out  fileone  filethree  filetwo  filetwo.out
bandit12@bandit:/tmp/piyush$ file data6.bin.out
data6.bin.out: POSIX tar archive (GNU)
bandit12@bandit:/tmp/piyush$ tar -xvf data6.bin.out
data8.bin
bandit12@bandit:/tmp/piyush$ file data8.bin
data8.bin: gzip compressed data, was "data9.bin", last modified: Thu Dec 28 13:34:36 2017, max compression, from Unix
bandit12@bandit:/tmp/piyush$ ls
data1.txt  data5.bin  data6.bin  data6.bin.out  data8.bin  fileone  filethree  filetwo  filetwo.out
bandit12@bandit:/tmp/piyush$ zcat data8.bin > filefour
bandit12@bandit:/tmp/piyush$ file filefour
filefour: ASCII text
bandit12@bandit:/tmp/piyush$ cat filefour
The password is 8ZjyCRiBWFYkneahHwxCv3wb2a1ORpYL
bandit12@bandit:/tmp/piyush$

```

(Password found)8ZjyCRiBWFYkneahHwxCv3wb2a1ORpYL
