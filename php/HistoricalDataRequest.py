
import argparse
import json
import ssl
import sys
import urllib2
import json
import csv

data = {
    "securities": [],
    "fields": ["PX_LAST", "OPEN", "EPS_ANNUALIZED"],
    "startDate": "20120101",
    "endDate": "20120301",
    "periodicitySelection": "DAILY"
}

def request(args):
    #print args.company_name + " US Equity"
    data["securities"].append(args.company_name + " US Equity")
    #print "data" + data["securities"][0]
    #list(data.viewvalues())
    req = urllib2.Request('https://{}/request/blp/refdata/HistoricalData'.format(args.host))
    req.add_header('Content-Type', 'application/json')

    ctx = ssl.SSLContext(ssl.PROTOCOL_SSLv23)
    ctx.load_verify_locations('bloomberg.crt')
    ctx.load_cert_chain('tartanhacks_spring_2015_019.crt', 'tartanhacks_spring_2015_019.key')

    try: 
        res = urllib2.urlopen(req, data=json.dumps(data), context=ctx)
        s = json.loads(res.read())
        json.dump(s, open('output.txt', 'w'))
        jsonToCsv(s)
    except Exception as e:
        e
        print e
        return 1
    return 0

def jsonToCsv(jsonDat):
    column = ['day', 'open', 'close']
    data = jsonDat['data']
    for companyData in data:
        seurityDat = companyData['securityData']
        outputFileName = './java/bin/' + seurityDat['security'] + '.dat'
        allData = seurityDat['fieldData']
        dayCounter = 0
        with open(outputFileName, 'w') as afile:
            writer = csv.writer(afile)
            writer.writerow(column)
            for dailyData in allData:
                writer.writerow([dayCounter, dailyData['OPEN'], dailyData['PX_LAST']])
                dayCounter += 1

                

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('host', type=str)
    parser.add_argument('company_name', type=str)
    return request(parser.parse_args())

if __name__ == "__main__":
    sys.exit(main())