import pandas as pd
import os
import csv
paizi=input("输入品牌:")
path = os.getcwd()+"\\"+paizi+".csv"
f = open(path, encoding='utf-8')
data = pd.read_csv(f,sep=',',names=["A","B"])
# data=data.drop_duplicates("A")
y=data[data["A"].str.contains("JD.COM")]
# data=data.drop(data.index[[y]])
y=list(y.index)
for i in y:
    data=data.drop([i])
# sum=data['B'][1]
i=0
sum=0
print(data)
ls1=list(data.index)

for i in ls1:
    file = open(paizi+"1.csv", "a+", encoding="utf-8", newline="\n")
    fwriter = csv.writer(file)
    w = "万+"
    j="+"
    if (w in data["B"][i]) == True:
        num = data["B"][i][:-2]
        num = int(num) * 10000
        print(num)
    elif (j in data["B"][i])==True and (w in data["B"][i])==False:
        num=int(data["B"][i][:-1])
        print(num)
    else:
        num=int(data["B"][i])
    sum+=num
    fwriter.writerow([data["A"][i]]+[num])
    file.close()
# w="万+"
# for i in range(ls1.start,ls1.stop):
#     if (w in data["B"][i])==True:
#         sum=data["B"][i][:-2]
#         sum=int(sum)*10000
#         print(sum)
#     else:
#         sum=int(data["B"][i])
pj=sum/len(ls1)
file = open("tongji.csv", "a+", encoding="utf-8", newline="")
fwriter = csv.writer(file)
fwriter.writerow([paizi] + [pj])
file.close()