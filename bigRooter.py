import sys
import math
def bigRooter(n,p):
	lo = 1
	hi = p
	while(lo<hi):
		pp = (lo + hi)>>1
		#print(pp)
		powF = powerF(pp,n)
		powF1 = powerF(pp+1,n)
		if (powF< p and powF1>p):
			return -1
		#print(powF)
		elif (powF == p):
			return pp
		elif(powF<p):
			lo = pp-1
			continue
		# if (powerF(pp,n) <= p):
		# 	lo = pp
		else:
			hi = pp-1
		# if (lo>hi):
		# 	return -1
		if (lo == hi):
			if (powerF(lo,n)!=p):
				return -1
			return lo
		# elif (lo>hi):
		# 	return -1
	#if (powerF(lo,n) != p):
	#	return -1	
	return lo

def bigRooter2(n,p):
	lo = 0
	hi = p+1
	while(hi-lo>1):
		pp = (lo+hi)/2
		if (powerF(pp,n)<=p):
			lo = pp
		else:
			hi = pp
	return lo

def powerF(a,n):
	x=1
	while (n>0):
		if ((n%2)!=0):
			x = a*x
		a = a*a
		n= n/2
	return x

def bigRooter3(n,p):
	lo = 1
	hi = p
	powF = 0
	while (lo<hi):
		#pp = int(math.ceil((lo+hi)//2.0))
		pp=(lo+hi)>>1
		powF = powerF(pp,n)
		if (powF == p):
			return pp
		if (powF<p):
			lo=pp+1
		else:
			hi = pp-1
	if(powerF(lo,n)!=p):
		return -1
	return lo

def main():
	input = sys.stdin
	caseID = 1
	n = int(input.readline())
	while(n!=0):
		
		
		p = int(input.readline())
		ret = str(bigRooter3(n,p))
		
		if ret == "-1":
			ret = "No solution"
		print("Case " + str(caseID) + ": " + ret + "\n")
		n= int(input.readline())
		caseID=caseID+1
		
main()