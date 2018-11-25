var websocket = null;

if ('WebSocket' in window) {
    //部署到服务器注意路径
    //ws://123.56.219.53:80/MyCourse/websocket
    //ws://localhost:8080/websocket
    websocket = new WebSocket("ws://localhost:8080/websocket");
}
else {
    alert("当前浏览器不支持websocket")
}

websocket.onerror = function () {
    //setMSGInHtml("连接时发生错误");
    //alert("网络不稳定")
};

websocket.onopen = function () {
    //setMSGInHtml("连接成功");
    alert("欢迎你 " + l_name + "!")
};
//接收到消息的回调方法
websocket.onmessage = function (event) {
    setMSGInHtml(event.data);
}


function send() {

    //传输数据到服务器
    var message = document.getElementById('send_txt').value;
    //alert(message)
    //向服务器发送消息
    //websocket.send(message);
    websocket.send(JSON.stringify({
        message: {
            content: message,
            from: l_name,
            to: "",
        },
        type: "message"
    }));
}

websocket.onclose = function () {
    // setMSGInHtml("连接已经关闭")
    //alert("已退出")
}

//连接关闭的回调方法
window.onbeforeunload = function () {
    closeWebSocket();
}

//close
function closeWebSocket() {
    websocket.close();
}

//需要定义成全局才有效
var now = -1;//左右浮动
var num;//判断左右
function setMSGInHtml(toHtml) {
    var send_btn = document.getElementById('send_btn');
    var send_txt = document.getElementById('send_txt');
    var chat_ul = document.getElementById('chat_ul');
    var chat_span = chat_ul.getElementsByTagName('span');
    var chat_img = chat_ul.getElementsByTagName('img');
    //JSON字符串转化为对象
    var message = JSON.parse(toHtml);
    //alert(message.message.from)
    if (l_name == message.message.from)
    //若果是自己则在右边
        num = 0;
    else
        num = 1;
    //http://123.56.219.53/MyCourse/static/img/img_26.jpg
    //../../static/img/img_26.jpg

    if (num == 0) {
        chat_ul.innerHTML += '<li><img src="../../static/img/img_26.jpg"><span>' + message.message.content + '</span>';
        now++;
        chat_span[now].className = 'spanright';
        chat_img[now].className = 'imgright';
    }

    else {
        chat_ul.innerHTML += '<li><img src="../../static/img/img_26.jpg"><span>' + message.message.content + '</span>';
        now++;
        chat_span[now].className = 'spanleft';
        chat_img[now].className = 'imgleft';
    }

    send_txt.value = '';
}



