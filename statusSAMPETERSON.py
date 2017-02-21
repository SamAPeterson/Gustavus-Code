class Status:
    #Status: (string, string, string, Comment)
    def __init__(self, poster, status):
        self.poster = poster
        self.status = status
        self.likers = []
        self.comments = []

    #void -> string
    def getPoster(self):
        return self.poster

    #void -> string
    def getStatus(self):
        return self.status

    #void -> list of string
    def getLikers(self):
        return self.likers

    #void -> list of Comments
    def getComments(self):
        return self.comments

    #string -> void
    def setPoster(self, newPoster):
        self.poster = newPoster

    #string -> void
    def setStatus(self, newStatus):
        self.status = newStatus

    #list of string -> void
    def setLikers(self, newLikers):
        self.likers = newLikers

    #list of comments -> void
    def setComments(self, newComments):
        self.comments = newComments

    #string -> void
    def addLike (self, newLiker):
        self.likers.append(newLiker)

    #comment -> void
    def addComment (self, newComment):
        self.comments.append(newComment)

    #void -> str
 #  def strOfLikers(self):
#        acc = ""
#        acc2 = "likes this."
#        if len(self.likers) > 1:
#            acc = str(self.likers[i]) + ", " + acc
#        else:
#            for i in range (0, len(self.likers)):
#            acc = str(self.likers[i]) + " likes this."

    #void -> str
    def strOfLikers(self):
        acc = ""
        for i in  range(0, len(self.likers)):
            acc = (str(self.likers[i])) + " " + acc
        return acc

    #void -> str
    def strOfComments(self):
        acc=""
        for i in range (0, len(self.comments)):
            acc = str(self.comments[i]) + "\n" + acc
        return acc

    #void -> int
    def statusID (self):
        acc = 0
        
            
           

    #void -> str
    def __str__(self):
        return (self.poster + " " + self.status + "\n" +  self.strOfLikers() + "like this.\n" + self.strOfComments())
    
    
