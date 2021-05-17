import jieba
import requests
import json
import matplotlib.pyplot as plt
from PIL import Image
from wordcloud import WordCloud
import numpy as np

def pl():
    res=requests.get(url).json()
    print(type(res))
    res=res['data']
    next=res.get('next')
    print(next)
    res=res['list']
    for i in range(20):
        comment=res[i].get('content')
        f = open('Bcomment.txt', 'a+', encoding='utf-8')
        print(comment)
        f.writelines(comment)
        f.close()
    return next
d=0
while d<15:
    if d==0:
        url="https://api.bilibili.com/pgc/review/short/list?media_id=8892&ps=20&sort=0"
        pl()
        d+=1
    else:
        cursor=pl()
        url="https://api.bilibili.com/pgc/review/short/list?media_id=8892&ps=20&sort=0&cursor="+str(cursor)
        d+=1
txt=open('Bcomment.txt','r',encoding='utf-8').read()
words=jieba.lcut(txt)
counts={}
for word in words:
    if len(word) == 1:
        continue
    else:
        counts[word]=counts.get(word,0)+1
items=list(counts.items())

items.sort(key=lambda x:x[1],reverse=True)
for e in range(50):
    word,count=items[e]
    print("{0:<15}{1:>5}".format(word,count))
wc = WordCloud(background_color="white", max_words=50)
# generate word cloud
wc.generate_from_frequencies(counts)

    # show
mask = np.array(Image.open("d2.jpg"))
wc = WordCloud(mask=mask,mode='RGBA', background_color=None,max_words=500).generate_from_frequencies(counts)

# 显示词云
plt.imshow(wc, interpolation='bilinear')
plt.axis("off")
plt.show()

# 保存到文件

'''
wc.to_file(path.join(d, "alice.png"))

# show
plt.imshow(wc, interpolation='bilinear')
plt.axis("off")
plt.figure()
plt.imshow(alice_mask, cmap=plt.cm.gray, interpolation='bilinear')
plt.axis("off")
plt.show()
'''