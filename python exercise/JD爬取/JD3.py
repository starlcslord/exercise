# import requests                         评价数
# headers={"User-Agent":""}
# url="https://club.jd.com/comment/productCommentSummaries.action?referenceIds=100014348478"
# rel=requests.get(url=url,headers=headers).json()
# a=rel['CommentsCount']
# b=a[0]['CommentCountStr']
# print(b)


# from bs4 import BeautifulSoup            商品名字
# import requests
# headers={"User-Agent":""}
# url="https://item.jd.com/100014348478.html#comment"
# rel=requests.get(url=url,headers=headers)
# f=rel.text
# soup=BeautifulSoup(0.f,"lxml")
# name=soup.html.title
# d=name
# d=str(d)
# print(d[7:-100])

from bs4 import BeautifulSoup    #该页每个商品连接
import requests
import re
import csv
import time
headers={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36",
         "cookie":"_jdu=736339850; areaId=18; ipLoc-djd=18-1495-1497-0; PCSYCityID=CN_430000_430300_430321; shshshfpa=f0893359-4704-9143-6333-e67fd411df17-1620098624; shshshfpb=bKFqpAM2qd3zdv0ult21bZw%3D%3D; unpl=V2_ZzNtbUYARhF9CUcGeEldBmIFEwlKUBdCfA8SV3wRXww0URRVclRCFnUUR1BnGFkUZwMZXkdcQxRFCEdkeB5fA2AFEFlBZxBFLV0CFi9JH1c%2bbRJcRF9BEnwOT1FyHmw1ZAMiXUNnRRdyCkRcch1dNVcEIm1yU0EVdAhCZHopXTUlV05US1ZBFnZFQFZ8G14NbgcTbUNnQA%3d%3d; __jdv=76161171|baidu-search|t_262767352_baidusearch|cpc|107936878486_0_5f45900c2a02470e96ef86e26928bc79|1620098644883; jwotest_product=99; user-key=28c57889-f1b5-46bd-a5da-78096493853a; shshshfp=7aa902f90f084214988fbae9d854f202; __jda=122270672.736339850.1620098622.1620111565.1620180738.5; __jdc=122270672; 3AB9D23F7A4B3C9B=3HVQEWNYJDZEWGEM4W73H54PWKFJQSZF5TVETPTA2KLUE7SH57PRWYMEHSGJ6T6B526N7SC6FEFNIY3RIEOG6R2RKY; __jdb=122270672.8.736339850|5.1620180738; shshshsID=031eb5a2b428c1bc6d8f79459d202370_8_1620181910191"
         }
c=1
APPLE=0
while c<6:
    url="https://search.jd.com/Search?keyword=%E8%8B%B9%E6%9E%9C%E6%89%8B%E6%9C%BA&qrst=1&wq=%E8%8B%B9%E6%9E%9C%E6%89%8B%E6%9C%BA&shop=1&ev=exbrand_Apple%5E&page="+str(c)
    print(url)
    rel=requests.get(url=url,headers=headers)
    d=rel.text
    selector=BeautifulSoup(d,"html.parser")
    href1=selector.find_all(href=re.compile("//item.jd.com"))
    i=1
    h=1
    while i<239:
        try:
            s=str(href1[i])
            s=s[23:35]
            print(s)
            url1="https://item.jd.com/"+s+".html#comment"
            rel1=requests.get(url=url1,headers=headers)
            f=rel1.text
            soup=BeautifulSoup(f,"lxml")
            name=soup.html.title
            d=name
            d=str(d)
            d=d[7:-24]
            print("第"+str(h)+"个"+d)
            url2="https://club.jd.com/comment/productCommentSummaries.action?referenceIds="+s
            rel2=requests.get(url=url2,headers=headers).json()
            a=rel2['CommentsCount']
            b=a[0]['CommentCountStr']
            print(b)
            i+=3
            h+=1
            file=open("apple.csv", "a+", encoding="utf-8",newline="")
            fwriter = csv.writer(file)
            fwriter.writerow([d]+[b])
            file.close()
            # time.sleep(25)
        except:
            break
    c+=1
