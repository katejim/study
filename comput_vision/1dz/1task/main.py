__author__ = 'kate'
import cv2


def showImg(name, mat):
    cv2.imshow(name, mat)
    key = cv2.waitKey(0)
    if key == 27:  # wait for ESC key to exit
        cv2.destroyWindow(name)

def saveImg(name, mat):
    cv2.imwrite(name, mat)

def saveBinImg(binImg):
    saveImg("rezBin.bmp", binImg)


def createBinaryImg(inImg):
    blurImg = cv2.GaussianBlur(inImg, (3, 3), 0)
    grayImg = cv2.cvtColor(blurImg, cv2.COLOR_BGR2GRAY, 1)

    laplacian = cv2.Laplacian(grayImg, cv2.CV_32F)
    ret, tresh = cv2.threshold(laplacian, 0, 255, cv2.THRESH_BINARY)
    return tresh

def readImg(name):
    return cv2.imread(name, 1)


inImg = readImg("text.bmp")
binImg = createBinaryImg(inImg)
saveBinImg(binImg)
showImg("RESULT", binImg)


