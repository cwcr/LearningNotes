import itchat
import requests
import logging

itchat.auto_login(hotReload=True)
logging.basicConfig(level=logging.DEBUG)

itchat.send('Message Content', 'filehelper')

apiUrl = 'http://www.tuling123.com/openapi/api'
data = {
    'key': 'eb720a8970964f3f855d863d24406576',  # 如果这个Tuling Key不能用，那就换一个
    'info': 'hello',  # 这是我们发出去的消息
    'userid': 'wechat-robot',  # 这里你想改什么都可以
}
# 我们通过如下命令发送一个post请求


def get_response(message):
    data.setdefault("info", message)
    r = requests.post(apiUrl, data=data).json()
    itchat.send(str(r), "filehelper")


@itchat.msg_register(itchat.content.TEXT)
def tuling_reply(msg):
    # 为了保证在图灵Key出现问题的时候仍旧可以回复，这里设置一个默认回复
    defaultReply = 'I received: ' + msg['Text']
    logging.debug(defaultReply)
    # 如果图灵Key出现问题，那么reply将会是None
    reply = get_response(msg['Text'])
    # a or b的意思是，如果a有内容，那么返回a，否则返回b
    # 有内容一般就是指非空或者非None，你可以用`if a: print('True')`来测试
    return reply or defaultReply


itchat.run()