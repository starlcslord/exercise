from flask import Flask, request, jsonify
import musiclist1
import json

app = Flask(__name__)
app.debug = True


@app.route('/add/student/', methods=['post'])
def add_stu():
    print(request.data)
    if not request.data:  # 检测是否有数据
        return ('fail')

    key = request.data.decode("utf-8")
    # 获取到POST过来的数据，因为我这里传过来的数据需要转换一下编码。根据晶具体情况而定
    return jsonify({'tasks': musiclist1.tk(key)})
    # student_json = json.loads(student)
    # 把区获取到的数据转为JSON格式。
    # return jsonify(student_json)
    # 返回JSON数据。


if __name__ == '__main__':
    app.run(debug=True)
