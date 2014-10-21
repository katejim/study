__author__ = 'kate'

import numpy as np
import cv2
from copy import copy


def showImg(name, mat):
    cv2.imshow(name, mat)
    key = cv2.waitKey(0)
    if key == 27:  # wait for ESC key to exit
        cv2.destroyWindow(name)


def saveImg(name, mat):
    cv2.imwrite(name, mat)


def saveBinImg(binImg):
    saveImg("rezBin.bmp", binImg)


def saveCounturedImg(inImg, binImg):
    contours = cv2.findContours(binImg, mode=cv2.RETR_LIST, method=cv2.RETR_LIST)[0]
    for cnt in contours:
        x, y, w, h = cv2.boundingRect(cnt)
        cv2.rectangle(inImg, (x, y), (x + w, y + h), (255, 0, 0), 2)
    saveImg("rezContour2.png", inImg)
    showImg("COUNTURED", inImg)




def createBinaryImg(inImg):
    blurImg = cv2.GaussianBlur(inImg, (21, 21), 0)
    grayImg = cv2.cvtColor(blurImg, cv2.COLOR_BGR2GRAY)

    laplacian = cv2.Laplacian(grayImg, cv2.CV_32F, None, ksize=17, scale=1)
    threshed_image = cv2.threshold(laplacian, 0, 255, cv2.THRESH_BINARY)[1]
    threshed_image = cv2.convertScaleAbs(threshed_image)
    opening = cv2.morphologyEx(threshed_image, cv2.MORPH_OPEN, np.ones((5, 5), np.uint8))
    saveBinImg(opening)
    return opening


def readImg(name):
    return cv2.imread(name, 1)


inImg = readImg("text.bmp")
binImg = createBinaryImg(inImg)
saveCounturedImg(inImg, binImg)
