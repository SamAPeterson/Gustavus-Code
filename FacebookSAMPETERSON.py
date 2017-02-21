from commentSAMPETERSON import *
from statusSAMPETERSON import *
from userSAMPETERSON import *

sam = User("Sam Peterson", "speter12@gac.edu")
john = User("John Beussman", "john@gac.edu")
status1 = Status("Sam Peterson", "is having fun")
sam.addFriend(john)
status1.addLike("john")
johnComment = Comment("john", "LOL")
status1.addComment(johnComment)


class Facebook:
    #Facebook: (dict of string: user, list of status, string)
    def __init__ (self):
        self.users = {}
        self.status = []
        self.inUser = ""

    #str, str -> void
    def registerUser(self, name, email):
        if name in self.users:
            return "Error: name in use."
        else:
            newUser = User(name, email)
            self.users[name] = newUser


    #str -> void
    def login(self, name):
        if self.inUser != "":
            return "Error: You must log out the other user first."
        else:
            self.inUser = name


    def getUser(self, name):
        return str(self.users[name])

    #void -> void
    def logout(self):
        self.inUser = ""


    #string -> void
    def addFriend(self, name):
        if self.inUser == "":
            return "Error: No one is logged in."
        elif name not in self.users:
            return "Error: User does not exist."
        else:
            self.users[name].addFriend(self.users[self.inUser])
 

    def getStat(self):
        return self.status

    #void -> void
    def viewProfile(self):
        if self.inUser == "":
            return "Error: no user logged in."
        else:
            print(str((self.users[self.inUser])))

    #status -> void
    def postStatus(self, newStatus):
        if self.inUser == "":
            return "Error: no user logged in."
        else:
            status = Status(self.inUser, newStatus)
            self.status.append(status)

    def getInUser(self):
        return self.users[self.inUser]

    def getPosterUser(self):
        for i in range(0, len(self.status)):
            return (self.users[self.status[i].getPoster()])

    #void -> boolean
    def checkIfFriend(self):
        for i in range(0, len(self.status)):
            if self.users[(self.inUser)].isFriend(self.getPosterUser) == True or self.inUser == (self.getPosterUser()).getName():
                print ("bam")
            else:
                print ("bum")
        
            
    #void -> void
    def viewStatus(self):
        if self.inUser == "":
            return "Error: no one is logged in."
        else:
            for i in range (0, len(self.status)):
                if ((self.users[self.inUser]).isFriend(self.users[self.status[i].getPoster()])) == True or (self.users[self.status[i].getPoster()]).getName() == self.inUser:
                    print ("(" + (str(i) + ")" + (str(self.status[i])) + "\n"))


    #int -> void
    def likeStatus (self, statusID):
        if self.inUser == "":
            return "Error: no user logged in."
        elif (self.users[((self.status[statusID]).getPoster())]).isFriend(self.users[self.inUser]) == False:
            return "Error: can not like status of someone who is not your friend."
        else:
            (self.status[statusID]).addLike(self.inUser)

    #int, string -> void
    def commentOnStatus (self, statusID, newComment):
        if self.inUser == "":
            return "Error: no user logged in."
        elif (self.users[((self.status[statusID]).getPoster())]).isFriend(self.users[self.inUser]) == False and (self.users[self.status[statusID].getPoster()]).getName() != self.inUser:
            return "Error: can not comment on content of someone who is not your friend."
        else:
            (self.status[statusID]).addComment(self.inUser + ": " + newComment)


