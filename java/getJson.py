#coding=utf-8
import json
import urllib.request
import datetime

# 从腾讯疫情接口抓取数据
def getData():
    # 设置目标url和伪装信息头
    url = 'https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5'
    header = {'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36'}
    req = urllib.request.Request(url, headers=header)

    # 返回的信息，解码为utf-8用于后面的读取
    response = urllib.request.urlopen(url)
    html = response.read().decode('utf-8')
    res = json.loads(html)
    data = json.loads(res['data'])
    return data

# 寻找lastUpdateTime最后的数据更新时间，并且添加使用脚本抓取数据的时间
def getTime(Data):
    data = Data
    curr_time = datetime.datetime.now()
    time_str = datetime.datetime.strftime(curr_time, '%Y-%m-%d %H:%M:%S')
    Time = ['lastUpdateTime：'+str(data['lastUpdateTime'])+'\n',
                     'currTime：'+time_str]
    result = ''.join(Time)
    with open('time.txt', 'w', encoding='utf-8') as f:
        f.write(result)
    print('getTime...')

# 寻找全国疫情数据
def getCNJson(Data):
    data = Data
    CNlist = ['ChinaConfirm：'+str(data['chinaTotal']['confirm'])+'\n',
          'ChinaAddConfirm：'+str(data['chinaAdd']['confirm'])+'\n',
          'ChinaSuspect：'+str(data['chinaTotal']['suspect'])+'\n',
          'ChinaAddSuspect：'+str(data['chinaAdd']['suspect'])+'\n',
          'ChinaHeal：'+str(data['chinaTotal']['heal'])+'\n',
          'ChinaAddHeal：'+str(data['chinaAdd']['heal'])+'\n',
          'ChinaDead：'+str(data['chinaTotal']['dead'])+'\n',
          'ChinaAddDead：'+str(data['chinaAdd']['dead'])+'\n']
    result = ''.join(CNlist)
    with open('ChinaData.txt', 'w', encoding='utf-8') as f:
        f.write(result)
    print('getCNJson...')

# 寻找所有地区数据，只包括每个行政区的信息
def getLocJson(Data):
    data = Data
    result = ''
    for item in data['areaTree'][0]['children']:
        AreaList = ['AreaName：'+str(item['name'])+'\n',
                    'AreaConfirm：'+str(item['total']['confirm'])+'\n',
                    'AreaNowConfirm：'+str(item['total']['nowConfirm'])+'\n',
                    'AreaSuspect：'+str(item['total']['suspect'])+'\n',
                    'AreaHeal：'+str(item['total']['heal'])+'\n',
                    'AreaDead：'+str(item['total']['dead'])+'\nNEXT\n']
        #在每串数据的下一行用NEXT标记，表示一串数据已经读取完毕，同时将开始读取下一串数据
        result = result+ ''.join(AreaList)
    with open('LocalData.txt', 'w', encoding='utf-8') as f:
        f.write(result)
    print('getLocJson...')

# 用于测试的方法，避免经常从目标网址抓取数据，造成ip被封
def testgetJson():
    # 读取本系统盘根目录下的文件
    with open('/test.txt', 'r+', encoding='utf-8') as fw:
        result = fw.read()
        if result.startswith(u'\ufeff'):
            result = result.encode('utf8')[3:].decode('utf8')
        res = json.loads(result)
        data = json.loads(res['data'])
        return data

if __name__ == '__main__':
    #data = testgetJson()
    data = getData()
    getTime(data)
    getCNJson(data)
    getLocJson(data)





