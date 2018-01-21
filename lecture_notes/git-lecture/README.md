# Git Lecture :green_book:

## What is VCS?
- Version Control System stores changes in file(s) over time so we can check out any version anytime.
- Tracks who modified what in the code. 
- Allows to revert back to a version.
- Each collaborator has the whole version history in his computer in `.git` folder.

## Git vs other VCSs
Most of the VCSs think of the information they store as a set of files and the changes made to each file over time (delta based version control).<br> 
 <img src = "images/deltas.png" width = "700" height="200" /><br> 
Git thinks of its data as a stream of snapshots. Each time we store data a snapshot is taken of all files at that time. For increasing efficiency, the files that aren't changed are referenced from previous commit. This makes common operations faster.<br>
<img src = "images/snapshots.png" width = "700" height="200" /><br>
 
## Install Git
### For Linux
```shell
$ add-apt-repository ppa:git-core/ppa
$ sudo apt update
$ sudo apt install git
```
### For Windows

## Initialise Git repo
```bash
$ cd ~/Desktop
$ mkdir git-tutorial
$ cd git-tutorial
$ git init
```

## `.git` folder
`.git` folder in the root directory of the project is where Git stores all the information. Deleting this folder will result in deletion of all versions and further Git commands will show the error -
```bash
$ fatal: Not a git repository (or any of the parent directories): .git
```

## Configure username and email
```bash
$ git config --global user.name 'RohanBh'
$ git config --global user.email 'brohan52@gmail.com’
```

## Install Atom or Sublime Text Editor
### Atom
```bash
$ sudo add-apt-repository ppa:webupd8team/atom
$ sudo apt update
$ sudo apt install atom
```
### Sublime
```bash
$ sudo add-apt-repository ppa:webupd8team/sublime-text-3
$ sudo apt-get update
$ sudo apt-get install sublime-text-installer
```
**Note -** Run Atom using the command `atom` and Sublime using the command `subl` from terminal.

## Use editor of your choice
```bash
$ git config --global core.editor "subl -n -w"
$ git config --global core.editor "atom --wait"
```
**OR**<br> 
```bash
$ export GIT_EDITOR=subl
```

<p>This will be valid only for current session of terminal.</p>


## Common commands
- **`git status`**
- **`git add`**

	- #### Usage
	```bash
	$ git add <file1> <file2> <file3>
	$ git add *.txt
	$ git add --all 
	$ git add .
	```
	- Untracked files
	- Tracked files
	<br>`git add` stages the changes made to files. Also git starts tracking the *untracked files* if they are added to staging area.
	- #### Three States of changes done
		- **Committed** means that the data is safely stored in your local database.
		- **Modified** means that you have changed the file but have not committed it to your database yet.
		- **Staged** means that you have marked a modified file in its current version to go into your next commit snapshot.
		
		#### The Staging Area
		<img src = "images/areas.png" width = "500" height="200" /><br>
		<br> Working Directory is also knows as Working Tree. Also, Staging Area is referred as Index. It acts as an interface between working tree and repository.
- **`git reset`**
	<br> It is used to undo add.

	- #### Usage
	```bash
	$ git reset <file1> <file2> <file3>
	$ git reset --hard commit-id
	$ git reset --soft commit-id
	```
- **`git commit`**
	<br>Commit is like a snapshot in time. We can return back to this commit anytime in future.

	- #### Usage
	```bash
	$ git commit -m "Commit message"
	```
	- Amend previous commit
	```bash
	$ git commit --amend -m "Change previous commit message"
	$ git commit --amend --no-edit
	```
	- Commit all tracked files 
	```bash
	$ git commit -a -m "Commit message"
	```
- **`git diff`**
	<br> `git diff` prints nothing for Untracked files.

	- #### Usage
	```bash
	$ git diff
	$ git diff --cached
	$ git diff <file1> <file2> <file3>
	```
- **`git log`**

	- #### Usage
	```bash
	$ git log
	$ git log -n 5
	$ git log --all
	$ git log --oneline --graph
	```
	Sample Output
	```bash
	commit 9c2f963d097df58040ee26673a15f50dc4c954dc
	Author: RohanBh <brohan52@gmail.com>
	Date:   Fri Jan 19 11:23:57 2018 +0530
    	Complete the more section
	commit 3b1c96765005b1806b31617987a9bfb374a943b7
	Author: RohanBh <brohan52@gmail.com>
	Date:   Thu Jan 18 19:45:19 2018 +0530
    	Add GridView adapter
	:
	```
	Git identifies commits by attaching a long hexadecimal number to every commit. These can be seen using `git log`. The ID corresponding to the commit <u>"Complete the more section"</u> is 9c2f963d097df58040ee26673a15f50dc4c954dc. Usually first 4-5 characters of this ID are enough to identify the commit.
- **`git checkout`**

	- #### Usage
	```bash
	$ git checkout commit-id
	$ git checkout branch-name
	$ git checkout -b branch-name
	$ git checkout -- <file1> <file2> <file3>
	```
	- #### HEAD
	HEAD is a reference to the last commit in the currently checked-out branch. Git automatically moves the HEAD pointer along when you create a new commit. You are automatically on the newest commit of the chosen branch.
	- #### Detached HEAD
	When we commit in a Detached HEAD state, the commits do not belong to any branch and are thus **lost**.
- **`git branch`**

	- #### Usage
	```bash
	$ git branch
	$ git branch new-branch
	$ git branch -d/-D old-branch
	```
- **`git stash`**
	
	- #### Usage
	```bash
	$ git stash
	$ git stash pop
	$ git stash apply
	$ git stash drop
	$ git stash abort
	```
	- `git stash pop` is `git stash apply && git stash drop`
- **`git remote`**
	
	- #### Usage
	```bash
	$ git remote add origin https://github.com/<username>/learning.git
	```
	**Note -** `.git` can be omitted.
- **`git push`**
	
	- #### Usage
	```bash
	$ git push origin branch-name
	$ git push -f origin branch-name
	```
- **`git pull`**

	- #### Usage
	```bash
	$ git pull origin branch-name
	```


## Useful Links
- [HEAD^ vs HEAD~](https://stackoverflow.com/questions/2221658/whats-the-difference-between-head-and-head-in-git)
- [Understanding git reset and checkout](https://git-scm.com/book/en/v2/Git-Tools-Reset-Demystified)
- [Git Rebasing](https://git-scm.com/book/en/v2/Git-Branching-Rebasing)
