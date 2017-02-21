class Comment:
    #Comment: (string, string)
    def __init__(self, name, comment):
        self.name = name
        self.content = comment

    #void -> string
    def getName(self):
        return self.name

    #void -> string
    def getContent(self):
        return self.content

    #string -> void
    def setName(self, newName):
        self.name = newName

    #string -> void
    def setContent(self, newContent):
        self.content = newContent

    #void -> string
    def __str__(self):
        return (self.name + ": " + self.content)
