import marshal, sys, time


def main(argv):
    jsonObject = convertJsonToCode(argv)
    header = genHeader()
    code = dumpCode(jsonObject)
    savePyc(header, code)


def convertJsonToCode(code):
    pass


def genHeader():
    pass


def dumpCode(object):
    pass


def savePyc(header, code):
    pass


if __name__ == "__main__":
    main(sys.argv[1])
