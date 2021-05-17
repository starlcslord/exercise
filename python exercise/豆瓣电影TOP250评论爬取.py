import jieba
from wordcloud import WordCloud
import matplotlib.pyplot as plt
excludes={"10","11","12","13","14","15","16","17","18","19","一个","没有","就是","自己","一部","还是","我们"}
txt=open('排行榜250.txt','r',encoding='utf-8').read()
words=jieba.lcut(txt)
counts={}
for word in words:
    if len(word) == 1:
            continue
    else:
        counts[word]=counts.get(word,0)+1
for word in excludes:
    del(counts[word])
items=list(counts.items())
items.sort(key=lambda x:x[1],reverse=True)
for g in range(200):
    word,count=items[g]
    print("{0:<15}{1:>5}".format(word,count))
wc = WordCloud(background_color="white", max_words=200)
# generate word cloud
wc.generate_from_frequencies(counts)

    # show
plt.imshow(wc, interpolation="bilinear")
plt.axis("off")
plt.show()