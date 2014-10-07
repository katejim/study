
__author__ = 'kate'
import numpy as np
import cv2


def showImg(name, mat):
    cv2.imshow(name, mat)
    key = cv2.waitKey(0)
    if key == 27:  # wait for ESC key to exit
        cv2.destroyWindow(name)


def saveImg(name, mat):
    cv2.imwrite(name, mat)


def saveCounturedImg(inImg, binImg):
    contours, hierarchy = cv2.findContours(binImg, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    for cnt in contours:
        x, y, w, h = cv2.boundingRect(cnt)
        cv2.rectangle(inImg, (x, y), (x + w, y + h), (255, 0, 0), 2)
    saveImg("rezContour2.png", inImg)
    showImg("COUNTURED", inImg)


def saveBinImg(binImg):
    saveImg("rezBin.png", binImg)


def createBinaryImg(inImg):
    blurImg = cv2.GaussianBlur(inImg, (3, 3), 0)
    grayImg = cv2.cvtColor(blurImg, cv2.COLOR_BGR2GRAY, 1)

    laplacian = cv2.Laplacian(grayImg, cv2.CV_32F)
    laplacian = cv2.convertScaleAbs(laplacian)

    ret, tresh = cv2.threshold(laplacian, 52, 255, cv2.THRESH_BINARY)

    closing = cv2.morphologyEx(tresh, cv2.MORPH_CLOSE, np.ones((3, 13), np.uint8))
    return closing

def readImg(name):
    inImg = cv2.imread(name, 1)
    return inImg


inImg = readImg("text.bmp")
binImg = createBinaryImg(inImg)
saveCounturedImg(inImg, binImg)


