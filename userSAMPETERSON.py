class User:
    #User:(string, string)
    def __init__ (self, name, email):
        self.name = name
        self.email = email
        self.friends = []

    #void -> string
    def getName (self):
        return self.name

    #void -> string
    def getEmail(self):
        return self.email

    #void -> list of Users
    def getFriends(self):
        return self.friends

    #string -> void
    def setName (self, newName):
        self.name = newName

    #string -> void
    def setEmail(self, newEmail):
        self.email = newEmail

    #list of users -> void
    def setFriends (self, newFriends):
        self.friends = newFriends

    #user -> void
    def addFriend (self, friend):
        self.friends.append(friend)
        friend.friends.append(self)

    #user -> Boolean
    def isFriend(self, friend):
        if friend in self.friends:
            return True
        else:
            return False

    #void -> str
    def strOfFriends (self):
        if self.friends == []:
            return "Friends: none"
        else:
            acc = "Friends: "
            for i in range (0, len(self.friends)):
                acc = acc + ((self.friends[i]).getName()) + ", "
            return acc
                            

    #void -> str
    def __str__(self):
        return (self.name + " (" + self.email + ")\n" + self.strOfFriends())
