The Unix OS  
===========

Made up of three parts kernel, shell and programs.

The kernel  
--------
The kernel of UNIX is the hub of the operating system: it allocates
 time and memory to programs and handles the filestore and
 communications in response to system calls.

As an illustration of the way that the shell and the kernel work
together, suppose a user types `rm myfile` (which has the effect of
removing the file myfile). The shell searches the filestore for the
file containing the program `rm`, and then requests the kernel,
through system calls, to execute the program rm and passes the file
name as a parameter. When the process `rm myfile` has finished
running, the shell then returns the UNIX prompt % to the user,
indicating that it is waiting for further commands.

The shell  
--------
The shell acts as an interface between the user and the kernel. When a
user logs in, the login program checks the username and password, and
then starts another program called the shell. A shell is a command
line interpreter (CLI). It interprets the commands the user types in
and arranges for them to be carried out.  The commands are themselves
programs: when they terminate, the shell gives the user another prompt
(% on our systems).

Files and processes  
--------
Everything in UNIX is either a file or a process.

A process is an executing program identified by a unique PID (process
identifier).

A file is a collection of data. They are created by users using text
editors, running compilers etc.

Some common commands  
--------
* `ls` : list of files (can also be used as ls {dir_name})
  * `-a` : files that are normally hidden
* `mkdir` : To make a subdirectory called unixstuff in your current
 working directory type

* `cp path1 path2` : to copy file specified by path1 to the
  destination specified by path2
* `mv path1 path2` : to move/rename
* `clear` : clear screen
* `cat` : to display contents
* `less` : same as above but one page at a time
* `head` : display first 10 lines (can also be used as head -5
  filename)
* `tail` : display the last 10 lines
* `grep string filename` : It searches files for specified words or
  patterns. First clear the screen
  * The grep command is case sensitive; it distinguishes between
    Science and science.
  * To search for a phrase or pattern, you must enclose it in single
    quotes (the apostrophe symbol)
  * `-v` display those lines that do NOT match
  * `-n` precede each matching line with the line number
  * `-c` print only the total count of matched lines
  * `-i` To ignore upper/lower case distinctions
  * `eg` : grep -ivc science science.txt
* `wc` : word count
  * `-w` for words
  * `-l` for lines
* `\>` : Redirects the output (stdout). for eg : cat > eg
* `\>>` : append
* `|` : pipe
* sort / who / \*,? wildcard
* Online manuals :
  * man wc
  * whatis wc
  * apropos copy

The directory structure  
--------
| Path | Description |
|---------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| / | This is the root directory which should contain only the directories needed at the top level of the file structure |
| /bin | This is where the executable files are located. These files are available to all users |
| /dev | These are device drivers |
| /etc | Supervisor directory commands, configuration files, disk configuration files, valid user lists, groups, ethernet, hosts, where to send critical messages |
| /lib | Contains shared library files and sometimes other kernel-related files |
| /boot | Contains files for booting the system |
| /home | Contains the home directory for users and other accounts |
| /mnt | Used to mount other temporary file systems, such as cdrom and floppy for the CD-ROM drive and floppy diskette drive, respectively |
| /proc | Contains all processes marked as a file by process number or other information that is dynamic to the system |
| /tmp | Holds temporary files used between system boots |
| /usr | Used for miscellaneous purposes, and can be used by many users. Includes administrative commands, shared files, library files, and others |
| /var | Typically contains variable-length files such as log and print files and any other type of file that may contain a variable amount of data |
| /sbin | Contains binary (executable) files, usually for system administration. For example, fdisk and ifconfig utlities |
| /kernel | Contains kernel files |

File system security  
--------
Each file (and directory) has associated
access rights, which may be found by typing ls -l. Also, ls -lg gives
additional information as to which group owns the file (beng95 in the
following example):

```shell
~$ ls -lg ee51ab
-rwxrw-r-- 1 ee51ab beng95 2450 Sept29 11:52 file1
```

In the left-hand column is a 10 symbol string consisting of the
symbols d, r, w, x, -, and, occasionally, s or S. If d is present, it
will be at the left hand end of the string, and indicates a directory:
otherwise - will be the starting symbol of the string.

The 9 remaining symbols indicate the permissions, or access rights,
and are taken as three groups of 3.

* The left group of 3 gives the file permissions for the user that
  owns the file (or directory) (ee51ab in the above example);
* the middle group gives the permissions for the group of people to
  whom the file (or directory) belongs (eebeng95 in the above
  example);
* the rightmost group gives the permissions for all others.

The symbols r, w, etc., have slightly different meanings depending on
whether they refer to a simple file or to a directory.

### Access rights on files  
* r (or -), indicates read permission (or otherwise), that is, the
  presence or absence of permission to read and copy the file
* w (or -), indicates write permission (or otherwise), that is, the
  permission (or otherwise) to change a file
* x (or -), indicates execution permission (or otherwise), that is,
  the permission to execute a file, where appropriate

### Access rights on directories  
* r allows users to list files in the directory;
* w means that users may delete files from the directory or move files
  into it;
