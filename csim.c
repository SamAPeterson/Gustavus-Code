#include "cachelab.h"
#include <stdio.h>
#include <stdlib.h>
#include <getopt.h>
#include <unistd.h>
#include <getopt.h>
#include <strings.h>
#include <math.h>
#define length 100
//Sam Peterson


typedef struct {
	int s;
	int E;
	int b;
	int s2;
	int b2;
	int hits;
	int misses;
	int evicts;
}cacheMain;

//typedef unsigned long long int tagHolder;

typedef struct{
	char *block;
	int time;
	int valid;
	long long t1;
}lines;

typedef struct{
	lines *lines1;
}set;

typedef struct{
	set *sets1;
}cache;
	
int verb;

cache makeCache(long long setNum, long long blockNum, int lineNum){
	cache cache1;
	set set1;
	lines line1;
	cache1.sets1 = (set *) malloc(sizeof(set) * setNum);
	int setI;
	int lineI;
	for (setI = 0; setI < setNum; setI++){
		set1.lines1= (lines *) malloc(sizeof(lines) * lineNum);
		cache1.sets1[setI] = set1;
		for (lineI = 0; lineI < lineNum; lineI++){
			line1.time = 0;
			line1.valid = 0;
			line1.t1 = 0;
			set1.lines1[lineI] = line1;
}
}
	return cache1;
}

void emptyCache(cache test, long long setNum, int numLines, long long blockSize){
	int setI;
	for (setI = 0; setI < setNum; setI ++){
		set testSet = test.sets1[setI];
		if(testSet.lines1 != NULL){
			free(testSet.lines1);
		}
	}
	if (test.sets1 != NULL){
		free(test.sets1);
	}
}
long long bitPow(int e){
	long long ret = 1;
	ret = ret << e;
	return ret;
}

int findEmptyLine(set testSet, cacheMain test){
	int numLines = test.E;
	int I;
	lines line;
	for (I = 0; I < numLines; I ++){
		line = testSet.lines1[I];
		if (line.valid == 0){
			return I;
		}
	}
	return -1;
}


int findLineEvict(set testSet, cacheMain test, int *usedLines){
	int numLines = test.E;
	int maxUsed = testSet.lines1[0].time;
	int minUsed = testSet.lines1[0].time;
	int minimumUsedI = 0;
	lines line;
	int lineI;
	for(lineI = 1; lineI < numLines; lineI ++){
		line = testSet.lines1[lineI];
		if (minUsed > line.time){
			minimumUsedI = lineI;
			minUsed = line.time;
		}
		if (maxUsed < line.time){
			maxUsed = line.time;
		}
	}
	usedLines[0] = minUsed;
	usedLines[1] = maxUsed;
	return minimumUsedI;
}


cacheMain fillCache(cache testCache, cacheMain test, long long tagTest){
	int lineI;
	int full = 1;
	int lineNum = test.E;
	int pastHits = test.hits;
	int tagSize = (64 -(test.s+ test.b));
	long long inTag = tagTest >> (test.s + test.b);
	unsigned long long temporary = tagTest << (tagSize);
	unsigned long long setI = temporary >> (tagSize + test.b);
	set checkSet = testCache.sets1[setI];
	for(lineI = 0; lineI < lineNum; lineI++){
		lines testLine = checkSet.lines1[lineI];
		if (testLine.valid){
			if (testLine.t1 == inTag){
				testLine.time ++;
				test.hits ++;
				checkSet.lines1[lineI] = testLine;
			}
		}
		else if (!(testLine.valid) && (full)){
			full = 0;
		}
	}
	if (pastHits == test.hits){
		test.misses ++;
	}
	else{
		return test;
	}
	int *usedLines = (int*) malloc(sizeof(int) * 2);
	int minimumUsedI = findLineEvict(checkSet, test, usedLines);
	if (full){
		test.evicts ++;
		checkSet.lines1[minimumUsedI].t1 = inTag;
		checkSet.lines1[minimumUsedI].time = usedLines[1]+1;
	}
	else{
		int emptyI = findEmptyLine(checkSet, test);
		checkSet.lines1[emptyI].t1 = inTag;
		checkSet.lines1[emptyI].valid = 1;
		checkSet.lines1[emptyI].time = usedLines[1]+1;
	}
	free(usedLines);
	return(test);
}



void printHelp (char *argv[])
{
	printf("Usage: %s [-hv] -s <num> -E <num> -b <num> -t <file>\n", argv[0]);
	printf("Options:\n");
	printf("  -h		Print this help message.\n");
	printf("  -v		Optional verbose flag.\n");
	printf("  -s <num>	Number of set index bits.\n");
	printf("  -E <num>	Number of lines per set.\n");
	printf("  -b <num>	Number of block offset bits.\n");
	printf("  -t <file>	Trace file.\n\n");
	printf("Examples:\n");
	printf("  linux>  %s -s 4 -E 1 -b 4 -t traces/yi.trace\n", argv[0]);
	printf("  linux>  %s -v -s 8 -E 2 -b 4 -t traces/yi.trace\n", argv[0]);
	exit(0);
}

int main(int argc, char *argv[])
{
    char c;
    cache testCache;
    cacheMain testCacheMain;
    bzero(&testCacheMain, sizeof(testCacheMain));
    long long numSets;
    long long blockSize;
    FILE *readTrace;
    char *traceFile;
    char traceCommand;
  	long adrs;
    int size;
	while ((c= getopt(argc, argv, "s:E:b:t:hv")) != -1){
		switch (c){
			case 's':
				testCacheMain.s = atoi(optarg);
				break;
			case 'E':
				testCacheMain.E = atoi(optarg);
				break;
			case 'b':
				testCacheMain.b = atoi(optarg);
				break;
			case 't':
				traceFile = optarg;
				break;
			case 'v':
				verb = 1;
				break;
			case 'h':
				printHelp(argv);
				exit(0);
			default:
				printHelp(argv);
				exit(1);
}
}
	if (testCacheMain.s == 0 || testCacheMain.E == 0 || testCacheMain.b == 0 || traceFile == NULL){
		printHelp(argv);
		exit(1);
	}
	numSets = pow(2.0, (testCacheMain.s));
	blockSize = (bitPow(testCacheMain.b));
	testCacheMain.hits = 0;
	testCacheMain.misses = 0;
	testCacheMain.evicts = 0;
	testCache = makeCache(numSets, testCacheMain.E, blockSize);
	readTrace = fopen(traceFile, "r");
	if (readTrace != NULL){
		while (fscanf(readTrace, " %c %11lx,%d", &traceCommand, &adrs, &size) == 3){
			switch(traceCommand){
				case 'I':
					break;
				case 'L':
					testCacheMain = fillCache(testCache, testCacheMain, adrs);
					break;
				case 'S':
					testCacheMain = fillCache(testCache, testCacheMain, adrs);
					break;
				case 'M':
			 		testCacheMain = fillCache(testCache, testCacheMain, adrs);
					testCacheMain = fillCache(testCache, testCacheMain, adrs);
					break;
				default:
					break;

			}
		}
	}


    printSummary(testCacheMain.hits, testCacheMain.misses, testCacheMain.evicts);
    emptyCache(testCache, numSets, testCacheMain.E, blockSize);
    fclose(readTrace);
    return 0;
}	

