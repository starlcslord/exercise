"""
作者：Zhanyu_Guo
创建日期：2020.10.25
更新日期：2020.11.03
文件名：CrossRiverDFS.py
"""
# 用以测试运行时间
import time

"""globals"""
n = 0  # 传教士与野人各自的人数N
path = []  # 递归查找的单次路径
paths = []  # 存放多个路径
graph = {}  # 图

# 已走过状态的列表
# 表示方式[ML, CL, MR, CR, B]（左传教士、左野人、右传教士、右野人、船）
# 船的位置（1：左岸，-1：右岸）决定了数目的增减
stateList = []

# 动作，即变化的方式，表示方式[M, C]（传教士、野人）
actions = []


# 判断状态是否满足条件，同时建图
def ok(state):
    # 判断是否都不小于0
    if state[0] < 0 or state[1] < 0 or state[2] < 0 or state[3] < 0:
        return False

    # 判断是否都满足不被吃的条件
    if (state[0] < state[1] and state[0] != 0) or (state[2] < state[3] and state[2] != 0):
        return False

    # 满足上述两个条件是有效节点，将其加入到图中
    if len(stateList) - 1:
        state_b = stateList[-2][:]
        if tuple(state_b) in graph.keys() and tuple(state) not in graph[tuple(state_b)]:
            graph[tuple(state_b)].append(tuple(state))
        else:
            graph[tuple(state_b)] = [tuple(state)]
            pass
        pass

    # 判断是否与前面状态重复
    for p in stateList[:-1]:
        if p[0] == state[0] and p[1] == state[1] and p[4] == state[4]:
            return False
        pass

    return True


def mapping(state):
    # 判断并建图
    if not ok(state):
        return

    # 到达目标状态[0, 0, n, n, -1]
    if state[0] == 0 and state[1] == 0:
        return

    # 执行动作，找到下一个有效点
    tmp = [0] * 5
    for action in actions:
        # 船的位置（1：左岸，-1：右岸）决定了数目的增减
        tmp[0] = state[0] - action[0] * state[4]
        tmp[1] = state[1] - action[1] * state[4]
        tmp[2] = state[2] + action[0] * state[4]
        tmp[3] = state[3] + action[1] * state[4]
        tmp[4] = -state[4]
        stateList.append(tmp[:])
        mapping(tmp)
        stateList.pop()
        pass

    return


# 深度搜索寻找路径
def find_path(state):
    global n

    # 走到重复的状态
    if state in path:
        path.append(state)
        return

    # 到达终点状态，记录路径
    if state == (0, 0, n, n, -1):
        path.append(state)
        paths.append(path[:])
        return

    path.append(state)

    # 逐个探索
    for i in range(len(graph[state])):
        find_path(graph[state][i])
        path.pop()
        pass

    pass


# 主函数
def main():
    global n

    # 输入
    n = int(input("输入各人数N："))
    k = int(input("输入载客量K："))

    # 初始状态
    s = [n, n, 0, 0, 1]
    stateList.append(s)

    # 生成动作
    # i：移动传教士和野人之和，从1到k
    for i in range(1, k + 1):
        # j：传教士的数目，从0到i
        for j in range(i + 1):
            # 如果满足传教士不少于野人或传教士为0，动作有效
            if (j >= i - j) or (j == 0):
                actions.append([j, i - j])
                pass
            pass
        pass
    # 生成完毕

    # 记录起始时间
    start = time.perf_counter()

    # 进入递归
    mapping(s)

    # 计算总时长
    total = time.perf_counter() - start
    print(total)

    # 搜索路径
    find_path(tuple(s))

    # 路径条数
    num = 0
    # 输出路径
    for p in paths:
        num += 1
        print("第%d条路径：" % num)
        str1 = "{:^6}{:^6}{:^6}{:^6}{:^6}"
        print(str1.format("ML", "CL", "MR", "CR", "B"))
        for i in p:
            print(str1.format(i[0], i[1], i[2], i[3], i[4]))
            pass
        pass

    # 结束
    print("总共有%d条路径" % num)


if __name__ == '__main__':
    try:
        main()
        pass
    except Exception as e:
        print(e)
        pass