* x means the right to access files in the directory. This implies
  that you may read files in the directory provided you have read
  permission on the individual files.

So, in order to read a file, you must have execute permission on the
directory containing that file, and hence on any directory containing
that directory as a subdirectory, and so on, up the tree.

### Changing permissions  
For example, to remove read write and execute permissions on the file
biglist for the group and others, type

|    Symbol   |         Description			   |
|-------------|--------------------------------|
| u           | user                      	   |
| g           | group                          |
| o           | other  						   |
| a           | all  						   |
| r           | read  						   |
| w           | write (and delete) 			   |
| x           | execute (and access directory) |
| +           | add permission  			   |
| -           | take away permission  		   |

```shell
~$ chmod go-rwx biglist
```
This will leave the other permissions unaffected.

To give read and write permissions on the file biglist to all,

```shell
~$ chmod a+rw biglist
```

### Octal permissions
r = 4 | w = 2 | x = 1

```shell
~$ chmod 755 MyDir
```

The root user
--------
Both `su` and `sudo` are used to run commands with root
permissions. The root user is basically equivalent to the
administrator user on Windows – the root user has maximum permissions
and can do anything to the system. Normal users on Linux run with
reduced permissions – for example, they can’t install software or
write to system directories.

To do something that requires these permissions, you’ll have to
acquire them with su or sudo.

`su` vs `sudo`
--------
The `su` command switches to the super user – or root user – when you
execute it with no additional options. You’ll have to enter the root
account’s password. This isn’t all the su command does, though – you
can use it to switch to any user account. If you execute the su bob
command, you’ll be prompted to enter Bob’s password and the shell will
switch to Bob’s user account.

Once you’re done running commands in the root shell, you should type
exit to leave the root shell and go back to limited-privileges mode.

Sudo runs a single command with root privileges. When you execute sudo
command, the system prompts you for your current user account’s
password before running command as the root user. By default, Ubuntu
remembers the password for fifteen minutes and won’t ask for a
password again until the fifteen minutes are up.

Processes and Jobs
--------
A process is an executing program identified by a unique PID (process
identifier). To see information about your processes, with their
associated PID and status, type

```shell
~$ ps
```

A process may be in the foreground, in the background, or be
suspended. In general the shell does not return the UNIX prompt until
the current process has finished executing.

Some processes take a long time to run and hold up the
terminal. Backgrounding a long process has the effect that the UNIX
prompt is returned immediately, and other tasks can be carried out
while the original process continues executing.

When a process is running, backgrounded or suspended, it will be
entered onto a list along with a job number. To examine this list,
type

```shell
~$ jobs
```

An example of a job list could be

* [1] Suspended sleep 1000
* [2] Running netscape
* [3] Running matlab

UNIX variables
--------
Variables are a way of passing information from the shell to programs
when you run them. Programs look "in the environment" for particular
variables and if they are found will use the values stored. Some are
set by the system, others by you, yet others by the shell, or any
program that loads another program.

Standard UNIX variables are split into two categories, environment
variables and shell variables. In broad terms, shell variables apply
only to the current instance of the shell and are used to set
short-term working conditions; environment variables have a farther
reaching significance, and those set at login are valid for the
duration of the session. By convention, environment variables have
UPPER CASE and shell variables have lower case names.

### Env variables

```shell
~$ echo $OSTYPE
```
ENVIRONMENT variables are set using the setenv command, displayed using the printenv or env commands, and unset using the unsetenv command.

To show all values of these variables, type
```shell
~$ printenv | less
```

Changing visibility of a process
--------
To send a process to background add an ampersand `&` at the end of the command, for example:
```shell
~$ emacs & # This will start emacs in background, NOTE: This does not redirect the output
```

To keep a process running when even when the terminal is closed use `& disown` at the end of a command:
```shell
~$ emacs & disown # This will start emacs in the bg and keep it runnning even when the terminal is closed
```

To read more about `&`, `disown` and `nohup`, you can
head
[here](https://unix.stackexchange.com/questions/3886/difference-between-nohup-disown-and#148698).

Further exploration
-------
kill, sleep, file, diff, find, history, setting up env variables etc.

## Random stuff

``` shell
nc localhost 1123     # to connect to port 1123
nc -l 1234            # to listen to port 1234
chmod                 # change file permissions
chown                 # change file owner
chgrp                 # change file group
ping -c4 8.8.8.8      # to send 4 packets to google's server at 8.8.8.8
sudo lsof -i          # to list network sockets
```
DNS == Domain Name System
            DNS contains records , few of which map domain names to their IP addresses.

```shell
host google.com           # This command can be used to look the DNS records
dig google.com            # This command is same as host but host provides data in human
                          # readable form whereas dig provides data in script readable form
ip addr show              # to bring out the interfaces on your computer
ifconfig | less           # does the same as above
ip route show default     # shows the address of the default gateways
                          # default gateway is the router through which your machine
                          # is connected to the rest of the internet
netstat -nr               # slightly similar to above
tcpdump -n port portName  # brings out details about the connections
uname -r                  #  to check for kernel version
```
