
import argparse
import json
import ssl
import sys
import urllib2
import json
import csv

Data = {
    "securities": [], #['IBM US Equity'],
    "fields": ["OPEN", "PX_LAST",  "NUM_TRADES", "BID", "ASK"],
    "startDate": "20140101",
    "endDate": "20150206",
    "periodicitySelection": "DAILY"
}
trainSize = 242 # random dummy

def request(args):
    data["securities"] = args.company_name + " US Equity"
    req = urllib2.Request('https://{}/request/blp/refdata/HistoricalData'.format(args.host))
    req.add_header('Content-Type', 'application/json')

    ctx = ssl.SSLContext(ssl.PROTOCOL_SSLv23)
    ctx.load_verify_locations('bloomberg.crt')
    ctx.load_cert_chain('tartanhacks_spring_2015_019.crt', 'tartanhacks_spring_2015_019.key')

    try: 
        res = urllib2.urlopen(req, data=json.dumps(Data), context=ctx)
        s = json.loads(res.read())
        json.dump(s, open('output.txt', 'w'))
        jsonToCsv(s)
    except Exception as e:
        e
        print e
        return 1
    return 0

def jsonToCsv(jsonDat):
    column = Data['fields']
    data = jsonDat['data']
    for companyData in data:
        seurityDat = companyData['securityData']
        #outputFileName = seurityDat['security'] + '.dat'
        outputFileName = 'train.dat'
        allData = seurityDat['fieldData']
        dayCounter = 0
        csvData = {}
       # print len(allData)
        for dailyData in allData:
            csvData[dayCounter] = []
            #print len(Data['fields'])
            for feature in Data['fields']:
                if dailyData.has_key(feature):
                    csvData[dayCounter].append(dailyData[feature]) 
                else:
                    dayCounter -= 1
                    break
            dayCounter += 1
            #print dayCounter
        trainSize = dayCounter - 31
        dayCounter = 0
        for i in range(1, len(csvData)):
            csvData[i-1][0] = csvData[i][0]

        with open(outputFileName, 'w') as afile:
            writer = csv.writer(afile)
            writer.writerow(column)
            for i in range(len(csvData)):
                if i == trainSize:
                    dayCounter = i
                    break
                #print (csvData[i])
                writer.writerow(csvData[i])

        with open("test.dat", 'w') as afile:
            writer = csv.writer(afile)
            writer.writerow(column)
            for i in range(dayCounter, len(csvData)-1):
                writer.writerow(csvData[i])
                

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('host', type=str)
    return request(parser.parse_args())

if __name__ == "__main__":
    sys.exit(main())