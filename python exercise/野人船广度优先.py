"""
作者：Zhanyu_Guo
创建日期：2020.10.25
更新日期：2020.11.03
文件名：CrossRiverBFS.py
"""
# 用以测试运行时间
import time

"""globals"""
n = 0  # 传教士与野人各自的人数N
q = []  # 作为队列
actions = []  # 动作，即变化的方式，表示方式[M, C]（传教士、野人）
path = []  # 递归查找的单次路径
paths = []  # 存放多个路径
checkList = []  # 已经遍历过的点
graph = {}  # 图


# 判断状态是否满足条件，同时建图
def ok(state_b, state):
    # 判断是否都不小于0
    if state[0] < 0 or state[1] < 0 or state[2] < 0 or state[3] < 0:
        return False

    # 判断是否都满足不被吃的条件
    if (state[0] < state[1] and state[0] != 0) or (state[2] < state[3] and state[2] != 0):
        return False

    # 满足上述条件为有效点，加入到图中
    if tuple(state_b) in graph.keys():
        if tuple(state) not in graph[tuple(state_b)]:
            graph[tuple(state_b)].append(tuple(state))
            pass
    else:
        graph[tuple(state_b)] = [tuple(state)]

    # 已经遍历过，不需要继续遍历
    if state in checkList:
        return False

    return True


def mapping():
    # 队列非空
    while len(q) > 0:
        # 出队
        state_b = q.pop(0)
        checkList.append(state_b)

        # 到达目标状态
        if state_b[0] == 0 and state_b[1] == 0:
            continue

        # 执行动作
        state_n = [0] * 5
        for action in actions:
            state_n[0] = state_b[0] - action[0] * state_b[4]
            state_n[1] = state_b[1] - action[1] * state_b[4]
            state_n[2] = state_b[2] + action[0] * state_b[4]
            state_n[3] = state_b[3] + action[1] * state_b[4]
            state_n[4] = -state_b[4]
            temp = state_n[:]

            # 判断是否符合条件
            if ok(state_b, temp):
                # 入队
                if temp not in q:
                    q.append(temp)
                    pass
                pass
            pass
        pass
    pass


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


def main():
    global n

    # 输入
    n = int(input("输入人数N："))
    k = int(input("输入载客量K："))

    # 初始状态
    state = [n, n, 0, 0, 1]
    q.append(state)

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

    # 记录开始时间
    start = time.perf_counter()
    # 建图
    mapping()

    # 计算总时长
    total = time.perf_counter() - start
    print(total)

    # 搜索路径
    find_path(tuple(state))

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
    pass


if __name__ == '__main__':
    try:
        main()
    except Exception as e:
        print(e)
        pass