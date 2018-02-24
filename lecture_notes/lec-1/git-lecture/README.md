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


## Use Vim Editor
- press i to start typing
- press esc to stop typing
- Type :wq (w-write q-quit)
- then press enter to exit


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
		**Note -**<br> Working Directory is also knows as **Working Tree**. Also, the Staging Area is referred to as **Index**. It acts as an interface between working tree and repository.
		- Any time you see your project, its state can be understood as 
			> Project = ".git Repository" + "changes in Index" + "changes in Working directory"
		- Although commands like `git diff` shows us these changes, remember that these "changes" are actually "whole files" as mentioned in [this section](#git-vs-other-vcss) 
		- Changes are "staged", if they are in the "Index" and unstaged if they are in the "Working directory".

- **`git reset`**
	<br> It is used to undo add.

	- #### Usage
	```bash
	$ git reset <file1> <file2> <file3> # Take the files out of the staging area
	$ git reset --hard <commit-id>  
	$ git reset --soft <commit-id>
	$ git reset --soft/hard HEAD~n # Use this to move the branch back by n commits
	```
	**Note -** `git reset` by default runs with the flag `--mixed`. To get an in depth knowledge of `git reset`, read [this SO post](https://stackoverflow.com/a/3528483/7263373) or [this official post](https://git-scm.com/book/en/v2/Git-Tools-Reset-Demystified).

- **`git commit`**
	<br>Commit is like a snapshot in time. We can return back to this commit anytime in future.

	- #### Usage
	```bash
	$ git commit -m "Commit message"
	```
	- Amend previous commit
	```bash
	$ git commit --amend -m "Change previous commit message"
	$ git commit --amend --no-edit # Modify previous commit but keep the commit message same
	```
	If we have some changes that we would like to include in the last commit, we use `--amend` flag with `git commit`. This new commit contains all the newly staged changes as well as the changes that were committed in the last commit. The new commit is not same as the previous commit. Therefore, if origin/branch has the old commit and you push an "ammended commit" then your branch will diverge!

	- Commit all tracked files 
	```bash
	$ git commit -a -m "Commit message"
	```
	This command adds all the "tracked files" to the staging area and then commits the changes made in them.


- **`git diff`**
	<br> `git diff` prints nothing for Untracked files.

	- #### Usage
	```bash
	$ git diff # View all the unstaged changes made since last commit
	$ git diff --cached # View all the staged changes made since the last commit
	$ git diff --staged # Same as --cached
	$ git diff <file1> <file2> <file3> # View the changes only for the mention files
	$ git diff <commit-id> # View the changes you have in your Working tree relative to the named <commit>
	$ git diff HEAD # if we use HEAD as <commit>, we will get both staged and unstaged diff since last commit.
	$ git diff <commit-1> <commit-2> # same as <commit-1>..<commit-2>, explained below
	```
	`git diff <commit-1> <commit-2>` is used to view the changes between two arbitrary commits. In other words, it prints out the difference between the working tree you'll get after doing `git checkout <commit-2>` and the working tree you'll get after doing `git checkout <commit-1>`. So, when using it for two commits in the same branch, to include the changes done in `<commit-1>`, we run `git checkout <commit-1>^ <commit-2>`, where `<commot-1>^1` points to the parent commit of `<commit-1>`.


- **`git log`**

	- #### Usage
	```bash
	$ git log
	$ git log -p # View diffs introduced with each commit
	$ git log -n 5
	$ git log --all # show all commits except for the ones that are "lost"
	$ git log --oneline # this flag condenses each commit to a single line
	$ git log --graph # this draws an ASCII graph representing the branch structure of the commit history
	$ git log --oneline --graph --all # Used to print the whole network
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
	$ git checkout <commit-id> # Visit the commit made earlier with id=<commit-d>
	$ git checkout branch-name # Change to another branch 
	$ git checkout -b branch-name # Create a new branch and then checkout that branch
	$ git checkout -- <file1> <file2> <file3> # discard unstaged changes(changes in the Working Directory)
	$ git checkout <commit-id> <file1> # Update the index and working directory so that they match with the committed file1 at that commit. 
	$ git checkout HEAD file1 # discard all changes(staged or unstaged) made to file1
	```
	- **NOTE - The last command is not Working Directory safe. Your changes in Working Directory could be lost. This is why it can be used for discarding changes with HEAD in place of <commit-id>.**
	- #### HEAD
	HEAD is a reference to the last commit in the currently checked-out branch. Git automatically moves the HEAD pointer along when you create a new commit. You are automatically on the newest commit of the chosen branch.
	- #### Detached HEAD
	When we commit in a Detached HEAD state, the commits do not belong to any branch and are thus **lost**.
- **`git branch`**

	- #### Usage
	```bash
	$ git branch --list # display all local branches
	$ git branch # same as above
	$ git branch -r # display all remote branches
	$ git branch new-branch # create a new branch
	$ git branch -d old-branch # Delete a branch if the local old-branch isn't ahead of origin/old-branch in commits. Shortcut for --delete.
	$ git branch -D old-branch # force delete a branch anyways. Shortcut for --delete --force
	```
- **`git stash`**
	
	- #### Usage
	```bash
	$ git stash # stash away modified tracked files and staged changes so that you are free to switch branches
	$ git stash apply # Apply the stashed changes. It doesn't removes the removes the changes from the stash list
	$ git stash drop # Remove a single stash entry from the stash list
	$ git stash pop # is equivalent to `git stash apply && git stash drop`
	```

- **`git merge`**

	- #### Usage
	```bash
	$ git merge branchname -m "Commit message"
	$ git merge --no-ff -m "Commit message"
	```
	- A fast forward merge moves the branch pointer to the head of branch that is being merged instead of creating a merge commit. 
	- Let's say branch B is to be merged in branch A. A fast forward merge happens by default if the branch A's tip is the ancestor of branch B. To prevent it, we use `no-ff` flag.

- **`git remote`**
	
	- #### Usage
	```bash
	$ git remote add origin https://github.com/<username>/learning.git
	$ git remote -v #list existing remotes
	$ git remote set-url origin url.git #change existing origin
	```
	**Note -** `.git` can be omitted.


- **`git push`**
	
	- #### Usage
	```bash
	$ git push origin branch-name # push local changes to remote branch
	$ git push -f origin branch-name # force-push local changes
	```

- **git fetch**

	- #### Usage
	```bash
	$ git fetch # fetch the latest changes from the default remote upstream repository
	$ git fetch remote # fetch changes from specific remote
	$ git fetch --all # fetch changes from all remote repos
	```


- **`git pull`**

	- #### Usage
	```bash
	$ git pull origin branch-name #   Fetch branch from a remote repository and merge it to local repository.
	```

## Using `.gitignore`
Add the files and directories you'd like git to ignore. These files and directories will not be tracked by git. 
<br> Related post : https://stackoverflow.com/a/1139797/7263373 


## Useful Links
- [Git Upstream vs Downstream](https://stackoverflow.com/a/2739476/7263373)
- [HEAD^ vs HEAD~](https://stackoverflow.com/questions/2221658/whats-the-difference-between-head-and-head-in-git)
- [Understanding git reset and checkout](https://git-scm.com/book/en/v2/Git-Tools-Reset-Demystified)
- [Git Rebasing](https://git-scm.com/book/en/v2/Git-Branching-Rebasing)
