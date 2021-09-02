import re
import urllib.request
import requests
from bs4 import BeautifulSoup
def tk(key):
    url="http://www.kuwo.cn/api/www/search/searchMusicBykeyWord?key="+key+"&pn=1&rn=30&httpsStatus=1&reqId=201d35d1-0b05-11ec-89f6-8d0e82fb66d5"
    headers = {
        'Accept': 'application/json, text/plain, */*',
        'Accept-Encoding': 'gzip, deflate',
        'Accept-Language': 'zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6',
        'Connection': 'keep-alive',
        'Cookie': '_ga=GA1.2.2078485210.1630481895; _gid=GA1.2.1657466852.1630481895; Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1630481895,1630484728,1630485262; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1630487517; _gat=1; kw_token=ALCLPMENXS8',
        'csrf': 'ALCLPMENXS8',
        'Host': 'www.kuwo.cn',
        'Referer': 'http://www.kuwo.cn/search/list?key=%E5%91%A8%E6%9D%B0%E4%BC%A6',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36 Edg/92.0.902.84',

    }
    task=[]
    res=requests.get(url=url,headers=headers).json()
    res=res['data']['list']
    print(res)
    for i in range(30):
        musicrid=res[i]['musicrid']
        musicrid=musicrid[6:]
        url="http://www.kuwo.cn/url?format=mp3&rid="+str(musicrid)+"&response=url&type=convert_url3&br=128kmp3&from=web&t=1630486845621&httpsStatus=1&reqId=17f28561-0b03-11ec-b54c-f39f6f46c167"
        url=requests.get(url)
        url=url.text
        url=url[40:-2]
        print(url)
        artist=res[i]['artist']
        print(artist)
        name=res[i]['name']
        print(name)
        test={'url':url,'artist':artist,'name':name}
        task.append(test)
    return task
