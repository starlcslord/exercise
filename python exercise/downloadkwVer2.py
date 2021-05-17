from bs4 import BeautifulSoup
import urllib.request
import base64
url_1=''
url_2=''
url_3=''

while True:
        a=input("请输入歌曲编号：")
        url_1='https://www.kuwo.cn/play_detail/'
        url_2='https://www.kuwo.cn/url?format=mp3&rid='
        url_3='&response=url&type=convert_url3&br=256kmp3&from=web&t=1607846145888&httpsStatus=1'
        a=str(a)
        e1=url_1+a
        e2=url_2+a+url_3
        response1 = urllib.request.urlopen(e2)
        music_txt2 = response1.read()
        slogo = base64.b64encode(music_txt2)
        slogo.decode('utf8')
        slogo = base64.b64decode(slogo)
        slogo.decode('utf8')
        k = slogo[40:-2]
        k = k.decode('utf-8')
        try:
                response = urllib.request.urlopen(e1)
                music_txt1 = response.read()
                html = music_txt1
                soup = BeautifulSoup(html,"lxml")
                name = soup.html.title
                name = list(name)
                name = ''.join(name)
                name = name[:-12]
                print(name)
                response1 = urllib.request.urlopen(k)
                music_mp3 = response1.read()

        except:
                print("该编号没有歌曲")
                continue
        with open(name+'.mp3','wb') as f:
                f.write(music_mp3)
                print(a)


