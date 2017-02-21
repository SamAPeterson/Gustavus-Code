import sys
class box:
    def __init__(self, dim, num):
        self.dim = dim
        self.neighbors = []
        self.num = num

def main():
    t = True
    input = sys.stdin
    caseID=1
    numBox = int(input.readline())
    while(t):
        global boxList
        boxList =[]
        for i in range(0,numBox):
            dimen = list(map(int,((input.readline()).split())))
            boxList.append(box(dimen,i))
        global d
        d=[]
        makeGraph(boxList)
        for i in boxList:
            d.append(-1)
        for i in boxList:
            if d[i.num] == -1:
                dfs(i)
        if (max(d) == 0):
            print("Case " + str(caseID)+": "+str(max(d)+1)+" box" + "\n")
        else:
            print("Case " + str(caseID)+": "+str(max(d)+1)+" boxes" + "\n")
        caseID= caseID +1
        numBox = int(input.readline())
        if (numBox == -1):
            t=False
            break
        

def fits(box1, box2):
    box1.dim.sort()
    box2.dim.sort()
    return (box1.dim[0] < box2.dim[0] and box1.dim[1] < box2.dim[1] and box1.dim[2] < box2.dim[2])

def makeGraph(boxList):
    for i in range(0,len(boxList)):
        for j in range(0,len(boxList)):
            if fits(boxList[i],boxList[j]):
                boxList[i].neighbors.append(boxList[j])

def dfs(v):
    d[v.num] = 0
    for i in v.neighbors:
        if d[i.num] == -1:
            dfs(i)
        d[v.num]=max(d[v.num],1 + d[i.num])

main()