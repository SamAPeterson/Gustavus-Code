import sys

class Node:
	def __init__ (self,name,parent,level, pName):
		self.name=name
		self.parent=parent
		self.level=level
		self.pName = pName
		if (name=="" and parent==""):
			self.parent = self

	def __repr__(self):
		return 'Node(Name: {} Parent: {} Level: {} Path: {})'.format(self.name, self.parent.name, self.level, self.pName)



def main():
	t = True
	caseID = 1
	input = sys.stdin
	global P
	P = str(input.readline())
	while(P!="\n"):
		global cList
		global pList
		global sumList
		global nodeDict
		nodeDict = {}
		global C
		C=""
		C= str(input.readline())
		cLoca, pLoca, abso = makeTree()
		fPath = makePath(nodeDict[cLoca], nodeDict[pLoca])
		print("Case " + str(caseID) +":\n" + "   P = " + P + "" + "   C = " + C + "" + "   S = " + fPath + "\n")
		
		P = str(input.readline())
		caseID = caseID +1

		

def makeTree():
	cList = C.split("/")
	cList[len(cList)-1]=cList[len(cList)-1].rstrip()
	nodeDict[""]=Node("","",0,"")
	cListname=[""]
	levelC = 1
	
	for i in range(1,len(cList)):
		if (cList[i] == "."):
			continue
		elif(cList[i]== ".."):
			if (levelC>1):
				del cListname[-1]
				levelC=levelC -1
				continue
			else:
				continue
		else:

			cListname.append(cList[i])
			name = "".join(cListname)
			parentName = "".join(cListname[0:len(cListname)-1])
			nodeDict[name] = Node(cList[i],nodeDict[parentName],levelC, parentName)
			
			levelC = levelC +1
	cLoca = "".join(cListname)
	levelP = 1
	pList = P.split("/")
	pList[len(pList)-1]=pList[len(pList)-1].rstrip()
	sumList = cList + pList
	pListname = [""]
	abso = (pList[0]=="")
	pLoca =""
	if (pList[0]==""):
		for i in range(1,len(pList)):
			if (pList[i]=="."):
				continue
			elif(pList[i]==".."):
				if (levelP>1):
					del pListname[-1]
					levelP=levelP-1
					continue
				else:
					continue
			else:
				pListname.append(pList[i])
				name = "".join(pListname)
				parentName = "".join(pListname[0:len(pListname)-1])
				nodeDict[name]=Node(pList[i],nodeDict[parentName],levelP, parentName)
				levelP = levelP +1
	
	else:
		for i in range(len(cList), len(sumList)):
			if (sumList[i]=="."):
				continue
			elif(sumList[i]==".."):
				if (levelC>1):
					del cListname[-1]
					levelC=levelC-1
					continue
				else:
					continue
			else:
				cListname.append(sumList[i])
				name = "".join(cListname)
				parentName= "".join(cListname[0:len(cListname)-1])
				nodeDict[name]=Node(sumList[i],nodeDict[parentName],levelC, parentName)
				levelC = levelC + 1
	if (abso):
		pLoca = "".join(pListname)
	else:
		pLoca="".join(cListname)
	return (cLoca, pLoca,abso)


def makePath(s,v,):
	levelS = s.level
	levelV = v.level
	sPath=[]
	vPath=[]
	currentS = s
	currentV = v
	if (currentS == currentV):
		return "."
	while(currentS != currentV):
		if (levelS > levelV):
			levelS = levelS -1
			currentS = nodeDict[currentS.pName]
			sPath.append("..")
			continue
		elif(levelS < levelV):
			levelV = levelV -1
			vPath.append(currentV.name)
			currentV = nodeDict[currentV.pName]
			
			continue
		else:

			levelV= levelV -1
			levelS=levelS-1
			vPath.append(currentV.name)
			currentV = nodeDict[currentV.pName]
			currentS=nodeDict[currentS.pName]
			sPath.append("..")
			
	vPath.reverse()
	fPath = sPath + vPath
	sPathName = "/".join(sPath)
	vPathName = "/".join(vPath)
	fpath = "/".join(fPath)
	if (fpath[0] == "/"):
		fpath = fpath[1:]
	elif(fpath[-1]=="/"):
		fpath = fpath[:-1]
	return(fpath)



main()	




