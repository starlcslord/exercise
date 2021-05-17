import pandas as pd
import os
import matplotlib.pyplot as plt
import random
import csv
path = os.getcwd()+"\\tongji.csv"
f = open(path, encoding='utf-8')
data = pd.read_csv(f,sep=',',names=["A","B"])
a=data.index.stop
lable = list(range(a))
size=list(range(a))
explode=list(range(a))
colors=list(range(a))
for i in range(a):
    lable[i]=data["A"][i]
    print(lable[i])
for i in range(a):
    size[i]=data["B"][i]
    print(size[i])
sort=sorted(size)
for i in range(a):
    if size[i]==sort[a-1]:
        d=i
for i in range(a):
    if d==i:
        explode[i]=0.05
    elif d!=i:
        explode[i]=0
print(explode)
explode=tuple(explode)
for i in range(a):
    r=random.uniform(0,1)
    g = random.uniform(0, 1)
    b = random.uniform(0, 1)
    colors[i]=(r,g,b)
patches,text1,text2 = plt.pie(size,
                      explode=explode,
                      labels=lable,
                      colors=colors,
                      labeldistance = 1.2,#图例距圆心半径倍距离
                      autopct = '%3.2f%%', #数值保留固定小数位
                      shadow = False, #无阴影设置
                      startangle =90, #逆时针起始角度设置
                      pctdistance = 0.6) #数值距圆心半径倍数距离
#patches饼图的返回值，texts1饼图外label的文本，texts2饼图内部文本
# x，y轴刻度设置一致，保证饼图为圆形
plt.axis('equal')
plt.legend()
plt.show()

