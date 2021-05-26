import os
libs={"https://www.bilibili.com/video/BV1Mv411V7NF","https://www.bilibili.com/video/BV1yU4y1L7W4"}
try:
    for lib in libs:
        os.system("you-get --format=flv480 "+lib)
    print("successful")
except:
    print("failed somehow")