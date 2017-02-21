def sequence(n):
	if n==0:
		return "A"
	elif n==1:
		return "B"
	elif n==2:
		return "C"
	else:
		return (sequence(n-3) + sequence(n-2) + sequence(n-1))

def sequenceFor(n):

	seq=""
	newLis=["A"]
	
	lis = ["A","B","C"]
	for i in range(n):
		if i<=2:
			pass
		else:
			newLis.append(newLis[i-3]+newLis[i-2]+newLis[i-1])
	return "".join(newLis)

def sequence2(n):
	seq=""
	newLis=[]
	lis = ["A","B","C"]
	if n<=2:
		return lis[n]
	newLis.append("A")
	newLis.append("B")
	newLis.append("C")
	for i in range(3, n+1):
		newLis.append(newLis[i-3]+newLis[i-2] +newLis[i-1])
	return newLis[n]


def main():
	x = int(input())
	print(sequence(x))
	print (sequence2(x))


main()

