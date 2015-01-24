import subprocess
import os

__author__ = 'kate'
from subprocess import Popen, call

attempts = ["_1", "_2", "_3", "_4", "_5"]
size = [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]
# for j in range(len(attempts)):
#     for i in range(len(size)):
#         os.system('java Main ' + str(size[i]) + ' ' + attempts[j])
#         print(str(size[i]) + attempts[j] + ' done')

mypath = "."
from os import listdir
from os.path import isfile, join
onlyfiles = [ f for f in listdir(mypath) if isfile(join(mypath,f)) ]
print(onlyfiles)

dict = {}
def getAvgFromFile(filename):
    mfile = open (filename, 'r')
    elCount = 0
    sum = 0
    for line in mfile:
        if line != '\n':
            sum += int(line)
            elCount+=1
    print('count = ' + str(elCount))
    return sum/elCount



for file in os.listdir("."):
    if file.endswith(".txt"):
        print(file)
        rez = getAvgFromFile(file)
        dict[file] = rez
        print(rez)


dictList = {}
for el in dict:
    key = int(el[:el.find('_')])
    if key in dictList:
        dictList[key].append(dict[el])
        if (len(dictList[key]) == len(attempts)):
            dictList[key].append(sum(dictList[key])/len(attempts))
    else:
        list = []
        list.append(dict[el])
        dictList[key] = list

print(dictList)
list = sorted(dictList.keys())

print(list)
listVal  = []
i = 0
for el in dictList:
    listVal.append(dictList[list[i]][len(attempts)])
    i+=1

import numpy as np
import math
import pylab
from matplotlib import mlab


x = np.array(list)
y = np.array(listVal)

import math
import pylab
from matplotlib import mlab


pylab.plot(x, y)
pylab.show()
