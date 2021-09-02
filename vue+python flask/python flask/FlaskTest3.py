from flask import Flask, request, jsonify
import musiclist2
import json

import musiclist2

app = Flask(__name__)
app.debug = True


@app.route('/add/student/', methods=['post'])
def add_stu():
    print(request.data)
    if not request.data:  # 检测是否有数据
        return ('fail')
    data = json.loads(request.get_data(as_text=True))
    print(data)
    # 获取到POST过来的数据，因为我这里传过来的数据需要转换一下编码。根据晶具体情况而定
    return jsonify({'tasks': musiclist2.tk(data['key'],data['num'])})
    # student_json = json.loads(student)
    # 把区获取到的数据转为JSON格式。
    # return jsonify(student_json)
    # 返回JSON数据。
@app.after_request
def cors(environ):
    environ.headers['Access-Control-Allow-Origin']='*'
    environ.headers['Access-Control-Allow-Method']='*'
    environ.headers['Access-Control-Allow-Headers']='x-requested-with,content-type'
    return environ

if __name__ == '__main__':
    app.run(debug=True)
