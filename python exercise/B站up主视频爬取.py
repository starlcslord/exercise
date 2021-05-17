import jieba
import requests
import wordcloud
import matplotlib.pyplot as plt
from PIL import Image
from wordcloud import WordCloud
import numpy as np
pg=0
pid=input("请输入你想要搜索的视频的pid:")
while pg<10:
    url="https://api.bilibili.com/x/v2/reply?pn="+str(pg)+"&type=1&oid="+pid+"&sort=2"
    params={'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36 Edg/89.0.774.77'}
    res=requests.get(url=url,params=params).json()
    res=res['data']['replies']
    for i in range(20):
        comment=res[i]['content']['message']
        f = open('upcomment.txt', 'a+', encoding='utf-8')
        f.writelines(comment)
        f.close()
    pg+=1
txt=open('upcomment.txt','r',encoding='utf-8').read()
words=jieba.lcut(txt)
counts={}
for word in words:
    if len(word) == 1:
        continue
    else:
        counts[word]=counts.get(word,0)+1
items=list(counts.items())

items.sort(key=lambda x:x[1],reverse=True)
for e in range(10):
    word,count=items[e]
    print("{0:<15}{1:>5}".format(word,count))
wc = WordCloud(background_color="white", max_words=50)
# generate word cloud
wc.generate_from_frequencies(counts)

    # show

wc = WordCloud(mode='RGBA', background_color=None,max_words=50).generate_from_frequencies(counts)

# 显示词云
plt.imshow(wc, interpolation='bilinear')
plt.axis("off")
plt.show()

# 保存到文件